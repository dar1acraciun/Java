import java.time.LocalTime;

public class Flight {
    private LocalTime startTimeArrival, endTimeArrival;
    private int indentifNum;
    private AirCraft airCraft;

    public Flight(LocalTime startTimeArrival, LocalTime endTimeArrival, int indentifNum, AirCraft airCraft) {
        this.startTimeArrival = startTimeArrival;
        this.endTimeArrival = endTimeArrival;
        this.indentifNum = indentifNum;
        this.airCraft = airCraft;
    }

    public LocalTime getStartTimeArrival() {
        return startTimeArrival;
    }

    public void setStartTimeArrival(LocalTime startTimeArrival) {
        this.startTimeArrival = startTimeArrival;
    }

    public LocalTime getEndTimeArrival() {
        return endTimeArrival;
    }

    public void setEndTimeArrival(LocalTime endTimeArrival) {
        this.endTimeArrival = endTimeArrival;
    }

    public int getIndentifNum() {
        return indentifNum;
    }

    public void setIndentifNum(int indentifNum) {
        this.indentifNum = indentifNum;
    }

    public AirCraft getAirCraft() {
        return airCraft;
    }

    public void setAirCraft(AirCraft airCraft) {
        this.airCraft = airCraft;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "indentifNum=" + indentifNum +'}';
    }
    public static int compareByStartTime(LocalTime startTime1,LocalTime startTime2) {
        if(startTime1.isBefore(startTime2))
            return 1;
        else return 0;


    }

}
