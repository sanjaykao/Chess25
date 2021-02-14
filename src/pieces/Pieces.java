package pieces;

public interface Pieces {
	String getLoc();
	String getColor();
	boolean isValid(String s);
	void move(String s);
}
