import com.sun.org.apache.xpath.internal.objects.XNull;

import java.util.List;

public class RobtManager {
    private List<PrimaryLockerRobot> primaryLockerRobot;
    private List<Locker> lockers;
    private int LockerLeftCaption = 0;
    private int primaryLockerRobotLeftCaption = 0;

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
        else if(bag.getBagSize().equals("m")){
            if(primaryLockerRobotLeftCaption>0){
                primaryLockerRobotLeftCaption-=1;
                try{
                    for(PrimaryLockerRobot primaryLockerRobot:this.primaryLockerRobot){
                        ticket = primaryLockerRobot.save(bag);
                        if(ticket!=null)
                            break;;
                    }
                }catch (Exception e){}
            }
        }
        if(ticket!=null)
            return ticket;
        throw new Exception("Locker is full");
    }
}
