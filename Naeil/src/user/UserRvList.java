package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import admin.store_rv.RvListController;
import admin.store_rv.RvListModel;

public class UserRvList extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel containerPanel;
	private JScrollPane scrollPane;
	private Timer timer;

	private RvListController rvc;
	private ArrayList<RvListModel> rvlm;

	private static final int REFRESH_INTERVAL = 1000;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UserRvList frame = new UserRvList();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UserRvList() {
		setTitle("예약 리스트");
		setSize(857, 686);
		setLocationRelativeTo(null);

		// 레이아웃 설정
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);

		containerPanel = new JPanel(); // 레이아웃은 updatePanel 내에서 설정
		containerPanel.setBackground(Color.white);

		scrollPane = new JScrollPane(containerPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// 주기적 패널 업데이트
		timer = new Timer(REFRESH_INTERVAL, e -> updatePanel());
		timer.start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (timer != null)
					timer.stop();
			}
		});
	}

	protected void updatePanel() {
		SwingUtilities.invokeLater(() -> {
			try {
				containerPanel.removeAll();
				containerPanel.setLayout(new GridBagLayout()); // GridLayout → GridBagLayout

				rvc = new RvListController();
				rvlm = rvc.getUserRvList();

				GridBagConstraints gbc = new GridBagConstraints();
				gbc.insets = new java.awt.Insets(10, 10, 10, 10); // 셀 간 간격
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.NORTHWEST;

				int colCount = 3; // 열 개수 고정
				int row = 0, col = 0;

				for (RvListModel rv : rvlm) {
					gbc.gridx = col;
					gbc.gridy = row;
					containerPanel.add(createRvListPanel(rv.getStoreImg(), rv.getRvNo(), rv.getStoreName(),
							rv.getRvDate(), rv.getRvTime(), rv.getYeyakjaName(), rv.getInwonsu()), gbc);
					col++;
					if (col == colCount) {
						col = 0;
						row++;
					}
				}

				containerPanel.revalidate();
				containerPanel.repaint();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	private JPanel createRvListPanel(String market_img, int rv_no, String store_name, String rv_date, String rv_time,
			String yeyakja_name, int inwonsu) {

		int width = 250;
		int height = (int) (width * 1.5);

		JPanel rvListPanel = new JPanel(new BorderLayout());
		rvListPanel.setPreferredSize(new Dimension(width, height));
		rvListPanel.setBackground(Color.white);
		rvListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// 이미지 패널
		JPanel imgPanel = new JPanel(new BorderLayout());
		imgPanel.setPreferredSize(new Dimension(width, width));
		imgPanel.setBackground(Color.white);

		try {
			String imagePath = "src/resources" + market_img;
			ImageIcon icon = new ImageIcon(new File(imagePath).getAbsolutePath());
			Image img = icon.getImage().getScaledInstance(width, width, Image.SCALE_SMOOTH);
			JLabel imgLabel = new JLabel(new ImageIcon(img));
			imgLabel.setHorizontalAlignment(JLabel.CENTER);
			imgPanel.add(imgLabel, BorderLayout.CENTER);
		} catch (Exception e) {
			JLabel noImg = new JLabel("이미지 없음");
			noImg.setHorizontalAlignment(JLabel.CENTER);
			imgPanel.add(noImg, BorderLayout.CENTER);
		}

		rvListPanel.add(imgPanel, BorderLayout.CENTER);

		// 텍스트 정보 패널
		JPanel textPanel = new JPanel(new GridLayout(3, 1));
		textPanel.setPreferredSize(new Dimension(width, height - width));
		textPanel.setBackground(Color.white);

		Font font = new Font("", Font.PLAIN, 12);
		textPanel.add(createLabel("NO. " + rv_no, new Font("", Font.BOLD, 11)));
		textPanel.add(createLabel("가게이름: " + store_name, font));
		textPanel.add(
				createLabel("예약자: " + rv_date + " " + rv_time + " / " + yeyakja_name + " / " + inwonsu + "명", font));

		rvListPanel.add(textPanel, BorderLayout.SOUTH);

		// 이벤트 핸들러
		rvListPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println("패널 클릭됨: " + rv_no);
				try {
					new YeyakHwakin(rv_no, store_name, UserRvList.this).setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		return rvListPanel;
	}

	// 라벨 기본 설정 메소드
	private JLabel createLabel(String text, Font font) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		return label;
	}
}
