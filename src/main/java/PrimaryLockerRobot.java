import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;
    private int leftCaption;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
        for (Locker locker : lockers) {
            leftCaption += locker.getlLeftCaption();
        }
    }

    public Ticket save(Bag bag) throws Exception {
        if(leftCaption!=0){
            leftCaption-=1;
            return new Ticket();
        }
        throw new Exception("Locker is full");
    }
}
