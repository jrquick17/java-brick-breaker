import java.util.LinkedList;

import javax.media.opengl.GLAutoDrawable;


public class Grid {
	private final GLAutoDrawable drawable;

	private final int space = 3, bottomGap = 350, topGap = 50;
	private final int length, height;
	private LinkedList<Brick> bricks;

	public Grid(GLAutoDrawable drawable, int rows, int columns) {
		this.drawable = drawable;

		this.length = Main.windowX;
		this.height = Main.windowY - bottomGap;

		createBricks(rows, columns);
	}

	private void createBricks(int rows, int columns) {
		bricks = new LinkedList<Brick>();

		int l = length/columns, h = height/rows;

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				float num = Main.gen.nextFloat();
				
				if (num < .7)
					bricks.add(new Brick(drawable, c*l, r*h+bottomGap-topGap, l-space, h-space));
				else if (num < .8)
					bricks.add(new MetalBrick(drawable, c*l, r*h+bottomGap-topGap, l-space, h-space));
				else if (num < .9)
					bricks.add(new RubberBrick(drawable, c*l, r*h+bottomGap-topGap, l-space, h-space));
				else 
					bricks.add(new BonusBrick(drawable, c*l, r*h+bottomGap-topGap, l-space, h-space));
			}
		}
	}
	
	public void toGold() {
		for (int i = 0; i < this.bricks.size(); i++) {
			bricks.remove(i);
			bricks.add(i,new BonusBrick(drawable, bricks.get(i).getX(), bricks.get(i).getY(),
					bricks.get(i).getLength(), bricks.get(i).getHeight()));
		}
	}

	public float[] hit(Ball ball) {
		Brick brick = null;
		float move[] = ball.getMove();

		for (int i = 0; i < bricks.size(); i++) {
			if (Collision.collides(ball, bricks.get(i))) {
				brick = bricks.remove(i);
				break;
			}
		}

		if (brick == null) 
			return move;

		if (Collision.collides(ball, brick)) {
			if (ball.getStrength() > brick.getResistance() & brick.getResistance() >= 0)
				ball.lowerStrength(brick.getResistance());
			else 
				ball.reverseY();
		} 
		
		if (brick.getResistance() < 0) {
			ball.speedUp();
		}

		bricks.remove(brick);
		
		Main.user.addPoints(brick.getValue());
		brick.dropBonus();
		brick.particlize();

		return move;
	}

	public void draw() {
		for (int i = 0; i < bricks.size(); i++)
			bricks.get(i).draw();
	}

	public int size() {
		return this.bricks.size();
	}

	public Brick get(int i) {
		return this.bricks.get(i);
	}
}
