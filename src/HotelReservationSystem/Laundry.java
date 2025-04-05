package HotelReservationSystem;

import javax.swing.JOptionPane;

public class Laundry extends Services {
	int clothingPieces;
	double laundryCost;
	public Laundry(int CustomerID) {
		super(CustomerID);
		boolean bool = true;
		do {
			try {
				String option = JOptionPane.showInputDialog(null, "How many pieces of clothing?");
				this.clothingPieces = Integer.parseInt(option);
				bool = false;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Day count must be a numeric value! ");
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
		laundryCost = 10*clothingPieces;
		return laundryCost;
	}
	@Override
	public String getServiceType() {
		return "Reservation ID #" + this.CustomerID +
				" has " + this.clothingPieces + " pieces assigned for Laundry Service\n";
	}
	
	
	
	
	
}
