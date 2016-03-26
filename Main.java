
import java.awt.*;
import java.awt.event.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.Random;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import com.jogamp.opengl.util.Animator;

public class Main implements GLEventListener {
	final static GLCanvas canvas = new GLCanvas();  
	final static Animator animator = new Animator(canvas);
	final static Frame frame = new Frame("Breakout");
	final static JLabel text = new JLabel();
	
	static FloatBuffer fb;
	static Random gen = new Random();

	static int windowX = 1150, windowY = 600;
	static float backdrop = 0.1f;

	static User user;
	static Paddle paddle;
	static Grid grid;
	static Shadow shadows;
	static Light light;
	
	static LinkedList<Ball> balls = new LinkedList<Ball>();
	static LinkedList<Bonus> bonuses = new LinkedList<Bonus>();
	static LinkedList<Particle> particles = new LinkedList<Particle>();

	public static float[] getNormal(float[] a, float[] b, float[] c) {
		float[] u = {a[0]-b[0], a[1]-b[1], a[2]-b[2]};
		float[] v = {a[0]-c[0], a[1]-c[1], a[2]-b[2]};

		float[] n = {(u[1]*v[2]-u[2]*v[1]), (u[2]*v[0]-u[0]*v[2]), (u[0]*v[1]-u[1]*v[0])};

		return n;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		gl.glClearColor(backdrop, backdrop, backdrop, 0.0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		shadows.draw();
		light.draw();
		gl.glRotatef(5, -10, -10, 1);
		paddle.draw();
		grid.draw();
		
		for (int i = 0; i < balls.size(); i++)
			balls.get(i).draw();

		for (int i = 0; i < bonuses.size(); i++)
			bonuses.get(i).draw();
		
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).draw();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GLLightingFunc.GL_NORMALIZE);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(16); 
		vbb.order(ByteOrder.nativeOrder());   
		fb = vbb.asFloatBuffer(); 

		user = new User();
		grid = new Grid(drawable, 10, 20);
		paddle = new Paddle(drawable);
		balls.add(new Ball(drawable));
		shadows = new Shadow(drawable);
		light = new Light(drawable);
		
		canvas.addKeyListener(paddle);
		canvas.addMouseMotionListener(paddle);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport(0, 0, windowX, windowY);
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION); 
		gl.glLoadIdentity();
		gl.glOrthof(0, windowX, 0, windowY, 100, -100);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {}

	public static void main(String[] args) {
		canvas.addGLEventListener(new Main());
		canvas.setSize(windowX, windowY);

		text.setOpaque(true);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.GRAY);

		text.setFont(new Font("Serif", Font.BOLD, 45));

		text.setSize(windowX, 100);

		frame.setSize(windowX, windowY+100);
		frame.add(canvas, SpringLayout.NORTH);
		frame.add(text, SpringLayout.SOUTH);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				animator.stop();
				frame.dispose();
				System.exit(0);
			}
		});
		frame.setVisible(true);
		animator.start();
		canvas.requestFocus();
	}
}