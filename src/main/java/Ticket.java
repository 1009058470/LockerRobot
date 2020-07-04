public class Ticket {

    private String bagSize;

    public Ticket(){
        this.bagSize="";
    }
    public Ticket(String bagSize){
        this.bagSize = bagSize;
    }

    public String getBagSize() {
        return this.bagSize;
    }
}
