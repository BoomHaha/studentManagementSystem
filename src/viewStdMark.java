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

public class viewStdMark extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel jlreno;
    JButton btnMark = new JButton("成绩");
    private JComboBox<String> cmbregno = new JComboBox<String>();

    public viewStdMark() {

        super("查看成绩", false, true, false, true);
        jlreno = new JLabel("注册学号");

        setBounds(350, 200, 300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        jlreno.setBounds(30, 30, 100, 30);
        cmbregno.setBounds(120, 30, 130, 25);
        btnMark.setBounds(100, 100, 100, 30);
        add(jlreno);
        add(cmbregno);
        add(btnMark);

        btnMark.addActionListener(this);
        try {
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select RollNo from Student");
            cmbregno.removeAllItems();
            while (rs.next()) {
                cmbregno.addItem(rs.getString(1));
            }
        } catch (Exception ee) {
        	ee.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMark) {
            String regno = (String) cmbregno.getSelectedItem();
            String qlfn;
            try {
                Statement st = Settings.getDBConnection().createStatement();
                int rno = Integer.parseInt(regno);
                ResultSet rs = st.executeQuery("select Qualification from Student where Rollno= " + rno);
                if (rs.next()) {
                    qlfn = rs.getString(1);
                    ViewMarkReport frm = new ViewMarkReport(regno, qlfn);
                    userMDI.desktop.add(frm);
                    frm.setVisible(true);
                    dispose();
                }
                MarkS frm = new MarkS(regno);
                frm.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
