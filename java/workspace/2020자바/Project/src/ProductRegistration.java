import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProductRegistration extends BaseFrame {

	String str[] = { "이름:", "카테고리:", "가격:", "재고:", "설명:" };

	public ProductRegistration() {
		super(350, 500, "상품등록", new BorderLayout(), 2);
		setDesign();
		setAction();
		setVisible(true);
	}

	private void setAction() {
		for (int i = 0; i < 3; i++) {
			jb[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(jb[0])) {
						JFileChooser jf = new JFileChooser();
						jf.setFileFilter(new FileNameExtensionFilter("JPG Image", "jpg"));
						if (jf.showOpenDialog(null) != jf.APPROVE_OPTION) {
							return;
						}
						jl[0].setIcon(addImage(jf.getSelectedFile().getAbsolutePath(), 300, 200));
					}
					if (e.getSource().equals(jb[1])) {
						for (int j = 0; j < 5; j++) {
							if (jtf[j].getText().equals("")) {
								eMsg("빈칸이 있습니다.");
								return;
							}
						}
						if (jl[0].getIcon() == null) {
							eMsg("사진을 선택해주세요");
							return;
						}
						try {
							stmt.execute("insert into product values(0,'" + jtf[1].getText() + "','" + jtf[0].getText()
									+ "','" + jtf[2].getText() + "','" + jtf[3].getText() + "','" + jtf[4].getText()
									+ "')");
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						Image im = ((ImageIcon) jl[0].getIcon()).getImage();
						BufferedImage buf = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
						Graphics2D g2d = buf.createGraphics();
						g2d.drawImage(im, 0, 0, null);
						try {
							ImageIO.write(buf, "jpg", new File(".//지급자료/이미지폴더/" + jtf[1].getText() + ".jpg"));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (e.getSource().equals(jb[2])) {
					}
				}
			});
		}
	}

	private void setDesign() {
		add(jp[0] = new JPanel(new BorderLayout()), BorderLayout.NORTH);
		add(jp[1] = new JPanel(new GridLayout(0, 1)), BorderLayout.CENTER);
		jp[0].setPreferredSize(new Dimension(0, 250));
		jp[0].add(jl[0] = new JLabel());
		jl[0].setBorder(new LineBorder(Color.black));
		jp[0].add(jb[0] = new JButton("사진 넣기"), BorderLayout.SOUTH);
		for (int i = 0; i < 5; i++) {
			jp[2] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			jp[2].add(jl[1] = new JLabel(str[i]));
			jl[1].setPreferredSize(new Dimension(80, 30));
			if (i == 4)
				jp[2].add(jtf[i] = new JTextField(20));
			else
				jp[2].add(jtf[i] = new JTextField(10));
			jp[1].add(jp[2]);
		}
		jp[2] = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp[2].add(jb[1] = new JButton("등록"));
		jp[2].add(jb[2] = new JButton("취소"));
		jp[1].add(jp[2]);

	}

	void openJFrame() {
		new Admin();
	}
	
	public static void main(String[] args) {
		new ProductRegistration();
	}

}
