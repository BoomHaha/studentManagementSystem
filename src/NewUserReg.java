import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NewUserReg extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JRadioButton rdoStudent;
    private JRadioButton rdoAdmin;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblCPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtCPassword;
    private JButton btnSave;
    private ButtonGroup group;

    public NewUserReg() {
        super("用户注册", false, true, false, true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(300, 175);
        this.setSize(350, 300);
        group = new ButtonGroup();
        rdoStudent = new JRadioButton("学生");
        rdoAdmin = new JRadioButton("管理员");
        lblUsername = new JLabel("用户名");
        lblPassword = new JLabel("密码");
        lblCPassword = new JLabel("确认密码");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtCPassword = new JPasswordField();
        btnSave = new JButton("保存");
        lblUsername.setBounds(30, 30, 100, 30);
        lblPassword.setBounds(30, 80, 150, 30);
        lblCPassword.setBounds(30, 130, 150, 30);
        txtUsername.setBounds(150, 30, 150, 25);
        txtPassword.setBounds(150, 80, 150, 25);
        txtCPassword.setBounds(150, 130, 150, 25);
        rdoStudent.setBounds(60, 170, 100, 30);
        rdoAdmin.setBounds(170, 170, 150, 30);
        btnSave.setBounds(120, 210, 100, 25);
        group.add(rdoAdmin);
        group.add(rdoStudent);
        add(lblUsername);
        add(lblPassword);
        add(lblCPassword);
        add(txtUsername);
        add(txtPassword);
        add(txtCPassword);
        add(rdoStudent);
        add(rdoAdmin);
        add(btnSave);
        btnSave.addActionListener(this);
        rdoStudent.setSelected(true);
    }
    public void actionPerformed(ActionEvent e) {
        String Type;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            con.createStatement();
            try {
                if (e.getSource() == btnSave) {
                    String Username = txtUsername.getText();
                    @SuppressWarnings("deprecation")
					String Password = txtPassword.getText();
                    @SuppressWarnings("deprecation")
					String CPassword = txtCPassword.getText();
                    if (!CPassword.equals(Password)) {
                        JOptionPane.showMessageDialog(null, "密码和确认密码不匹配。", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String str = "insert into UAC (Username,Password,Type) values(?,?,?)";
                    PreparedStatement ps = con.prepareStatement(str);
                    if (rdoStudent.isSelected()) {
                        Type = "Student";
                    } else {
                        Type = "Admin";
                    }
                    ps.setString(1, Username);
                    ps.setString(2, Password);
                    ps.setString(3, Type);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "数据库更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtCPassword.setText("");
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
