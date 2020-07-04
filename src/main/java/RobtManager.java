import com.sun.org.apache.xpath.internal.objects.XNull;

import java.util.List;

public class RobtManager {
    private final List<Locker> lockers;
    private int leftCaption = 0;
    public RobtManager(List<Locker> lockers) {
        this.lockers = lockers;
        for(Locker locker:lockers){
            this.leftCaption+=locker.getlLeftCaption();
        }
    }

    public Ticket save(Bag bag) throws Exception {
        Ticket ticket = null;
        if(leftCaption>0){
            leftCaption-=1;
            try {
                for(Locker locker:lockers){
                    ticket = locker.save(bag);
                    if(ticket!=null)
                        break;
                }
            }catch (Exception e){ }

            return ticket;
        }
        throw new Exception("Locker is full");
    }
}
