import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Shadow {
	private final GL2 gl;
	
	private final float depth = 100;	
	private float[] project = new float[2];
	
	public Shadow(GLAutoDrawable drawable) {
		this.gl = drawable.getGL().getGL2();
	}
	
	public void draw() {
		gl.glPushMatrix();
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		for (int i = 0; i < Main.grid.size(); i++) {
			float[] pointA = {Main.grid.get(i).getLeft(), Main.grid.get(i).getBottom(), 0},
					pointB = {Main.grid.get(i).getLeft(), Main.grid.get(i).getTop(), 0},
					pointC = {Main.grid.get(i).getRight(), Main.grid.get(i).getTop(), 0},
					pointD = {Main.grid.get(i).getRight(), Main.grid.get(i).getBottom(), 0};
			
			gl.glBegin(GL2.GL_POLYGON); 
			project(pointA);
			gl.glVertex3f(project[0], project[1], depth);
			project(pointB);
			gl.glVertex3f(project[0], project[1], depth);
			project(pointC);
			gl.glVertex3f(project[0], project[1], depth);
			project(pointD);
			gl.glVertex3f(project[0], project[1], depth);
			gl.glEnd();
		}
		gl.glPopMatrix();   
	}
	
	public void project(float[] pt) {
		project[0] = pt[0]+pt[0]-Main.light.getX();
		project[1] = pt[1]+pt[1]-Main.light.getY();
	}
}
