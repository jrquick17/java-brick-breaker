
public class Timer {
	long start = 0, stop = 0; 
	public Timer() {
		
	}
	
	public void start() {
		start = System.currentTimeMillis();
		stop = 0;
	}
	
	public void stop() {
		stop = System.currentTimeMillis();
	}
	
	public float elapsed() {
		if (this.stop != 0)
			return this.stop - this.start;
		else 
			return System.currentTimeMillis() - this.start;
	}
	
	public void reset() {
		this.start = 0;
		this.stop = 0;
	}
}
