package HotelReservationSystem;

import javax.swing.*;

public class Reservation extends Services implements Comparable<Reservation>{
    static String monthsArray[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    static int totalNumOfReservations = 0;
    private String hotelName;
    private String reservationMonth;
    private int reservationStart;
    private int reservationEnd;
    private String roomType;
    private int dailyCost;
    private int roomSize;
    private boolean hasBath;
	private String leaveMonth;
    private int totalCost;
    Room room;
    
    public Reservation(){
    	super();
    	this.CustomerID = getCustomerID();
        this.hotelName = JOptionPane.showInputDialog(null, "Enter Hotel Name:");
        boolean roomCheck = false;
        do {
        	try {
                this.roomType = addRoom();
                validateRoomType(roomType);
                roomCheck = true;
        	}catch(Exception e) {
        		if(e instanceof RoomTypeException) {
        			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        }while(!roomCheck);
        boolean monthCheck = false;
        do {
        	try {
                this.reservationMonth = JOptionPane.showInputDialog(null, "Reservation Month: ");
                for(String s : monthsArray) {
                	if(reservationMonth.equals(s)) {
                		monthCheck = true;
                	}
                }
                if(!monthCheck) {
                	throw new Exception();
                }
        	}catch(Exception e) {
        		JOptionPane.showMessageDialog(null, "You entered an invalid month! Enter again.",null ,JOptionPane.ERROR_MESSAGE);
        	}
        }while(!monthCheck);
        
        boolean start = false;
        do {
            try {
                String startStr = JOptionPane.showInputDialog(null, "Enter Reservation Start:");
                this.reservationStart = Integer.parseInt(startStr);
                start = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Reservation Start must be a numeric value!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!start);

        boolean end = false;
        do {
            try {
                String endStr = JOptionPane.showInputDialog(null, "Enter Reservation End:");
                this.reservationEnd = Integer.parseInt(endStr);
                end = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Reservation End must be a numeric value!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!end);
        
        switch (roomType) {
        case "Single":
            room = new Single();
            break;
        case "Double":
        	room = new Double();
            break;
        case "Club":
        	room = new Club();
            break;
        case "Family":
        	room = new Family();
            break;
        case "FamilyView":
        	room = new FamilyView();
            break;
        case "Suite":
        	room = new Suite();
            break;
        } 
        this.dailyCost = room.getDailyCost();
        this.roomSize = room.getRoomSize();
        this.hasBath = room.isHasBath();     
    }
    @Override
	public double getCost() {
		return calculateService();
	}
    @Override
	protected void displayServiceInfo() {
		System.out.println("Hotel name: " +this.hotelName +
				", CustomerID: " + this.CustomerID +
				", Service Type: " + getServiceType() +
				", Total Cost: " + calculateService());
	}
    @Override
    public int compareTo(Reservation otherReservation) {
        return this.hotelName.compareTo(otherReservation.getHotelName());
    }
    
    @Override
	public double calculateService(){
    	if (reservationMonth.equals("June") || reservationMonth.equals("July") || reservationMonth.equals("August")) {
            return calculateTotalPrice(reservationStart, reservationEnd, dailyCost, reservationMonth);
        } else {
            return calculateTotalPrice(reservationStart, reservationEnd, dailyCost);
        }
	}
	@Override
	public String getServiceType() {
		return "Reservation ID #" + this.CustomerID +
				" has " + this.getCost() + " cost assigned for Room booking Service\n";
	}
	
	 private void validateRoomType(String roomType) throws RoomTypeException {
	        switch (roomType) {
	            case "Single":
	            case "Double":
	            case "Club":
	            case "Family":
	            case "FamilyView":
	            case "Suite":
	                break;
	            default:
	                throw new RoomTypeException("Invalid room type input.Try again!");
	        }
	    }

    public static int calculateTotalPrice(int reservationStart, int reservationEnd, int dailyCost) {
        int daysOfReservation = (reservationStart > reservationEnd) ? (30 - reservationStart + reservationEnd) : (reservationEnd - reservationStart);
        return daysOfReservation * dailyCost;
    }
    public static int calculateTotalPrice(int reservationStart, int reservationEnd, int dailyCost, String reservationMonth) {
        int daysOfReservation = (reservationStart > reservationEnd) ? (30 - reservationStart + reservationEnd) : (reservationEnd - reservationStart);
        return 2*daysOfReservation * dailyCost;
    }
    
    @Override
    public String toString() {
        int index = 0;
        while (!reservationMonth.contains(monthsArray[index])) {
            index++;
        }
        this.leaveMonth = (reservationStart > reservationEnd) ? monthsArray[(index + 1) % 12] : monthsArray[index];
        if (reservationMonth.equals("June") || reservationMonth.equals("July") || reservationMonth.equals("August")) {
            this.totalCost = calculateTotalPrice(reservationStart, reservationEnd, dailyCost, reservationMonth);
        } else {
            this.totalCost = calculateTotalPrice(reservationStart, reservationEnd, dailyCost);
        }
        return "Reservation ID #" + this.CustomerID
        		+"\nReservation for a " + roomType + " room in " + hotelName
                + " starts on " + reservationMonth + " " + reservationStart + " and\nends on "
                + leaveMonth + " " + reservationEnd + ". Reservation has a total cost of $"
                + totalCost + ".\n";
    }


    	
    public static String addRoom() {
    	return JOptionPane.showInputDialog(null,"Enter the room type:\n"
    			+"ROOM INFOS:\n"
                + "Room Type: Single, Daily Cost: 100, Room Size: 15, Has Bath: false\n"
                + "Room Type: Double, Daily Cost: 180, Room Size: 30, Has Bath: false\n"
                + "Room Type: Club, Daily Cost: 250, Room Size: 45, Has Bath: true\n"
                + "Room Type: Family, Daily Cost: 400, Room Size: 50, Has Bath: false\n"
                + "Room Type: Family With View, Daily Cost: 450, Room Size: 50, Has Bath: true\n"
                + "Room Type: Suite, Daily Cost: 650, Room Size: 80, Has Bath: true");
    }
    
    public boolean listReservations(String cityName) {
        if (hotelName.toLowerCase().contains(cityName.toLowerCase())) {
            return true;
        }else {
            return false;
            }
    }
  

    
    public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getReservationMonth() {
		return reservationMonth;
	}
	public void setReservationMonth(String reservationMonth) {
		this.reservationMonth = reservationMonth;
	}
	public int getReservationStart() {
		return reservationStart;
	}
	public void setReservationStart(int reservationStart) {
		this.reservationStart = reservationStart;
	}
	public int getReservationEnd() {
		return reservationEnd;
	}
	public void setReservationEnd(int reservationEnd) {
		this.reservationEnd = reservationEnd;
	}
	public String getLeaveMonth() {
		return leaveMonth;
	}
	public void setLeaveMonth(String leaveMonth) {
		this.leaveMonth = leaveMonth;
	}
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
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
