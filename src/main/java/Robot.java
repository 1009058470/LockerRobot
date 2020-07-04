import java.util.List;

public class Robot {
    protected List<Locker> lockers;
    protected int leftCaption;
    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
        for (Locker locker : lockers) {
            leftCaption += locker.getlLeftCaption();
        }
    }

    public Ticket save(Bag bag) throws Exception {
        throw new Exception("must do a real save");
    }
}
