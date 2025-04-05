package HotelReservationSystem;

public class Room {
	private int dailyCost;
	private int roomSize;
	private boolean hasBath;
	public Room(){	}
	public int getDailyCost() {
		return dailyCost;
	}
	public void setDailyCost(int dailyCost) {
		this.dailyCost = dailyCost;
	}
	public int getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
	public boolean isHasBath() {
		return hasBath;
	}
	public void setHasBath(boolean hasBath) {
		this.hasBath = hasBath;
	}
}
class RoomTypeException extends Exception{
	
	public RoomTypeException(String message) {
		super(message);
	}
	
}

class Single extends Room{
	public Single() {
		setDailyCost(100);
		setRoomSize(15);
		setHasBath(false);
	}
}
class Double extends Room{
	public Double() {
		setDailyCost(180);
		setRoomSize(30);
		setHasBath(false);
	}

}
class Club extends Room{
	public Club() {
		setDailyCost(250);
		setRoomSize(45);
		setHasBath(true);
	}
}
class Family extends Room{
	public Family() {
		setDailyCost(400);
		setRoomSize(50);
		setHasBath(false);
	}
}
class FamilyView extends Room{
	public FamilyView() {
		setDailyCost(450);
		setRoomSize(50);
		setHasBath(true);
	}
}
class Suite extends Room{
	public Suite() {
		setDailyCost(650);
		setRoomSize(80);
		setHasBath(true);
	}
}
