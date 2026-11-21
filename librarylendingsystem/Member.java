package librarylendingsystem;

public class Member {
    private int id;
    private String name;
    private int borrowedCount = 0;
    private double fines = 0;
    private  static  int nextId = 1;

    public Member(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.id = nextId;
        Member.nextId++;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getBorrowedCount(){
        return this.borrowedCount;
    }
    public double getFines(){
        return this.fines;
    }
    public boolean canBorrow(){
        if(this.borrowedCount < 3 && this.fines < 50.00){
            return  true;
        }else{
            return false;
        }
    }
    public void addBook(){
        this.borrowedCount++;
    }
    public void removeBook(){
        this.borrowedCount--;
    }
    public void addFine(double amount){
        if (amount > 0) {
            this.fines+=amount;
        }
        else{
            throw new IllegalArgumentException("Cant add fines");
        }
    }
    public void payFine(double amount){
        if(amount > 0 && !(amount > this.fines)){
            this.fines -= amount;
        }else{
            throw new IllegalArgumentException("cant payFine");
        }
    }
}
