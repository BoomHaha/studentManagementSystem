import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class User extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
    private JButton btnCancel,  btnView;
    private JLabel lblName,  lblAdNo,  lblPhNo,  lblSex,  lblFatherName,  lblOccupation;
    private JLabel lblMotherName,  lblDOB,  lblAge,  lblCaste,  lblReligion,  lblHouseName;
    private JLabel lblCity,  lblDistrict,  lblState,  lblPin,  lblYear,  lblQualification;
    private JTextField txtName,  txtAdNo,  txtPhNo,  txtFatherName,  txtOccupation;
    private JTextField txtMotherName,  txtDOB,  txtAge,  txtCaste,  txtReligion,  txtHouseName;
    private JTextField txtCity,  txtDistrict,  txtState,  txtPin,  txtYear,  txtQualification;
    private JComboBox<String> cmbSex;
    private Statement st;
    
    public User() {
        super("查看学生详细信息",true,true,true,true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000, 1000);
        
        panel = new JPanel();
        lblAdNo = new JLabel("登记号码");
        lblName = new JLabel("姓名");
        lblPhNo = new JLabel("电话号码");
        lblSex = new JLabel("性别");
        lblDOB = new JLabel("出生日期");
        lblAge = new JLabel("年龄");
        lblCaste = new JLabel("民族");
        lblReligion = new JLabel("宗教信仰");
        lblHouseName = new JLabel("家庭住址");
        lblCity = new JLabel("所在城市");
        lblDistrict = new JLabel("所在区域");
        lblState = new JLabel("所在州");
        lblPin = new JLabel("身份证号");
        lblYear = new JLabel("入学年份");
        lblQualification = new JLabel("成绩");

        txtName = new JTextField();
        txtAdNo = new JTextField();
        txtPhNo = new JTextField();
        cmbSex = new JComboBox<String>();
        cmbSex.addItem("男");
        cmbSex.addItem("女");
        cmbSex.setSelectedIndex(0);
        txtFatherName = new JTextField();
        txtOccupation = new JTextField();
        txtMotherName = new JTextField();
        txtDOB = new JTextField();
        txtAge = new JTextField();
        txtCaste = new JTextField();
        txtReligion = new JTextField();
        txtHouseName = new JTextField();
        txtCity = new JTextField();
        txtDistrict = new JTextField();
        txtState = new JTextField();
        txtPin = new JTextField();
        txtQualification = new JTextField();
        txtYear = new JTextField();

        btnCancel = new JButton("取消");
        btnView = new JButton("查看");

        btnCancel.addActionListener(this);
        btnView.addActionListener(this);

        panel.setLayout(null);

        lblAdNo.setBounds(100, 50, 150, 25);
        txtAdNo.setBounds(200, 50, 200, 25);
        lblName.setBounds(100, 90, 150, 25);
        txtName.setBounds(200, 90, 200, 25);
        lblPhNo.setBounds(100, 130, 150, 25);
        txtPhNo.setBounds(200, 130, 200, 25);
        lblSex.setBounds(100, 170, 150, 25);
        cmbSex.setBounds(200, 170, 200, 25);
        lblFatherName.setBounds(100, 210, 150, 25);
        txtFatherName.setBounds(200, 210, 200, 25);
        lblOccupation.setBounds(100, 250, 100, 25);
        txtOccupation.setBounds(200, 250, 200, 25);
        lblMotherName.setBounds(100, 290, 100, 25);
        txtMotherName.setBounds(200, 290, 200, 25);
        lblDOB.setBounds(100, 330, 150, 25);
        txtDOB.setBounds(200, 330, 200, 25);
        lblCaste.setBounds(100, 370, 150, 25);
        txtCaste.setBounds(200, 370, 200, 25);

        lblAge.setBounds(500, 90, 150, 25);
        txtAge.setBounds(600, 90, 200, 25);
        lblReligion.setBounds(500, 130, 100, 25);
        txtReligion.setBounds(600, 130, 200, 25);
        lblHouseName.setBounds(500, 170, 100, 25);
        txtHouseName.setBounds(600, 170, 200, 25);
        lblCity.setBounds(500, 210, 150, 25);
        txtCity.setBounds(600, 210, 200, 25);
        lblDistrict.setBounds(500, 250, 100, 25);
        txtDistrict.setBounds(600, 250, 200, 25);
        lblState.setBounds(500, 290, 50, 25);
        txtState.setBounds(600, 290, 200, 25);
        lblPin.setBounds(500, 330, 150, 25);
        txtPin.setBounds(600, 330, 200, 25);
        lblYear.setBounds(500, 370, 100, 25);
        txtYear.setBounds(600, 370, 200, 25);
        lblQualification.setBounds(100, 420, 80, 25);
        txtQualification.setBounds(200, 420, 600, 25);

        btnCancel.setBounds(700, 470, 100, 25);
        btnView.setBounds(500, 470, 100, 25);

        panel.add(lblAdNo);
        panel.add(txtAdNo);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblPhNo);
        panel.add(txtPhNo);
        panel.add(lblSex);
        panel.add(cmbSex);
        panel.add(lblFatherName);
        panel.add(txtFatherName);
        panel.add(lblOccupation);
        panel.add(txtOccupation);
        panel.add(lblMotherName);
        panel.add(txtMotherName);
        panel.add(lblDOB);
        panel.add(txtDOB);
        panel.add(lblAge);
        panel.add(txtAge);
        panel.add(lblCaste);
        panel.add(txtCaste);
        panel.add(lblReligion);
        panel.add(txtReligion);
        panel.add(lblHouseName);
        panel.add(txtHouseName);
        panel.add(lblCity);
        panel.add(txtCity);
        panel.add(lblDistrict);
        panel.add(txtDistrict);
        panel.add(lblState);
        panel.add(txtState);
        panel.add(lblPin);
        panel.add(txtPin);
        panel.add(txtYear);
        panel.add(lblYear);
        panel.add(lblQualification);
        panel.add(txtQualification);
        panel.add(btnCancel);
        panel.add(btnView);

        add(panel, BorderLayout.CENTER);
        try {
            Class.forName("com.mysql.jdbc.Driver");                
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
            st = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("查看")) {

            try {
                ResultSet rs = st.executeQuery("SELECT * FROM Student WHERE RollNo=" + txtAdNo.getText());
                if (rs.next()) {
                    Integer.parseInt(txtAdNo.getText());
                    txtName.setText(rs.getString("SName"));
                    cmbSex.setSelectedItem(rs.getString("Sex"));
                    txtAge.setText(rs.getString("Age"));
                    txtPhNo.setText(rs.getString("Phno"));
                    txtReligion.setText(rs.getString("Religion"));
                    txtHouseName.setText(rs.getString("Hname"));
                    txtCaste.setText(rs.getString("Caste"));
                    txtCity.setText(rs.getString("City"));
                    txtState.setText(rs.getString("State"));
                    txtDOB.setText(rs.getDate("Dob").toString());
                    txtDistrict.setText(rs.getString("District"));
                    txtPin.setText(rs.getString("Pin"));
                    txtYear.setText(rs.getString("Year"));
                    txtQualification.setText(rs.getString("Qualification"));
                } else {
                    JOptionPane.showMessageDialog(null, "登记号码在数据库中未找到。", "未找到", JOptionPane.INFORMATION_MESSAGE);
                    ClearForm();
                }
            } catch (Exception x) {
            		x.printStackTrace();
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("取消")) {
            this.dispose();
        }
    }

    private void ClearForm() {
        txtAdNo.setText("");
        txtName.setText("");
        cmbSex.setSelectedIndex(0);
        txtFatherName.setText("");
        txtOccupation.setText("");
        txtMotherName.setText("");
        txtPhNo.setText("");
        txtDOB.setText("");
        txtAge.setText("");
        txtCaste.setText("");
        txtReligion.setText("");
        txtHouseName.setText("");
        txtCity.setText("");
        txtDistrict.setText("");
        txtState.setText("");
        txtPin.setText("");
        txtYear.setText("");
        txtQualification.setText("");
    }
}
