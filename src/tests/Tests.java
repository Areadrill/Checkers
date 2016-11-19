package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import logic.Board;
import logic.Game;
import logic.Game.Direction;
import logic.Piece;
import logic.Player;
import logic.Position;
import logic.PositionPair;

public class Tests {
	
	@Test
	public void correctGeneration(){
		Game g = new Game(false);
		g.players[0] = new Player("p1");
		g.players[1] = new Player("p2");
		g.currentPlayer = 0;
		
		Board testBoard = new Board();
		Piece testPiece = new Piece(g.players[0], new Position(2, 2));
		
		testBoard.getBoard().put(new Position(2, 2), testPiece);
		
		g.board = testBoard;
		ArrayList<PositionPair> validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		
		
		assertTrue(validPositions.contains(new  PositionPair(null, new Position(3, 3))));
		assertEquals(validPositions.size(), 1);
		
		Piece testPiece1 = new Piece(g.players[0], new Position(3, 3));
		g.board.getBoard().put(new Position(3, 3), testPiece1);
		
		validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		assertEquals(validPositions.size(), 0);
		
		g.board.getBoard().remove(new Position(3, 3));
		
		Piece testPiece2 = new Piece(g.players[1], new Position(3, 3));
		g.board.getBoard().put(new Position(3, 3), testPiece2);
		
		validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		assertEquals(validPositions.size(), 1);
		assertTrue(validPositions.contains(new PositionPair(null, new Position(4, 4))));
		
		
		Piece testPiece3 = new Piece(g.players[1], new Position(4, 4));
		g.board.getBoard().put(new Position(4, 4), testPiece3);
		validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		assertEquals(validPositions.size(), 0);
		
		g.board.getBoard().remove(new Position(4, 4));
		
		testPiece3.getPosition().set(new Position(5, 5));
		g.board.getBoard().put(new Position(5, 5), testPiece3);
		validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		assertEquals(validPositions.size(), 2);
		assertTrue(validPositions.contains(new PositionPair(null, new Position(4, 4))));
		assertTrue(validPositions.contains(new PositionPair(new Position(4, 4), new Position(6, 6))));
		
		
		Piece testPiece4 = new Piece(g.players[1], new Position(3, 5));
		g.board.getBoard().put(new Position(3, 5), testPiece4);
		validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		assertEquals(validPositions.size(), 3);
		assertTrue(validPositions.contains(new PositionPair(null, new Position(4, 4))));
		assertTrue(validPositions.contains(new PositionPair(new Position(4, 4), new Position(6, 6))));
		assertTrue(validPositions.contains(new PositionPair(new Position(4, 4), new Position(2, 6))));
		
		testPiece2.getPosition().set(new Position(7, 7));
		g.board.getBoard().put(new Position(7, 7), testPiece2);
		validPositions = g.getAvailableMoves(testPiece2, Direction.SE, null, false);
		assertEquals(validPositions.size(), 0);
		
		g.board.getBoard().remove(new Position(7, 7));
		testPiece2.getPosition().set(new Position(6, 6));
		testPiece4.getPosition().set(new Position(7, 7));
		g.board.getBoard().put(new Position(7, 7), testPiece4);
		g.board.getBoard().put(new Position(6, 6), testPiece2);
		validPositions = g.getAvailableMoves(testPiece2, Direction.SE, null, false);
		assertEquals(validPositions.size(), 0);
		
		g.board.getBoard().clear();
		
		testPiece.getPosition().set(0, 0);
		testPiece.setKing(true);
		g.board.getBoard().put(new Position(0, 0), testPiece);
		validPositions = g.getAvailableMoves(testPiece, Direction.SE, null, false);
		assertEquals(validPositions.size(), 7);
		
		
		
	}
	
}
