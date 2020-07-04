import java.util.List;

public class SuperLockerRobot extends Robot{
    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket save(Bag bag) throws Exception {
        if(leftCaption!=0){
            leftCaption-=1;
            return new Ticket();
        }
        throw new Exception("Locker is full");
    }
}
