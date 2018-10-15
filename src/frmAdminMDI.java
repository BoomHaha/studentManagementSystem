import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class frmAdminMDI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar;
    private JMenu mnuAccounts;
    private JMenu mnuStdDetails;
    private JMenu mnuView;
    private JMenu mnuOptions;
    private JMenuItem mnuNewUser;
    private JMenuItem mnuEditUser;
    private JMenuItem mnuDeleteUser;
    private JMenuItem mnuNewReg;
    private JMenuItem mnuEditReg;
    private JMenuItem mnuDelDetails;
    private JMenuItem mnuEditMark;
    private JMenuItem mnuViewStdDetails;
    private JMenuItem mnuMemList;
    private JMenuItem mnuMarkList;
    private JMenuItem mnuAddSubs,mnuEditSubs,mnuDelSubs;
    private JMenuItem mnuSubAlloc;
    public static JDesktopPane desktop;

    public frmAdminMDI() {
        super("学生成绩管理系统");
        this.setSize(Settings.getScreenSize().width, Settings.getScreenSize().height - 30);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        desktop = new JDesktopPane();
        menubar = new JMenuBar();
        mnuAccounts = new JMenu("用户相关");
        mnuStdDetails = new JMenu("学生相关");
        mnuView = new JMenu("浏览");
        mnuOptions=new JMenu("学科相关");
        mnuNewUser = new JMenuItem("添加新用户");
        mnuEditUser = new JMenuItem("编辑用户类型");
        mnuDeleteUser = new JMenuItem("删除用户");
        mnuNewReg = new JMenuItem("注册新学生");
        mnuEditReg = new JMenuItem("编辑学生信息");
        mnuDelDetails = new JMenuItem("删除学生");
        mnuEditMark = new JMenuItem("编辑学生成绩");
        mnuViewStdDetails = new JMenuItem("学生详细信息");
        mnuMemList = new JMenuItem("查看用户");
        mnuMarkList = new JMenuItem("查看学生成绩");
        mnuAddSubs=new JMenuItem("添加学科");
        mnuEditSubs=new JMenuItem("编辑学科");
        mnuDelSubs=new JMenuItem("删除学科");
        mnuSubAlloc=new JMenuItem("学科分配");

        mnuAccounts.add(mnuNewUser);
        mnuNewUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_MASK));
        mnuAccounts.add(mnuEditUser);
        mnuEditUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.ALT_MASK));
        mnuAccounts.addSeparator();
        mnuAccounts.add(mnuDeleteUser);
        mnuDeleteUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        mnuStdDetails.add(mnuNewReg);
        mnuNewReg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        mnuStdDetails.add(mnuEditReg);
        mnuEditReg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        mnuStdDetails.add(mnuDelDetails);
        mnuDelDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        mnuStdDetails.addSeparator();
        mnuStdDetails.add(mnuEditMark);
        mnuEditMark.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_MASK));
        mnuView.add(mnuMarkList);
        mnuMarkList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        mnuView.add(mnuMemList);
        mnuMemList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        mnuView.add(mnuViewStdDetails);
        mnuViewStdDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
        mnuOptions.add(mnuAddSubs);
        mnuOptions.add(mnuEditSubs);
        mnuOptions.add(mnuDelSubs);
        mnuOptions.addSeparator();
        mnuOptions.add(mnuSubAlloc);
        menubar.add(mnuAccounts);
        menubar.add(mnuStdDetails);
        menubar.add(mnuView);
        menubar.add(mnuOptions);
        mnuNewUser.addActionListener(this);
        mnuEditUser.addActionListener(this);
        mnuDeleteUser.addActionListener(this);
        mnuNewReg.addActionListener(this);
        mnuEditReg.addActionListener(this);
        mnuDelDetails.addActionListener(this);
        mnuEditMark.addActionListener(this);
        mnuMarkList.addActionListener(this);
        mnuMemList.addActionListener(this);
        mnuViewStdDetails.addActionListener(this);
        mnuAddSubs.addActionListener(this);
        mnuEditSubs.addActionListener(this);
        mnuDelSubs.addActionListener(this);
        mnuSubAlloc.addActionListener(this);
        this.setJMenuBar(menubar);
        this.add(desktop);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == mnuNewUser) {
                NewUserReg frm = new NewUserReg();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuEditUser) {
                Edituser frm = new Edituser();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuDeleteUser) {
                Deleteuser frm = new Deleteuser();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuNewReg) {
                NewStdReg frm = new NewStdReg();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuEditReg) {
                EditStdReg frm = new EditStdReg();
                desktop.add(frm);
                frm.setVisible(true);

            }
            if (e.getSource() == mnuDelDetails) {
                Deletedetails frm = new Deletedetails();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuMarkList) {
                viewMark frm = new viewMark();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuEditMark) {
                EditMark frm = new EditMark();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuMemList) {
                MembersList frm = new MembersList();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuViewStdDetails) {
                User frm = new User();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if(e.getSource()==mnuAddSubs){
                AddSubjects frm=new AddSubjects();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if(e.getSource()==mnuEditSubs){
                EditSubjects frm=new EditSubjects();
                desktop.add(frm);
                frm.setVisible(true);
            }
             if(e.getSource()==mnuDelSubs){
                DelSubjects frm=new DelSubjects();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if(e.getSource()==mnuSubAlloc){
                SubjectAllocation frm=new SubjectAllocation();
                desktop.add(frm);
                frm.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
