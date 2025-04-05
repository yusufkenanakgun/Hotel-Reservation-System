package HotelReservationSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class testGUI extends JFrame implements ActionListener {
	static ArrayList<ICalculable> calcArray = new ArrayList<ICalculable>();
	protected JButton reservationButton, serviceButton, displayCityButton;
	protected JMenuBar menuBar;
	protected JMenu fileMenu, newMenu, helpMenu;
	protected JMenuItem resItem, serItem, exitItem, contItem, aboutItem;
	protected JPanel buttonPanel;
	JTextArea textArea;
	
	public testGUI() {
		
		setTitle("Hotel Reservation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        newMenu = new JMenu("New");
        helpMenu = new JMenu("Help");
        
        resItem = new JMenuItem("Reservation");
        serItem = new JMenuItem("Services");
        exitItem = new JMenuItem("Exit");
        contItem = new JMenuItem("Contents");
        aboutItem = new JMenuItem("About");
        
        resItem.addActionListener(this);
        serItem.addActionListener(this);
        exitItem.addActionListener(this);
        contItem.addActionListener(this);
        aboutItem.addActionListener(this);
        
        newMenu.add(resItem);
        newMenu.add(serItem);
        fileMenu.add(exitItem);
        helpMenu.add(contItem);
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(newMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        
        final Dimension buttonSize = new Dimension(200, 30);
        reservationButton = new JButton("Display Reservations");
        reservationButton.setPreferredSize(buttonSize);
        serviceButton = new JButton("Display Services");
        serviceButton.setPreferredSize(buttonSize);
        displayCityButton = new JButton("Disp. Res. For City");
        displayCityButton.setPreferredSize(buttonSize);
        
        reservationButton.addActionListener(this);
        serviceButton.addActionListener(this);
        displayCityButton.addActionListener(this);
        
        textArea = new JTextArea(21, 40);
        textArea.setEditable(false);
        Font font = new Font("Serif", Font.BOLD, 16);
        textArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(reservationButton);
        buttonPanel.add(serviceButton);
        buttonPanel.add(displayCityButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        setVisible(true);
	}
	
      

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== exitItem) {
				System.exit(0);
			}else if(e.getSource() == resItem) {
				calcArray.add(new Reservation());
				JOptionPane.showMessageDialog(null, "Reservation has been created!");
			}else if(e.getSource() == reservationButton) {
				textArea.setText(null);
				boolean resFound = false;
            	for(ICalculable cal : calcArray) {
            		if(cal instanceof Reservation) {
            			textArea.append(cal.toString() + "\n");
            			resFound = true;
            		}
            	}
            	if(!resFound) {
            		JOptionPane.showMessageDialog(null, "No reservations available.");
            	}
			}else if(e.getSource() == displayCityButton) {
				textArea.setText(null);
                String cityName = JOptionPane.showInputDialog(null, "Type a city name for reservation search: ");
                boolean found = false;
                for (ICalculable cal : calcArray) {
                	if(cal instanceof Reservation) {
                		if(((Reservation) cal).getHotelName().contains(cityName)) {
                			textArea.append(((Reservation) cal).getHotelName());
                			found = true;
                		}
                	}
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No reservations found for " + cityName, "Error", JOptionPane.ERROR_MESSAGE);
                }
			}else if(e.getSource() == serItem) {
				boolean valid = false;
        		do {
        			try {
        				String option = JOptionPane.showInputDialog(null, "Please select one of the extra services from below:\n"
        						+ "1. Laundry Service\n"
        						+ "2. Spa Service");
        				 int select = Integer.parseInt(option);
                		if(select != 1 && select != 2) {
                			throw new Exception();
                		}
                		valid = true;
                        String number = JOptionPane.showInputDialog(null, "Type the reservation ID to assign this service: ");
                        int id = Integer.parseInt(number);
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
                    		JOptionPane.showMessageDialog(null,"Customer with given id is not found.");
                    	}
        			}catch(Exception a) {
        				JOptionPane.showMessageDialog(null,
        						"Select menu must be numeric value and between below integers. ",
        						"Error",JOptionPane.ERROR_MESSAGE);
        			}
        		}while(!valid);
			}else if(e.getSource() == serviceButton) {
				textArea.setText(null);
				boolean found = true;
				ListIterator<ICalculable> iteratorA = calcArray.listIterator();
            	while(iteratorA.hasPrevious()) {
            		iteratorA.previous();
            	}
            	while (iteratorA.hasNext()) {
            		ICalculable current = iteratorA.next();
            		if(current instanceof Reservation) {
            			textArea.append(((Reservation) current).getServiceType());
            			found = false;
            		}else if(current instanceof Laundry) {
            			textArea.append(((Laundry) current).getServiceType());
            			found = false;
            		}else if(current instanceof Spa) {
            			textArea.append(((Spa) current).getServiceType());
            			found = false;
            		}
            	}
            	if(found) {
            		JOptionPane.showMessageDialog(null,
    						"No service has been created so far.",
    						"Error",JOptionPane.ERROR_MESSAGE);
            	}
			}else if(e.getSource() == contItem) {
				JOptionPane.showMessageDialog(contItem ,"The services we can provide:\n"
						+ "Room boking, Spa , Laundry");
			}else if(e.getSource() == aboutItem) {
				JOptionPane.showMessageDialog(aboutItem ,"This is a hotel reservation system. You can book a hotel reservation\n" 
						+"with different types of rooms. You can also add Spa and \n"
						+ "Laundry services for your room as much as you want");
			}
			
		}
		  public static void main(String[] args) {
	        	new testGUI();
		}
}
