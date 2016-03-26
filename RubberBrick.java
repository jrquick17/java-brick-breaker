import javax.media.opengl.GLAutoDrawable;


public class RubberBrick extends Brick {
	public RubberBrick(GLAutoDrawable drawable, int x, int y, int length, int height) {
		super(drawable, x, y, length, height);
		
		this.color[0] = 0.0f;
		this.color[1] = 0.0f;
		this.color[2] = 0.0f;

		this.ambient[0] = 0.02f;
		this.ambient[1] = 0.02f;
		this.ambient[2] = 0.02f;
		
		this.diffuse[0] = 0.01f;
		this.diffuse[1] = 0.01f;
		this.diffuse[2] = 0.01f;
		
		this.specular[0] = 0.4f;
		this.specular[1] = 0.4f;
		this.specular[2] = 0.4f;
		
		this.resistance = -1.0f;
		this.value = 100;
		this.bonusChance = 0.10f;
	}
	
}
