/*package HotelReservationSystem;
//********* I moved the main to the "testGUI" ..********
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.util.ListIterator;

public class test{
    static Scanner scan = new Scanner(System.in);
    static ArrayList<ICalculable> calcArray = new ArrayList<ICalculable>();
    public static void main(String[] args) {      
        int input;
        do {
        for (MenuOptions value : MenuOptions.values()) {
            System.out.println(value.getDescription());
        }
        input = scan.nextInt();
        switch (input) {
            case 1:
            	calcArray.add(new Reservation());
                System.out.println("Reservation with ID: " + Reservation.totalNumOfReservations + " has been created.");
                break;
            case 2:
            	boolean resFound = false;
            	for(ICalculable cal : calcArray) {
            		if(cal instanceof Reservation) {
            			//((Reservation) cal).displayInfo();
            			resFound = true;
            		}
            	}
            	if(!resFound) {
            		System.out.println("No reservations available.");
            	}
                break;
            case 3:
                System.out.println("Type a city name for reservation search: ");
                String cityName = scan.nextLine();
                cityName = scan.nextLine();
                boolean found = false;
                for (ICalculable cal : calcArray) {
                	if(cal instanceof Reservation) {
                		if(((Reservation) cal).getHotelName().contains(cityName)) {
                			((Reservation) cal).listReservations(cityName);
                			found = true;
                		}
                	}
                }
                if (!found) {
                    System.out.println("No reservations found for " + cityName);
                }else {
                	System.out.println(" ");
                }
                break;
            case 4:
        		boolean valid = false;
        		do {
        			try {
        				System.out.println("Please select one of the extra services from below:");
                		System.out.println("1. Laundry Service");
                		System.out.println("2. Spa Service");
        				int select = scan.nextInt();
                		if(select != 1 && select != 2) {
                			throw new Exception();
                		}
                		scan.nextLine();
                		valid = true;
                		System.out.println("Type the reservation ID to assign this service: ");
                        int id = scan.nextInt();
                        scan.nextLine();
                        boolean equal = false;
                        ListIterator<ICalculable> iteratorR = calcArray.listIterator();
                        while(iteratorR.hasPrevious()) {
                    		iteratorR.previous();
                    	}
                    	while (iteratorR.hasNext() && !equal) {
                    		if (((Services) iteratorR.next()).getCustomerID() == id) {
                    			equal = true;
                            	switch(select) {
                    			case 1:
                    				calcArray.add(new Laundry(id));
                    				break;
                    			case 2:
        							calcArray.add(new Spa(id));
        							
                            	}
                    		}
                    	}
                    	if(!equal) {
                    		System.out.println("Customer with given id is not found.");
                    	}
                    	break;
        			}catch(Exception e) {
        				System.err.println("Select menu must be numeric value and between below integers. ");
        				scan.nextLine();
        			}
        		}while(!valid);
        		break;
            case 5:
            	ListIterator<ICalculable> iteratorA = calcArray.listIterator();
            	while(iteratorA.hasPrevious()) {
            		iteratorA.previous();
            	}
            	while (iteratorA.hasNext()) {
            		ICalculable current = iteratorA.next();
            		if(current instanceof Reservation) {
            			((Reservation) current).totalCostOfServices();
            		}else if(current instanceof Laundry) {
            			((Laundry) current).totalCostOfServices();
            		}else if(current instanceof Spa) {
            			((Spa) current).totalCostOfServices();
            		}
            	}           
                break;
            case 6:
            	System.out.println("Grand Total Bill per Reservation ID:");
            	for (ICalculable cal : calcArray) {
                	if(cal instanceof Reservation) {
                		double totalBill = ((Reservation) cal).calculateService();
                		for (ICalculable calc : calcArray) {
                        	if(calc instanceof Laundry) {
                        		if (((Reservation) cal).getCustomerID() == ((Laundry) calc).getCustomerID()) {
                    	            totalBill += ((Laundry) calc).calculateService();
                        		}
                        	}else if(calc instanceof Spa) {
                        		if (((Reservation) cal).getCustomerID() == ((Spa) calc).getCustomerID()) {
                    	            totalBill += ((Spa) calc).calculateService();
                        		}
                        	}
                		}
                		System.out.println("Reservation ID: " + ((Reservation) cal).getCustomerID() + ", Total Bill: $" + totalBill);
                	}
            	}
                break;
            case 7:
            	calcArray.add(new Employees());
            	break;
            case 8:
            	calcArray.add(new Bills());
            	break;
            case 9:
            	System.out.println("Enter the month to calculate the general balance.");
            	String month = scan.nextLine();
            	month = scan.nextLine();
            	double incomes = 0;
            	double bills = 0;
            	double employees = 0;
            	for (ICalculable cal : calcArray) {
            		if(cal instanceof Reservation) {
            			if (((Reservation) cal).getReservationMonth().equals(month)) {
            				System.out.println("For reservation ID: "+ ((Reservation) cal).getCustomerID() 
                			+ ", Service type: Room booking, Service Cost: " + ((Reservation) cal).calculateService());
            				double totalBill = ((Reservation) cal).calculateService();
            				for (ICalculable calc : calcArray) {
                        		if(calc instanceof Spa && ((Reservation) cal).getCustomerID() == ((Spa) calc).getCustomerID()) {
                        			System.out.println("For reservation ID: "+ ((Spa) calc).getCustomerID() 
                                			+ ", Service type: Spa, Service Cost: " + ((Spa) calc).calculateService());
                        			totalBill += ((Spa) calc).calculateService();
                        		}else if(calc instanceof Laundry && ((Reservation) cal).getCustomerID() == ((Laundry) calc).getCustomerID()) {
                        			System.out.println("For reservation ID: "+ ((Laundry) calc).getCustomerID() 
                                			+ ", Service type: Laundry, Service Cost: " + ((Laundry) calc).calculateService());
                        			totalBill += ((Laundry) calc).calculateService();
                        		}
                        	}
            				incomes += totalBill;
            			}
            		}else if(cal instanceof Bills) {
            			if(((Bills) cal).getMonth().equals(month)) {
            				bills += ((Bills) cal).getCost();
            			}
            		}else if(cal instanceof Employees) {
            			employees += ((Employees) cal).getCost();

            		}
            	}
            	System.out.println("Total monthly income: " + incomes);
            	System.out.println("Total monthly bills due: " + bills);
            	System.out.println("Total monthly employee cost: " + employees);
            	System.out.println("End of month balance: " + (incomes - employees - bills));
            	break;
            case 10:
                ArrayList<Services> servicesList = new ArrayList<>();
                for (ICalculable cal : calcArray) {
                    if (cal instanceof Services) {
                        servicesList.add((Services) cal);
                    }
                }
                servicesList.sort(new CostComparator());
                for (Services service : servicesList) {
                    service.displayServiceInfo();
                }
                break;
            case 11:
                ArrayList<Reservation> reservationsList = new ArrayList<>();
                for (ICalculable cal : calcArray) {
                    if (cal instanceof Reservation) {
                        reservationsList.add((Reservation) cal);
                    }
                }
                reservationsList.sort(Reservation::compareTo);
                for (Reservation reservation : reservationsList) {
                    reservation.displayServiceInfo();
                }
                break;
            case 12:
                System.out.println("Exiting, Goodbye!");
                break;
            default:
            	System.out.println("");
                System.err.println("You entered an invalid menu option. Enter again.");
                System.out.println("");
                break;
        }
        }while(input!=12);
        
    }
}*/