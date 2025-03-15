public interface PassengerCapable {

    int getPassengerCapacity();

    default boolean hasBusinessClassSeats() {
        return true;
    }
}


