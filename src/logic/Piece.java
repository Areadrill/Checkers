package logic;

public class Piece {
	private Player owner;
	private boolean king;
	private Position position;
	
	public Piece(Player player){
		this.owner = player;
		this.king = false;
		this.position = new Position(0, 0);
	}
	
	public Piece(Player player, Position pos){
		this.owner = player;
		this.king = false;
		this.position = pos;
	}
	
	public Piece(Player player, int x, int y){
		this.owner = player;
		this.king = false;
		this.position = new Position(x, y);
	}
	
	
	public void setKing(boolean king){
		this.king = king;
	}
	
	public boolean isKing(){
		return this.king;
	}
	
	public Player getPlayer(){
		return this.owner;
	}
}
