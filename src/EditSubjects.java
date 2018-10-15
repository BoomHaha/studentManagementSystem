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

public class EditSubjects extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblSubcode,  lblSubName,  lblCredit,  lblMaxmark,  lblType,  lblPract;
    private JTextField txtSubName,  txtCredit,  txtMaxmark;
    private JComboBox<String> cmbCode,  cmbType,  cmbPract;
    private JButton btnSave,  btnCancel;

    public EditSubjects() {
    	
        super("编辑学科", false, true, false, true);
        setSize(200, 200);
        setLocation(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        getContentPane().setLayout(new GridLayout(7, 2));
        
        lblSubcode = new JLabel("学科号码");
        lblSubName = new JLabel("学科名称");
        lblCredit = new JLabel("学科学分");
        lblMaxmark = new JLabel("最高成绩");
        lblType = new JLabel("学科类型");
        lblPract = new JLabel("是否考试课");
        cmbCode = new JComboBox<String>();
        txtSubName = new JTextField();
        txtCredit = new JTextField();
        txtMaxmark = new JTextField();
        cmbType = new JComboBox<String>();
        cmbType.addItem("理科");
        cmbType.addItem("文科");
        cmbType.setSelectedIndex(0);
        cmbPract = new JComboBox<String>();
        cmbPract.addItem("是");
        cmbPract.addItem("否");
        cmbPract.setSelectedIndex(0);
        btnSave = new JButton("更新");
        btnCancel = new JButton("取消");

        c.add(lblSubcode);
        c.add(cmbCode);
        c.add(lblSubName);
        c.add(txtSubName);
        c.add(lblCredit);
        c.add(txtCredit);
        c.add(lblMaxmark);
        c.add(txtMaxmark);
        c.add(lblType);
        c.add(cmbType);
        c.add(lblPract);
        c.add(cmbPract);
        c.add(btnSave);
        c.add(btnCancel);
        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
        cmbCode.addActionListener(this);
        loadSubjectCodes();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSave) {
            try {
                if (cmbCode.getSelectedItem() == null) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
                String sql = "UPDATE Subject SET SubName=?,Credit=?,MaxMark=?,Type=?,Pract=? WHERE SubCode=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtSubName.getText());
                ps.setInt(2, Integer.parseInt(txtCredit.getText()));
                ps.setInt(3, Integer.parseInt(txtMaxmark.getText()));
                ps.setString(4, cmbType.getSelectedItem().toString());
                ps.setString(5, cmbPract.getSelectedItem().toString());
                ps.setString(6, cmbCode.getSelectedItem().toString());
                int retval = ps.executeUpdate();
                if (retval > 0) {
                    JOptionPane.showMessageDialog(null, "记录更新成功");
                    cmbCode.removeActionListener(this);
                    loadSubjectCodes();
                    txtMaxmark.setText("");
                    txtSubName.setText("");
                    txtCredit.setText("");
                    cmbType.setSelectedIndex(0);
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
                String sql = "SELECT SubName,Credit,MaxMark,Type,Pract FROM Subject WHERE SubCode=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, cmbCode.getSelectedItem().toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    txtSubName.setText(rs.getString(1));
                    txtCredit.setText(String.valueOf(rs.getInt(2)));
                    txtMaxmark.setText(String.valueOf(rs.getString(3)));
                    cmbType.setSelectedItem(rs.getString(4));;
                    cmbPract.setSelectedItem(rs.getString(5));
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
