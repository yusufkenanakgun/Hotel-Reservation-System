package HotelReservationSystem;

public class Bills implements ICalculable{
	private String type, month;
	private double amount;
	public Bills() {
		System.out.println("Enter the type of the Bill that is going to be bought: ");
		this.type = scan.nextLine();
		boolean amountCheck = false;
        do {
        	try {
        		System.out.println("Enter the amount of the Bill that is going to be bought: ");
        		this.amount = scan.nextDouble();
        		scan.nextLine();
                amountCheck = true;
            }catch(Exception e) {
            	System.err.println("Bill amount must be a numeric value!");
            	scan.nextLine();
            }
        }while(!amountCheck);
        boolean monthCheck = false;
        do {
        	try {
        		System.out.println("Enter the month of the Bill that is going to be bought: ");
        		this.month = scan.nextLine();
        		for(String s : Reservation.monthsArray) {
        			if(month.equals(s)) {
        				monthCheck = true;
        			}
        		}
        		if(!monthCheck) {
        			throw new Exception();
        		}
            }catch(Exception e) {
            	System.err.println("You entered valid month input. Enter again.!");
            }
        }while(!monthCheck);
		
	}
	
	@Override
	public double getCost() {
		return this.amount;
	}
	
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
