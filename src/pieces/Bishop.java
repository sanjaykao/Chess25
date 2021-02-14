package pieces;

public class Bishop implements Pieces{
	String loc;
	String color;
	
	public Bishop(String loc, String color) {
		this.loc = loc;
		this.color = color;
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
		if(Math.abs(h) == Math.abs(v)) {
			return true;
		}
		return false;	
	}
	
	public void move(String to) {
		loc = to;
	}
}
