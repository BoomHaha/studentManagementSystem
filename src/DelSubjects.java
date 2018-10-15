import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DelSubjects extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblSubcode,  lblSubName,  lblCredit,  lblMaxmark;
    private JTextField txtSubName,  txtCredit,  txtMaxmark;
    private JComboBox<String> cmbCode;
    private JButton btnDelete,  btnCancel;

    public DelSubjects() {
        super("删除学科", false, true, false, true);
        setSize(200, 150);
        setLocation(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        getContentPane().setLayout(new GridLayout(5, 2));

        lblSubcode = new JLabel("学科代码");
        lblSubName = new JLabel("学科名称");
        lblCredit = new JLabel("学科学分");
        lblMaxmark = new JLabel("最高成绩");
        cmbCode = new JComboBox<String>();
        txtSubName = new JTextField();
        txtCredit = new JTextField();
        txtMaxmark = new JTextField();
        btnDelete = new JButton("删除");
        btnCancel = new JButton("取消");

        c.add(lblSubcode);
        c.add(cmbCode);
        c.add(lblSubName);
        c.add(txtSubName);
        c.add(lblCredit);
        c.add(txtCredit);
        c.add(lblMaxmark);
        c.add(txtMaxmark);
        c.add(btnDelete);
        c.add(btnCancel);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        cmbCode.addActionListener(this);
        txtCredit.setEditable(false);
        txtMaxmark.setEditable(false);
        txtSubName.setEditable(false);
        loadSubjectCodes();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnDelete) {
            try {
                if(cmbCode.getSelectedItem()==null) return;
                Class.forName("com.mysql.jdbc.Driver");                
                Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
                String sql = "DELETE FROM Subject WHERE SubCode=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,cmbCode.getSelectedItem().toString());
                int retval = ps.executeUpdate();
                if (retval > 0) {
                    JOptionPane.showMessageDialog(null, "记录成功删除");  
                    cmbCode.removeActionListener(this);
                    loadSubjectCodes();                    
                    txtMaxmark.setText("");
                    txtSubName.setText("");
                    txtCredit.setText("");
                    cmbCode.addActionListener(this);
                    cmbCode.setSelectedIndex(0);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (ae.getSource() == btnCancel) {
            this.dispose();
        }
        if (ae.getSource() == cmbCode) {
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
                String sql = "SELECT SubName,Credit,MaxMark FROM Subject WHERE SubCode=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, cmbCode.getSelectedItem().toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    txtSubName.setText(rs.getString(1));
                    txtCredit.setText(String.valueOf(rs.getInt(2)));
                    txtMaxmark.setText(String.valueOf(rs.getString(3)));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadSubjectCodes() {
        try {
            cmbCode.removeAllItems();
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            String sql = "SELECT SubCode FROM Subject";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbCode.addItem(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
