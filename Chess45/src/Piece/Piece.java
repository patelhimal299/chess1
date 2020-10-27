package Piece;

/**
 * Abstract class for all the pieces
 * @author Dhwani shah(dvs48)
 * @author Srushti patel(sap353)
 */
public abstract class Piece {
	private String pn;
	
	/**
	 * @param pn userinput the piecename
	 */
	public Piece(String pn) {  
		this.pn = pn;
	}
	/**
	 * @param move
	 * inputed by user
	 * @return returns true: if move is legal, 
	 * @return false if move is not legal
	 */
	public abstract boolean checkMove(String move);
	/**
	 * @param move inputed by user
	 * @return return true: in check, if the piece puts King of the opposite color 
	 */
	public abstract boolean isCheck(String move);
	/**
	 * @return returns the piecename
	 */
	public String gainPieceName() {
		return pn;
	}
	public String toString() {
		return pn;
	}
}