import javax.media.opengl.GLAutoDrawable;

public class MetalBrick extends Brick {
	public MetalBrick(GLAutoDrawable drawable, float x, float y, float length, float height) {
		super(drawable, x, y, length, height);

		this.color[0] = 0.5f;
		this.color[1] = 0.5f;
		this.color[2] = 0.5f;
		
		this.ambient[0] = 0.25f;
		this.ambient[1] = 0.25f;
		this.ambient[2] = 0.25f;
		this.ambient[3] = 1.0f;
		
		this.diffuse[0] = 0.4f;
		this.diffuse[1] = 0.4f;
		this.diffuse[2] = 0.4f;
		this.diffuse[0] = 1.0f;
		
		this.specular[0] = 0.774597f;
		this.specular[1] = 0.774597f;
		this.specular[2] = 0.774597f;
		this.specular[3] = 1.0f;

		this.resistance = 2.0f;
		this.value = 150;
		this.bonusChance = 0.20f;
	}
}
