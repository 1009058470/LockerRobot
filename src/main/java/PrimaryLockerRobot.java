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
            Ticket ticket = new Ticket("m","primaryLockerRobot");
            ticketBagMap.put(ticket,bag);
            leftCaption-=1;
            for (Locker locker:lockers) {
                try {
                    if(locker.getlLeftCaption()>0){
                        locker.getTicketBagMap().put(ticket,bag);
                        locker.save(bag);
                        break;
                    }
                }
                catch (Exception e){}
            }
            return ticket;
        }
        throw new Exception("Locker is full");
    }

    public Bag pickUp(Ticket ticket) throws Exception {
        Bag bag = null;
        if(!ticket.getBagSize().equals("m"))
            throw new Exception("ticket is no match");
        if(!ticketBagMap.containsKey(ticket))
            throw new Exception("no vaild ticket");
        for(Locker locker:lockers){
            try{
                bag = locker.pickUp(ticket);
                if(bag!=null)
                    break;
            }catch (Exception e){}
        }
        return bag;
    }
}
