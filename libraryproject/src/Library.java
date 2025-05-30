import java.time.LocalDate;
import java.util.*;

public class Library {

    private static final Random random = new Random();

    private List<Book> books = new ArrayList<>();
    private List<Loan> allLoans = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    private Set<Integer> IDs = new HashSet<>();

    public enum BorrowStatus {
        SUCCESS,
        BOOK_NOT_FOUND,
        BOOK_NOT_AVAILABLE,
        MEMBER_NOT_FOUND
    }

    public enum ReturnStatus {
        SUCCESS,
        BOOK_NOT_LOANED
    }

    public void addBook(String title, String author, String category, Integer pages) {
        int id = generateUniqueId();
        Book book = new Book(title, author, category, pages, id);
        books.add(book);
        IDs.add(id);
    }

    public boolean removeBook(Book book) {

        for (Loan loan : allLoans) {
            if (loan.getBook().equals(book) && !loan.isReturned()) {
                loan.setReturnDate(LocalDate.now());
            }
        }

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.equals(book)) {

                iterator.remove();
                IDs.remove(book.getID());
                return true;
            }
        }
        return false;

        // or return books.removeIf(b -> b.getID() == book.getID());

    }

    public List<Book> listBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> findBookByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBookByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book findBookByID(int id) {
        for (Book book : books) {
            if (book.getID() == id) {
                return book;
            }
        }
        return null;
    }

    public boolean addMember(int id, String name, String email) {
        for (Member member : members) {
            if (member.getID() == id || member.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }

        members.add(new Member(id, name, email));
        return true;
    }

    public List<Member> listMembers() {
        return new ArrayList<>(members);
    }

    public List<Member> findMemberByName(String name) {
        List<Member> result = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(member);
            }
        }
        return result;
    }

    public Member findMemberByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equalsIgnoreCase(email)) {
                return member;
            }
        }
        return null;
    }

    public Member findMemberByID(int id) {
        for (Member member : members) {
            if (member.getID() == id) {
                return member;
            }
        }
        return null;
    }

    public BorrowStatus borrowBook(Book book, Member member) {
        if (!members.contains(member)) {
            return BorrowStatus.MEMBER_NOT_FOUND;
        }

        else if (!books.contains(book)) {
            return BorrowStatus.BOOK_NOT_FOUND;
        }

        else if (!book.isAvailable()) {
            return BorrowStatus.BOOK_NOT_AVAILABLE;
        }



        else {
            Loan loan = new Loan(book, LocalDate.now());
            allLoans.add(loan);
            member.addLoan(loan);
            book.setAvailable(false);
            return BorrowStatus.SUCCESS;
        }
    }

    public BorrowStatus borrowBook(Book book, Member member, LocalDate date) {

        if (!members.contains(member)) {
            return BorrowStatus.MEMBER_NOT_FOUND;
        }

        else if (!books.contains(book)) {
            return BorrowStatus.BOOK_NOT_FOUND;
        }

        else if (!book.isAvailable()) {
            return BorrowStatus.BOOK_NOT_AVAILABLE;
        }

        else {
            Loan loan = new Loan(book, date);
            allLoans.add(loan);
            member.addLoan(loan);
            book.setAvailable(false);
            return BorrowStatus.SUCCESS;
        }
    }

    public ReturnStatus returnBook(Book book) {

        for (Loan loan : allLoans) {
            if (loan.getBook().equals(book) && !loan.isReturned()) {
                loan.setReturnDate(LocalDate.now());
                book.setAvailable(true);
                return ReturnStatus.SUCCESS;
            }
        }

        return ReturnStatus.BOOK_NOT_LOANED;
    }

    public ReturnStatus returnBook(Book book, LocalDate date) {
        for (Loan loan : allLoans) {
            if (loan.getBook().equals(book) && !loan.isReturned()) {
                loan.setReturnDate(date);
                book.setAvailable(true);
                return ReturnStatus.SUCCESS;
            }
        }

        return ReturnStatus.BOOK_NOT_LOANED;
    }

    public int generateUniqueId() {
        int id;
        do {
            id = 10000 + random.nextInt(90000);
        } while (IDs.contains(id));
        return id;
    }

}
