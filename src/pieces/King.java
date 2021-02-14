package pieces;

public class King implements Pieces{
	String loc;
	String color;
	boolean moved;
	
	public King(String loc, String color) {
		this.loc = loc;
		this.color = color;
		moved = false;
	}
	
	public String getLoc() {
		return loc;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean isValid(String to) {
		int h = to.charAt(0) - loc.charAt(0);
		int v = to.charAt(1) - loc.charAt(1);
		if(Math.abs(h) <= 1 && Math.abs(v) <= 1) {
			return true;
		}
		return false;
	}
	
	public void setMoved(boolean m) {
		moved = m;
	}
	
	public boolean moved() {
		return moved;
	}
	
	public void move(String to) {
		loc = to;
	}
}
