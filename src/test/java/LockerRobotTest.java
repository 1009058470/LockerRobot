import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LockerRobotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
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
    //endregion
    //endregion
}
