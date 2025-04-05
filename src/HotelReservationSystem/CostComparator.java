package HotelReservationSystem;
import java.util.Comparator;

public class CostComparator implements Comparator<Services> {


	@Override
    public int compare(Services service1, Services service2) {
        double cost1 = service1.calculateService();
        double cost2 = service2.calculateService();
        if (cost1 < cost2) {
            return 1;
        } else if (cost1 > cost2) {
            return -1;
        }
        return 0;
    }

}
