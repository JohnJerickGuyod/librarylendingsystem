package librarylendingsystem;

public class Ebook extends Book {
    private double fileSizeMb;

    public Ebook(String title, String author, double fileSizeMb){
        super(title, author);
        if (fileSizeMb <= 0) {
            throw new IllegalArgumentException("File size must be greater than 0");
        }
        this.fileSizeMb = fileSizeMb;
    }
    public double getFileSizeMb(){
        return this.fileSizeMb;
    }
    public String describeFile(){
        return this.fileSizeMb + " MB" ;
    }
}
