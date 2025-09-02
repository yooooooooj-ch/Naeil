package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import admin.store_rv.RvListConn;
import main.Log;
import main.StaticInfo;

public class MyPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;

	private File selectedImageFile = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MyPage frame = new MyPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyPage() {
		String img_path = "/resources/mypage";

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 150, 608, 659);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.setLayout(null);
		try {
			conn = new RvListConn().getConnection();
			String sql = "SELECT users_name FROM users WHERE users_signum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, StaticInfo.statUserNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				StaticInfo.statUserName = rs.getString("users_name");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
		}

		Font bFont = new Font("맑은 고딕", Font.BOLD, 15);
		contentPane.setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(171, 47, 346, 100);
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JLabel lblName = new JLabel();
		lblName.setBounds(22, 10, 126, 38);
		panel_1.add(lblName);
		lblName.setFont(bFont);
		lblName.setText(StaticInfo.statUserName + " 님");

		JLabel lblImg = new JLabel();
		lblImg.setBounds(59, 47, 100, 100);
		lblImg.setBackground(Color.WHITE);
		contentPane.add(lblImg);
		lblImg.setLayout(new BorderLayout());
		
		ImageIcon icon = null;
		String newFileName = "";

		try {
			conn = new RvListConn().getConnection();

			String sql = "select users_img from users where users_signum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, StaticInfo.statUserNo);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				newFileName = rs.getString("users_img");
				if (newFileName == null) {
					newFileName = "/resources/mypage/profile.png";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			File imageFile = new File("src/resources" + newFileName);
			if (imageFile.exists()) {
				ImageIcon fileIcon = new ImageIcon(imageFile.getAbsolutePath());
				System.out.println(imageFile.getAbsolutePath());
				Image image = fileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(image);

				JLabel label = new JLabel(scaledIcon);
				label.setHorizontalAlignment(JLabel.CENTER);
				lblImg.setLayout(new BorderLayout());
				lblImg.add(label, BorderLayout.CENTER);
			} else {
				// 기본 이미지로 fallback
				ImageIcon defaultIcon = new ImageIcon(getClass().getResource(img_path + "/profile.png"));
				Image image = defaultIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(image);

				JLabel label = new JLabel(scaledIcon);
				label.setHorizontalAlignment(JLabel.CENTER);
				lblImg.setLayout(new BorderLayout());
				lblImg.add(label, BorderLayout.CENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 버튼 클릭 시 새 이미지로 교체
		JButton btnProfile = new JButton("프로필 설정");
		btnProfile.setBackground(Color.WHITE);
		btnProfile.setBounds(22, 67, 100, 23);
		panel_1.add(btnProfile);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(59, 204, 458, 379);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnResetPw = new JButton("비밀번호 변경");
		btnResetPw.setBounds(32, 84, 109, 37);
		btnResetPw.setBackground(Color.WHITE);
		panel.add(btnResetPw);

		JButton btnChange_PhNum = new JButton("전화번호 변경");
		btnChange_PhNum.setBounds(175, 84, 109, 37);
		panel.add(btnChange_PhNum);
		btnChange_PhNum.setBackground(new Color(255, 255, 255));
		btnChange_PhNum.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnChangeEmail = new JButton("이메일 변경");
		btnChangeEmail.setBounds(320, 84, 109, 37);
		panel.add(btnChangeEmail);
		btnChangeEmail.setBackground(Color.WHITE);

		JLabel lblrefresh_icon = new JLabel("");
		lblrefresh_icon
				.setIcon(new ImageIcon(MyPage.class.getResource(img_path + "/Password_refresh_icon.png")));
		lblrefresh_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lblrefresh_icon.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblrefresh_icon.setBounds(53, 10, 64, 64);
		panel.add(lblrefresh_icon);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblPassword.setBounds(39, 28, 90, 21);
		panel.add(lblPassword);

		JLabel lblphone_icon = new JLabel("");
		lblphone_icon
				.setIcon(new ImageIcon(MyPage.class.getResource(img_path + "/Phone_Number_icon.png")));
		lblphone_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone_icon.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblphone_icon.setBounds(205, 10, 64, 64);
		panel.add(lblphone_icon);

		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPhone.setBounds(172, 29, 130, 21);
		panel.add(lblPhone);

		JLabel lblrefresh_email_icon = new JLabel("");
		lblrefresh_email_icon
				.setIcon(new ImageIcon(MyPage.class.getResource(img_path + "/email_icon.png")));
		lblrefresh_email_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lblrefresh_email_icon.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblrefresh_email_icon.setBounds(347, 10, 64, 64);
		panel.add(lblrefresh_email_icon);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblEmail.setBounds(356, 29, 55, 21);
		panel.add(lblEmail);

		JButton btnTal = new JButton("회원탈퇴");
		btnTal.setBounds(32, 333, 109, 37);
		panel.add(btnTal);
		btnTal.setBackground(new Color(255, 255, 255));

		JButton btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(175, 333, 109, 37);
		panel.add(btnLogout);
		btnLogout.setBackground(new Color(255, 255, 255));

		JButton btnOK = new JButton("확인");
		btnOK.setBounds(320, 333, 97, 37);
		panel.add(btnOK);
		btnOK.setBackground(Color.WHITE);
		
		JLabel lbl_nosmile_icon = new JLabel("");
		lbl_nosmile_icon.setIcon(new ImageIcon(MyPage.class.getResource(img_path + "/nosmile_icon.png")));
		lbl_nosmile_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nosmile_icon.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbl_nosmile_icon.setBounds(53, 259, 64, 64);
		panel.add(lbl_nosmile_icon);
		
		JLabel lbl_logout_icon = new JLabel("");
		lbl_logout_icon.setIcon(new ImageIcon(MyPage.class.getResource(img_path + "/logout_icon.png")));
		lbl_logout_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_logout_icon.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbl_logout_icon.setBounds(194, 259, 64, 64);
		panel.add(lbl_logout_icon);
		
		JLabel lblcheck_icon = new JLabel("");
		lblcheck_icon.setIcon(
				new ImageIcon(MyPage.class.getResource(img_path + "/check_icon.png")));
		lblcheck_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lblcheck_icon.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblcheck_icon.setBounds(336, 259, 64, 64);
		panel.add(lblcheck_icon);
		

		// 이벤트 핸들러
		// 비밀번호 변경
		btnResetPw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				System.out.println(StaticInfo.statUserId);

				try {
					conn = new RvListConn().getConnection();

					String userId = null;
					String userEmail = null;
					String oldHashedPW = null;

					// 1️⃣ 아이디 확인 루프
					while (true) {
						userId = JOptionPane.showInputDialog("아이디를 입력해 주세요.");
						if (userId == null)
							return;

						if (!StaticInfo.statUserId.equals(userId)) {
							JOptionPane.showMessageDialog(null, "아이디가 일치하지 않습니다.");
							return;
						}
						String idCheckSql = "SELECT * FROM users WHERE users_id = ?";
						try (PreparedStatement idCheckStmt = conn.prepareStatement(idCheckSql)) {
							idCheckStmt.setString(1, userId.trim());
							ResultSet idRs = idCheckStmt.executeQuery();

							if (idRs.next()) {
								break;
							} else {
								JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다. 다시 입력해 주세요.");
							}
						}
					}

					// 2️⃣ 이메일 확인 및 기존 해시 비밀번호 가져오기
					while (true) {
						userEmail = JOptionPane.showInputDialog("이메일을 입력해 주세요.");
						if (userEmail == null)
							return;

						// 이메일: 기본 이메일 형식 체크
						if (!userEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
							JOptionPane.showMessageDialog(null, "올바른 이메일 형식으로 입력해주세요. 😢", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							return;
						}

						String emailCheckSql = "SELECT * FROM users WHERE users_id = ? AND users_email = ? ";
						try (PreparedStatement emailCheckStmt = conn.prepareStatement(emailCheckSql)) {
							emailCheckStmt.setString(1, userId.trim());
							emailCheckStmt.setString(2, userEmail.trim());
							ResultSet emailRs = emailCheckStmt.executeQuery();
							System.out.println("dr");

							if (emailRs.next()) {
								System.out.println("drdfdf");
								oldHashedPW = emailRs.getString("users_password");
								System.out.println("draasdasdf");
								break;
							} else {
								JOptionPane.showMessageDialog(null, "이메일이 일치하지 않습니다. 다시 입력해 주세요.");
							}
						}
					}

					// 3️⃣ 비밀번호 입력 및 확인 루프
					String newPW = "", confirmPW = "", hashedPW = "";

					while (true) {
						// 새 비밀번호 입력
						JPasswordField passwordField1 = new JPasswordField();
						int option1 = JOptionPane.showConfirmDialog(null, passwordField1, "새 비밀번호를 입력해 주세요.",
								JOptionPane.OK_CANCEL_OPTION);
						if (option1 != JOptionPane.OK_OPTION)
							return;

						newPW = new String(passwordField1.getPassword()).trim();
						if (newPW.isEmpty()) {
							JOptionPane.showMessageDialog(null, "새 비밀번호를 입력해 주세요.");
							continue;
						}

						// 비밀번호: 영문 + 숫자 포함, 6자 이상
						if (!newPW.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
							JOptionPane.showMessageDialog(null, "비밀번호는 영문과 숫자를 포함하여 6자 이상이어야 합니다. 😢", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							return;
						}

						// 암호화
						MessageDigest md = MessageDigest.getInstance("SHA-256");
						byte[] hashBytes = md.digest(newPW.getBytes("UTF-8"));
						StringBuilder sb = new StringBuilder();
						for (byte b : hashBytes) {
							sb.append(String.format("%02x", b));
						}
						String newHashedPW = sb.toString();

						// 기존 비밀번호와 같은지 확인
						if (newHashedPW.equals(oldHashedPW)) {
							JOptionPane.showMessageDialog(null, "기존 비밀번호와 동일합니다. 다른 비밀번호를 입력해 주세요.");
							continue;
						}

						// 비밀번호 확인 입력
						JPasswordField passwordField2 = new JPasswordField();
						int option2 = JOptionPane.showConfirmDialog(null, passwordField2, "새 비밀번호를 한 번 더 입력해 주세요.",
								JOptionPane.OK_CANCEL_OPTION);
						if (option2 != JOptionPane.OK_OPTION)
							return;

						confirmPW = new String(passwordField2.getPassword()).trim();
						if (confirmPW.isEmpty()) {
							JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해 주세요.");
							continue;
						}

						if (!newPW.equals(confirmPW)) {
							JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
							continue;
						}

						hashedPW = newHashedPW;
						break;
					}

					// 4️⃣ 비밀번호 업데이트
					String updateSql = "UPDATE users SET users_password = ? WHERE users_id = ?";
					try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
						updateStmt.setString(1, hashedPW);
						updateStmt.setString(2, userId);

						int result = updateStmt.executeUpdate();
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "비밀번호가 성공적으로 변경되었습니다.");
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호 변경에 실패했습니다.");
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
				}
			}
		});

		// 프로필 사진 변경
		btnProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(getParent());

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedImageFile = jfc.getSelectedFile(); // 선택된 이미지 파일

					try {
						// 기존 이미지 제거
						lblImg.removeAll();

						// 새 이미지 로드 및 적용
						ImageIcon newIcon = new ImageIcon(selectedImageFile.getAbsolutePath());
						Image img = newIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon scaledIcon = new ImageIcon(img);

						JLabel newImageLabel = new JLabel(scaledIcon);
						newImageLabel.setHorizontalAlignment(JLabel.CENTER);
						lblImg.add(newImageLabel, BorderLayout.CENTER);

						// UI 갱신
						lblImg.revalidate();
						lblImg.repaint();

						String updateSql = "UPDATE users SET users_img = ? WHERE users_signum = ?";
						PreparedStatement pstmt = conn.prepareStatement(updateSql);
						pstmt.setString(1, "/" + selectedImageFile.getName());
						pstmt.setInt(2, StaticInfo.statUserNo);

						int result = pstmt.executeUpdate();
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "프로필 사진이 성공적으로 변경되었습니다.");
						} else {
							JOptionPane.showMessageDialog(null, "프로필 사진 변경에 실패했습니다.");
						}

						pstmt.close();
						conn.close();

						String fileName = "/" + selectedImageFile.getName();
						String destPath = "src/resources" + fileName;
						File destFile = new File(destPath);
						// 파일 복사
						try (FileInputStream fis = new FileInputStream(selectedImageFile);
								FileOutputStream fos = new FileOutputStream(destFile)) {

							byte[] buffer = new byte[1024];
							int length;
							while ((length = fis.read(buffer)) > 0) {
								fos.write(buffer, 0, length);
							}

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "이미지 저장 실패");
							ex.printStackTrace();
							return;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
					}
				}
			}
		});

		// 전화번호 변경
		btnChange_PhNum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new RvListConn().getConnection();

					// 기존 전화번호 조회
					String currentPhone = null;
					String selectSql = "SELECT users_phone FROM users WHERE users_signum = ?";
					PreparedStatement selectStmt = conn.prepareStatement(selectSql);
					selectStmt.setInt(1, StaticInfo.statUserNo);
					ResultSet rs = selectStmt.executeQuery();

					if (rs.next()) {
						currentPhone = rs.getString("users_phone");
					} else {
						JOptionPane.showMessageDialog(null, "사용자 정보를 불러오지 못했습니다.");
						return;
					}

					// 반복 입력
					while (true) {
						// 기존 전화번호를 기본값으로 표시
						String newPhone = JOptionPane.showInputDialog(null, "새 전화번호를 입력해 주세요 (예: 010-1234-5678)",
								currentPhone);

						if (newPhone == null) {
							JOptionPane.showMessageDialog(null, "전화번호 변경이 취소되었습니다.");
							return;
						}

						newPhone = newPhone.trim();

						// 전화번호 형식 검사
						if (!newPhone.matches("^010-\\d{4}-\\d{4}$")) {
							JOptionPane.showMessageDialog(null, "전화번호 형식이 올바르지 않습니다.\n예: 010-1234-5678");
							continue;
						}

						// 기존과 동일한 번호
						if (newPhone.equals(currentPhone)) {
							JOptionPane.showMessageDialog(null, "기존 전화번호와 동일합니다. 다른 번호를 입력해 주세요.");
							continue;
						}

						// DB 업데이트
						String updateSql = "UPDATE users SET users_phone = ? WHERE users_signum = ?";
						PreparedStatement updateStmt = conn.prepareStatement(updateSql);
						updateStmt.setString(1, newPhone);
						updateStmt.setInt(2, StaticInfo.statUserNo);

						int result = updateStmt.executeUpdate();
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "전화번호가 성공적으로 변경되었습니다.");
							// textArea3.setText(newPhone); // UI 반영하고 싶을 경우
						} else {
							JOptionPane.showMessageDialog(null, "전화번호 변경에 실패했습니다.");
						}

						updateStmt.close();
						conn.close();
						return;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
				}
			}
		});

		// 이메일변경
		btnChangeEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new RvListConn().getConnection();

					// 기존 이메일 조회
					String currentEmail = null;
					String emailSelectSql = "SELECT users_email FROM users WHERE users_signum = ?";
					PreparedStatement selectStmt = conn.prepareStatement(emailSelectSql);
					selectStmt.setInt(1, StaticInfo.statUserNo);
					ResultSet rs = selectStmt.executeQuery();

					if (rs.next()) {
						currentEmail = rs.getString("users_email");
					} else {
						JOptionPane.showMessageDialog(null, "사용자 정보를 불러오지 못했습니다.");
						return;
					}

					// 반복 입력 시작
					while (true) {
						// 기존 이메일을 입력창 기본값으로 보여줌
						String newEmail = JOptionPane.showInputDialog(null, "새 이메일을 입력해 주세요 (예: example@email.com)",
								currentEmail);

						if (newEmail == null) {
							JOptionPane.showMessageDialog(null, "이메일 변경이 취소되었습니다.");
							return;
						}

						newEmail = newEmail.trim();

						// 이메일 형식 확인
						if (!newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
							JOptionPane.showMessageDialog(null, "이메일 형식이 올바르지 않습니다.\n다시 입력해 주세요.");
							continue;
						}

						// 기존 이메일과 비교
						if (newEmail.equals(currentEmail)) {
							JOptionPane.showMessageDialog(null, "기존 이메일과 동일합니다. 다른 이메일을 입력해 주세요.");
							continue;
						}

						// 이메일 업데이트
						String updateSql = "UPDATE users SET users_email = ? WHERE users_signum = ?";
						PreparedStatement updateStmt = conn.prepareStatement(updateSql);
						updateStmt.setString(1, newEmail);
						updateStmt.setInt(2, StaticInfo.statUserNo);

						int result = updateStmt.executeUpdate();
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "이메일이 성공적으로 변경되었습니다.");
							// UI 업데이트 예: textAreaEmail.setText(newEmail);
						} else {
							JOptionPane.showMessageDialog(null, "이메일 변경에 실패했습니다.");
						}
						return;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
				}
			}
		});

		// 로그아웃 버튼
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(btnLogout, "로그아웃 하시겠습니까?", "로그아웃",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					try {
						JOptionPane.showMessageDialog(btnLogout, "로그아웃이 완료되었습니다.");
						dispose();
						new Log().setVisible(true);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		// 회원 탈퇴
		btnTal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(btnTal, "탈퇴 하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					try {
						conn = new RvListConn().getConnection();
						String sql = "DELETE FROM users WHERE users_signum = ? ";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, StaticInfo.statUserNo);
						ResultSet rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(btnLogout, "탈퇴가 완료되었습니다.");
							dispose();
							new Log().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(btnLogout, "아이디가 존재하지 않습니다.");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
					}
				}
			}
		});

		// 확인 버튼
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedImageFile != null) { // ★ 추가됨
					try {
						File destDir = new File("src/resources"); // ★ 추가됨
						if (!destDir.exists())
							destDir.mkdirs(); // ★ 추가됨

						Path sourcePath = selectedImageFile.toPath(); // ★ 추가됨
						Path targetPath = destDir.toPath().resolve(selectedImageFile.getName()); // ★ 추가됨

						Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING); // ★ 추가됨
					} catch (IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "이미지 저장 중 오류 발생: " + ex.getMessage()); // ★ 추가됨
					}
				}
				new UserMain().setVisible(true);
				dispose();
			}
		});
	}
}
