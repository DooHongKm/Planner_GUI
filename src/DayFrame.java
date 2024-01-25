import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DayFrame extends JFrame {
	
	String fName = "C:/Users/±èµÎÈ«/Desktop/schedule-file.data";
	File file = new File(fName);
	ScheduleList list = new ScheduleList(fName);
	ArrayList <Schedule> info = new ArrayList<>();
	
	int y, m, d;
	private LocalDate l_date;
	private JButton cancel, add, save;
	private JTextField title, stime, etime, memo;
	private DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	MyListener1 listener1 = new MyListener1();
	MyListener2 listener2 = new MyListener2();
	MyListener3 listener3 = new MyListener3();
	
	public DayFrame() { 
		
		this(LocalDate.now(), 0);
		
	}
	
	public DayFrame(LocalDate date, int adding) {
		
		super("Day Schedule" + "  " + date);
		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		l_date = date;
		for(int i=0;i<list.schedulelist.size();i++) {
			y = list.schedulelist.get(i).startingTime.getYear();
			m = list.schedulelist.get(i).startingTime.getMonthValue();
			d = list.schedulelist.get(i).startingTime.getDayOfMonth();
			if(date.isEqual(LocalDate.of(y, m, d))) {
				info.add(list.schedulelist.get(i));
			}
		}
				
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		for(int i=0;i<info.size();i++) panel.add(new DayPanel2(info.get(i)));
		if(adding==1) panel.add(new DayPanel2());
		
		add(new DayPanel1(), BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(new DayPanel3(), BorderLayout.SOUTH);
		
	}
	
	private class DayPanel1 extends JPanel {
		public DayPanel1() {
			
			JLabel Title = new JLabel("Title");
			JLabel Stime = new JLabel("Start Time");
			JLabel Etime = new JLabel("End Time");
			JLabel Memo = new JLabel("Memo");
			
			setLayout(new GridLayout(1, 4));
			Title.setHorizontalAlignment(JLabel.CENTER);
			Stime.setHorizontalAlignment(JLabel.CENTER);
			Etime.setHorizontalAlignment(JLabel.CENTER);
			Memo.setHorizontalAlignment(JLabel.CENTER);
			
			add(Title);
			add(Stime);
			add(Etime);
			add(Memo);
			
		}
	}
	
	private class DayPanel2 extends JPanel {
		public DayPanel2() {
			
			title = new JTextField("");
			stime = new JTextField("");
			etime = new JTextField("");
			memo = new JTextField("");	
			
			setLayout(new GridLayout(1, 4));
			add(title);
			add(stime);
			add(etime);
			add(memo);
			
		}
		
		public DayPanel2(Schedule s) {
			
			
			title = new JTextField(s.title);
			stime = new JTextField(s.startingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
			etime = new JTextField(s.endingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
			memo = new JTextField(s.memo);
			
			setLayout(new GridLayout(1, 4));
			title.setHorizontalAlignment(JTextField.CENTER);
			stime.setHorizontalAlignment(JTextField.CENTER);
			etime.setHorizontalAlignment(JTextField.CENTER);
			memo.setHorizontalAlignment(JTextField.CENTER);
			
			add(title);
			add(stime);
			add(etime);
			add(memo);
			
		}
	}
	
	private class DayPanel3 extends JPanel {
		public DayPanel3() {
			
			cancel = new JButton("Cancel");
			add = new JButton("Add");
			save = new JButton("Save");
			
			setLayout(new GridLayout(1, 3));
			
			cancel.addActionListener(listener1);
			add.addActionListener(listener2);
			save.addActionListener(listener3);
			
			add(cancel);
			add(add);
			add(save);
			
		}
		
	}
	
	private class MyListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			list.schedulelist.removeAll(info);
			try {
				BufferedWriter f = new BufferedWriter(new FileWriter(fName));
			}catch (Exception e1) {
				System.out.println("Delete Error");
			}
			try (FileWriter fw = new FileWriter(fName, true);
				BufferedWriter bw = new BufferedWriter(fw);)
			{
				for(int i=0;i<list.schedulelist.size();i++) {
					bw.write(list.schedulelist.get(i).title + "//");
					bw.write(list.schedulelist.get(i).startingTime.format(form) + "//");
					bw.write(list.schedulelist.get(i).endingTime.format(form) + "//");
					bw.write(list.schedulelist.get(i).memo);
					bw.newLine();
				}
			}catch (Exception e2) {
				System.out.println("Write Error");
			}
			new DayFrame(l_date, 0);
		}
	}
	
	private class MyListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new DayFrame(l_date, 1);
		}
	}
	
	private class MyListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			try (FileWriter fw = new FileWriter(fName, true);
					BufferedWriter bw = new BufferedWriter(fw);)
			{
				bw.newLine();
				bw.write(title.getText() + "//");
				bw.write(stime.getText() + "//");
				bw.write(etime.getText() + "//");
				bw.write(memo.getText());
			}catch (Exception e2) {
				System.out.println("Write Error");
			}
			new DayFrame(l_date, 0);
		}
	}
	
}