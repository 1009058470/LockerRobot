import org.junit.Assert;
import org.junit.Test;

public class LockerRobotTest {
    //region 普通用户
    //region 存包
    @Test
    public void given_a_s_bag_and_a_s_locker_and_local_has_caption_when_people_save_then_save_success_and_give_ticket()
    {
        //given
        Bag bag = new Bag("s");
        Locker locker = new Locker("s",2);
        //when
        Ticket ticket = locker.save(bag);
        //then
        Assert.assertNotNull(ticket);
    }
    //endregion
    //endregion
}
