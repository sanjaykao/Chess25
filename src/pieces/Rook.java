package pieces;

public class Rook implements Pieces{
	String loc;
	String color;
	boolean moved;
	
	public Rook(String loc, String color) {
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
		if(to.charAt(0) == loc.charAt(0) || to.charAt(1) == loc.charAt(1)) {
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
