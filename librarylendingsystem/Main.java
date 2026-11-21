package librarylendingsystem;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // ---------- SETUP ----------
        Library library = new Library("City Library");

        Book cleanCode = new Book("Clean Code", "Robert C. Martin");
        Book pragmatic = new Book("The Pragmatic Programmer", "Andrew Hunt");
        Ebook designPatterns = new Ebook("Design Patterns", "Erich Gamma", 2.5);

        Member ana = new Member("Ana");
        Member ben = new Member("Ben");

        library.addBook(cleanCode);
        library.addBook(pragmatic);
        library.addBook(designPatterns);
        library.addMember(ana);
        library.addMember(ben);

        // ---------- STEP 1: Ana checks out Clean Code ----------
        System.out.println("--- Step 1: Ana checks out Clean Code ---");
        try {
            library.checkOut(ana.getId(), cleanCode.getId());
            System.out.println(cleanCode.describe());
            System.out.println("Ana's books: " + ana.getBorrowedCount());
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------- STEP 2: Ben tries to check out Clean Code (should fail) ----------
        System.out.println();
        System.out.println("--- Step 2: Ben tries to check out Clean Code ---");
        try {
            library.checkOut(ben.getId(), cleanCode.getId());
            System.out.println("PROBLEM: this should have failed!");
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------- STEP 3: Ben checks out Design Patterns ----------
        System.out.println();
        System.out.println("--- Step 3: Ben checks out Design Patterns ---");
        try {
            library.checkOut(ben.getId(), designPatterns.getId());
            System.out.println(designPatterns.describe());
            System.out.println("Ben's books: " + ben.getBorrowedCount());
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------- STEP 4: Search by author "martin" ----------
        System.out.println();
        System.out.println("--- Step 4: Search by author \"martin\" ---");
        ArrayList<Book> results = library.searchByAuthor("martin");
        System.out.println("Found " + results.size() + " book(s):");
        for (Book b : results) {
            System.out.println(b.describe());
        }

        // ---------- STEP 5: Ana returns Clean Code 4 days late ----------
        System.out.println();
        System.out.println("--- Step 5: Ana returns Clean Code, 4 days late ---");
        try {
            double fine = library.returnBook(cleanCode.getId(), 4);
            System.out.println("Fine: " + String.format("%.2f", fine));
            System.out.println("Ana's fines: " + String.format("%.2f", ana.getFines()));
            System.out.println("Ana's books: " + ana.getBorrowedCount());
            System.out.println(cleanCode.describe());
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------- STEP 6: Ana pays her fine ----------
        System.out.println();
        System.out.println("--- Step 6: Ana pays 20.00, then tries to pay 5.00 more ---");
        ana.payFine(20.00);
        System.out.println("Ana's fines: " + String.format("%.2f", ana.getFines()));
        try {
            ana.payFine(5.00);
            System.out.println("PROBLEM: this should have failed!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------- STEP 7: Get the ebooks ----------
        System.out.println();
        System.out.println("--- Step 7: Get the ebooks ---");
        ArrayList<Ebook> ebooks = library.getEbooks();
        System.out.println("Ebooks found: " + ebooks.size());
        for (Ebook eb : ebooks) {
            System.out.println(eb.describe() + " - " + eb.describeFile());
        }
    }
}