import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddSubjects extends JInternalFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblSubcode,lblSubName,lblCredit,lblMaxmark,lblType,lblPract;
    private JTextField txtSubcode,txtSubName,txtCredit,txtMaxmark;
    private JComboBox<String> cmbType,cmbPract;
    private JButton btnSave,btnCancel;
    public AddSubjects() {
        super("添加新学科",false,true,false,true);
        setSize(200,200);
        setLocation(400,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c=this.getContentPane();
        getContentPane().setLayout(new GridLayout(7,2));
        lblSubcode=new JLabel("学科代码");
        lblSubName=new JLabel("学科名称");
        lblCredit=new JLabel("学科学分");
        lblMaxmark=new JLabel("最高成绩");
        lblType=new JLabel("学科类型");
        lblPract=new JLabel("是否考试课");
        txtSubcode=new JTextField();
        txtSubName=new JTextField();
        txtCredit=new JTextField();
        txtMaxmark=new JTextField();
        cmbType=new JComboBox<String>();
        cmbPract=new JComboBox<String>();
        cmbType.addItem("理科");
        cmbType.addItem("文科");
        cmbType.setSelectedIndex(0);
        cmbPract.addItem("是");
        cmbPract.addItem("否");
        cmbPract.setSelectedIndex(0);
        btnSave=new JButton("保存");
        btnCancel=new JButton("取消");

        c.add(lblSubcode);
        c.add(txtSubcode);
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
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnSave){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.MySQLAddress+":3306/student",Login.MySQLUser,Login.MYSQLPasswd);
                String sql="INSERT INTO Subject VALUES (?,?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, txtSubcode.getText());
                ps.setString(2, txtSubName.getText());
                ps.setInt(3, Integer.parseInt(txtCredit.getText()));
                ps.setInt(4, Integer.parseInt(txtMaxmark.getText()));
                ps.setString(5,cmbType.getSelectedItem().toString());
                ps.setString(6,cmbPract.getSelectedItem().toString());
                int retval=ps.executeUpdate();
                if (retval>0) {
                    JOptionPane.showMessageDialog(null, "成功加入数据库中");
                    txtCredit.setText("");
                    txtMaxmark.setText("");
                    txtSubName.setText("");
                    txtSubcode.setText("");
                    cmbType.setSelectedIndex(0);
                    cmbPract.setSelectedIndex(0);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }else if(ae.getSource()==btnCancel){
            this.dispose();
        }
    }
}
