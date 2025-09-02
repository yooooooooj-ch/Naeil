package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import main.StaticInfo;

public class YeyakDetail extends JFrame {

	private JPanel contentPane;
	private JTextField yearField;// 년도 필드
	private JTextField monthField;// 월 필드
	private JButton[] dayButtons = new JButton[42];// 날짜 버튼 42개 한 이유가 1일이 토요일이면 세로 6줄이 필요함

	private String[] human = { "1명", "2명", "3명", "4명", "5명", "6명", "7명", "8명", "9명", "10명", "직접입력" };// 이건
																												// db에서


	private int minute = 0; // start에 캘린더에서 시작한 분을 minute에 저장
	int year;
	int month;
	private String dd;
	int hour;

	JComponent timePicker; // 시간 컴포넌트
	JComponent dataPicker; // 데이터 컴포넌트
	private String manypeople; // 인원 직접입력시 사용
	private int inwonsu = 0;// 인원수
	private String login_id = "id"; // 이것도 임시로 한 것 뿐
	private String login_pw = "pw"; // me too
	private String yeyakja_name = "종로"; // 임시
	private String yeyakja_phone = "010-1234-5678"; // 임시
	private String timeStr;
	private String yochung; // 요구사항

	// 이제부터는 파일을 받은것
	int store_id;
	int menu_id;
	int suryang;
	int price;
	String rv_date;
	String rv_time;

	sqlDAO2 dao = new sqlDAO2();

	// 가게마다 운영시간이 다달라서 그냥 20으로 설정을 하였고 관리자 서버에서 운영시간을 따로 입력 받아서 그걸로 참고하게 하려고 생각중


	public YeyakDetail() {

		setTitle("예약");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		// 상단 패널
		JPanel topPanel = new JPanel();
		yearField = new JTextField(4);
		monthField = new JTextField(2);
		JButton drawButton = new JButton("검색");
		JButton nextmonthButton = new JButton("다음달");
		JButton lastmonthButton = new JButton("전달");
		JLabel label2 = new JLabel();
		label2.setText("(년도랑 월 입력하고 싶으면 텍스트 입력)");

		topPanel.add(yearField);
		topPanel.add(new JLabel("년도"));
		topPanel.add(monthField);
		topPanel.add(new JLabel("월"));
		topPanel.add(drawButton);
		topPanel.add(lastmonthButton);
		topPanel.add(nextmonthButton);
		topPanel.add(label2);

		contentPane.add(topPanel, BorderLayout.NORTH);

		// 달력 요일 헤더
		JPanel calendarPanel = new JPanel(new GridLayout(7, 7, 5, 5));
		String[] days = { "일", "월", "화", "수", "목", "금", "토" };
		for (String day : days) {
			JLabel label = new JLabel(day, SwingConstants.CENTER);
			label.setForeground(day.equals("일") ? Color.RED : day.equals("토") ? Color.BLUE : Color.BLACK);
			calendarPanel.add(label);
		}

		// 날짜 버튼들 초기화
		for (int i = 0; i < 42; i++) {
			JButton btn = new JButton("");
			btn.setEnabled(false);
			final int index = i;

			btn.addActionListener(e -> {
				dd = dayButtons[index].getText();
				year = Integer.parseInt(yearField.getText());
				month = Integer.parseInt(monthField.getText());


				JPanel timePanel = timeButtons();
				JScrollPane scrollPane = new JScrollPane(timePanel);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane.setPreferredSize(new Dimension(500, 100));

				// ⬇️ 시간 버튼 보여주기
				Object[] options = { "OK", "날짜 다시 선택" };
				int result = JOptionPane.showOptionDialog(this, scrollPane, "예약 가능한 시간", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if (result == 1) {
					JOptionPane.showMessageDialog(this, "날짜를 다시 선택해주세요.");
					year = 0;
					month = 0;
					dd = null;
				}

			});

			dayButtons[i] = btn;
			calendarPanel.add(btn);

		}

		contentPane.add(calendarPanel, BorderLayout.CENTER);

		// 버튼 액션 리스너
		drawButton.addActionListener(e -> {

			year = Integer.parseInt(yearField.getText());
			month = Integer.parseInt(monthField.getText());
			if (year == 0 || month == 0) {
				JOptionPane.showMessageDialog(this, "올바른 값을 입력해주세요.");
			}
			else if (year < 1900 || year > 2100) {
				JOptionPane.showMessageDialog(this, "1900년 ~ 2100년 사이로 입력해주세요.");
				return;
			} else if (month > 12) {
				JOptionPane.showMessageDialog(this, "월은 12까지 입력 가능합니다.");
				return;
			}
			drawCalendar(year, month);

		});
		nextmonthButton.addActionListener(e -> {
			try {

				month++; // 다음 달로 증가
				if (month > 12) {// month의 값이 12초과일때
					month = 1;// month의 값은 1이 되고
					year++; // 년도가 1이 증가함
				}

				yearField.setText(String.valueOf(year));
				monthField.setText(String.valueOf(month));
				drawCalendar(year, month); // 달력 다시 그리기
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "올바른 연도와 월을 입력하세요.");
			}
		});

		lastmonthButton.addActionListener(e -> {
			try {
				month--; // 다음 달로 증가
				if (month < 1) {// month의 값이 12초과일때
					month = 12;// month의 값은 1이 되고
					year--; // 년도가 1이 증가함
				}

				yearField.setText(String.valueOf(year));
				monthField.setText(String.valueOf(month));
				drawCalendar(year, month); // 달력 다시 그리기
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "올바른 연도와 월을 입력하세요.");
			}
		});

		// 기본값 현재 날짜로 입력
		Calendar now = Calendar.getInstance();
		yearField.setText(String.valueOf(now.get(Calendar.YEAR)));
		monthField.setText(String.valueOf(now.get(Calendar.MONTH) + 1));
		drawCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1);

	}



	// 날짜 계산 및 버튼 활성화
	private void drawCalendar(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);

		int startDay = cal.get(Calendar.DAY_OF_WEEK); // 1~7 (일~토)
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Calendar d30 = (Calendar) today.clone();
		d30.add(Calendar.DATE, 30); // 오늘부터 +30일

		// 초기화
		for (int i = 0; i < 42; i++) {
			dayButtons[i].setText("");
			dayButtons[i].setEnabled(false);
		}

		for (int i = 0; i < lastDay; i++) {
			int index = (startDay - 1) + i;
			int day = i + 1;

			Calendar cellDate = Calendar.getInstance();
			cellDate.set(year, month - 1, day);
			cellDate.set(Calendar.HOUR_OF_DAY, 0);
			cellDate.set(Calendar.MINUTE, 0);
			cellDate.set(Calendar.SECOND, 0);
			cellDate.set(Calendar.MILLISECOND, 0);

			dayButtons[index].setText(String.valueOf(day));

			if (!cellDate.before(today) && !cellDate.after(d30)) {
				dayButtons[index].setEnabled(true); // 오늘 ~ +30일 이내만 클릭 가능
			}
		}
	}

	private JPanel timeButtons() {
		JPanel timebuttons = new JPanel();
		timebuttons.setLayout(new GridLayout(0, 5, 10, 10));// 가로로 스크롤 할 수 있게 설정할 거임 그래서 스크롤 바를 설정해야함

		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 10);

		Calendar last = Calendar.getInstance();
		last.set(Calendar.HOUR_OF_DAY, 22);

		Calendar now = Calendar.getInstance(); // 현재시간을 알려주는 now 객체 생성
		boolean istoday = false; // 오늘이 지났다면 거짓
		int selectedyear = Integer.parseInt(yearField.getText());
		int selectedmonth = Integer.parseInt(monthField.getText());
		int selectedday = Integer.parseInt(dd);
		now.set(Calendar.SECOND, 3600);

		if (selectedyear == now.get(Calendar.YEAR) && selectedmonth == now.get(Calendar.MONTH) + 1
				&& selectedday == now.get(Calendar.DAY_OF_MONTH)) {
			istoday = true;
		}

		hour = 20;
		while (!start.after(last)) {

			if (istoday && start.before(now)) {
				start.add(Calendar.MINUTE, 30);
				minute = minute == 0 ? 30 : 0;
				hour += 1;
				continue; // 현재 시간 전이면 건너뜀
			}
			timeStr = String.format("%02d:%02d", hour / 2, minute);
			JButton timeBtn = new JButton(timeStr);

			timeBtn.addActionListener(e -> {

				JPanel humanPanel = humanpanel();
				timeStr = timeBtn.getText();
				JScrollPane scrollPane2 = new JScrollPane(humanPanel);
				JOptionPane.showMessageDialog(this, scrollPane2, "예약 인원수", JOptionPane.PLAIN_MESSAGE);

			});
			timebuttons.add(timeBtn);
			minute = minute == 0 ? 30 : 0;
			hour += 1;
			start.add(Calendar.MINUTE, 30);

		}
		minute = minute == 0 ? 30 : 0;
		return timebuttons;

	}

	private JPanel humanpanel() { // 사람 수 버튼들이 담길 패널을 생성하는 메소드
		JPanel humanpanel = new JPanel(); // JPanel 객체 생성
		humanpanel.setLayout(new GridLayout(0, 4, 10, 10)); // 4열 그리드 레이아웃으로 버튼을 정렬하고, 간격 10px 지정

		for (String personLabel : human) {// human 배열에 있는 각 인원수 옵션을 바탕으로 버튼 생성
			JButton hubtl = new JButton(personLabel); // 버튼 생성
			hubtl.addActionListener(e -> { // 각 버튼에 액션 리스너 추가

				if (personLabel.contains("직접입력")) {// 버튼 텍스트가 "직접입력"일 경우
					boolean validInput = false;
					while (!validInput) {
						manypeople = JOptionPane.showInputDialog("예약하실 인원수를 입력해주세요");

						if (manypeople == null) { // 사용자가 입력 없이 취소한 경우
							return;
						}

						try {
							inwonsu = Integer.parseInt(manypeople.trim());

							if (inwonsu > 0 && inwonsu <= 99) {
								validInput = true; // 유효한 숫자 입력
							} else {
								JOptionPane.showMessageDialog(this, "1~99의 숫자만 입력 가능합니다.");
							}

						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(this, "숫자만 입력해주세요.");
						}
					}
					}
				else {
					inwonsu = Integer.parseInt(personLabel.replaceAll("[^0-9]", ""));
				}

				// "직접입력"이 아닌 경우 - 확인 다이얼로그로 예약 인원 확인
				// timeStr = 시간(10:00)

				for (JButton btn : dayButtons) { // btn버튼이 daybutton이 활성화 될 때까지
					if (btn.getModel().isArmed()) {
						dd = btn.getText();
						break;
					}
				}

				String date = year + "-" + month + "-" + dd;


				// yes를 선택하면 정상적으로 진행
				// no가 선택되면은 예약일자만 기록이 남아 있고 다시 설정하게 하면됨
				boolean inputValid = false; // 올바르게 입력될 때까지 반복
				while (!inputValid) {
					JTextField nameField = new JTextField(10);// 네임필드라는 텍스트 필드 (추가는 안하고 그냥 기본 설정만)
					JTextField phoneField = new JTextField(10);// 폰필드라는 텍스트 필드(추가는 안하고 그냥 기본 설정만)
					JTextField requestField = new JTextField(15);// 요청필드라는 텍스트필드 (추가는 안하고 그냥 기본 설정만)
					JPanel inpanel = new JPanel(new GridLayout(0, 1));
					if (!login_id.isEmpty() && login_id != null && !login_pw.isEmpty() && login_pw != null) {
						// 위에 회원이 아니면 주문이 안되지만 일단 임시로 했음. 아이디와 비밀번호가 일치하면 이라고 해야지(사실 이거 안해도 yes옵션이라고 하면
						// 될 것 같은데)
						nameField.setText(StaticInfo.statUserName);// 예약자의 이름을 namefield에 입력된 것(지금은 setter만 있으니
																	// 오류가 남)
						phoneField.setText(StaticInfo.statUserPhone);// 예약자의 전화번호를 phonefield에 입력된 것( "" )
						inpanel.add(new JLabel("예약자 성함"));// 예약자의 이름을 알려주는 텍스트필드 왼쪽에 있음
						inpanel.add(nameField);// namefield라는 텍스트 필드 추가

						inpanel.add(new JLabel("전화번호 (중간에 - 입력 ex)010-####-####)"));// 예약자의 전화번호를 알려주는 텍스트필드 왼쪽에
																					// 있음
						inpanel.add(phoneField); // 텍스트필드 추가
						inpanel.add(new JLabel("요구사항 (선택사항)"));// 텍스트필드 왼쪽에 있음
						inpanel.add(requestField);// 요청필드 추가
						inpanel.add(
								new JLabel("<html>예약자와 방문자가 다를경우<br>예약자의 정보를 지우고<br>방문자의 이름과 전화번호를 입력해주세요.</html>"));
					}
					Object[] option2 = { "확인", "취소" };
					int result = JOptionPane.showOptionDialog(this, inpanel, "예약자 정보를 입력해주세요",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, option2, option2[0]);
					if (result == 0) {
						String name = nameField.getText().trim();
						String phone = phoneField.getText().trim();
						yochung = requestField.getText();

						if (name.matches(".*[a-zA-Z가-힣]+.*") && phone.matches("^\\d{3}-\\d{4}-\\d{4}$")) {

							if (yochung.isEmpty()) {
								yochung = "없음";
							}
							// 여기서 예약 정보 요약 출력하거나 저장하는 코드로 연결!
							String finalSummary = "예약신청이 완료되었습니다!\n\n" + "▶ 예약자 성함: " + name + "\n" + "▶ 연락처: " + phone
									+ "\n" + "▶ 날짜: " + date + "\n" + "▶ 시간: " + timeStr + "\n" + "▶ 인원: " + inwonsu
									+ "명\n" + "▶ 요구사항: " + yochung;

							JOptionPane.showMessageDialog(this, finalSummary, "최종 예약 정보",
									JOptionPane.INFORMATION_MESSAGE);

							JOptionPane.showMessageDialog(this, "예약 신청이 완료되었으며 예약 최종확인은\n 마이페이지에서 가능합니다.");

							// 3. 각 OrderItem을 기반으로 OrderedItemInfo 생성
							List<OrderedItemInfo> orderedList = new ArrayList<>();
							for (OrderItem item : StoreMenu.orderItems) {
								OrderedItemInfo ordered = new OrderedItemInfo(item.getMenuId(), item.getQuantity(),
										name, phone, date, timeStr, inwonsu, yochung);
								orderedList.add(ordered);
							}

							try {
								dao.setMenuOrdered(orderedList); // DB에 예약 정보 삽입
								JOptionPane.showMessageDialog(null, "예약이 완료되었습니다!");
								orderedList.clear();
								StoreMenu.orderItems.clear();
								inputValid = true;
							} catch (Exception ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null, "예약 중 오류 발생: " + ex.getMessage());
							}
							dispose();

						}

						else if (!name.matches(".*[a-zA-Z가-힣]+.*")) {
							JOptionPane.showMessageDialog(this, "문자를 입력해주세요.");
							continue;
						}

						else if (!phone.matches("^\\d{3}-\\d{4}-\\d{4}$")) {
							JOptionPane.showMessageDialog(this, "전화번호를 형식에 맞게 입력해주세요.\n예: 010-1234-5678");
							continue;
						}
					}

					else if (result == 1) {
						JOptionPane.showMessageDialog(this, "취소했습니다.");
						inwonsu = 0;
						return;
					}
				}
			});

			humanpanel.add(hubtl);// 생성된 버튼을 패널에 추가
		}

		return humanpanel; // 패널 반환
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			YeyakDetail frame = new YeyakDetail();
			frame.setVisible(true);

		});

	}

}
