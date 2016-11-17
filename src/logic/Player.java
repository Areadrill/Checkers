package logic;

import java.io.Serializable;

public class Player implements Serializable{
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Player && ((Player)obj).getName().equals(this.name)){
			return true;
		}
		return false;
	}
}
