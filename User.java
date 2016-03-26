import java.awt.Color;

public class User {
	private int points = 0;
	private int multiplier = 1;
	private int lives = 3;
	
	public User() {
		setText();
	}
	
	public void setText() {
		String lives = "Lives: " + this.lives;
		String points = "Points: " + this.points;
		String space = "";
		
		for (int i = lives.length() + points.length(); i < 80; i++)
			space += " ";
		
		Main.text.setText(lives + space + points);
		Main.text.repaint();
	}
	
	public void setText(String str) {
		for (int i = str.length(); i < 80; i+=2)
			str = " " + str + " ";
		
		Main.text.setText(str);
		Main.text.repaint();
	}
	
	public void addPoints(int points) {
		this.points += multiplier * points;
		
		setText();
	}
	
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
		
		setText("You got a x" + multiplier + " multiplier!");
	}
	
	public void oneUp() {
		lives++;
		
		setText("You got an extra life!");
	}
	
	public void died() {
		lives--;
		
		setText("You lost a life!");
		
		if (lives == 0) {
			Main.frame.remove(Main.canvas);
			
			Main.text.setForeground(Color.RED);
			setText("YOU LOSE");
		}
	}
}
