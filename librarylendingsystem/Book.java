package librarylendingsystem;

public class Book{
    private int id;
    private String title;
    private String author;
    private boolean available;
    private  static int nextId = 1;
    Book(String title, String author){
        if(title == null || title.isBlank()){
           throw new IllegalArgumentException("Title cannot be blank"); 
        }
         if(author == null || author.isBlank()){
           throw new IllegalArgumentException("Author cannot be blank"); 
        }
        this.id = Book.nextId;
        this.title = title;
        this.author = author;
        this.available = true;
        Book.nextId++;
    }
    public int getId(){
        return this.id;
    }
    public String getTitle(){
        return  this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public boolean isAvailable(){
        return this.available;
    }
    public void borrow(){
        this.available = false;
    }
    public void giveBack(){
        this.available = isAvailable();
    }
    public String describe(){
        String status = this.available ? "[AVAILABLE]" : "[BORROWED]";
        return "#"+getId()+" "+ getTitle()+ " by " + getAuthor() + " "+ status;  
    }
}