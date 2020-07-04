public class Ticket {

    public String getSaveBy() {
        return saveBy;
    }

    public void setSaveBy(String saveBy) {
        this.saveBy = saveBy;
    }

    public void setBagSize(String bagSize) {
        this.bagSize = bagSize;
    }

    private String saveBy;
    private String bagSize;

    public Ticket(){
        this.bagSize="";
    }
    public Ticket(String bagSize){
        this.bagSize = bagSize;
        saveBy = "locker";
    }

    public Ticket(String bagSize,String saveBy){
        this(bagSize);
        this.saveBy = saveBy;
    }

    public String getBagSize() {
        return this.bagSize;
    }
}
