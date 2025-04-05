package HotelReservationSystem;

public enum MenuOptions {
    CREATE_NEW_RESERVATION_WITH_ROOM_TYPE_AND_CITY("1. Create new Reservation with Room Type and city "),
    DISPLAY_ALL_RESERVATIONS("2. Display all Reservations"),
    LIST_THE_RESERVATIONS_FOR_A_SPECIFIC_CITY("3. List the reservations for a specific city "),
    ADD_EXTRA_SERVİCES_TO_A_RESERVATION("4. Add extra services to a reservation"),
    CALCULATE_TOTAL_COST_FOR_EACH_SERVİCE("5. Calculate total cost for each service "),
    DISPLAY_THE_TOTAL_COST_OF_EVERY_CUSTOMER("6. Display the total cost of every customer"),
    ADD_AN_EMPLOYEE("7. Add an employee"),
    ADD_A_BILL("8. Add a bill"),
    GET_MONTHLY_PRİCE("9. Get monthly price"),
    LIST_ALL_SEVICES_BASED_ON_COST("10. List all Services sorted based on cost"),
    LIST_ALL_RESERVATION_BASED_ON_HOTELNAMES("11. List all Reservations sorted based on hotel names"),
    EXIT("12. Exit");

    private final String description;

    MenuOptions(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
