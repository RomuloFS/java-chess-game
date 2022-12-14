package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		List<ChessPiece> captured = new ArrayList();
		
		while(!match.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(match, captured);
				System.out.println();
				System.out.println("Origem: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = match.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(match.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.println("Destino: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (match.getPromoted() != null) {
					System.out.print("Insira uma peça para promover (B/N/R/Q): ");
					String piece = sc.nextLine().toUpperCase();
					
					while (!piece.equals("B") &&
							!piece.equals("N") &&
							!piece.equals("R") &&
							!piece.equals("Q")) {
						System.out.print("VALOR INVALIDO, insira uma peça para promover (B/N/R/Q): ");
						piece = sc.nextLine().toUpperCase();
					}
					match.replacePromotedPiece(piece);
				}
				
			} catch(ChessException ex) {
				System.out.println(ex.getMessage());
				sc.nextLine();
			} catch(InputMismatchException ex) {
				System.out.println(ex.getMessage());
				sc.nextLine();
			} 
		}
		
		UI.clearScreen();
		UI.printMatch(match, captured);
	}
}
