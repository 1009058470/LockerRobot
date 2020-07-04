import com.sun.org.apache.xpath.internal.objects.XNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobtManager {
    private Map<Ticket,Bag> ticketBagMap = new HashMap<>();
    private List<SuperLockerRobot> superLockerRobots;
    private List<PrimaryLockerRobot> primaryLockerRobot;
    private List<Locker> lockers;
    private int LockerLeftCaption = 0;
    private int primaryLockerRobotLeftCaption = 0;
    private int superLockerRobotLeftCaption = 0;

    public RobtManager(List<Locker> lockers) {
        this.lockers = lockers;
        for (Locker locker : lockers) {
            this.LockerLeftCaption += locker.getlLeftCaption();
        }
    }

    public RobtManager(List<Locker> lockers, List<PrimaryLockerRobot> primaryLockerRobots) {
        this(lockers);
        this.primaryLockerRobot = primaryLockerRobots;
        for (PrimaryLockerRobot primaryLockerRobot : primaryLockerRobots) {
            this.primaryLockerRobotLeftCaption += primaryLockerRobot.leftCaption;
        }
    }

    public RobtManager(List<Locker> lockers, List<PrimaryLockerRobot> primaryLockerRobots, List<SuperLockerRobot> superLockerRobots) {
        this(lockers, primaryLockerRobots);
        this.superLockerRobots = superLockerRobots;
        for (SuperLockerRobot superLockerRobot : superLockerRobots) {
            this.superLockerRobotLeftCaption += superLockerRobot.leftCaption;
        }
    }

    public Ticket save(Bag bag) throws Exception {
        Ticket ticket = null;
        if (bag.getBagSize().equals("s")) {
            if (LockerLeftCaption > 0) {
                LockerLeftCaption -= 1;
                try {
                    for (Locker locker : lockers) {
                        ticket = locker.save(bag);
                        if (ticket != null)
                            break;
                    }
                } catch (Exception e) {
                }
            }
        }
        else if (bag.getBagSize().equals("m")) {
            if (primaryLockerRobotLeftCaption > 0) {
                primaryLockerRobotLeftCaption -= 1;
                try {
                    for (PrimaryLockerRobot primaryLockerRobot : this.primaryLockerRobot) {
                        ticket = primaryLockerRobot.save(bag);
                        if (ticket != null)
                            break;
                    }
                } catch (Exception e) {
                }
            }
        }
        else if(bag.getBagSize().equals("l")){
            if(superLockerRobotLeftCaption>0){
                superLockerRobotLeftCaption-=1;
                try{
                    for(SuperLockerRobot superLockerRobot:superLockerRobots){
                        ticket = superLockerRobot.save(bag);
                        if(ticket!=null)
                            break;
                    }
                }catch (Exception e){ }
            }
        }
        if (ticket != null){
            ticketBagMap.put(ticket,bag);
            return ticket;
        }
        throw new Exception("Locker is full");
    }

    public Bag pickup(Ticket ticket) throws Exception {
        if(!ticketBagMap.containsKey(ticket))
            throw new Exception("no vaild ticket");
        Bag bag = null;
        for(Locker locker:lockers){
            bag = locker.pickUp(ticket);
            if(bag!=null)
                break;
        }
        return bag;
    }
}
