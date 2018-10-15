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

public class EditMark extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel jlreno;
    JButton btnMark = new JButton("进行修改");
    private JComboBox<String> cmbregno = new JComboBox<String>();

    public EditMark() {
        super("编辑学生成绩", false, true, false, true);
        jlreno = new JLabel("学生号码");
        
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
        try {
            if (e.getSource() == btnMark) {
                String regno = (String) cmbregno.getSelectedItem();
                Statement st = Settings.getDBConnection().createStatement();
                int rno = Integer.parseInt(regno);
                ResultSet rs = st.executeQuery("select Qualification from Student where RollNo=" + rno);
                if (rs.next()) {
                    String qlfn = rs.getString(1);
                    if (qlfn.equalsIgnoreCase("PLUS TWO") || qlfn.equalsIgnoreCase("+2")) {
                        MarkT frm = new MarkT(regno);
                        frmAdminMDI.desktop.add(frm);
                        frm.setVisible(true);
                    } else {
                        MarkS frm = new MarkS(regno);
                        frmAdminMDI.desktop.add(frm);
                        frm.setVisible(true);
                    }
                }
                dispose();
            }
        } catch (Exception ek) {
        		ek.printStackTrace();
        }
    }
}
