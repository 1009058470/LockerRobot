import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class LockerRobotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PrimaryLockerRobot getPrimaryLockerRobot(boolean IsFull) throws Exception {
        Locker locker = new Locker("m",2);
        if (IsFull) {
            for(int i = 0;i <2;i++){
                locker.save(new Bag("m"));
            }
        }
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker));
        return primaryLockerRobot;
    }

    private SuperLockerRobot getSuperLockerRobot(boolean IsFull) throws Exception {
        Locker locker = new Locker("l",2);
        if (IsFull) {
            for(int i = 0;i <2;i++){
                locker.save(new Bag("l"));
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
        Locker locker2 = new Locker("m",2);
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
        Locker locker2 = new Locker("m",2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2));
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(true);

        //when
        superLockerRobot.save(bag);
        superLockerRobot.save(bag);
    }

    //endregion

    //region 取包
    @Test
    public void given_a_s_bag_and_save_success_and_give_a_valid_ticket_when_people_pick_up_bag_and_then_pick_up_successful() throws Exception {
        //give
        Locker locker = new Locker("s",1);
        Bag bag = new Bag("s");
        Ticket ticket = locker.save(bag);
        //when
        Bag bag2 = locker.pickUp(ticket);
        //then
        Assert.assertEquals(bag,bag2);
    }

    @Test
    public void given_a_s_bag_and_save_success_and_give_a_no_valid_ticket_when_people_pick_up_bag_and_then_pick_up_successful() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild ticket");


        //give
        Locker locker = new Locker("s",1);
        Bag bag = new Bag("s");
        locker.save(bag);
        //when
        Bag bag2 = locker.pickUp(new Ticket("s"));

    }

    @Test
    public void given_a_m_bag_and_save_success_and_give_a_valid_ticket_when_people_pick_up_bag_and_then_pick_up_successful() throws Exception {
        //given
        Bag bag = new Bag("m");
        PrimaryLockerRobot primaryLockerRobot = getPrimaryLockerRobot(false);
        Ticket ticket = primaryLockerRobot.save(bag);
        //when
        Bag bag2 = primaryLockerRobot.pickUp(ticket);
        //then
        Assert.assertEquals(bag,bag2);
    }

    @Test
    public void given_a_m_bag_and_save_success_and_give_a_no_valid_ticket_when_people_pick_up_bag_and_then_pick_up_successful() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild ticket");

        //given
        Bag bag = new Bag("m");
        PrimaryLockerRobot primaryLockerRobot = getPrimaryLockerRobot(false);
        primaryLockerRobot.save(bag);
        //when
        primaryLockerRobot.pickUp(new Ticket("m"));
    }

    @Test
    public void given_a_l_bag_and_save_success_and_give_a_valid_ticket_when_super_locker_robot_pick_up_bag_and_then_pick_up_successful() throws Exception {
        //given
        Bag bag = new Bag("l");
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(false);
        Ticket ticket = superLockerRobot.save(bag);
        //when
        Bag bag1 = superLockerRobot.pickUp(ticket);
        //then
        Assert.assertEquals(bag,bag1);
    }

    @Test
    public void given_a_l_bag_and_save_success_and_give_a_no_valid_ticket_when_super_locker_robot_pick_up_bag_and_then_pick_up_successful() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("ticket is no match");

        //given
        Bag bag = new Bag("l");
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(false);
        superLockerRobot.save(bag);
        //when
        Bag bag1 = superLockerRobot.pickUp(new Ticket());

    }

    @Test
    public void given_a_s_bag_and_save_success_and_give_a_no_valid_ticket_when_super_locker_robot_pick_up_bag_and_then_pick_up_fail_and_get_message_ticker_is_not_match() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("ticket is no match");

        //given
        Bag bag = new Bag("s");
        Locker locker = new Locker("s",1);
        Ticket ticket = locker.save(bag);
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(false);
        //when
        superLockerRobot.pickUp(ticket);

    }

    @Test
    public void given_a_s_bag_and_save_success_and_give_a_no_valid_ticket_when_primary_locker_robot_pick_up_bag_and_then_pick_up_fail_and_get_message_ticker_is_not_match() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("ticket is no match");

        //given
        Bag bag = new Bag("s");
        Locker locker = new Locker("s",1);
        Ticket ticket = locker.save(bag);
        PrimaryLockerRobot primaryLockerRobot = getPrimaryLockerRobot(false);
        //when
        primaryLockerRobot.pickUp(ticket);

    }

    @Test
    public void given_a_l_bag_and_save_success_and_give_a_no_valid_ticket_when_locker_pick_up_bag_and_then_pick_up_fail_and_get_message_ticker_is_not_match() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("ticket is no match");

        //given
        Bag bag = new Bag("l");
        SuperLockerRobot superLockerRobot = getSuperLockerRobot(false);
        Locker locker = new Locker("s",1);
        Ticket ticket = superLockerRobot.save(bag);
        //when
        locker.pickUp(ticket);

    }
    //endregion
    //endregion

    //#region VIP用户
    //#region 存包
    @Test
    public void given_a_s_bag_and_a_manager_has_a_s_locker_when_manager_save_bag_then_save_success() throws Exception {
        //given
        Bag bag = new Bag("s");
        RobtManager manager = new RobtManager(Arrays.asList(new Locker("s",1)));
        //when
        Ticket ticket = manager.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }

    @Test
    public void given_a_s_bag_and_a_manager_has_a_s_locker_without_valid_caption_when_manager_save_bag_then_save_success() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("Locker is full");

        //given
        Bag bag = new Bag("s");
        RobtManager manager = new RobtManager(Arrays.asList(new Locker("s",1)));
        manager.save(bag);
        //when
        manager.save(new Bag("s"));
    }

    @Test
    public void given_a_m_bag_and_a_manager_has_a_primate_locker_robot_with_a_m_locker_when_manager_save_bag_then_save_success() throws Exception {
        //given
        Bag bag = new Bag("m");
        RobtManager manager = new RobtManager(Arrays.asList(),Arrays.asList(getPrimaryLockerRobot(false)));
        //when
        Ticket ticket = manager.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }

    @Test
    public void given_a_m_bag_and_a_manager_has_a_primate_locker_robot_with_a_m_locker_with_no_valid_caption_when_manager_save_bag_then_save_success() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("Locker is full");

        //given
        Bag bag = new Bag("m");
        RobtManager manager = new RobtManager(Arrays.asList(),Arrays.asList(getPrimaryLockerRobot(true)));
        //when
        manager.save(bag);
    }

    @Test
    public void given_a_l_bag_and_a_manager_has_a_super_locker_robot_with_a_l_locker_when_manager_save_bag_then_save_success() throws Exception {
        //given
        Bag bag = new Bag("l");
        RobtManager manager = new RobtManager(Arrays.asList(),Arrays.asList(),Arrays.asList(getSuperLockerRobot(false)));
        //when
        Ticket ticket = manager.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }

    @Test
    public void given_a_l_bag_and_a_manager_has_a_super_locker_robot_with_a_l_locker_without_valid_caption_when_manager_save_bag_then_save_success() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("Locker is full");

        //given
        Bag bag = new Bag("l");
        RobtManager manager = new RobtManager(Arrays.asList(),Arrays.asList(),Arrays.asList(getSuperLockerRobot(true)));
        //when
        manager.save(bag);
    }
    //#endregion

    //#region 取包
    @Test
    public void given_a_manager_a_s_bag_and_save_success_and_a_valid_ticket_when_manager_pickup_bag_then_pick_up_success() throws Exception {
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(),Arrays.asList());
        Bag bag = new Bag("s");
        Ticket ticket = robtManager.save(bag);
        //when
        Bag bag1 = robtManager.pickup(ticket);
        //then
        Assert.assertEquals(bag,bag1);
    }

    @Test
    public void given_a_manager_a_s_bag_and_save_success_and_a_no_valid_ticket_when_manager_pickup_bag_then_pick_up_success() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild ticket");
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(),Arrays.asList());
        Bag bag = new Bag("s");
        robtManager.save(bag);
        //when
        robtManager.pickup(new Ticket());
    }

    @Test
    public void given_a_manager_a_m_bag_and_save_success_and_a_valid_ticket_when_manager_pickup_bag_then_pick_up_success() throws Exception {
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(getPrimaryLockerRobot(false)),Arrays.asList());
        Bag bag = new Bag("m");
        Ticket ticket = robtManager.save(bag);
        //when
        Bag bag1 = robtManager.pickup(ticket);
        //then
        Assert.assertEquals(bag,bag1);
    }

    @Test
    public void given_a_manager_a_m_bag_and_save_success_and_a_no_valid_ticket_when_manager_pickup_bag_then_pick_up_success() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild ticket");
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(getPrimaryLockerRobot(false)),Arrays.asList());
        Bag bag = new Bag("m");
        robtManager.save(bag);
        //when
        robtManager.pickup(new Ticket());
    }

    @Test
    public void given_a_manager_a_l_bag_and_save_success_and_a_valid_ticket_when_manager_pickup_bag_then_pick_up_success() throws Exception {
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(getPrimaryLockerRobot(false)),Arrays.asList(getSuperLockerRobot(false)));
        Bag bag = new Bag("l");
        Ticket ticket = robtManager.save(bag);
        //when
        Bag bag1 = robtManager.pickup(ticket);
        //then
        Assert.assertEquals(bag,bag1);
    }

    @Test
    public void given_a_manager_a_l_bag_and_save_success_and_a_no_valid_ticket_when_manager_pickup_bag_then_pick_up_success() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild ticket");
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(getPrimaryLockerRobot(false)),Arrays.asList(getSuperLockerRobot(false)));
        Bag bag = new Bag("l");
        robtManager.save(bag);
        //when
        robtManager.pickup(new Ticket());
    }
    //#endregion
    //#endregion

    //#region 配置Rebot和Manger
    @Test
    public void given_manager_a_s_locker_then_success() throws Exception {
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("s",1)),Arrays.asList(),Arrays.asList());
        //when
        //then
        Assert.assertNotNull(robtManager);
    }

    @Test
    public void given_manager_a_m_locker_then_failed() throws Exception {
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild setting");
        //given
        RobtManager robtManager = new RobtManager(Arrays.asList(new Locker("m",1)),Arrays.asList(),Arrays.asList());
        //when

    }

    @Test
    public void given_primary_locker_robot_a_m_locker_then_success() throws Exception{
        //given
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(new Locker("m",1)));
        //when
        //then
        Assert.assertNotNull(primaryLockerRobot);
    }

    @Test
    public void given_primary_locker_robot_a_s_locker_then_success() throws Exception{
        //then
        thrown.expect(Exception.class);
        thrown.expectMessage("no vaild setting");

        //given
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(new Locker("s",1)));
        //when
    }
    //#endregion
}
