import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class userMDI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar;
    private JMenu mnuView;
    private JMenuItem mnuViewStdDetails;
    private JMenuItem mnuMarkList;
    public static JDesktopPane desktop;

    public userMDI() {
        super("学生成绩管理系统");
        this.setSize(Settings.getScreenSize().width, Settings.getScreenSize().height - 30);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        desktop = new JDesktopPane();
        menubar = new JMenuBar();
        mnuView = new JMenu("查看");
        mnuViewStdDetails = new JMenuItem("学生信息");
        mnuMarkList = new JMenuItem("成绩列表");
        mnuView.add(mnuMarkList);
        mnuView.add(mnuViewStdDetails);
        menubar.add(mnuView);
        mnuMarkList.addActionListener(this);
        mnuViewStdDetails.addActionListener(this);
        this.setJMenuBar(menubar);
        this.add(desktop);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == mnuMarkList) {
                viewStdMark frm = new viewStdMark();
                desktop.add(frm);
                frm.setVisible(true);
            }
            if (e.getSource() == mnuViewStdDetails) {
                User frm = new User();
                desktop.add(frm);
                frm.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
