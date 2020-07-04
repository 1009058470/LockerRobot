import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperLockerRobot extends Robot{
    private Map<Ticket,Bag> ticketBagMap = new HashMap<>();
    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket save(Bag bag) throws Exception {
        if(leftCaption!=0){
            Ticket ticket = new Ticket();
            ticketBagMap.put(ticket,bag);
            leftCaption-=1;
            return ticket;
        }
        throw new Exception("Locker is full");
    }

    public Bag pickUp(Ticket ticket) throws Exception {
        if(!ticketBagMap.containsKey(ticket))
            throw new Exception("no vaild ticket");
        return ticketBagMap.get(ticket);
    }
}
