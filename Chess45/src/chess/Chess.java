package chess;
import java.io.*;
import java.util.*;
import Piece.*;
/**
 * Main function which contains the chess game loop
 * @author Dhwani Shah(dvs48)
 * @author Srushti Patel(sap353)
 */
public class Chess {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Board.chessBoardSetting();  
		Board.printChessBoard();  
		boolean wt = true;  
		boolean drawStatus = false;
		String mv;
		int trys;
		while(true) {
			trys = 0;
			do {
				if(trys > 0) {
					System.out.println("Illegal mv, try again"); 
					System.out.println();
					System.out.println();
				}
				if(wt) {  
					System.out.print("White's move: ");
					mv = scan.nextLine();  
					System.out.println();
					System.out.println();	
				} else 
				{
					System.out.print("Black's move: ");
					mv = scan.nextLine(); 
					System.out.println();
					System.out.println();
				}
				if(drawStatus) { 
					if(Board.isDrawAccepted(mv)) {
						System.out.println("Draw");
						scan.close();
						return;
					} 
				}				
				if(Board.checkUserInput(mv, wt)) {
					scan.close();
					return;
				}	
				trys++;
			} while(!Board.checkUserMove(mv,wt));
			if(drawStatus == true) {  	
				drawStatus = false;
			}
			if(Board.checkDrawCondition(mv)) { 	
				drawStatus = true;
			}
			if(Board.checkKingStatus()) { 
				
				scan.close();
				return; 
			}
			if(Board.isCheckmate())
			{
				scan.close();
				return;
			}
			Board.printChessBoard();
			wt = !wt;  
		}  
	}
}