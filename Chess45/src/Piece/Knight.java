package Piece;
import chess.Board;
/**
 * Represents the Knight piece
 * @author Srushti Patel(sap353)
 * @author Dhwani shah(dvs48)
 */
public class Knight extends Piece {
	/**
	 * @param pn userInput the piecename
	 */
	public Knight(String pn) {
		super(pn);
	}
	@Override
	public boolean checkMove(String mv) { 
		
		String[] userInput = mv.split(" ");  
		int cp = Character.getNumericValue( userInput[0].charAt(0) )-10; 
		int rp = 8 - Character.getNumericValue( userInput[0].charAt(1) ); 
		int mvc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mvr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		if((cp + 2 == mvc) || (cp - 2 == mvc)) {  
			if((rp - 1 == mvr) || (rp + 1 == mvr)) {  
				return MoveLocation(mv);  
			}
		}
		if((rp + 2 == mvr) || (rp - 2 == mvr)) {  
			if((cp + 1 == mvc) || (cp - 1 == mvc)) {  
				return MoveLocation(mv);  
			}
		}
		return false;  
	}
	/**
	 * @param mv 
	 * inputed by user
	 * @return return true: if mv location is contains piece of opposite color or empty, else 
	 * @return return false
	 */
	public boolean MoveLocation(String mv) {
		
		String[] userInput = mv.split(" ");  
		int cp = Character.getNumericValue( userInput[0].charAt(0) )-10; 
		int rp = 8 - Character.getNumericValue( userInput[0].charAt(1) ); 
		int mvc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mvr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		if(Board.checkIndexValue(mvr, mvc)) {  
			return true;  
		}
		if(Board.gainPieceName(mvr, mvc).charAt(0) != Board.gainPieceName(rp, cp).charAt(0)) {  
			return true; 
		}
		return false; 
	}

	@Override
	public boolean isCheck(String mv) {   
		String[] userInput = mv.split(" ");  
		int mvc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mvr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		if(Board.checkIndexBound
		(mvr - 2, mvc + 1) && !Board.checkIndexValue(mvr - 2, mvc + 1)) {  
			if(Board.gainPieceName(mvr - 2, mvc + 1).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr - 2, mvc + 1).charAt(1) == 'K') {
				return true;
			}	
		}
		if(Board.checkIndexBound
		(mvr - 2, mvc - 1) && !Board.checkIndexValue(mvr - 2, mvc - 1)) {  
			
			if(Board.gainPieceName(mvr - 2, mvc - 1).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr - 2, mvc - 1).charAt(1) == 'K') {
				return true;
			}	
		}
		if(Board.checkIndexBound
		(mvr + 2, mvc + 1) && !Board.checkIndexValue(mvr + 2, mvc + 1)) {  
			if(Board.gainPieceName(mvr + 2, mvc + 1).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr + 2, mvc + 1).charAt(1) == 'K') {
				return true;
			}
		}
		
		if(Board.checkIndexBound
		(mvr + 2, mvc - 1) && !Board.checkIndexValue(mvr + 2, mvc - 1)) {  
			if(Board.gainPieceName(mvr + 2, mvc - 1).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr + 2, mvc - 1).charAt(1) == 'K') {
				return true;
			}
		}
		if(Board.checkIndexBound
		(mvr - 1, mvc - 2) && !Board.checkIndexValue(mvr - 1, mvc - 2)) {  
			
			if(Board.gainPieceName(mvr - 1, mvc - 2).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr - 1, mvc - 2).charAt(1) == 'K') {
				return true;
			}
		}
		if(Board.checkIndexBound
		(mvr + 1, mvc - 2) && !Board.checkIndexValue(mvr + 1, mvc - 2)) { 
			
			if(Board.gainPieceName(mvr + 1, mvc - 2).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr + 1, mvc - 2).charAt(1) == 'K') {
				return true;
			}
		}
		if(Board.checkIndexBound
		(mvr - 1, mvc + 2) && !Board.checkIndexValue(mvr - 1, mvc + 2)) { 
			if(Board.gainPieceName(mvr - 1, mvc + 2).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr - 1, mvc + 2).charAt(1) == 'K') {
				return true;
			}
		}
		if(Board.checkIndexBound
		(mvr + 1, mvc + 2) && !Board.checkIndexValue(mvr + 1, mvc + 2)) {  
			
			if(Board.gainPieceName(mvr + 1, mvc + 2).charAt(0) != gainPieceName().charAt(0) && Board.gainPieceName(mvr + 1, mvc + 2).charAt(1) == 'K') {
				return true;
			}
		}
		return false;
	}

}