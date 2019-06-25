import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Bonus implements Object {
	private final GL2 gl;
	private final GLU glu;
	private final GLUquadric quad;

	private final int radius = 10, precision = 50;

	private final float color[] = new float[3];
	private final float ambient[] = new float[4];
	private final float diffuse[] = new float[4];
	private final float specular[] = new float[4];
	private final float shininess;

	private float x, y;
	private final float move[] = {0, -2};

	public Bonus(GL2 gl, float x, float y) {
		this.gl = gl;
		this.glu = new GLU();
		quad = glu.gluNewQuadric();

		this.x = x;
		this.y = y;

		this.color[0] = 0.71f;
		this.color[1] = 0.8f;
		this.color[2] = 0.31f;

		this.ambient[0] = 0.24725f;
		this.ambient[1] = 0.1995f;
		this.ambient[2] = 0.0745f;
		this.ambient[3] = 1.0f;

		this.diffuse[0] = 0.75164f;
		this.diffuse[1] = 0.60648f;
		this.diffuse[2] = 0.22648f;
		this.diffuse[3] = 1.0f;

		this.specular[0] = 0.628281f;
		this.specular[1] = 0.555802f;
		this.specular[2] = 0.366065f;
		this.specular[3] = 1.0f;

		this.shininess = 1.0f;
	}

	public void collect() {
		Main.bonuses.remove(this);
		Main.user.addPoints(300);

		float num = Main.gen.nextInt(13);

		if (num == 0) {
			activate(83);
		} else if (num == 1) {
			activate(37);
		} else if (num == 2) {
			activate(38);
		} else if (num == 3) {
			activate(39);
		} else if (num == 4) {
			activate(40);
		} else if (num == 5) {
			activate(66);
		} else if (num == 6) {
			activate(68);
		} else if (num == 7) {
			Main.user.setMultiplier(3);
		} else if (num == 8) {
			Main.user.setMultiplier(2);
		} else if (num == 9) {
			Main.user.setMultiplier(1);
		} else if (num == 10) {
			activate(70);
		} else if (num == 11) {
			activate(71);
		} else if (num == 12) {
			activate(72);
		} else {
			Main.user.oneUp();
		}
	}

	public static void activate(int key) {
		switch (key) {
		case 32: //SPACE
			Main.balls.get(0).start();
			break;
		case 37:  //LEFT
			Main.paddle.length *= .5f;
			Main.user.setText("You've been shrunk!");
			break;
		case 38: //UP
			for (int i = 0; i < Main.balls.size(); i++) {
				Main.balls.get(i).speedUp();
			}
			Main.user.setText("Things are speeding up!");
			break;
		case 39: //RIGHT
			Main.paddle.length *= 1.5f;
			Main.user.setText("You're getting larger");
			break;
		case 40: //DOWN
			for (int i = 0; i < Main.balls.size(); i++) {
				Main.balls.get(i).slowDown();
			}
			Main.user.setText("Time to slow your roll.");
			break;
		case 66: //B
			if (Main.backdrop < 1.0f) 
				Main.backdrop += 0.1;
			Main.user.setText("It sure is getting bright.");
			break;
		case 68: //D
			if (Main.backdrop > 0.1f) 
				Main.backdrop -= 0.1;
			Main.user.setText("Who turned down the lights?");
			break;
		case 70: //F
			for (int i = 0; i < Main.balls.size(); i++)
				Main.balls.get(i).shrink();
			Main.user.setText("You can barely even see those balls!");
			break;
		case 71: //G
			for (int i = 0; i < Main.balls.size(); i++)
				Main.balls.get(i).grow();
			Main.user.setText("Them balls are getting mighty large.");
			break;
		case 72: //H
			if (Main.grid.get(0).getY() > 100)
				for (int i = 0; i < Main.grid.size(); i++)
					Main.grid.get(i).drop();
			Main.user.setText("Drop them!");
			break;
		case 76: //L
			Main.light.crazy();
			Main.user.setText("Who is messing with the lights?");
			break;
		case 83: //S
			for (int i = 0; i < Main.balls.size(); i++)
				Main.balls.get(i).split = true;
			Main.user.setText("Split 'em up boys!");
			break;
		case 90: //Z
			Main.grid.toGold();
			break;
		}
	}

	public void move() {
		if (Collision.collides(this, Main.paddle))
			this.collect();

		x = x + move[0];
		y = y + move[1];

		if (y < 0)
			Main.bonuses.remove(this);
	}

	public void materials() {
		gl.glColor3f(color[0], color[1], color[2]);

		Main.fb.put(ambient);    
		Main.fb.position(0);   
		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, Main.fb);

		Main.fb.put(diffuse);    
		Main.fb.position(0);  
		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_DIFFUSE, Main.fb);

		Main.fb.put(specular);    
		Main.fb.position(0);  
		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, Main.fb);

		gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, (float)(shininess*128.0));
	}

	public void draw() {
		move();

		gl.glPushMatrix();
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_FILL);
		gl.glTranslatef(x, y, radius*4);

		this.materials();

		glu.gluSphere(quad, radius, precision, precision);
		gl.glPopMatrix();   
	}


	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getLeft() {
		return x - radius;
	}

	public float getRight() {
		return x + radius;
	}

	public float getTop() {
		return y + radius;
	}

	public float getBottom() {
		return y - radius;
	}

	public float getLength() {
		return radius*2;
	}

	public float getWidth() {
		return radius*2;
	}

	public float getHeight() {
		return radius*2;
	}
}
