import java.util.*;

public class Member {

    private int ID;
    private String name;
    private String email;
    private List<Loan> loans;

    public void borrowBook(Book book){
        borrowedBooks.add(book);
    }

    public void returnBook(Book book){
        borrowedBooks.remove(book); //returns false if not exist
    }

    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getLoans() {
        return borrowedBooks;
    }
}

