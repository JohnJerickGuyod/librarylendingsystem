package librarylendingsystem;

public class Loan {
    private Book book;
    private Member member;
    private boolean returned;

    public Loan(Book book, Member member){
        this.member = member;
        this.book = book;
        this.returned = false;
    }
    public Book getBook(){
        return this.book;
    }
    public Member getMember(){
        return this.member;
    }
    public boolean isReturned(){
        return this.returned;
    }
    public void markReturned(){
        this.returned = true;
    }
}
