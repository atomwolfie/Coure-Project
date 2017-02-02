

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {
	private JButton startBtn = new JButton("Start");
	protected int highScore;
	protected ArrayList<Integer> highScoreArray = new ArrayList<Integer>();
	protected ArrayList<String> nameArray = new ArrayList<String>();
	protected int score;
	protected int allScores = 0;
	protected int gameResets = 0;
	private JLabel highScoreLabel;
	protected JLabel scoreLabel;
	protected boolean speedIncreased = false;
	
	
	public ScorePanel() {
		scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
		highScoreLabel = new JLabel("High Score: " + highScore, SwingConstants.CENTER);
		
		
		setLayout(new GridLayout(1, 3));
	
		add(scoreLabel);
		add(startBtn);
		add(highScoreLabel);		
		
		
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		highScoreArray.add(0);
		
		nameArray.add("Aaron");
		nameArray.add("Zach");
		nameArray.add("Kami");
		nameArray.add("Cody");
		nameArray.add("Ryan");
		nameArray.add("Johnny");
		nameArray.add("Kyle");
		nameArray.add("Joe");
		nameArray.add("Jess");
		nameArray.add("Mike");
		
	}
	
	
	public JButton getStartBtn() {
		return startBtn;
	}
	
	public void reset() {
		
		score = 0;
		scoreLabel.setText("Score: " + score);
		update(scoreLabel.getGraphics());
		++gameResets;
		
			}
		

		

	
	public void incrScore() {
		score++;
		allScores++;
		scoreLabel.setText("Score: " + score);
		update(scoreLabel.getGraphics());
		checkHighScore();
		
	}
	
	protected void checkHighScore() {
		if (score > highScore) {
			highScore = score;
			highScoreLabel.setText("High Score: " + highScore);
			this.update(this.getGraphics());
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public int getHighScore() {
		return highScore;
	}
}
