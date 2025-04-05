package HotelReservationSystem;

import javax.swing.JOptionPane;

public class Spa extends Services {
	int days;
	double spaCost;
	public Spa(int CustomerID){
		super(CustomerID);
		boolean bool = true;
		do {
			try {
				System.out.println("How many days? ");
				String option = JOptionPane.showInputDialog(null, "How many days? ");
				this.days = Integer.parseInt(option);
				bool = false;
			}catch(Exception e) {
				JOptionPane.showInputDialog(null, "Day count must be a numeric value! ");
			}
		}while(bool);
		
		
	}
	@Override
	public double getCost() {
		return calculateService();
	}
	@Override
	protected void displayServiceInfo() {
		System.out.println("CustomerID: " + this.CustomerID +
				" Service Type: " + getServiceType() +
				" Total Cost: " + calculateService());
	}
	@Override
	public double calculateService(){
		spaCost = 100*days;
		return spaCost;
	}
	@Override
	public String getServiceType() {
		return "Reservation ID #" + this.CustomerID +
				" has " + this.days + " pieces assigned for SPA Service\n";
	}
}
