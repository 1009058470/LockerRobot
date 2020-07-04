import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int caption;
    private int leftCaption;
    private Map<Ticket,Bag> ticketBagMap = new HashMap<>();

    public Locker(String lockerSize, int caption) {
        this.caption = caption;
        this.leftCaption = caption;
    }

    public Ticket save(Bag bag) throws Exception {
        if(leftCaption>0)
        {
            leftCaption-=1;
            Ticket ticket = new Ticket();
            ticketBagMap.put(ticket,bag);
            return ticket;
        }

        throw new Exception("Locker is full");
    }

    public int getlLeftCaption() {
        return leftCaption;
    }

    public Bag pickUp(Ticket ticket) throws Exception {
        if(!ticketBagMap.containsKey(ticket))
            throw new Exception("no vaild ticket");
        return ticketBagMap.get(ticket);
    }
}
