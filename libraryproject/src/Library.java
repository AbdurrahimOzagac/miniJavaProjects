import java.util.*;

public class Library {

    private static final Random random = new Random();

    private List<Book> books = new ArrayList<>();
    private List<Loan> allLoans = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    private Set<Integer> IDs = new HashSet<>();

    public void addBook(String title, String author, String category, Integer pages){
        int id = generateUniqueId();
        Book book = new Book(title, author, category, pages, id);
        books.add(book);
        IDs.add(id);
    }

    public void removeBook(int id){
        for(Book book:books){
            if(book.getID() == id){
                books.remove(book);
                break;
            }
        }
    }

    public List<Book> listBooks(){
        return new ArrayList<>(books);
    }

    public List<Book> findBookByTitle(String title){
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if(book.getTitle().equalsIgnoreCase(title)){
                result.add(book);
            }
        }
        return result;
    }

    public int generateUniqueId() {
    int id;
    do {
        id = 10000 + random.nextInt(90000);
    } while (IDs.contains(id));
    IDs.add(id);
    return id;
}

}
