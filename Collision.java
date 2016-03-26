
public class Collision {
	public static boolean collides(Object a, Object b) {
		if (a.getBottom() <= b.getTop() && a.getBottom() >= b.getBottom() || a.getTop() >= b.getBottom() && a.getBottom() < b.getBottom()) 
			if (a.getRight() > b.getLeft() && a.getLeft() < b.getRight()) 
				return true;

		return false;
	}

	public static float collidesAngle(Object a, Object b) {
		float move = 0;

		move = (a.getX() - b.getLeft())/b.getLength();

		if (move < .52 && move > .48)
			move = 0;
		else if (move <= .48) 
			move = (move/.45f)-1;
		else if (move >= .52) 
			move = (move-.55f)/.45f;

		return move;
	}

	public static boolean fromY(Object a, Object b) {
		float fromTop = Math.abs(Math.abs(a.getBottom()) - Math.abs(b.getTop()));
		float fromBottom = Math.abs(Math.abs(a.getTop()) - Math.abs(b.getBottom()));
		float fromLeft = Math.abs(Math.abs(a.getRight()) - Math.abs(b.getLeft()));
		float fromRight = Math.abs(Math.abs(a.getLeft()) - Math.abs(b.getRight()));

		float top, side;
		if (fromTop < fromBottom) {
			top = fromTop;
		} else {
			top = fromBottom;
		}

		if (fromLeft < fromRight) {
			side = fromLeft;
		} else {
			side = fromRight;
		}

		if (top < side) 
			return true;
		else
			return false;
	}
}
