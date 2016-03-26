import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLLightingFunc;

import java.awt.event.*;

public class Paddle implements Object, MouseMotionListener, java.awt.event.KeyListener {
	final GL2 gl;
	float height = 20, length = 100, width = 50;
	float[] color = {1.0f, 0.0f, 0.0f};
	float[] ambient = {1.0f, 0.0f, 0.0f};
	float shininess = 0.5f;
	
	float x = 0, y = 0;
	
	public Paddle(GLAutoDrawable gLDrawable) {
		gl = gLDrawable.getGL().getGL2();
	}
	
	public void materials() {
		gl.glColor3f(color[0], color[1], color[2]);

		Main.fb.put(ambient);    
		Main.fb.position(0);   
		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, Main.fb);

		gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, (float)(shininess*128.0));
	}
	
	public void draw() {
		float[] frontA = {0, 0, 0}, 
				frontB = {0, height, -1}, 
				frontC = {length, height, -1}, 
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
		gl.glTranslatef(x, 0, 0);
		
		gl.glColor3f(color[0], color[1], color[2]);

	    gl.glBegin(GL2.GL_POLYGON); //FRONT
	    gl.glNormal3f(frontN[0], frontN[1], frontN[2]);
	    gl.glVertex3f(frontA[0], frontA[1], frontA[2]);
	    gl.glVertex3f(frontB[0], frontB[1], frontB[2]);
	    gl.glVertex3f(frontC[0], frontC[1], frontC[2]);
	    gl.glVertex3f(frontD[0], frontD[1], frontD[2]);
	    gl.glEnd();

	    gl.glBegin(GL2.GL_POLYGON); //LEFT
	    gl.glNormal3f(leftN[0], leftN[1], leftN[2]);
	    gl.glVertex3f(leftA[0], leftA[1], leftA[2]);
	    gl.glVertex3f(leftB[0], leftB[1], leftB[2]);
	    gl.glVertex3f(leftC[0], leftC[1], leftC[2]);
	    gl.glVertex3f(leftD[0], leftD[1], leftD[2]);
	    gl.glEnd();

	    gl.glBegin(GL2.GL_POLYGON); //TOP
	    gl.glNormal3f(topN[0], topN[1], topN[2]);
	    gl.glVertex3f(topA[0], topA[1], topA[2]);
	    gl.glVertex3f(topB[0], topB[1], topB[2]);
	    gl.glVertex3f(topC[0], topC[1], topC[2]);
	    gl.glVertex3f(topD[0], topD[1], topD[2]);
	    gl.glEnd();
	    
	    gl.glPopMatrix();   
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		this.mouseMoved(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		x = arg0.getX();
	}
	
	public void keyPressed(KeyEvent arg0) {
		Bonus.activate(arg0.getKeyCode());	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
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
		return height;
	}
	
	public float getBottom() {
		return 0;
	}
	
	public float getLength() {
		return length;
	}
	
	public float getWidth() {
		return -10;
	}
	
	public float getHeight() {
		return height;
	}
}
