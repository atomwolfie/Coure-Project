

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;



public class SimonishMano extends JFrame implements MouseListener, ActionListener{
	Color[] colorArr = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
	ColorPanel[] panelArr = new ColorPanel[4];
	ScorePanel scorePanel;
	JPanel gamePanel;
	int sleepTime = 500;
	int delayTime = 1000;
	ArrayList<ColorPanel> seq = new ArrayList<ColorPanel>();
	Iterator seqItr;
	Random rand = new Random();
	boolean gameOver = true;
	private ImageIcon penguin1;
    private ImageIcon penguin2;
    
	
	Container pane;
	
	
	public SimonishMano() {
		this.setTitle("Simonish");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		
		pane.setPreferredSize(new Dimension(400, 450));
		scorePanel = new ScorePanel();
		
	
		
		scorePanel.setBounds(0, 0, 400, 50);
		
		scorePanel.getStartBtn().addActionListener(this);
		
		
		
			
		
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		
		JMenu help = new JMenu("Help");
		menuBar.add(help);
		
		JMenuItem about = new JMenuItem("About");
		help.add(about);
		
		JMenuItem rules = new JMenuItem("Rules");
		help.add(rules);
		
		JMenu mode = new JMenu("Mode");
		menuBar.add(mode);
		
		mode.addSeparator();
		
		JMenuItem fastSpeed = new JMenuItem("Fast Speed");
		JMenuItem mediumSpeed = new JMenuItem("Medium Speed");
		JMenuItem slowSpeed = new JMenuItem("Slow Speed");
		JMenu increasedSpeedMode = new JMenu("Increased Speed Mode");
		JMenuItem increasedOn = new JMenuItem("On");
		JMenuItem increasedOff = new JMenuItem("Off");
		
		
		
		increasedSpeedMode.add(increasedOn);
		increasedSpeedMode.add(increasedOff);
		

		
		
		
		JMenuItem chooseColor = new JMenuItem("Choose Colors");
		
		
		JMenu chooseSpeed = new JMenu("Choose Speed");
		mode.add(chooseColor);
		mode.add(chooseSpeed);
		mode.add(increasedSpeedMode);
		
		
		chooseSpeed.add(slowSpeed);
		chooseSpeed.add(mediumSpeed);
		chooseSpeed.add(fastSpeed);
		
		
		JMenu stats = new JMenu("Stats");
		menuBar.add(stats);
		
		JMenuItem highScores = new JMenuItem("High Scores");
		JMenuItem history = new JMenuItem("History");
		stats.add(highScores);
		stats.add(history);
		
		
				
				
		
		
		
		class increasedSpeedOn implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				scorePanel.speedIncreased = true;
				
				JOptionPane.showMessageDialog(null, "Games in now changed to increasing speed mode "
						+ "\nAfter each turn the computer will increase it's speed!");
				
			}		
			
		}
		
		class increasedSpeedOff implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				scorePanel.speedIncreased = false;
				sleepTime = 500;
				delayTime = 1000;
				
				JOptionPane.showMessageDialog(null, "Game has been changed back to normal mode.");
			}
			
			
		}
		
		
		
		
		
		/*a class created for exit, will shut down program
		 */
			class exitation implements ActionListener{      
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
			
		}
		
			 class highScoreMenu extends ScorePanel implements ActionListener  {
				
				public void actionPerformed(ActionEvent e){
					
							JOptionPane.showMessageDialog(null,"1st place: " + scorePanel.nameArray.get(0) + " with " + scorePanel.highScoreArray.get(0) + " points " +
									"\n2nd place: " + scorePanel.nameArray.get(1) + " with " + scorePanel.highScoreArray.get(1) + " points " +
									"\n3rd place: " + scorePanel.nameArray.get(2)  + " with " + scorePanel.highScoreArray.get(2) +" points " +
									"\n4th place: " + scorePanel.nameArray.get(3) + " with " + scorePanel.highScoreArray.get(3) +" points " +
									"\n5th place: " + scorePanel.nameArray.get(4) + " with " + scorePanel.highScoreArray.get(4) +" points " +
									"\n6th place: " + scorePanel.nameArray.get(5) + " with " + scorePanel.highScoreArray.get(5) +" points " +
									"\n7th place: " + scorePanel.nameArray.get(6) + " with " + scorePanel.highScoreArray.get(6) +" points " +
									"\n8th place: " + scorePanel.nameArray.get(7) + " with " + scorePanel.highScoreArray.get(7) +" points " +
									"\n9th place: " + scorePanel.nameArray.get(8) + " with " + scorePanel.highScoreArray.get(8) +" points " +
									"\n10th place: " + scorePanel.nameArray.get(9) + " with " + scorePanel.highScoreArray.get(9) + " points "  );

						
				}
				
				
			}
			
			 
			 class gameHistory extends ScorePanel implements ActionListener{
				 
				 public void actionPerformed(ActionEvent e){
					
					 if(scorePanel.gameResets >= 1){
					 double averageScore = scorePanel.allScores / scorePanel.gameResets;
					 
					 JOptionPane.showMessageDialog(null, "Number of games played: " + scorePanel.gameResets + "\n Average Score: " + averageScore);
					 }
					 
					 else{
						 
						 JOptionPane.showMessageDialog(null, "Number of games played: 0 \nAverage Score: 0.0");
					 }
				 }
				 
			 }
			
			 class ruleBook implements ActionListener{
				 
				 public void actionPerformed(ActionEvent e){
					 
					 JOptionPane.showMessageDialog(null, "The computer will create a sequence with the buttons, copy the sequence."
					 		+ "\nThe sequence will restart and one move will be added to the end."
					 		+ "\nKeep copying the sequence, if the sequenced isn't repeated correctly the game is over.");
				 }
				 
			 }
			 
			 
			 class aboutTheGame implements ActionListener{
				 public void actionPerformed(ActionEvent e){
					 JOptionPane.showMessageDialog(null, "This new and improved version of Simonish includes features such as: "
					 		+ "\nGame History, High Score History, "
					 		+ "Choosing the colors for the panels,\n and the window size can also adjust.");
					 
				 }
				 
			 }
			 
			 class colorChoice implements ActionListener{
				 public void actionPerformed(ActionEvent e){
					 
					for(int i = 0; i < panelArr.length; ++i){
					  
						colorArr[i] = JColorChooser.showDialog(null, "Pick colors for the buttons for panel " + (i + 1), colorArr[i]);
					  
					  		panelArr[i].setBackground(colorArr[i]);
							
					}
						
				 }
			 }
			 
			 class fastChoice implements ActionListener{
				 public void actionPerformed(ActionEvent e){
					 //CHANGE THREADS TO A SMALLER NUMBER 
					 sleepTime = 200;
					 delayTime = 400;
					JOptionPane.showMessageDialog(null, "Game set to fast mode!");
					 
				 }
				 
			 }
			class mediumChoice implements ActionListener{
				public void actionPerformed(ActionEvent e){
					//changes thread to normal numbers 500, 1000
					sleepTime = 500;
					delayTime = 1000;
					JOptionPane.showMessageDialog(null, "Game set to normal (medium speed) mode!");
				}
			}
			
			class slowChoice implements ActionListener{
				public void actionPerformed(ActionEvent e){
					//Changes thread to slower speeds
					sleepTime = 900;
					delayTime = 1800;
					JOptionPane.showMessageDialog(null, "Game set to slow mode!");
				}
				
				
			}
				 
			 
			
			 
			 
		/*
		 * giving exit an action listener and adding the exitation class to it
		 */
		exit.addActionListener(new exitation());   
		highScores.addActionListener(new highScoreMenu());
		history.addActionListener(new gameHistory());
		about.addActionListener(new aboutTheGame());
		rules.addActionListener(new ruleBook());
		chooseColor.addActionListener(new colorChoice());
		fastSpeed.addActionListener(new fastChoice());
		mediumSpeed.addActionListener(new mediumChoice());
		slowSpeed.addActionListener(new slowChoice());
		increasedOn.addActionListener(new increasedSpeedOn());
		increasedOff.addActionListener(new increasedSpeedOff());
		
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(2, 2));
		gamePanel.setBounds(0, 50, 400, 400);
		
		
		
		pane.add(scorePanel, BorderLayout.PAGE_START);
		pane.add(gamePanel, BorderLayout.CENTER);
		
		
		
		
		
		boolean internet = true;
		if (internet) {
	        try {
					penguin1 = new ImageIcon(new URL("https://openclipart.org/image/200px/svg_to_png/27563/lemmling-Little-penguin-5.png"));
					penguin2 = new ImageIcon(new URL("https://openclipart.org/image/200px/svg_to_png/61207/flat-lemmling-Cartoon-penguin.png"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {   
		        penguin1 = new ImageIcon("desktop/lemmling-Little-penguin-5.png", null);
				penguin2 = new ImageIcon("desktop/flat-lemmling-Cartoon-penguin.png", null);
	        }
			
			
		
		
		
		
		
		
		createColors();	
				
		
		this.pack();
		this.setLocationRelativeTo(null);	
		this.setVisible(true);
	}



	private void createColors() {
		gamePanel.removeAll();
		for (int i = 0; i < panelArr.length; i++) {
			panelArr[i] = new ColorPanel(colorArr[i]);
			
			panelArr[i].add(new JLabel(penguin1));
			
			
			panelArr[i].addMouseListener(this);
			gamePanel.add(panelArr[i]);
		}
		gamePanel.updateUI();
	}
	
	
	
	
	
	public void playSound(String soundName){
		  
		try {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	    Clip clip = AudioSystem.getClip( );
	    clip.open(audioInputStream);
	    clip.start();
	   }
		
	   catch(Exception ex){
	     System.out.println("Error with playing sound.");
	     ex.printStackTrace( );
	   }
	 }
	
	
	
	
	public class ComputerRunnable implements Runnable{
	
		
		
		
		public void run(){
			
			
			
			
			
			if(scorePanel.speedIncreased == true){
				sleepTime = sleepTime - 50;
				delayTime = delayTime - 100;
				
			}
			
			
			seq.add(panelArr[rand.nextInt(panelArr.length)]);
			
			for (ColorPanel i : seq) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				i.pressed();
				playSound("beeper.wav");
				
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i.released();
			}
			
			seqItr = seq.iterator();
			
			
		}
		
	}
	
	private void gameOver() {
		gameOver = true;
		String msg = "Game Over";
		
		
		
		
		if (scorePanel.getHighScore() == scorePanel.getScore()){
			
			String name = JOptionPane.showInputDialog("You got the high score! \nEnter your name: ");
			
			scorePanel.nameArray.add(0, name);
		}
		
		for(int i = 0; i < scorePanel.highScoreArray.size(); ++i){
			if(scorePanel.score > scorePanel.highScoreArray.get(i)){
				
				scorePanel.highScoreArray.add(i, scorePanel.score);
				
				scorePanel.score = 0;
				scorePanel.scoreLabel.setText("Score: " + scorePanel.score);
				update(scorePanel.scoreLabel.getGraphics());
				++scorePanel.gameResets;
				
				
				
				return;
				
			}
			
		
		
		
		else if(scorePanel.getScore() < scorePanel.getHighScore()){
			
			for(int j = 0; j < scorePanel.highScoreArray.size(); ++j){
				
				if(scorePanel.getScore() >= scorePanel.highScoreArray.get(j)){
					
					msg = "You are number " + (j + 1) + " in the top ten high score list";
					JOptionPane.showMessageDialog(null, msg);
					
					String name = JOptionPane.showInputDialog("Enter your name");
					
					scorePanel.nameArray.add(j, name);
					scorePanel.highScoreArray.add(j, scorePanel.score);
					
					
					return;
				}
				
			}
			
		}
		
		}
		JOptionPane.showMessageDialog(this, msg);
	}
		
	
	
	public static void main(String[] args) {
		new SimonishMano();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gameOver = false;
		scorePanel.reset();
		seq.clear();
		Thread computerThread = new Thread(new ComputerRunnable());
		computerThread.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		playSound("beeper.wav");
		
		if (gameOver) return;
		
		ColorPanel tmp = ((ColorPanel)e.getSource());
		tmp.pressed();
		
		if (tmp != seqItr.next()) {
			gameOver();
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gameOver) return;
		
		ColorPanel tmp = ((ColorPanel)e.getSource());
		tmp.released();
		if (!seqItr.hasNext()) {
			scorePanel.incrScore();
			Thread computerThread = new Thread(new ComputerRunnable());
			computerThread.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}