import javax.media.opengl.GLAutoDrawable;

public class BonusBrick extends Brick implements Object {
	public BonusBrick(GLAutoDrawable drawable, float x, float y, float length, float height) {
		super(drawable, x, y, length, height);
		
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

		this.resistance = 0.5f;
		this.value = 10;
		this.bonusChance = 1.0f;
	}
}
