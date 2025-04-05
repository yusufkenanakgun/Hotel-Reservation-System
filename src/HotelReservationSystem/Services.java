package HotelReservationSystem;

public abstract class Services implements ICalculable{
	protected int CustomerID;
	
	public Services() {
		Reservation.totalNumOfReservations++;
		this.CustomerID = Reservation.totalNumOfReservations;
	}
	public Services(int CustomerID) {
        this.CustomerID = CustomerID;
    }
	abstract String getServiceType();
	abstract double calculateService();
	
	protected void displayServiceInfo() {
		System.out.println("CustomerID: " + this.CustomerID +
				" Service Type: " + getServiceType() +
				" Total Cost: " + calculateService());
	}
	
	protected String totalCostOfServices() {
		return ("The cost for the " + getServiceType() + 
				" \nservice of reservation ID " + this.CustomerID + ": " + calculateService());
    }
	
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}		
}
