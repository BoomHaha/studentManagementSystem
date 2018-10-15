import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewStdReg extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
    private JButton btnSave,  btnCancel,  btnClear;
    private JLabel lblName,  lblAdNo,  lblPhNo,  lblSex;
    private JLabel lblDOB,  lblAge,  lblCaste,  lblReligion,  lblHouseName;
    private JLabel lblCity,  lblDistrict,  lblState,  lblPin,  lblYear,  lblQualification;
    private JTextField txtName,  txtAdNo,  txtPhNo;
    private JTextField txtDOB,  txtAge,  txtCaste,  txtReligion,  txtHouseName;
    private JTextField txtCity,  txtDistrict,  txtState,  txtPin,  txtYear,  txtQualification;
    private JComboBox<String> cmbSex;
    
    public NewStdReg() {
        super("创建新的学生", true, true, true, true);
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
        lblQualification = new JLabel("学历");

        txtName = new JTextField();
        txtAdNo = new JTextField();
        txtPhNo = new JTextField();
        cmbSex = new JComboBox<String>();
        cmbSex.addItem("男");
        cmbSex.addItem("女");
        cmbSex.setSelectedIndex(0);
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

        btnSave = new JButton("保存");
        btnCancel = new JButton("取消");
        btnClear = new JButton("清除");
        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
        btnClear.addActionListener(this);

        panel.setLayout(null);

        lblAdNo.setBounds(100, 50, 150, 25);
        txtAdNo.setBounds(200, 50, 200, 25);
        lblName.setBounds(100, 90, 150, 25);
        txtName.setBounds(200, 90, 200, 25);
        lblPhNo.setBounds(100, 130, 150, 25);
        txtPhNo.setBounds(200, 130, 200, 25);
        lblSex.setBounds(100, 170, 150, 25);
        cmbSex.setBounds(200, 170, 200, 25);
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

        btnSave.setBounds(300, 470, 100, 25);
        btnCancel.setBounds(450, 470, 100, 25);
        btnClear.setBounds(600, 470, 100, 25);

        panel.add(lblAdNo);
        panel.add(txtAdNo);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblPhNo);
        panel.add(txtPhNo);
        panel.add(lblSex);
        panel.add(cmbSex);
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
        panel.add(btnSave);
        panel.add(btnCancel);
        panel.add(btnClear);
        add(panel, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("保存")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
                 try {
                    String str = "insert into Student(RollNo,SName,Phno,Sex," +
                            "Dob,Age,Caste,Religion,Hname,City,District,State,Pin,Year,Qualification)" +
                            " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(str);
                    ps.setString(1, txtAdNo.getText());
                    ps.setString(2, txtName.getText());
                    ps.setString(3, txtPhNo.getText());
                    ps.setString(4, cmbSex.getSelectedItem().toString());
                    ps.setString(5, txtDOB.getText());
                    ps.setString(6, txtAge.getText());
                    ps.setString(7, txtCaste.getText());
                    ps.setString(8, txtReligion.getText());
                    ps.setString(9, txtHouseName.getText());
                    ps.setString(10, txtCity.getText());
                    ps.setString(11, txtDistrict.getText());
                    ps.setString(12, txtState.getText());
                    ps.setString(13, txtPin.getText());
                    ps.setString(14, txtYear.getText());
                    ps.setString(15, txtQualification.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "学生信息更新成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                    ClearForm();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("清除")) {
            ClearForm();
        }
        if (e.getActionCommand().equalsIgnoreCase("取消")) {
            this.dispose();
        }
    }

    private void ClearForm() {
        txtAdNo.setText("");
        txtName.setText("");
        cmbSex.setSelectedIndex(0);
        txtDOB.setText("");
        txtAge.setText("");
        txtPhNo.setText("");
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
