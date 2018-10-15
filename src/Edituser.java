import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Edituser extends JInternalFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JRadioButton rdoStudent;
    private JRadioButton rdoAdmin;
    private JLabel lblUName;
    private JComboBox<String> cmbUName;
    private JButton btnUpdate;
    private ButtonGroup group;

    public Edituser() {
        super("编辑用户类型", false, true, false, true);
        setBounds(350, 200, 300, 200);
        setLayout(null);
        rdoStudent = new JRadioButton("学生");
        rdoAdmin = new JRadioButton("管理员");
        lblUName = new JLabel("用户姓名");
        cmbUName = new JComboBox<String>();
        btnUpdate = new JButton("更新");
        group = new ButtonGroup();
        lblUName.setBounds(30, 30, 100, 30);
        cmbUName.setBounds(140, 30, 100, 25);
        rdoStudent.setBounds(30, 70, 100, 30);
        rdoAdmin.setBounds(140, 70, 150, 30);
        btnUpdate.setBounds(100, 110, 100, 25);
        add(lblUName);
        add(cmbUName);
        group.add(rdoAdmin);
        group.add(rdoStudent);
        add(rdoAdmin);
        add(rdoStudent);
        add(btnUpdate);
        btnUpdate.addActionListener(this);
        rdoStudent.setSelected(true);
        try {
            String name;
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Username from UAC");
            while (rs.next()) {
                name = rs.getString(1);
                if (!name.equalsIgnoreCase("admin")) {
                    cmbUName.addItem(name);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            this.dispose();
        }
    }
    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            Statement st = con.createStatement();
            try {
                String Type;
                if (e.getSource() == btnUpdate) {
                    if (rdoStudent.isSelected()) {
                        Type = "Student";
                    } else {
                        Type = "Admin";
                    }
                    String sql = "update UAC set Type ='" + Type + "' where Username='" + cmbUName.getSelectedItem().toString() + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "数据库更新成功。", "成功", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
    }
}
