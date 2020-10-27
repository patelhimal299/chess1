package Piece;
import chess.Board;
/**
 * Represents the Bishop piece
 * @author Srushti Patel(sap353)
 * @author Dhwani Shah(dvs48)
 */
public class Bishop extends Piece
{
	int initiallyColumn = -1;
	int initiallyRow= -1;
	int columnDestination = -1;
	int rowDestination = -1;
	public Bishop(String pieceName) {
		
		super(pieceName);
	}
/**
	 * @param user move input
	 * @return return true when the bishop makes a legal mv ;
	 * 	@return	return false when the bishop makes an illegal mv;
	 */
	@Override
	public boolean checkMove(String mv) {
		String[] userInput = mv.split(" ");  
		
		initiallyColumn = Character.getNumericValue(userInput[0].charAt(0)) - 10; 
		initiallyRow= 8 - Character.getNumericValue(userInput[0].charAt(1)); 
		
		columnDestination = Character.getNumericValue(userInput[1].charAt(0)) - 10; 
		rowDestination = 8 - Character.getNumericValue(userInput[1].charAt(1)); 
		
		if (columnDestination == -1 || initiallyColumn == -1 || rowDestination == -1 || initiallyRow== -1)
		{
			return false;
		}
		
		if (columnDestination == initiallyColumn || rowDestination == initiallyRow)
		{
			return false;
		}
		
		if (Math.abs(columnDestination - initiallyColumn) != Math.abs(rowDestination - initiallyRow))
		{
			return false;
		}

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

		return true;
	}
		/**
	 * @return return true when there is a piece  bishop will capture
	 * 	@return	return false when there is a piece of same color on  destination
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
	 * @return returns true when  piece in the way
	 * 			returns false when  no piece in the way
	 */
	private boolean checkPieceLeapOver()
	{
		int ci = 0;
		int ri = 0;
		
		if (columnDestination - initiallyColumn < 0)
		{
			ci = initiallyColumn - 1;
			
			if (rowDestination - initiallyRow< 0)
			{
				//moving it to the upper left
				ri = initiallyRow- 1;
				
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
				//moving it to the lower left
				ri = initiallyRow+ 1;
				
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
			
			if (rowDestination - initiallyRow< 0)
			{
				//moving it to the upper right
				ri = initiallyRow- 1;
				
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
				//moving it to the lower right
				ri = initiallyRow+ 1;
				
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
	/**
	 * @param mv inputed by user
	 * @return returns true when  bishop put  king on check
	 * 			returns false when  bishop doesn't put  king on check
	 */
	@Override
	public boolean isCheck(String mv) {
		String[] userInput = mv.split(" ");  
		initiallyColumn = Character.getNumericValue(userInput[0].charAt(0)) - 10; 
		initiallyRow= 8 - Character.getNumericValue(userInput[0].charAt(1)); 
		columnDestination = Character.getNumericValue(userInput[1].charAt(0)) - 10; 
		rowDestination = 8 - Character.getNumericValue(userInput[1].charAt(1)); 
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
		//check it to the lower left
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
		//check it to the upper right
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
		//check it to the lower right
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