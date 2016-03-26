import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Ball implements Object {
	private final GL2 gl;
	private final GLU glu;
	private final GLUquadric quad;

	private final float standardRadius = 8;
	private final int precision = 50;
	private final float color[] = {.22f, .67f, .94f};
	public final float minSpeed = 3;

	private float radius;

	private float x = 500, y = 200;
	private float move[] = {0, 0};

	boolean split = false;


	public Ball(GLAutoDrawable drawable) {
		this.gl = drawable.getGL().getGL2();
		this.glu = new GLU();
		this.quad = glu.gluNewQuadric();

		this.radius = this.standardRadius;
	}

	public Ball(GL2 gl, float radius) {
		this.gl = gl;
		this.glu = new GLU();
		this.quad = glu.gluNewQuadric();

		this.radius = radius;
	}

	public void split() {
		split = false;
		Ball ball = new Ball(this.gl, this.radius);

		ball.x = this.x + this.radius*2 + 5;
		if (this.y > 1)
			ball.y = this.y + 1;
		else 
			ball.y = this.y - 1;

		ball.move[0] = this.move[0]*-1;
		ball.move[1] = this.move[1];

		Main.balls.add(ball);
	}

	public void slowDown() {
		if (this.move[1] > this.minSpeed + 1)
			this.move[1]--;
		else if (this.move[1] > -1*this.minSpeed + 1)
			this.move[1]++;
	}

	public void speedUp() {
		if (this.move[1] > 0)
			this.move[1]++;
		else 
			this.move[1]--;
	}

	public void start() {
		this.move[1] = -1*this.minSpeed;
	}

	public void reset() {
		Main.balls.add(this);

		this.move[0] = 0;
		this.move[1] = 0;

		this.x = 500;
		this.y = 200;
	}

	public void move() {
		if (Collision.collides(this, Main.paddle)) { //HITS PADDLE
			move[0] = Collision.collidesAngle(this, Main.paddle);
			move[1] *= -1;
		} else if (this.getTop() >= Main.windowY) { //HITS ROOF
			move[1] *= -1;
		} else if (this.getLeft() <= 0 || this.getRight() >= Main.windowX) { //HIT WALL
			move[0] *= -1;
		} 

		for (int i = 0; i < Main.balls.size(); i++) {
			if (this != Main.balls.get(i)) {
				if (Collision.collides(this, Main.balls.get(i))) {
					if (Collision.fromY(this, Main.balls.get(i))) {
						this.move[1] *= -1;
					} else {
						this.move[0] *= -1;
					}
				}
			}
		}

		move = Main.grid.hit(this);

		x = x + move[0];
		y = y + move[1];

		if (this.y < 0) {
			Main.balls.remove(this);
			if (Main.balls.size() == 0) {
				Main.user.died();
				this.reset();
			}
		}
	}

	public void draw() {
		if (split) split();
		move();

		gl.glPushMatrix();
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_FILL);
		gl.glTranslatef(x, y, radius*2);

		gl.glColor3f(color[0], color[1], color[2]);
		glu.gluSphere(quad, radius, precision, precision);
		gl.glPopMatrix();   
	}

	public boolean bounce(float resistance) {
		if (move[1] > 2)
			move[1] -= resistance;
		else if (move[1] < -2) 
			move[1] += resistance;
		else 
			return true;

		return false;
	}

	public void shrink() {
		if (this.radius * 0.65f > 2)
			this.radius *= 0.65f;
	}

	public void grow() {
		if (this.radius * 1.5f < 40)
			this.radius *= 1.5f;
	}

	public void reverseY() {
		this.move[1] *= -1;
	}

	public void reverseX() {
		this.move[0] *= -1;
	}

	public float[] getMove() {
		return this.move;
	}

	public float getSpeed() {
		if (this.move[1] > 0)
			return this.move[1];
		else
			return -1*this.move[1];
	}

	public void lowerStrength(float amount) {
		if (this.move[1] - amount > minSpeed)
			this.move[1] -= amount;
		else if (this.move[1] + amount < -1*minSpeed)
			this.move[1] += amount;
		else if (this.move[1] > 0)
			this.move[1] = this.minSpeed;
		else 
			this.move[1] = -1*this.minSpeed;
	}

	public float getStrength() {
		return this.radius/this.standardRadius - 1 + this.getSpeed() - this.minSpeed;
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

	public float getRadius() {
		return radius;
	}
}
