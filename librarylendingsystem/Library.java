package librarylendingsystem;
import java.util.ArrayList;
public class Library {
    private String name;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();// Book[] books = new Book[50];
    private ArrayList<Loan> loans = new ArrayList<>();


    public Library(String name){
        this.name = name;
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void addMember(Member member){
        members.add(member);
    }
    public String getName(){
        return this.name;
    }
    public Loan checkOut(int memberId, int bookId) throws LibraryException {
        Book foundBookId = null;
        for (Book book : books) {
            if(book.getId() == bookId){
                foundBookId = book;
                break;
            }
        }
        Member foundMemberId = null;
        for (Member member : members) {
            if(member.getId() == memberId){
                foundMemberId = member;
                break;
            }
        }
        if (foundBookId == null) {
            throw new LibraryException("BOOK ID \"" + bookId + "\" CANT BE FOUND");
        }
        if (foundMemberId == null) {
            throw new LibraryException(" MEMBER ID \"" + memberId + "\" CANT BE FOUND");
        }
        if (!foundBookId.isAvailable()) {
            throw new LibraryException("BOOK IS NOT AVAILABLE");
        }
        if (!foundMemberId.canBorrow()) {
            throw new LibraryException("MEMBER CANNOT BORROW RIGHT NOW");
        }

        Loan l1 = new Loan(foundBookId, foundMemberId);
        foundBookId.borrow();
        foundMemberId.addBook();
        loans.add(l1);
        return l1;
    }

    public double returnBook (int bookId, int daysLate)throws LibraryException{
        Loan foundLoan = null;

        for (Loan loan : loans) {
            if (bookId == loan.getBook().getId() && !loan.isReturned()) {
                foundLoan = loan;
            }
        }

        if (foundLoan == null) {
            throw new LibraryException("LOAN CANT FOUND");
        }

        if(daysLate < 0){
            throw new LibraryException("DAYS LATE CANNOT BE LESS THAN ZERO");
        }

        double fine = daysLate * 5;
        foundLoan.markReturned();
        foundLoan.getBook().giveBack();
        foundLoan.getMember().removeBook();
        
        if(fine > 0){
            foundLoan.getMember().addFine(fine);
        }
        return fine;
    }
    public  ArrayList<Book> searchByAuthor(String text){
        ArrayList<Book> foundAuthorBook = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                foundAuthorBook.add(book);
            }
        }
        return foundAuthorBook;
    }
    public ArrayList<Ebook> getEbooks(){
        ArrayList<Ebook> results = new ArrayList<>();
        for (Book book : books) {
            if (book instanceof Ebook) {
                results.add((Ebook) book);
            }
        }
        return results;
    }
}
