import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int caption;
    private int leftCaption;
    protected String lockerSize;

    public Map<Ticket, Bag> getTicketBagMap() {
        return ticketBagMap;
    }

    public void setTicketBagMap(Map<Ticket, Bag> ticketBagMap) {
        this.ticketBagMap = ticketBagMap;
    }

    protected Map<Ticket,Bag> ticketBagMap = new HashMap<>();

    public Locker(String lockerSize, int caption) {
        this.lockerSize = lockerSize;
        this.caption = caption;
        this.leftCaption = caption;
    }

    public Ticket save(Bag bag) throws Exception {
        if(leftCaption>0)
        {
            leftCaption-=1;
            Ticket ticket = new Ticket("s");
            ticketBagMap.put(ticket,bag);
            return ticket;
        }

        throw new Exception("Locker is full");
    }

    public int getlLeftCaption() {
        return leftCaption;
    }

    public Bag pickUp(Ticket ticket) throws Exception {
        if(!ticket.getBagSize().equals(this.lockerSize))
            throw new Exception("ticket is no match");
        if(!ticketBagMap.containsKey(ticket))
            throw new Exception("no vaild ticket");
        return ticketBagMap.get(ticket);
    }

    public int getCaption() {
        return this.caption;
    }
}
