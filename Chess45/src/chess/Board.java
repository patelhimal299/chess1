package chess;

import Piece.*;

/**
 * chess game board Representing here
 * @author Srushti Patel(sap353)
 * @author Dhwani shah(dvs48)
 */

public class Board {
	
	//2D array
	public static Piece[][] chessBoard = new Piece[8][8];
	
	
	/**
	 * @param r userInput r of 2D array which is from 0 to 7
	 * @param c userInput column of 2D array which is from 0 to 7
     * @return returns true: if the index is in bounds, and
     * @return returns false if the index is not equal to null.
	 */
	public static boolean checkIndexBound(int r, int c) {
		
		if(!((r >= 0 && r <= 7) &&  (c >= 0 && c <= 7))) {  
			
			return false;  
		}
		return true;
	}
	
	public static void chessBoardSetting() {
		
		
		for(int i = 0; i < 8; i++) {  
			
			Piece pawn = new Pawn("bp");
			chessBoard[1][i] = pawn;
		}
		
		// setup the rmaining pieces.
		chessBoard[0][0] = new Rook("bR");
		chessBoard[0][1] = new Knight("bN");
		chessBoard[0][2] = new Bishop("bB");
		chessBoard[0][3] = new Queen("bQ");
		chessBoard[0][4] = new King("bK");
		chessBoard[0][5] = new Bishop("bB");
		chessBoard[0][6] = new Knight("bN");
		chessBoard[0][7] = new Rook("bR");
		
		
		
		for(int i = 0; i < 8; i++) {  
			
			Piece pawn = new Pawn("wp");
			
			chessBoard[6][i] = pawn;
		}
		
		// setup the remaining white pieces
		chessBoard[7][0] = new Rook("wR");
		chessBoard[7][1] = new Knight("wN");
		chessBoard[7][2] = new Bishop("wB");
		chessBoard[7][3] = new Queen("wQ");
		chessBoard[7][4] = new King("wK");
		chessBoard[7][5] = new Bishop("wB");
		chessBoard[7][6] = new Knight("wN");
		chessBoard[7][7] = new Rook("wR");
		
	}
	/**
	 * @param r as userInput row of 2D array which is from 0 to 7
	 * @param c as userInput column of 2D array which is from 0 to 7
     * @return returns true: if the index is null, and
               returns false: if index is not null
	 */
	public static boolean checkIndexValue(int r, int c) {  

		
		if(chessBoard[r][c] == null) { 
			
			return true;
		}
		
		return false;
		
	}
	/**
	 * @param row as "r" userInput of 2D array which is from 0 to 7
	 * @param column as "col" userInput of 2D array which is from 0 to 7
	 * @return returns a String of piece name at the index, and if index is empty, then it will
     return empty String
	 */
	public static String gainPieceName(int r, int c) {  
		
		if(!((r >= 0 && r <= 7) &&  (c >= 0 && c <= 7))) {  
			
			return null;
		}
		
		if(chessBoard[r][c] != null) {  
			
			return chessBoard[r][c].gainPieceName();
			
		} 
		
		return null;
		
	}
		/**
	 * @param r userInput r of 2D array which is from 0 to 7
	 * @param c userInput column of 2D array, from 0 to 7
	 * @return returns piece object that is at the index. returns null if index dont contain a piece.
	 */
	public static Piece gainPiece(int r, int c) {
		
		return chessBoard[r][c];  
	}
	
	public static void printChessBoard() {
		
		int rc = 8;  
		
		boolean ps = true;  
		
		for(int i = 0; i < 8; i++) {  
			
			for(int j = 0; j < 8; j++) {  
				
				if(chessBoard[i][j] != null) {  
				
					System.out.print(chessBoard[i][j].toString());
					
				} else if(ps) {   
					
					
						if(i % 2 != 0) {
							
							System.out.print("##");
						} else {
							
							System.out.print("  ");
						}
						
						
										
				} else {  
					
					
					if(i % 2 == 0) {
						
						System.out.print("##");
					} else {
						 
						System.out.print("  ");
					}
						
					
				}
				
				System.out.print(" ");
				
				ps = !ps;  
				
				
			}
			
			System.out.print(rc);  
			rc--;
			
			System.out.println();
		}
		
		System.out.print(" a");
		
		for(char i = 'b'; i < 105; i++) {  
			
			System.out.print("  " + i);
			
		}
		
		System.out.println();
		System.out.println();
	}
	/**
	 * @param move,      userInput the move entered by the user
	 * @param whiteTurn true if it is white's turn and false if it is black's turn
	 * @return returns true if it is legal move
	 */
	public static boolean checkUserMove(String move, boolean whiteTurn) {
		
		String[] userInput = move.split(" ");  
		
		
		if(userInput.length < 2) {  
			
			return false;
		}
		if(userInput[0].length() != 2 || userInput[1].length() != 2) {  
			
			return false;
		}
		
		
		int columnPiece = Character.getNumericValue( userInput[0].charAt(0) ) - 10; 
		int rowPiece = 8 - Character.getNumericValue( userInput[0].charAt(1) ); 
		
		int moveCol = Character.getNumericValue( userInput[1].charAt(0) ) - 10; 
		int moveRow = 8 - Character.getNumericValue( userInput[1].charAt(1)) ; 
		
		if(!isMoveInBound(move)) {
			
			return false;
		}
		
		if(chessBoard[rowPiece][columnPiece] == null) {  
			
			return false;
		}
		
		if(whiteTurn && gainPieceName(rowPiece,columnPiece).charAt(0) != 'w') { 
			
			return false;  
		}
		
		if(!whiteTurn && gainPieceName(rowPiece,columnPiece).charAt(0) != 'b') {  
			
			return false;  
		}
		
		if(!chessBoard[rowPiece][columnPiece].checkMove(move)) {  
			
			return false;
		}
		
		if(chessBoard[rowPiece][columnPiece].isCheck(move)) {
			
			System.out.println("Check");
			System.out.println();
		}

		if (gainPieceName(rowPiece, columnPiece).charAt(1) == 'K')
		{
			
			if (moveCol - columnPiece == 2 && moveRow == rowPiece)
			{
				
				chessBoard[moveRow][moveCol] = chessBoard[rowPiece][columnPiece];
				chessBoard[rowPiece][columnPiece] = null;
				
				
				chessBoard[moveRow][moveCol - 1] = chessBoard[moveRow][moveCol + 1];
				chessBoard[moveRow][moveCol + 1] = null;
				return true;
			}
			
			if (moveCol - columnPiece == -2 && moveRow == rowPiece)
			{
				
				chessBoard[moveRow][moveCol] = chessBoard[rowPiece][columnPiece];
				chessBoard[rowPiece][columnPiece] = null;
				
				
				chessBoard[moveRow][moveCol + 1] = chessBoard[moveRow][moveCol - 2];
				chessBoard[moveRow][moveCol - 2] = null;
				return true;
			}
		}
		
		
		if (promotion(columnPiece, rowPiece, moveCol, moveRow))
		{
			if (userInput.length == 3)
			{
				char promoteTo = userInput[2].charAt(0);
				switch (Character.toUpperCase(promoteTo))
				{
				case 'Q':
					chessBoard[moveRow][moveCol] 
							= (gainPieceName(rowPiece, columnPiece).charAt(0) == 'w') ? new Queen("wQ") : new Queen("bQ");
					chessBoard[rowPiece][columnPiece] = null;
					break;
				case 'N':
					chessBoard[moveRow][moveCol] 
							= (gainPieceName(rowPiece, columnPiece).charAt(0) == 'w') ? new Knight("wN") : new Knight("bN");
					chessBoard[rowPiece][columnPiece] = null;
					break;
				case 'R':
					chessBoard[moveRow][moveCol] 
							= (gainPieceName(rowPiece, columnPiece).charAt(0) == 'w') ? new Rook("wR") : new Rook("bR");
					chessBoard[rowPiece][columnPiece] = null;
					break;
				case 'B':
					chessBoard[moveRow][moveCol] 
							= (gainPieceName(rowPiece, columnPiece).charAt(0) == 'w') ? new Bishop("wB") : new Bishop("bB");
					chessBoard[rowPiece][columnPiece] = null;
					break;
				default:
					return false;
				}
			} else {
				chessBoard[moveRow][moveCol] 
						= (gainPieceName(rowPiece, columnPiece).charAt(0) == 'w') ? new Queen("wQ") : new Queen("bQ");
				chessBoard[rowPiece][columnPiece] = null;
			}
			
			return true;
		}
		
		
		Piece temprory =  chessBoard[rowPiece][columnPiece];
		
		chessBoard[rowPiece][columnPiece] = null;
		
		chessBoard[moveRow][moveCol] = temprory;
		
		return true;
		
		
	}
	/**
	 * @param r row userInput r of 2D array which is from 0 to 7
	 * @param c column userInput col of 2D array which is from 0 to 7
	 */
	public static void setIndexValue(int r, int c) {
		
		chessBoard[r][c] = null;
	}
	/**
	 * @param move,
     * move inputed by user
     * @param whiteTurn true: if it is white's turn,
     * and false if it is black's turn
	 * @return returns true if resigning is inputed by user
	 */
	public static boolean checkUserInput(String move, boolean whiteTurn) {
		
		String[] userInput = move.split(" ");  // splits the userInput
		
		if(userInput[0].equals("resign")) {
			
			if(whiteTurn) {
				
				System.out.println("Black wins");
				
			} else {
				
				System.out.println("White wins");
				
			}
			
			return true;
		}
		
		return false;
	}
	/**
	 * @param move,
     * inputed by user
     * @return returns true: if user inputs "draw?"
	 */
	public static boolean checkDrawCondition(String move) {
		
		String[] userInput = move.split(" ");  
		
		if(userInput.length >= 3) {
			
			if(userInput[2].equals("draw?")) {
				
				return true;
				
			}
		}
		
		return false;
	}
	/**
	 * @param move inputed by user
	 * @return returns true if "draw" is inputed by user
	 */
	public static boolean isDrawAccepted(String move) {
		
		String[] userInput = move.split(" ");  
		
		if(userInput[0].equals("draw")) {
			
			return true;
		}
		
		return false;
	}
	/**
	 * @return returns true either the white king or black king is no longer on the chess board
	 */
	public static boolean checkKingStatus() {
		
		boolean whiteKing = false;  
		boolean blackKing = false;
		
				
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
					
				if(!Board.checkIndexValue(r, c) && chessBoard[r][c].gainPieceName().equals("bK")) {
						
					blackKing = true;
				}
					
				if(!Board.checkIndexValue(r, c) && chessBoard[r][c].gainPieceName().equals("wK")) {
						
					whiteKing = true;
				}
			}
		}
			
		
		if(!whiteKing) {
			
			System.out.println("Black wins");
			return true;
		}
		
		if(!blackKing) {
			
			System.out.println("White wins");
			return true;
		}
		
		return false;
	}
	
	
	
	/**
     * @return true: if a king is in checkmate; false if no kings are in checkmate
	 */
	public static boolean isCheckmate()
	{
		int bkr = -1;  
		int bkc = -1;
		boolean bKingInCheck = false;
		
		int wkr = -1;
		int wkc = -1;
		boolean wKingInCheck = false;
		
		
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(!Board.checkIndexValue(r, c) && chessBoard[r][c].gainPieceName().equals("bK")) {
					bkr = r;
					bkc = c;
				}
					
				if(!Board.checkIndexValue(r, c) && chessBoard[r][c].gainPieceName().equals("wK")) {
					wkr = r;
					wkc = c;
				}
			}
		}
		
		
		if (bkr == -1 && bkc == -1)
		{
			System.out.println("White wins");
			return true;
		} else if (wkr == -1 && wkc == -1)
		{
			System.out.println("Black wins");
			return true;
		}
		
		bKingInCheck = doesKingInCheck('w');
		wKingInCheck = doesKingInCheck('b');

		
		if (bKingInCheck)
		{
			Piece tempKing = chessBoard[bkr][bkc];
			chessBoard[bkr][bkc] = null;
			for(int r = bkr - 1; r < bkr + 2; r++)
			{
				for (int c = bkc - 1; c < bkc + 2; c++)
				{
					if (Board.checkIndexBound(r, c) && Board.checkIndexValue(r, c))
					{
						chessBoard[r][c] = tempKing;
						
						
						if (!doesKingInCheck('w'))
						{
							chessBoard[r][c] = null;
							chessBoard[bkr][bkc] = tempKing;
							return false;
						}
						
						chessBoard[r][c] = null;
					}
				}
			}
			chessBoard[bkr][bkc] = tempKing;
			
			System.out.println();
			System.out.println("Checkmate");
			System.out.println();
			System.out.println("White wins");
			System.out.println();
			
			return true;
		} else if (wKingInCheck) {
			Piece tempKing = chessBoard[wkr][wkc];
			chessBoard[wkr][wkc] = null;
			for(int r = wkr - 1; r < wkr + 2; r++)
			{
				for (int c = wkc - 1; c < wkc + 2; c++)
				{
					if (Board.checkIndexBound(r, c) && Board.checkIndexValue(r, c))
					{
						chessBoard[r][c] = tempKing;
						
						
						if (!doesKingInCheck('b'))
						{
							chessBoard[r][c] = null;
							chessBoard[wkr][wkc] = tempKing;
							return false;
						}
						
						chessBoard[r][c] = null;
					}
				}
			}
			chessBoard[wkr][wkc] = tempKing;
			
			System.out.println();
			System.out.println("Checkmate");
			System.out.println();
			System.out.println("Black wins");
			System.out.println();
			
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * @param color userInput color of the piece
     * @return true: if the king is in check , otherwise returns false.
	 */
	private static boolean doesKingInCheck(char color)
	{
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if (!Board.checkIndexValue(r, c) && chessBoard[r][c].gainPieceName().charAt(1) != 'K')
				{
					
					String move = convertIndexToChar(c) + "" + (8 - r) + " " + convertIndexToChar(c) + "" + (8 - r);
					if (chessBoard[r][c].isCheck(move))
					{
						if (chessBoard[r][c].gainPieceName().charAt(0) == color)
						{
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	/**
	 * @param move
     * inputed by user
     * @return returns true: if the move is on the game board;
     * @return returns false if move is out of bounds
	 */
	public static boolean isMoveInBound(String move) {  
		
		String[] userInput = move.split(" ");  
		
		char columnLetterForPiece = userInput[0].charAt(0); 
		char columnLetterForMove = userInput[1].charAt(0);  
		
		int pn= Character.getNumericValue( userInput[0].charAt(1) );  
		int mn = Character.getNumericValue( userInput[1].charAt(1) );  
		
		if(!((columnLetterForPiece >= 97 && columnLetterForPiece <= 104) &&  (columnLetterForMove >= 97 && columnLetterForMove <= 104))) {  // checks if userInput letters are not between a - h using ascii
			
			return false;
		}
		
		if(!((pn>= 1 && pn<= 8) &&  (mn >= 1 && mn <= 8))) {  
			
			return false;
		}
		

		return true;  
	}
	

	/**
	 * @param initiallycol      current col of piece
	 * @param initiallyRow      current r of piece
	 * @param colDestination col where piece is being moved
	 * @param rowDestination  r  where piece is being moved
     * @return returns true; if a pawn is eligible for promotion;
     * @return false if the pawn is ineligible for promotion
	 */
	private static boolean promotion(int initiallyColumn, int initiallyRow, int columnDestination, int rowDestination)
	{
		if (gainPieceName(initiallyRow, initiallyColumn).charAt(1) == 'p')
		{
			if (gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'w')
			{
				if (rowDestination == 0)
				{
					return true;
				}
			} else if (gainPieceName(initiallyRow, initiallyColumn).charAt(0) == 'b')
			{
				if (rowDestination == 7)
				{
					return true;
				}
			}
		}

		return false;
	}
	

	/**
	 * @param index insert the column index
	 * @return the column index of the 2d array which is converted into a letter that agrees to a column on the board
	 */
	
	private static char convertIndexToChar(int index)
	{
		char column = ' ';
		
		if (index == 0)
		{
			column = 'a';
		}
		else if (index == 1)
		{
			column = 'b';
		}
		else if (index == 2)
		{
			column = 'c';
		}
		else if (index == 3)
		{
			column = 'd';
		}
		else if (index == 4)
		{
			column = 'e';
		}
		else if (index == 5)
		{
			column = 'f';
		}
		else if (index == 6)
		{
			column = 'g';
		}
		else if (index == 7)
		{
			column = 'h';
		}
		
		return column;
	}
	
}