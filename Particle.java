import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;


public class Particle {
	GL2 gl;
	
	private final float color[], ambient[], diffuse[], specular[], shininess;
	private final float size;
	
	private float x, y, z;
	private float velocity[] = new float[3];
	
	public Particle(GL2 gl, Brick brick, float x, float y) {
		this.gl = gl;
		
		this.color = brick.getColor();
		this.ambient = brick.getAmbient();
		this.diffuse = brick.getDiffuse();
		this.specular = brick.getSpecular();
		this.shininess = brick.getShininess();
		
		this.size = Main.gen.nextInt(5) + 1;
		
		this.x = brick.getX() + x;
		this.y = brick.getY() + y;
		this.z = 0;
		
		this.velocity[0] = Main.gen.nextInt(10) + 1;
		if (Main.gen.nextInt(2) == 0) velocity[0] *= -1;
		this.velocity[1] = Main.gen.nextInt(10) + 1;
		if (Main.gen.nextInt(2) == 0) velocity[1] *= -1;
		this.velocity[2] = Main.gen.nextInt(10) + 1;
		if (Main.gen.nextInt(2) == 0) velocity[2] *= -1;
	}
	
	public void move() {
		this.x = this.x + velocity[0] + 0.5f;
		this.y = this.y + velocity[1] + 0.5f;
		this.z = this.z + velocity[2] + 0.5f;
		
		if (x < 0 || x > Main.windowX || y < 0)
			Main.particles.remove(this);
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
		materials();
		
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		gl.glColor3f(color[0], color[1], color[2]);
		gl.glPointSize(size);
		gl.glBegin(GL.GL_POINTS);
		gl.glVertex3f(1.0f, 1.0f, 1.0f);
		gl.glEnd();
		gl.glPopMatrix();
	}
}
