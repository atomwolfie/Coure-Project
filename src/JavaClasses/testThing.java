import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JList;

public class testThing {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testThing window = new testThing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testThing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(35, 33, 100, 100);
		frame.getContentPane().add(scrollPane);
		
		ScrollPane scrollPane_1 = new ScrollPane();
		scrollPane_1.setBounds(254, 10, 100, 100);
		frame.getContentPane().add(scrollPane_1);
		
		JList list = new JList();
		list.setBounds(254, 10, 100, 100);
		frame.getContentPane().add(list);
	}
}
