package HotelReservationSystem;

public class Employees implements ICalculable {
	private String name, surname;
	private double monthlyPayment;
	private int id;
	public Employees() {
		System.out.println("Enter the employee's name: ");
		this.name = scan.nextLine();
		System.out.println("Enter the employee's surname: ");
		this.surname = scan.nextLine();
		boolean idCheck = false;
        do {
        	try {
        		System.out.println("Enter the employee's id: ");
        		this.id = scan.nextInt();
        		scan.nextLine();
                idCheck = true;
            }catch(Exception e) {
            	System.err.println("Employee's id must be a numeric value!");
            	scan.nextLine();
            }
        }while(!idCheck);
        boolean salaryCheck = false;
        do {
        	try {
        		System.out.println("Enter the employee's monthly salary: ");
        		this.monthlyPayment = scan.nextDouble();
        		scan.nextLine();
                salaryCheck = true;
            }catch(Exception e) {
            	System.err.println("Employee's salary must be a numeric value!");
            	scan.nextLine();
            }
        }while(!salaryCheck);
		
		
	}
	
	@Override
	public double getCost() {
		return this.monthlyPayment;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
