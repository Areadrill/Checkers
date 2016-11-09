package logic;

public class Position {
	private int x, y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Position(Position pos){
		this.x = pos.getX();
		this.y = pos.getY();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.getY();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Position){
			Position pos = (Position) obj;
			if(pos.getX() == this.x && pos.getY() == this.y){
				return true;
			}
			
		}
		return false;
	}
}
