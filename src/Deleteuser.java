import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Deleteuser extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblUName = new JLabel("用户名");
    private JComboBox<String> cmbUName = new JComboBox<String>();
    private JButton btnDelete = new JButton("删除");

    public Deleteuser() {
        super("删除用户", false, true, false, true);
        setBounds(350, 200, 300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        lblUName.setBounds(30, 30, 100, 30);
        cmbUName.setBounds(120, 30, 130, 25);
        btnDelete.setBounds(100, 90, 100, 25);
        add(lblUName);
        add(cmbUName);
        add(btnDelete);
        btnDelete.addActionListener(this);
        LoadUserames();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            Statement st = con.createStatement();
            try {
                if (e.getSource() == btnDelete) {
                    String sql = "delete from UAC where Username='" + cmbUName.getSelectedItem().toString() + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "数据库更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    LoadUserames();
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
    }
    private void LoadUserames() {
        String name;
        try {
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Username from UAC");
            cmbUName.removeAllItems();
            while (rs.next()) {
                name=rs.getString(1);
                if (!(name.equalsIgnoreCase("admin"))) {
                    cmbUName.addItem(name);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            this.dispose();
        }
    }
}
