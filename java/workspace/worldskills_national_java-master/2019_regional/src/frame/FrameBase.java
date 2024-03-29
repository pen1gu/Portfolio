package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

public class FrameBase extends JFrame {

	public static Connection con;
	public static Statement stmt;

	public static int userNo;
	public static String userName;
	public static String userGrade;
	public static int userPoint;

	public static DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	public static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

	static {
		userNo = 1;
		userName = "이기민";
		userGrade = "일반";
		userPoint = 14000;

		rightRenderer.setHorizontalAlignment(4);
		centerRenderer.setHorizontalAlignment(0);

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/coffee?serverTimezone=UTC", "user", "1234");
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrameBase(int width, int height, String title) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(title);
		setLocationRelativeTo(null);
	}

	public static void eMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "메시지", JOptionPane.ERROR_MESSAGE);
	}

	public static void iMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "메시지", JOptionPane.INFORMATION_MESSAGE);
	}

	public static JLabel createLabel(JLabel lb, Font font) {
		lb.setFont(font);

		return lb;
	}

	public static <T extends JComponent> T createComp(T comp, int x, int y, int w, int h) {
		comp.setBounds(x, y, w, h);

		return comp;
	}

	public static <T extends JComponent> T createComp(T comp, int w, int h) {
		comp.setPreferredSize(new Dimension(w, h));

		return comp;
	}

	public static JButton createButton(String text, ActionListener action) {
		var btn = new JButton(text);

		btn.addActionListener(action);

		return btn;
	}

	public static JButton createButtonWithoutMargin(String text, ActionListener action) {
		var btn = new JButton(text);

		btn.setMargin(new Insets(0, 0, 0, 0));
		btn.addActionListener(action);

		return btn;
	}

	public static ImageIcon getImage(String path, int w, int h) {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(path).getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

	public void openFrame(JFrame frame) {
		dispose();
		frame.setVisible(true);
	}
}
