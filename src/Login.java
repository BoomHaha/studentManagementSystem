import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Container c = getContentPane();
    private JButton btnLogin,btnCancel;
    private JLabel lblUName,lblPasswd;
    private JTextField txtUName;
    private JPasswordField txtPasswd;
    public static String MySQLAddress = "192.168.1.110";
    public static String MySQLUser = "root";
    public static String MYSQLPasswd = "52a001";
    private JTextField passwordField;
    private JTextField passwordField_1;
    
    public Login() {
        super("登录界面");
        this.setSize(350, 296);
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setLocation((Settings.getScreenSize().width / 2) - 175, (Settings.getScreenSize().height / 2) - 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        lblUName = new JLabel("用户名：");
        lblPasswd = new JLabel("密码：");
        txtUName = new JTextField();
        txtPasswd = new JPasswordField();
        btnLogin = new JButton("登录");
        btnCancel = new JButton("取消");
        lblUName.setBounds(50, 40, 71, 25);
        txtUName.setBounds(171, 40, 130, 25);
        lblPasswd.setBounds(50, 80, 71, 25);
        txtPasswd.setBounds(171, 80, 130, 25);
        btnLogin.setBounds(50, 205, 100, 25);
        btnCancel.setBounds(180, 205, 100, 25);
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        getContentPane().add(lblUName);
        getContentPane().add(lblPasswd);
        getContentPane().add(txtUName);
        getContentPane().add(txtPasswd);
        getContentPane().add(btnLogin);
        getContentPane().add(btnCancel);
        
        JLabel lblip = new JLabel("数据库IP地址：");
        lblip.setBounds(31, 119, 90, 25);
        getContentPane().add(lblip);
        
        JLabel label_1 = new JLabel("数据库连接密码：");
        label_1.setBounds(31, 155, 119, 25);
        getContentPane().add(label_1);
        
        passwordField = new JTextField();
        passwordField.setBounds(171, 116, 130, 25);
        getContentPane().add(passwordField);
        
        passwordField_1 = new JTextField();
        passwordField_1.setBounds(171, 155, 130, 25);
        getContentPane().add(passwordField_1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                MySQLAddress=passwordField.getText();
                MYSQLPasswd=passwordField_1.getText();
                Connection con=DriverManager.getConnection("jdbc:mysql://"+MySQLAddress+":3306/student",MySQLUser,MYSQLPasswd);
                try {
                    Statement st = con.createStatement();
					@SuppressWarnings("deprecation")
					ResultSet rs = st.executeQuery("SELECT * FROM UAC WHERE Username='" + txtUName.getText() + "' and Password='" + txtPasswd.getText() + "'");
                    if (rs.next()) {
                        if (rs.getString(3).equals("Student")) {
                            userMDI frm = new userMDI();
                            frm.setVisible(true);
                        } else {
                            new frmAdminMDI().setVisible(true);
                        }
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,"用户名或密码不正确。","错误！",JOptionPane.ERROR_MESSAGE);
                    }
                    con.close();
                } catch (Exception ex) {
                	ex.printStackTrace();
                    txtUName.setText("");
                    txtPasswd.setText("");
                }
            } catch (Exception x) {
            	x.printStackTrace();
            }
        }
        if (e.getSource() == btnCancel) {
            System.exit(0);
        }
    }
    public static void main(String args[]) {
        new Login().setVisible(true);
    }
}
