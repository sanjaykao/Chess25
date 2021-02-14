package pieces;

public class Pawn implements Pieces{
	String loc;
	String color;
	boolean canEnpassant;
	
	public Pawn(String loc, String color) {
		this.loc = loc;
		this.color = color;
		this.canEnpassant = false;
	}
	
	public String getLoc() {
		return loc;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean canEnpassant() {
		return canEnpassant;
	}
	
	public void removeEnpassant() {
		this.canEnpassant = false;
	}
	
	public boolean isValid(String to) {
		int h = to.charAt(0) - loc.charAt(0);
		int v = to.charAt(1) - loc.charAt(1);
		int c = color.charAt(0);
		
		//regular move
		if(h == 0 && Math.abs(v) == 1) {
			if(canEnpassant) {
				canEnpassant = false;
			}
			//make sure pawns aren't going backwards
			if(c == 'w' && v == -1) {
				return false;
			} else if(c == 'b' && v == 1) {
				return false;
			}
			return true;
		}
		//first move -- can move 2 spaces (row 2 to row 4 OR row 7 to row 5)
		if(h == 0 && loc.charAt(1) == '2' && to.charAt(1) == '4') {
			canEnpassant = true;
			return true;
		} else if(h == 0 && loc.charAt(1) == '7' && to.charAt(1) == '5') {
			canEnpassant = true;
			return true;
		}
		
		return false;
	}
	
	public void move(String to) {
		loc = to;
	}
}
