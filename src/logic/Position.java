package logic;

import java.io.Serializable;

public class Position implements Serializable{
	private int x, y;

	public Position() {
	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position pos) {
		this.x = pos.getX();
		this.y = pos.getY();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void set(Position pos){
		this.x = pos.x;
		this.y = pos.y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result += prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position pos = (Position) obj;
			if (pos.getX() == this.x && pos.getY() == this.y) {
				return true;
			}

		}
		return false;
	}
}
