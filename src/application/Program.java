package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printBoard(match.getPieces());
				System.out.println();
				System.out.println("Origem: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				System.out.println();
				System.out.println("Destino: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
			} catch(ChessException ex) {
				System.out.println(ex.getMessage());
				sc.nextLine();
			} catch(InputMismatchException ex) {
				System.out.println(ex.getMessage());
				sc.nextLine();
			} 
		}
	}
}
