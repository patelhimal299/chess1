package Piece;
import chess.Board;
/**
 * Represents the King piece
 * @author Srushti Patel(sap353)
 * @author Dhwani shah(dvs48)
 */
public class King extends Piece{

	boolean MoveStatus = false;
	int initiallyColumn = -1;
	int initiallyRow = -1;
	int columnDestination = -1;
	int rowDestination = -1;
	
	public King(String pn) {
		
		super(pn);
	}
	/**
	 * @param mv 
	 * inputed by user
	 * @return returns true if the king makes a legal mv,
	 * @return false if the king makes an illegal mv
	 */
	@Override
	public boolean checkMove(String mv) {
		String[] userInput = mv.split(" ");  // splits the users userInput
		initiallyColumn = Character.getNumericValue(userInput[0].charAt(0)) - 10; 
		initiallyRow = 8 - Character.getNumericValue(userInput[0].charAt(1)); 
		columnDestination = Character.getNumericValue(userInput[1].charAt(0)) - 10; 
		rowDestination = 8 - Character.getNumericValue(userInput[1].charAt(1)); 
		
		if (gainPieceName().equals("wK"))
		{
			if (!Board.checkIndexValue(rowDestination, columnDestination) && Board.gainPieceName(rowDestination, columnDestination).charAt(0) == 'w')
			{
				return false;
			}
		} else if (gainPieceName().equals("bK"))
		{
			if (!Board.checkIndexValue(rowDestination, columnDestination) && Board.gainPieceName(rowDestination, columnDestination).charAt(0) == 'b')
			{
				return false;
			}
		}
		if (castlingMove())
		{
			MoveStatus = true;
			return true;
		}
		if (columnDestination == initiallyColumn && (rowDestination == initiallyRow + 1 || rowDestination == initiallyRow - 1)) 
		{
			MoveStatus = true;
			return true;
		}
		if ((columnDestination == initiallyColumn - 1 || columnDestination == initiallyColumn + 1) && rowDestination == initiallyRow) 
		{
			MoveStatus = true;
			return true;
		}
		if (columnDestination == initiallyColumn - 1 && (rowDestination == initiallyRow + 1 || rowDestination == initiallyRow - 1)) 
		{
			MoveStatus = true;
			return true;
		}
		if (columnDestination == initiallyColumn + 1 && (rowDestination == initiallyRow + 1 || rowDestination == initiallyRow - 1)) 
		{
			MoveStatus = true;
			return true;
		}
		return false;
	}
	/**
	 * @return true if the king is performing a legal castling mv; 
	 * @return false if the castling mv is illegal
	 */
	private boolean castlingMove()
	{
		if (!MoveStatus)
		{
			if (columnDestination - initiallyColumn == 2 && rowDestination == initiallyRow)
			{
				if (Board.gainPiece(rowDestination, columnDestination + 1) instanceof Rook)
				{
					if (!((Rook) Board.gainPiece(rowDestination, columnDestination + 1)).getHasMoved())
					{
						if (Board.checkIndexValue(rowDestination, columnDestination - 1) && Board.checkIndexValue(rowDestination, columnDestination))
						{
							if (Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == Board.gainPieceName(rowDestination, columnDestination + 1).charAt(0))
							{
								((Rook) Board.gainPiece(rowDestination, columnDestination + 1)).setHasMoved(true);
								return true;
							}
						}
					}
				}
			}
			if (columnDestination - initiallyColumn == -2 && rowDestination == initiallyRow)
			{
				if (Board.gainPiece(rowDestination, columnDestination - 2) instanceof Rook)
				{
					if (!((Rook) Board.gainPiece(rowDestination, columnDestination - 2)).getHasMoved())
					{
						if (Board.checkIndexValue(rowDestination, columnDestination - 1) && Board.checkIndexValue(rowDestination, columnDestination) && Board.checkIndexValue(rowDestination, columnDestination + 1))
						{
							if (Board.gainPieceName(initiallyRow, initiallyColumn).charAt(0) == Board.gainPieceName(rowDestination, columnDestination - 2).charAt(0))
							{
								((Rook) Board.gainPiece(rowDestination, columnDestination - 2)).setHasMoved(true);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * @param mv inputed by user
	 * @return returns true if the king puts the opposite king in the check
	 * @return false if the king does not put the opposite king in the check
	 */
	@Override
	public boolean isCheck(String mv) {
		
		if (Board.checkIndexBound(rowDestination - 1, columnDestination) && !Board.checkIndexValue(rowDestination - 1, columnDestination))
		{
			if(Board.gainPieceName(rowDestination - 1, columnDestination).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination - 1, columnDestination).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination + 1, columnDestination) && !Board.checkIndexValue(rowDestination + 1, columnDestination))
		{
			if(Board.gainPieceName(rowDestination + 1, columnDestination).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination + 1, columnDestination).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination, columnDestination - 1) && !Board.checkIndexValue(rowDestination, columnDestination - 1))
		{
			if(Board.gainPieceName(rowDestination, columnDestination - 1).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination, columnDestination - 1).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination, columnDestination + 1) && !Board.checkIndexValue(rowDestination, columnDestination + 1))
		{
			if(Board.gainPieceName(rowDestination, columnDestination + 1).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination, columnDestination + 1).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination - 1, columnDestination - 1) && !Board.checkIndexValue(rowDestination - 1, columnDestination - 1))
		{
			if(Board.gainPieceName(rowDestination - 1, columnDestination - 1).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination - 1, columnDestination - 1).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination + 1, columnDestination - 1) && !Board.checkIndexValue(rowDestination + 1, columnDestination - 1))
		{
			if(Board.gainPieceName(rowDestination + 1, columnDestination - 1).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination + 1, columnDestination - 1).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination - 1, columnDestination + 1) && !Board.checkIndexValue(rowDestination - 1, columnDestination + 1))
		{
			if(Board.gainPieceName(rowDestination - 1, columnDestination + 1).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination - 1, columnDestination + 1).charAt(1) == 'K') {	
				return true;
			}
		}
		if (Board.checkIndexBound(rowDestination + 1, columnDestination + 1) && !Board.checkIndexValue(rowDestination + 1, columnDestination + 1))
		{
			if(Board.gainPieceName(rowDestination + 1, columnDestination + 1).charAt(0) != gainPieceName().charAt(0) 
					&& Board.gainPieceName(rowDestination + 1, columnDestination + 1).charAt(1) == 'K') {	
				return true;
			}
		}
		return false;
	}
	/**
	 * @return true if the king has moved; 
	 * @return false if the king hasn't moved
	 */
	public boolean getHasMoved()
	{
		return MoveStatus;
	}
}