import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLLightingFunc;


public class Light {
	private final GL2 gl;

	private float x, y, z;
	private final float brightness;

	private boolean crazy = false;

	public Light(GLAutoDrawable drawable) {
		this.gl = drawable.getGL().getGL2();

		this.x = Main.balls.get(0).getX();
		this.y = Main.balls.get(0).getY();
		this.z = -50f;

		this.brightness = 1.0f;
	}

	public void draw() {
		gl.glEnable(GLLightingFunc.GL_LIGHTING);
		gl.glEnable(GLLightingFunc.GL_LIGHT0);
		gl.glEnable(GLLightingFunc.GL_COLOR_MATERIAL);
		gl.glEnable(GLLightingFunc.GL_AMBIENT);
		gl.glEnable(GLLightingFunc.GL_DIFFUSE);
		gl.glEnable(GLLightingFunc.GL_SPECULAR);
		gl.glEnable(GLLightingFunc.GL_SHININESS);

		float[] diffuse = {brightness, brightness, brightness, 1f}; 
		float[] position = {this.getX(), this.getY(), this.getZ(), 1};  
		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, position, 0);
	}
	
	public void crazy() {
		if (crazy)
			crazy = false;
		else 
			crazy = true;
	}

	public float getX() {
		if (crazy) {
			if (Main.gen.nextInt(200000) % 199999 == 1)
				this.y = Main.gen.nextInt(Main.windowX);
				return this.x;
		} else 
			return Main.balls.get(0).getX();

	}

	public float getY() {
		if (crazy) {
			if (Main.gen.nextInt(200000) % 199999 == 1)
				this.y = Main.gen.nextInt(Main.windowY);
			return this.y;
		} else 
			return Main.balls.get(0).getY();
	}

	public float getZ() {
		if (crazy) 
			return z;
		else
			return z;
	}
}
