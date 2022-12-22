package culminating;
/*
 * Brian
 * May 28, 2021
 * Board Class.
 */
public class Board {
	private Tile[][]a=new Tile[4][4];//grid for the tiles
	int score=0; //current score
	boolean tilesMove = false; //tracks if tiles moved on the board
	public Board() {
		for(int r=0;r<4;r++) { //fills array with tiles with 0 value
			for(int c=0;c<4;c++) {
				a[r][c] = new Tile(0);
			}
		}
		//spawns in two tiles
		spawn();
		spawn();
	}
	/*
	 * Returns the value of a tile on the board
	 * pre: 2 integers for row and column values
	 * post: returns value of tile
	 */
	public int getValue(int r, int c) {
		return a[r][c].getValue();
	}
	/*
	 * sets the value of a tile on the board
	 * pre: 3 integers for row, column, and the value to set the tile
	 */
	public void setValue(int r, int c, int v) {
		a[r][c].setValue(v);
	}
	/*
	 * spawns tile randomly on empty spot of the grid
	 * post: randomly spawns a tile with a value of 2
	 */
	public void spawn() {
		boolean spawn=false;
		while(spawn==false) {  //continues until a tile is spawned
			//generates random row and column for the tile
			int row=(int)(Math.random()*4);
			int col=(int)(Math.random()*4);
			if(a[row][col].getValue()==0) { //checks if the sopt is empty
				spawn=true;
				a[row][col].setValue(2);
				Tile.numTiles++;
			}}
	}
	/*
	 * slides tiles up,down,left, or right and combines the tiles in the direction they slide
	 * pre: integer for direction (up:d=1 down:d=2 left:d=3 right:d=4)
	 * post: tiles slide and get combined
	 */
	public void slide(int d) { 
		int x,y; //previous coordinates of the tile before they slide
		tilesMove=false; 
		if(d==1) {
			//slides tiles up
			for(int c=0;c<4;c++) {
				for(int r=0;r<4;r++) { //looks down the column
					if(a[r][c].getValue()>0) { //checks if there is a tile
						y=r;
						x=c;
						for(int row=r;row>=0;row--) { //check up the column
							if(a[row][c].getValue()==0) { //checks if there is an empty grid above
								a[row][c].setValue(a[y][x].getValue()); 
								a[y][x].setValue(0);
								tilesMove=true; //tile has moved
								y=row; //new y coordinate for the tile
							}
							if(row-1>=0) { //checks if there is a row above
								// combines tiles if tile above has the same value
								if(a[row-1][c].getValue()==a[row][c].getValue() && a[row-1][c].combined==false && a[row][c].combined==false) {
									a[row-1][c].setValue(2*a[row-1][c].getValue());
									a[row][c].setValue(0);
									a[row-1][c].combined=true; //sets combined variable to true
									score=score+a[row-1][c].getValue(); //adds value of newly created tile to score
									tilesMove=true; //tile has moved
									Tile.numTiles--; //tile is removed when combined
								}}
						}}}}}
		if(d==2) {
			//slides tiles down
			for(int c=0;c<4;c++) {
				for(int r=3;r>=0;r--) { //looks up the column
					if(a[r][c].getValue()>0) {  //checks if there is a tile
						y=r;
						x=c;
						for(int row=r;row<4;row++) { //check down the column
							if(a[row][c].getValue()==0) { //checks if there is an empty grid below
								a[row][c].setValue(a[y][x].getValue());
								a[y][x].setValue(0);
								tilesMove=true; //tile has moved
								y=row; //new y coordinate for the tile
							}
							if(row+1<4) { //checks if there is a row below
								// combines tiles if tile above has the same value
								if(a[row+1][c].getValue()==a[row][c].getValue() && a[row+1][c].combined==false && a[row][c].combined==false) { // combines tiles if tile above has the same value
									a[row+1][c].setValue(2*a[row+1][c].getValue());
									a[row][c].setValue(0);
									a[row+1][c].combined=true;
									score=score+a[row+1][c].getValue(); //adds value of newly created tile to score
									tilesMove=true; //tile has moved
									Tile.numTiles--; //tile is removed when combined
								}}
						}}}}}
		if(d==3) {
			//slides tiles to the left
			for(int r=0;r<4;r++) {
				for(int c=0;c<4;c++) { //looks to right of the row
					if(a[r][c].getValue()>0) {  //checks if there is a tile
						y=r;
						x=c;
						for(int col=c;col>=0;col--) { //check to left in the row
							if(a[r][col].getValue()==0) { //checks if there is an empty grid to the left
								a[r][col].setValue(a[y][x].getValue());
								a[y][x].setValue(0);
								tilesMove=true; //tile has moved
								x=col; //new x coordinate for the tile
							}
							if(col-1>=0) { //checks if there is a column to the left
								// combines tiles if tile above has the same value
								if(a[r][col-1].getValue()==a[r][col].getValue() && a[r][col-1].combined==false && a[r][col].combined==false) { // combines tiles if tile above has the same value
									a[r][col-1].setValue(2*a[r][col-1].getValue());
									a[r][col].setValue(0);
									a[r][col-1].combined=true;
									score=score+a[r][col-1].getValue(); //adds value of newly created tile to score
									tilesMove=true; //tile has moved
									Tile.numTiles--; //tile is removed when combined
								}}
						}}}}}
		if(d==4) {
			//slides tiles to the right
			for(int r=0;r<4;r++) {
				for(int c=3;c>=0;c--) { //looks to the left of the row
					if(a[r][c].getValue()>0) {  //checks if there is a tile
						y=r;
						x=c;
						for(int col=c;col<4;col++) { //check to right in the row
							if(a[r][col].getValue()==0) { //checks if there is an empty grid to the right
								a[r][col].setValue(a[y][x].getValue());
								a[y][x].setValue(0);
								tilesMove=true; //tile has moved
								x=col; //new x coordinate for the tile
							}
							if(col+1<4) { //checks if there is a column to the right
								// combines tiles if tile above has the same value
								if(a[r][col+1].getValue()==a[r][col].getValue() && a[r][col+1].combined==false && a[r][col].combined==false) { // combines tiles if tile above has the same value
									a[r][col+1].setValue(2*a[r][col+1].getValue());
									a[r][col].setValue(0);
									a[r][col+1].combined=true;
									score=score+a[r][col+1].getValue(); //adds value of newly created tile to score
									tilesMove=true; //tile has moved
									Tile.numTiles--; //tile is removed when combined
								}}
						}}}}
		}
		for(int r=0;r<4;r++) { //sets combined variable to false for all tiles
			for(int c=0;c<4;c++) {
				a[r][c].combined=false;
			}
		}
	}
	/*
	 * checks whether the player has won or lost
	 * post: returns 0 if game is not over, returns 1 if player has won, returns -1 if player has lost
	 */
	public int gameOver() {
		boolean cannotMove=true; //tiles can no longer slide
		for(int r=0;r<4;r++) {
			for(int c=0;c<4;c++) {
				if(a[r][c].getValue()==2048) { //player wins
					return 1;
				}
				//checks up
				if(r-1>=0) {
					if(a[r][c].getValue()==a[r-1][c].getValue()) {
						cannotMove=false;
					}
				}
				//checks down
				if(r+1<4) {
					if(a[r][c].getValue()==a[r+1][c].getValue()) {
						cannotMove=false;
					}
				}
				//checks left
				if(c-1>=0) {
					if(a[r][c].getValue()==a[r][c-1].getValue()) {
						cannotMove=false;
					}
				}
				//checks right
				if(c+1<4) {
					if(a[r][c].getValue()==a[r][c+1].getValue()) {
						cannotMove=false;
					}
				}
			}
		}
		if(Tile.numTiles==16 && cannotMove==true) { //player loses
			return -1;
		}
		return 0; //game continues
	}
}
