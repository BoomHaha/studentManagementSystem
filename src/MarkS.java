import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class MarkS extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Container c = getContentPane();
    int rno;
    JButton jbc = new JButton("关闭");
    JButton jb1 = new JButton("第一学期");
    JButton jb2 = new JButton("第二学期");
    JButton jb3 = new JButton("第三学期");
    JButton jb4 = new JButton("第四学期");
    JButton jb5 = new JButton("第五学期");
    JButton jb6 = new JButton("第六学期");
    String regn;

    public MarkS(String reg) {
        super("学生各学期成绩", false, true, false, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(150, 100, 500, 200);
        setLayout(null);
        regn = reg;

        JLabel jlr = new JLabel("正在操作的学生号码：" + regn);
        JToolBar jtb = new JToolBar();
        jtb.setBounds(0, 0, 700, 30);

        add(jtb);
        jtb.add(jb1);
        jtb.add(jb2);
        jtb.add(jb3);
        jtb.add(jb4);
        jtb.add(jb5);
        jtb.add(jb6);
        jlr.setBounds(50, 35, 500, 25);
        jbc.setBounds(150, 100, 80, 30);
        add(jlr);
        add(jbc);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        jb5.addActionListener(this);
        jb6.addActionListener(this);
        jbc.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            SSLC frm = new SSLC(regn,"第一学期成绩",1);
            frmAdminMDI.desktop.add(frm);
            frm.setVisible(true);
        }
        if (e.getSource() == jb2) {
            SSLC frm = new SSLC(regn,"第二学期成绩",2);
            frmAdminMDI.desktop.add(frm);
            frm.setVisible(true);
        }
        if (e.getSource() == jb3) {
            SSLC frm = new SSLC(regn,"第三学期成绩",3);
            frmAdminMDI.desktop.add(frm);
            frm.setVisible(true);
        }
        if (e.getSource() == jb4) {
            SSLC frm = new SSLC(regn,"第四学期成绩",4);
            frmAdminMDI.desktop.add(frm);
            frm.setVisible(true);
        }
        if (e.getSource() == jb5) {
            SSLC frm = new SSLC(regn,"第五学期成绩",5);
            frmAdminMDI.desktop.add(frm);
            frm.setVisible(true);
        }
        if (e.getSource() == jb6) {
            SSLC frm = new SSLC(regn,"第六学期成绩",6);
            frmAdminMDI.desktop.add(frm);
            frm.setVisible(true);
        }
        if (e.getSource() == jbc) {
            dispose();
        }
    }
}
