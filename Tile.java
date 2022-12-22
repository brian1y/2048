package culminating;
/*
 * Brian
 * May 27, 2021
 * Tile Class.
 */
public class Tile {
	private int value; //value of the tile
	boolean combined=false; //set to true if tile has recently been combined with another tile
	static int numTiles=0; // number of tiles on the board
	public Tile(int v) {
		value=v;
		if(v>=2) {numTiles++;}
	}
	public int getValue() {
		return value;
	}
	public void setValue(int v) {
		value=v;
	}
	/*
	 * Returns a String that represents the tile object.
	 * post: A string representing the tile object has
	 * been returned.
	 */
	public String toString() {
		String ts;
		ts = "The tile has the value "+value;
		return ts;
	}
	/*
	 * Determines if the object is equal to another tile object
	 * pre: tile object
	 * post: true has been returned if objects have the same
	 * value and name. false returned otherwise.
	 */
	public boolean equals(Object t) {
		Tile testObj = (Tile)t;
		if (testObj.getValue() == value) {
			return true;
		} 
		else {
			return false;
		}
	}

}
