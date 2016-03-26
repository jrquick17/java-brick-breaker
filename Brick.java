import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLLightingFunc;

public class Brick implements Object {
	private final GL2 gl;

	private float x, y;
	protected final float length, width = 50, height;

	protected float color[] = new float[3];
	protected float ambient[] = new float[4];
	protected float diffuse[] = new float[4];
	protected float specular[] = new float[4];
	protected float shininess = 1.0f;

	protected float resistance = 0.20f;
	protected int value = 25;
	protected float bonusChance = 0.15f;

	public Brick(GLAutoDrawable drawable, float x, float y, float length, float height) {
		this.gl = drawable.getGL().getGL2();

		this.x = x;
		this.y = y;
		this.length = length;
		this.height = height;

		this.color[0] = Main.gen.nextFloat();
		this.color[1] = Main.gen.nextFloat();
		this.color[2] = Main.gen.nextFloat();

		this.ambient[0] = Main.gen.nextFloat();
		this.ambient[1] = Main.gen.nextFloat();
		this.ambient[2] = Main.gen.nextFloat();

		this.diffuse[0] = Main.gen.nextFloat();
		this.diffuse[1] = Main.gen.nextFloat();
		this.diffuse[2] = Main.gen.nextFloat();

		this.specular[0] = Main.gen.nextFloat();
		this.specular[1] = Main.gen.nextFloat();
		this.specular[2] = Main.gen.nextFloat();
	}

	public void dropBonus() {
		if (Main.gen.nextFloat() < this.bonusChance) 
			Main.bonuses.add(new Bonus(gl, this.x, this.y));
	}
	
	public void drop() {
		this.y -= 25;
	}

	public void particlize() {
		for (int x = 0; x < this.length; x+=2) {
			for (int y = 0; y < this.length; y+=2) {
				if (Main.particles.size() > 2000)
					Main.particles.remove(Main.gen.nextInt(Main.particles.size()));
				Main.particles.add(new Particle(gl, this, x, y));
			}
		}
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
		float[] frontA = {0, 0, 0}, 
				frontB = {0, height, 0}, 
				frontC = {length, height, 0}, 
				frontD = {length, 0, 0},
				frontN = Main.getNormal(frontA, frontB, frontC);

		float[] leftA = {0, 0, 0},
				leftB = {0, 0, width},
				leftC = {0, height, width},
				leftD = {0, height, 0},
				leftN = Main.getNormal(leftA, leftB, leftC);

		float[] topA = {0, height, 0},
				topB = {0, height, width},
				topC = {length, height, width},
				topD = {length, height, 0},
				topN = Main.getNormal(topA, topB, topC);

		gl.glPushMatrix();
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_FILL);
		gl.glTranslatef(x, y, 0);

		materials();

		gl.glBegin(GL2.GL_POLYGON); //FRONT
		gl.glNormal3d(frontN[0], frontN[1], frontN[2]);
		gl.glVertex3f(frontA[0], frontA[1], frontA[2]);
		gl.glVertex3f(frontB[0], frontB[1], frontB[2]);
		gl.glVertex3f(frontC[0], frontC[1], frontC[2]);
		gl.glVertex3f(frontD[0], frontD[1], frontD[2]);
		gl.glEnd();

		gl.glBegin(GL2.GL_POLYGON); //LEFT
		gl.glNormal3d(leftN[0], leftN[1], leftN[2]);
		gl.glVertex3f(leftA[0], leftA[1], leftA[2]);
		gl.glVertex3f(leftB[0], leftB[1], leftB[2]);
		gl.glVertex3f(leftC[0], leftC[1], leftC[2]);
		gl.glVertex3f(leftD[0], leftD[1], leftD[2]);
		gl.glEnd();

		gl.glBegin(GL2.GL_POLYGON); //TOP
		gl.glNormal3d(topN[0], topN[1], topN[2]);
		gl.glVertex3f(topA[0], topA[1], topA[2]);
		gl.glVertex3f(topB[0], topB[1], topB[2]);
		gl.glVertex3f(topC[0], topC[1], topC[2]);
		gl.glVertex3f(topD[0], topD[1], topD[2]);
		gl.glEnd();

		gl.glPopMatrix();   
	}
	
	public float getResistance() {
		return this.resistance;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public float[] getColor() {
		return this.color;
	}
	
	public float[] getAmbient() {
		return this.ambient;
	}
	
	public float[] getDiffuse() {
		return this.diffuse;
	}
	
	public float[] getSpecular() {
		return this.specular;
	}
	
	public float getShininess() { 
		return this.shininess;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getLeft() {
		return x;
	}

	public float getRight() {
		return x + length;
	}

	public float getTop() {
		return y + height;
	}

	public float getBottom() {
		return y;
	}

	public float getLength() {
		return length;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}