

public class Locker {
    private int caption;
    private int leftCaption;

    public Locker(String lockerSize, int caption) {
        this.caption = caption;
        this.leftCaption = caption;
    }

    public Ticket save(Bag bag) throws Exception {
        if(leftCaption>0)
        {
            leftCaption-=1;
            return new Ticket();
        }

        throw new Exception("Locker is full");
    }
}
