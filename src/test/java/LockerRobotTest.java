import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class LockerRobotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PrimaryLockerRobot getPrimaryLockerRobot(boolean IsFull) throws Exception {
        Locker locker = new Locker("M",2);
        if (IsFull) {
            for(int i = 0;i <2;i++){
                locker.save(new Bag("M"));
            }
        }
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker));
        return primaryLockerRobot;
    }

    private SuperLockerRobot getSuperLockerRobot(boolean IsFull) throws Exception {
        Locker locker = new Locker("L",2);
        if (IsFull) {
            for(int i = 0;i <2;i++){
                locker.save(new Bag("L"));
            }
        }
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(locker));
        return superLockerRobot;
    }

    //region 普通用户
    //region 存包
    @Test
    public void given_a_s_bag_and_a_s_locker_and_local_has_caption_when_people_save_then_save_success_and_give_ticket() throws Exception {
        //given
        Bag bag = new Bag("s");
        Locker locker = new Locker("s",2);
        //when
        Ticket ticket = locker.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }

    @Test
    public void given_a_s_bag_and_a_s_locker_and_local_has_no_valid_caption_when_people_save_then_save_success_and_give_ticket() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("Locker is full");

        //given
        Bag bag = new Bag("s");
        Locker locker = new Locker("s",1);
        locker.save(bag);
        //when
        locker.save(bag);
    }

    @Test
    public void give_a_m_bag_and_a_s_locker_and_a_primary_locker_robot_with_a_m_locker_has_valid_caption_when_people_save_then_save_success_and_given_ticket() throws Exception {
        //given
        Bag bag = new Bag("m");
        Locker locker = new Locker("s",2);
        PrimaryLockerRobot primaryLockerRobot = getPrimaryLockerRobot(false);
        //when
        Ticket ticket = primaryLockerRobot.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }

    @Test
    public void give_a_m_bag_and_a_s_locker_and_a_primary_locker_robot_with_a_m_locker_has_no_valid_caption_when_people_save_then_save_success_and_given_ticket() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("Locker is full");
        //given
        Bag bag = new Bag("m");
        Locker locker = new Locker("s",2);
        PrimaryLockerRobot primaryLockerRobot = getPrimaryLockerRobot(true);
        //when
        primaryLockerRobot.save(bag);
        primaryLockerRobot.save(new Bag("m"));
    }

    @Test
    public void give_a_l_bag_and_a_s_locker_and_a_primary_locker_robot_with_a_m_locker_and_supe_locker_robot_has_valid_caption_when_people_save_then_save_success_and_given_ticket() throws Exception {
        //given
        Bag bag = new Bag("L");
        Locker locker = new Locker("s",2);
        Locker locker2 = new Locker("M",2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2));
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(false);

        //when
        Ticket ticket = superLockerRobot.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }

    @Test
    public void give_a_l_bag_and_a_s_locker_and_a_primary_locker_robot_with_a_m_locker_and_supe_locker_robot_has_no_valid_caption_when_people_save_then_save_success_and_given_ticket() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("Locker is full");
        //given
        Bag bag = new Bag("L");
        Locker locker = new Locker("s",2);
        Locker locker2 = new Locker("M",2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2));
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(true);

        //when
        superLockerRobot.save(bag);
        superLockerRobot.save(bag);
    }

    //endregion
    //endregion
}
