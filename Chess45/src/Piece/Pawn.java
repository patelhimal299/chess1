package Piece;
import chess.Board;
/**
 * Represents the Pawn piece
 * @author Srushti Patel(sap353)
 * @author Dhwani shah(dvs48)
 */
public class Pawn extends Piece {
	private boolean fmv = true;  
	private boolean dmv = false;  
	/**
	 * @param pn userInput piecename 
	 */
	public Pawn(String pn) {
		super(pn);
	}
	/**
	 * @return returns the dmv value
	 */
	public boolean isDoubleMove() {	
		return dmv;
	}
	public boolean checkMove(String move) {
		String[] userInput = move.split(" "); 
		int pc = Character.getNumericValue( userInput[0].charAt(0) )-10; 
		int pr = 8 - Character.getNumericValue( userInput[0].charAt(1) ); 
		int mc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		if(gainPieceName().equals("wp")) { 
			if(isPassant(move,true)) {	
				return true;
			}
			if((pc - 1 == mc  && pr - 1 == mr) || (pc + 1 == mc && pr - 1 == mr)) {  
				if(!Board.checkIndexValue(mr, mc) && Board.gainPieceName(mr, mc).charAt(0) == 'b') {  
					fmv = false;
					return true;	
				}
				return false;		
			}
			if(pr - 1 == mr && pc == mc) {   
				if(Board.checkIndexValue(pr-1, pc)) { 	
					fmv = false;
					return true;
				}
				return false;	
			}
			if(fmv) {  
				if(pr - 2 == mr && pc == mc) { 
					if(Board.checkIndexValue(pr-1, pc) && Board.checkIndexValue(pr-2, pc)) {  
						fmv = false;  
						dmv = true;	
						return true;
					}
					return false;							
				}
			}
		} else {  
			if(isPassant(move,false)) {	
				return true;
			}
			if((pc + 1 == mc  && pr + 1 == mr) || (pc - 1 == mc && pr + 1 == mr)) { 
				if(!Board.checkIndexValue(mr, mc) && Board.gainPieceName(mr, mc).charAt(0) == 'w') { 
					fmv = false;
					return true;	
				}
				return false;	
			}
			if(pr + 1 == mr && pc == mc) { 
				if(Board.checkIndexValue(pr+1, pc)) { 	
					fmv = false;
					return true;
				}
				return false;	
			}
			if(fmv) { 
				if(pr + 2 == mr && pc == mc) { 
					if(Board.checkIndexValue(pr+1, pc) && Board.checkIndexValue(pr+2, pc)) { 
						fmv = false;  
						dmv = true;	
						return true;
					}
					return false;							
				}
			}	
		}
		return false; 
	}
	/**
	 * @param move        
	 * inputed by user
	 * @param isWhiteMove insert true if it is white's turn, false if it is black's turn
	 * @return return true if pawn is making an passant move
	 */
	public boolean isPassant(String move, boolean isWhiteMove) {
		String[] userInput = move.split(" ");  
		int pc = Character.getNumericValue( userInput[0].charAt(0) )-10; 
		int pr = 8 - Character.getNumericValue( userInput[0].charAt(1) ); 
		int mc = Character.getNumericValue( userInput[1].charAt(0) )-10;
		int mr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		if(isWhiteMove) {
			if(Board.checkIndexBound(mr, mc) && Board.checkIndexValue(mr, mc) && pr - 1 == mr && pc + 1 == mc) {
				if(pr == 3 && !Board.checkIndexValue(pr, pc + 1) && Board.gainPieceName(pr, pc + 1).equals("bp")) {
					Pawn blackPawn = (Pawn)Board.gainPiece(pr, pc + 1);
					if(blackPawn.isDoubleMove()) { 
						Board.setIndexValue(pr, pc + 1); 
						return true;		
					}	
				}	
			}
			if(Board.checkIndexBound(mr, mc) && Board.checkIndexValue(mr, mc) && pr - 1 == mr && pc - 1 == mc) {
				if(pr == 3 && !Board.checkIndexValue(pr, pc - 1) && Board.gainPieceName(pr, pc - 1).equals("bp")) {
					Pawn blackPawn = (Pawn)Board.gainPiece(pr, pc - 1);
					if(blackPawn.isDoubleMove()) { 
						Board.setIndexValue(pr, pc - 1); 
						return true;		
					}	
				}	
			}
		} else { 
			if(Board.checkIndexBound(mr, mc) && Board.checkIndexValue(mr, mc) && pr + 1 == mr && pc - 1 == mc) {
				if(pr == 4 && !Board.checkIndexValue(pr, pc - 1) && Board.gainPieceName(pr, pc - 1).equals("wp")) {
					Pawn whitePawn = (Pawn)Board.gainPiece(pr, pc - 1);
					if(whitePawn.isDoubleMove()) {  
						Board.setIndexValue(pr, pc - 1);  
						return true;		
					}	
				}	
			}
			if(Board.checkIndexBound(mr, mc) && Board.checkIndexValue(mr, mc) && pr + 1 == mr && pc + 1 == mc) {
				if(pr == 4 && !Board.checkIndexValue(pr, pc + 1) && Board.gainPieceName(pr, pc + 1).equals("wp")) {
					Pawn whitePawn = (Pawn)Board.gainPiece(pr, pc + 1);
					if(whitePawn.isDoubleMove()) {  
						Board.setIndexValue(pr, pc + 1); 
						return true;		
					}	
				}	
			}	
		}
		return false; 
	}
	@Override
	public boolean isCheck(String move) {
		String[] userInput = move.split(" "); 
		int mc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		if(gainPieceName().equals("wp")) { 
			if(Board.checkIndexBound(mr - 1, mc + 1) && !Board.checkIndexValue(mr - 1, mc + 1) && Board.gainPieceName(mr - 1, mc + 1).equals("bK")) {
				return true;	
			}
			if(Board.checkIndexBound(mr - 1, mc - 1) && !Board.checkIndexValue(mr - 1, mc - 1) && Board.gainPieceName(mr - 1, mc - 1).equals("bK")) {
				return true;  
			}
		} else { 
			if(Board.checkIndexBound(mr + 1, mc - 1) && !Board.checkIndexValue(mr + 1, mc - 1) && Board.gainPieceName(mr + 1, mc - 1).equals("wK")) {	
				return true;
			}
			if(Board.checkIndexBound(mr + 1, mc + 1) && !Board.checkIndexValue(mr + 1, mc + 1) && Board.gainPieceName(mr + 1, mc + 1).equals("wK")) {	
				return true;
			}	
		}			
		return false;
	}
}