import java.time.LocalDate;

public class Loan {

    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(Book book, LocalDate loanDate) {
        this.book = book;
        this.loanDate = loanDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}