import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimaryLockerRobot extends Robot{
    private Map<Ticket,Bag> ticketBagMap = new HashMap<>();

    public PrimaryLockerRobot(List<Locker> lockers) {
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

    public Bag pickUp(Ticket ticket) {
        return ticketBagMap.get(ticket);
    }
}
