import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class MembersList extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
    private JButton btnCancel,  btnRefresh;
    private JPanel rptPanel;
    private JTabbedPane listsTabs;
    private JTextArea listPane;

    public MembersList() {
        super("用户列表", false, true, false, true);
        setLayout(new BorderLayout());
        setSize(500, 500);

        buttonPanel = new JPanel(new FlowLayout());
        btnRefresh = new JButton("刷新");
        btnCancel = new JButton("取消");

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnCancel);
        btnCancel.addActionListener(this);
        btnRefresh.addActionListener(this);

        listsTabs = new JTabbedPane();
        rptPanel = new JPanel(new GridLayout(1, 1));
        rptPanel.setBackground(Color.white);
        listsTabs.add(rptPanel);
        listPane = new JTextArea();
        listPane.setEditable(false);
        listPane.setFont(new Font("monospaced", Font.BOLD, 14));
        listPane.setForeground(Color.black);
        listPane.setLineWrap(true);
        listPane.setWrapStyleWord(true);
        rptPanel.add(listPane);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(listsTabs, BorderLayout.CENTER);

        printUsersList();
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnCancel) {
            dispose();
        }
        if (ae.getSource() == btnRefresh) {
            printUsersList();
        }
    }

    private void printUsersList() {
        listPane.setText("");
        listPane.append("\n\t\t\t用户列表");
        try {
            Statement st = Settings.getDBConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from UAC");
            listPane.append("\n\n");
            listPane.append("\t\t用户名 \t 用户类型");
            listPane.append("\n\t\t------------------------");
            while (rs.next()) {
                listPane.append("\n\t\t" + rs.getString(1) + "\t\t" + rs.getString(3));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
