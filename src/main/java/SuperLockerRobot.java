import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperLockerRobot extends Robot{
    private  Map<Ticket,Ticket> ticketTicketMap = new HashMap<>();
    private Map<Ticket,Bag> ticketBagMap = new HashMap<>();
    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket save(Bag bag) throws Exception {
        if(leftCaption!=0){
            Locker saveLocker = lockers.get(0);
            float currency = 0;
            for (Locker locker:lockers){
                if(locker.getlLeftCaption() > 0){
                    if(locker.getlLeftCaption()/locker.getCaption()>currency)
                        saveLocker = locker;
                }
            }
            Ticket ticket = saveLocker.save(bag);
            saveLocker.getTicketBagMap().remove(ticket);
            ticket.setSaveBy("SuperLockerRobot");
            ticket.setBagSize("l");
            saveLocker.getTicketBagMap().put(ticket,bag);
            ticketBagMap.put(ticket,bag);
            leftCaption-=1;
            return ticket;
        }
        throw new Exception("Locker is full");
    }

    public Bag pickUp(Ticket ticket) throws Exception {
        Bag bag = null;
        if(!ticket.getBagSize().equals("l"))
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
