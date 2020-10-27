package Piece;

import chess.Board;

/**
 *  Rook piece
 * @author Dhwani shah(dvs48)
 * @author Srushti Patel(sap353)
 */
public class Rook extends Piece{

	boolean hasMoved = false;
	/**
	 * @param pn userInput piecename
	 */
	public Rook(String pn) {
		
		super(pn);
	}

	@Override
	public boolean checkMove(String move) {
		
		String[] userInput = move.split(" "); 
		
		int cp = Character.getNumericValue( userInput[0].charAt(0) )-10; 
		int rp = 8 - Character.getNumericValue( userInput[0].charAt(1) );
		int mc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		
		
		if(cp != mc && rp != mr) {  
			
			return false;  
		}
		
		if(cp == mc) {   
			
			if(!checkVertical(rp,mr,cp)) {  
				
				return false;  
			}	
			
		}
		
		if(rp == mr) {  
			
			if(!checkHorizontal(cp,mc,rp)) { 
				
				return false; 
			}
			
		}
		
		
		if(Board.checkIndexValue(mr,mc)) {  
			
			hasMoved = true;
			return true;  
		}
		
		if(Board.gainPieceName(mr, mc).charAt(0) != Board.gainPieceName(rp, cp).charAt(0)) {
		
			hasMoved = true;
			return true;  
		}
		
		
		return false;  
	}
	
	/**
	 * @param rp userInput - the r as row the piece is on
	 * @param mr userInput - r as row the piece is attempting to move
	 * @param c userInput column piece is on
	 * @return returns true if there are no pieces in path of rook
	 */
	public boolean checkVertical(int rp, int mr, int c) {
		
		int vc;  
		
		if(rp > mr) {  
			
			vc = rp - 1; 
			
			while(vc > mr) {  
				
				if(!Board.checkIndexValue(vc, c)) {
					
					return false;
				}
				
				vc--;
			}
			
		}  else {
			
			vc = rp + 1;
			
			while(vc < mr) {
				
				if(!Board.checkIndexValue(vc, c)) {
					
					return false;
				}
				
				vc++;
			}
			
		}
		return true;  
	}
	/**
	 * @param cp userInput - the column the piece is on
	 * @param mc userInput column the piece is attempting to move
	 * @param r userInput r as row piece is on
	 * @return returns true: if there are no pieces in rook's path
	 */
	public boolean checkHorizontal(int cp, int mc, int r) {
		
		int vc;
		
		if(cp > mc) {
			
			vc = cp - 1;
			
			while(vc > mc) {
				
				if(!Board.checkIndexValue(r, vc)) {
					
					return false;
				}
				
				vc--;
			}
			
		}  else {
			
			vc = cp + 1;
			
			while(vc < mc) {
				
				if(!Board.checkIndexValue(r, vc)) {
					
					return false;
				}
				vc++;
			}
			
		}	
		return true;  

	}

	@Override
	public boolean isCheck(String move) {
		
		String[] userInput = move.split(" ");  
		
		int mc = Character.getNumericValue( userInput[1].charAt(0) )-10; 
		int mr = 8 - Character.getNumericValue( userInput[1].charAt(1)); 
		
		
		int c = mc - 1;  
		
		while(Board.checkIndexBound(mr,c)) {  
			
			if(!Board.checkIndexValue(mr, c) && gainPieceName().charAt(0) != Board.gainPieceName(mr, c).charAt(0) && Board.gainPieceName(mr, c).charAt(1) == 'K') {
			
				
				return true;  
				
			} 

			if(!Board.checkIndexValue(mr, c)){  
				
				break;
			}
			
			c--; 
		}
		
		c = mc + 1;  
		
		while(Board.checkIndexBound(mr,c)) { 
			
			if(!Board.checkIndexValue(mr, c) && gainPieceName().charAt(0) != Board.gainPieceName(mr, c).charAt(0) && Board.gainPieceName(mr, c).charAt(1) == 'K') {
				
				
				return true; 
				
			} 

			if(!Board.checkIndexValue(mr, c)){ 
				
				break;
			}
			
			c++; 
		}
		
		int r = mr - 1; 
		
		while(Board.checkIndexBound(r,mc)) { 
			
			if(!Board.checkIndexValue(r, mc) && gainPieceName().charAt(0) != Board.gainPieceName(r, mc).charAt(0) && Board.gainPieceName(r, mc).charAt(1) == 'K') {
			
				return true; 
				
			} 
			if(!Board.checkIndexValue(mc, r)){ 
				
				break;
			}
			r--; 
		}
		r = mr + 1;
		while(Board.checkIndexBound(r,mc)) { 
			
			if(!Board.checkIndexValue(r, mc) && gainPieceName().charAt(0) != Board.gainPieceName(r, mc).charAt(0) && Board.gainPieceName(r, mc).charAt(1) == 'K') {	
				return true; 	
			} 
 
			if(!Board.checkIndexValue(r, mc))
			{ 
				
				break;
			}
			r++; 
		}

		return false; 
	}
	/**
	 * @return true: if the king has moved; 
	 * @return false: if the king has not moved
	 */
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	/**
	 * @param here boolean value changes this field
	 */
	public void setHasMoved(boolean state)
	{
		hasMoved = state;
	}
}
			