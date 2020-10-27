package Piece;
import chess.Board;
/**
 * Queen piece
 * @author Dhwani shah(dvs48)
 * @author Srushti Patel(sap353)
 */

public class Queen extends Piece{
	int initiallyColumn = -1;
	int initiallyRow = -1;
	int columnDestination = -1;
	int rowDestination = -1;
	public Queen(String pn) {
		
		super(pn);
	}
	/**
	 * @param move userInput from user
	 * @return true if move is legal; 
	 * @return false if move is illegal
	 */
	@Override
	public boolean checkMove(String move) {
		String[] userInput = move.split(" ");  // splits the users userInput
		
		initiallyColumn = Character.getNumericValue(userInput[0].charAt(0)) - 10; 
		initiallyRow = 8 - Character.getNumericValue(userInput[0].charAt(1)); 
		columnDestination = Character.getNumericValue(userInput[1].charAt(0)) - 10; 
		rowDestination = 8 - Character.getNumericValue(userInput[1].charAt(1)); 
	
		if (columnDestination == -1 || initiallyColumn == -1 || rowDestination == -1 || initiallyRow == -1)
		{
			return false;
		}
		
		if (Math.abs(columnDestination - initiallyColumn) > 0 && Math.abs(rowDestination - initiallyRow) > 0)
		{
			if (Math.abs(columnDestination - initiallyColumn) != Math.abs(rowDestination - initiallyRow))
			{
				return false;
			}
		}

		if ((initiallyColumn != columnDestination) && (initiallyRow != rowDestination))
		{
			if (!Board.checkIndexValue(rowDestination, columnDestination))
			{
				if(checkPieceLeapOver())
				{
					return false;
				}
				
				if(!captureCheck())
				{
					return false;
				}
			} else {
				if(checkPieceLeapOver())
				{
					return false;
				}
			}
		}

		if(initiallyColumn == columnDestination)
		{
	
			if(!checkVertical())
			{
				return false; 
			}
		}

		if(initiallyRow == rowDestination)
		{

			if(!checkHorizontal())
			{
				return false;  
			}
			
		}
	
		if(Board.checkIndexValue(rowDestination,columnDestination))
		{
			return true;  
		}
		
			
		if(Board.gainPieceName(rowDestination, columnDestination).charAt(0) == Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0))
		{
			return false;  
		}
		
		return true;
	}
		
	/**
	 * @return true: if there is a piece of a different color on the spot queen is moving to; 
	 * @return false if the piece is the same color
	 */
		private boolean captureCheck()
		{
			if (Board.gainPieceName(rowDestination, columnDestination).charAt(0) == 'b'
					&& Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'w')
			{
				return true;
			} else if (Board.gainPieceName(rowDestination, columnDestination).charAt(0) == 'w'
					&& Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'b')
			{
				return true;
			}
			
			return false;
		}
		
		/**
		 * @return returns true if there is a piece in the way
		 * 	@return returns false if there is no piece in the way
		 */
		private boolean checkPieceLeapOver()
		{
			int ci = 0;
			int ri = 0;
			
			if (columnDestination - initiallyColumn < 0)
			{
				ci = initiallyColumn - 1;
				
				if (rowDestination - initiallyRow < 0)
				{
					
					ri = initiallyRow - 1;
					
					while (ri != rowDestination)
					{
						if(!Board.checkIndexValue(ri, ci))
						{
							return true;
						}
						ri --;
						ci --;
					}
				} else {
					
					ri = initiallyRow + 1;
					
					while (ri != rowDestination)
					{
						if(!Board.checkIndexValue(ri, ci))
						{
							return true;
						}
						ri ++;
						ci --;
					}
				}
			} else {
				ci = initiallyColumn + 1;
				
				if (rowDestination - initiallyRow < 0)
				{
					
					ri = initiallyRow - 1;
					
					while (ri != rowDestination)
					{
						if(!Board.checkIndexValue(ri, ci))
						{
							return true;
						}
						ri --;
						ci ++;
					}
				} else {
					
					ri = initiallyRow + 1;
					
					while (ri != rowDestination)
					{
						if(!Board.checkIndexValue(ri, ci))
						{
							return true;
						}
						ri ++;
						ci ++;
					}
				}
			}
			return false;
		}
		
		public boolean checkVertical()
		{
			int vChecker;
			
			if(initiallyRow > rowDestination)
			{  
				vChecker = initiallyRow - 1; 
				
				while(vChecker > rowDestination)
				{  
					if(!Board.checkIndexValue(vChecker, initiallyColumn))
					{
						return false;
					}
					
					vChecker--;
				}
			} else {
				vChecker = initiallyRow + 1;
				
				while(vChecker < rowDestination)
				{
					if(!Board.checkIndexValue(vChecker, initiallyColumn))
					{
						return false;
					}
					
					vChecker++;
				}
			}
			return true;  
		}
		
		public boolean checkHorizontal() {
			
			int vChecker;
			
			if(initiallyColumn > columnDestination)
			{
				vChecker = initiallyColumn - 1;
				
				while(vChecker > columnDestination)
				{
					if(!Board.checkIndexValue(initiallyRow, vChecker))
					{
						return false;
					}
					vChecker--;
				}
				
			} else {
				vChecker = initiallyColumn + 1;
				
				while(vChecker < columnDestination)
				{
					if(!Board.checkIndexValue(initiallyRow, vChecker))
					{
						return false;
					}
					
					vChecker++;
				}
			}		
			return true;  
		}

	@Override
	public boolean isCheck(String move) {
		String[] userInput = move.split(" ");  // splits the users userInput
		
		initiallyColumn = Character.getNumericValue(userInput[0].charAt(0)) - 10; 
		initiallyRow = 8 - Character.getNumericValue(userInput[0].charAt(1)); 
		columnDestination = Character.getNumericValue(userInput[1].charAt(0)) - 10; 
		rowDestination = 8 - Character.getNumericValue(userInput[1].charAt(1)); 		
	
		if (checkCardinals())
		{
			return true;
		}
		
		
		if (checkDiagonals())
		{
			return true;
		}

		return false;
	}
	
	/**
	 * @return true if a king has been found vertically 
	 * @return false if a king hasn't been found vertically
	 */
	private boolean checkCardinals()
	{
		int ci = columnDestination - 1;  
		
		while(Board.checkIndexBound(rowDestination,ci)) {  
			
			if(!Board.checkIndexValue(rowDestination, ci) 
					&& gainPieceName().charAt(0) != Board.gainPieceName(rowDestination, ci).charAt(0) 
					&& Board.gainPieceName(rowDestination, ci).charAt(1) == 'K') {
				
				
				return true;  
				
			} 

			if(!Board.checkIndexValue(rowDestination, ci)){  				
				break;
			}
			
			ci--;  
		}
		
		ci = columnDestination + 1; 
		
		while(Board.checkIndexBound(rowDestination,ci)) { 
			
			if(!Board.checkIndexValue(rowDestination, ci) 
					&& gainPieceName().charAt(0) != Board.gainPieceName(rowDestination, ci).charAt(0) 
					&& Board.gainPieceName(rowDestination, ci).charAt(1) == 'K') {
				
				
				return true; 
				
			} 

			if(!Board.checkIndexValue(rowDestination, ci)){  
				
				break;
			}
			
			ci++; 
		}
		
		int ri = rowDestination - 1; 
		
		while(Board.checkIndexBound(ri,columnDestination)) {  
			
			if(!Board.checkIndexValue(ri, columnDestination) 
					&& gainPieceName().charAt(0) != Board.gainPieceName(ri, columnDestination).charAt(0) 
					&& Board.gainPieceName(ri, columnDestination).charAt(1) == 'K') {
			
				
				return true;  
				
			} 

			if(!Board.checkIndexValue(columnDestination, ri)){ 
				
				break;
			}
			
			ri--; 
		}
		
		ri = rowDestination + 1; 		
		while(Board.checkIndexBound(ri,columnDestination)) {  
			
			if(!Board.checkIndexValue(ri, columnDestination) 
					&& gainPieceName().charAt(0) != Board.gainPieceName(ri, columnDestination).charAt(0) 
					&& Board.gainPieceName(ri, columnDestination).charAt(1) == 'K') {
			
				return true; 
				
			} 
 
			if(!Board.checkIndexValue(ri, columnDestination))
			{ 
				
				break;
			}
			ri++;
		}

		return false;  
	}
	
	/**
	 * @return true if a king has been found diagonally; 
	 * @return false if a king hasn't been found diagonally
	 */
	private boolean checkDiagonals()
	{		
		int ci = 0;
		int ri = 0;

		ci = columnDestination - 1;
		ri = rowDestination - 1;
		while (Board.checkIndexBound(ri, ci))
		{
			if (!Board.checkIndexValue(ri, ci))
			{
				if ((Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'w' && Board.gainPieceName(ri, ci) == "bK")
						|| (Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'b' && Board.gainPieceName(ri, ci) == "wK"))
				{
					return true;
				} else {
					break;
				}
			}
			ri ++;
			ci ++;
		}
		ci = columnDestination - 1;
		ri = rowDestination + 1;
		while (Board.checkIndexBound(ri, ci))
		{
			if (!Board.checkIndexValue(ri, ci))
			{
				if ((Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'w' && Board.gainPieceName(ri, ci) == "bK")
						|| (Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'b' && Board.gainPieceName(ri, ci) == "wK"))
				{
					return true;
				} else {
					break;
				}
			}
			ri ++;
			ci ++;
		}
		ci = columnDestination + 1;
		ri = rowDestination - 1;
		while (Board.checkIndexBound(ri, ci))
		{
			if (!Board.checkIndexValue(ri, ci))
			{
				if ((Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'w' && Board.gainPieceName(ri, ci) == "bK")
						|| (Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'b' && Board.gainPieceName(ri, ci) == "wK"))
				{
					return true;
				} else {
					break;
				}
			}
			ri ++;
			ci ++;
		}
		ci = columnDestination + 1;
		ri = rowDestination + 1;	
		while (Board.checkIndexBound(ri, ci))
		{
			if (!Board.checkIndexValue(ri, ci))
			{
				if ((Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'w' && Board.gainPieceName(ri, ci) == "bK")
						|| (Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'b' && Board.gainPieceName(ri, ci) == "wK"))
				{
					return true;
				} else {
					break;
				}
			}
			ri ++;
			ci ++;
		}
		return false;
	}
}