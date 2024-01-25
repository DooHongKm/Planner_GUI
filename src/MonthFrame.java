import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonthFrame extends JFrame {
	
	int year, month;
	int day, len, i, j;
	private LocalDate f_date;
	private JButton btn;
	private JButton lArrow = new JButton("<-");
	private JButton rArrow = new JButton("->");
	private JLabel date = new JLabel();
	private JLabel sun = new JLabel("SUN");
	private JLabel mon = new JLabel("MON");
	private JLabel tue = new JLabel("TUE");
	private JLabel wed = new JLabel("WED");
	private JLabel thu = new JLabel("THU");
	private JLabel fri = new JLabel("FRI");
	private JLabel sat = new JLabel("SAT");
	MyListener1 listener1 = new MyListener1();
	MyListener2 listener2 = new MyListener2();
	MyListener3 listener3 = new MyListener3();
	
	public MonthFrame() {
		
		this(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
		
	}
	
	public MonthFrame(int y, int m) {
		
		super("Schedule Planner");
		setSize(500, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		year = y; month = m;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(new MonthPanel1());
		panel.add(new MonthPanel2());
		
		add(panel, BorderLayout.NORTH);
		add(new MonthPanel3(), BorderLayout.CENTER);
		
	}

	private class MonthPanel1 extends JPanel {
		public MonthPanel1() {
			
			setLayout(new GridLayout(1, 3));
			date.setText(Integer.toString(year)+"³â "+Integer.toString(month)+"¿ù");
			date.setHorizontalAlignment(JLabel.CENTER);
			lArrow.addActionListener(listener1);
			rArrow.addActionListener(listener2);
			
			add(lArrow);
			add(date);
			add(rArrow);
			
		}	
	}
	
	private class MonthPanel2 extends JPanel {
		public MonthPanel2() {
			
			setLayout(new GridLayout(1, 7));
			sun.setHorizontalAlignment(JLabel.CENTER);
			mon.setHorizontalAlignment(JLabel.CENTER);
			tue.setHorizontalAlignment(JLabel.CENTER);
			wed.setHorizontalAlignment(JLabel.CENTER);
			thu.setHorizontalAlignment(JLabel.CENTER);
			fri.setHorizontalAlignment(JLabel.CENTER);
			sat.setHorizontalAlignment(JLabel.CENTER);
			
			add(sun);
			add(mon);
			add(tue);
			add(wed);
			add(thu);
			add(fri);
			add(sat);
			
		}
	}
	
	private class MonthPanel3 extends JPanel {
		public MonthPanel3() {
			
			f_date = LocalDate.of(year, month, 01);
			day = f_date.getDayOfWeek().getValue() % 7;
			len = f_date.lengthOfMonth();

			setLayout(new GridLayout(0, 7));
			for(i=0;i<day;i++) {
				add(new JLabel());
			}
			for(i=0;i<len;i++) {
				btn = new JButton(Integer.toString(i+1));
				btn.addActionListener(listener3);
				add(btn);
			}
			
		}
	}
	
	private class MyListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(month!=1) {
				month--;
			}
			else {
				year--;
				month = 12;
			}
			setVisible(false);
			new MonthFrame(year, month);
		}
	}
	
	private class MyListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(month!=12) {
				month++;
			}
			else {
				year++;
				month = 1;
			}
			setVisible(false);
			new MonthFrame(year, month);
		}
	}
	
	private class MyListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			j = Integer.parseInt(e.getActionCommand());
			new DayFrame(LocalDate.of(year, month, j), 0);
		}
	}
	
}