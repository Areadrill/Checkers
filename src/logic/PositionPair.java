package logic;

public class PositionPair {
	private Position pOrigin, pDestination;
	
	public PositionPair(Position pOrigin, Position pDestination){
		this.pOrigin = pOrigin;
		this.pDestination = pDestination;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof PositionPair){
			if(this.pOrigin == null && ((PositionPair)obj).pOrigin == null  && this.pDestination.equals(((PositionPair)obj).pDestination)){
				return true;
			}
			else if(this.pOrigin == null){
				return false;
			}
			
			if(this.pOrigin.equals(((PositionPair)obj).pOrigin) && this.pDestination.equals(((PositionPair)obj).pDestination)){
				return true;
			}
		}
		return false;
	}
	
	public Position getOrigin(){
		return this.pOrigin;
	}
	
	public Position getDestination(){
		return this.pDestination;
	}
}
