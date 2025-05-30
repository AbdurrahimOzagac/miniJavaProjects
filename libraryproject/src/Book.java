public class Book {

    private boolean available;
    private final int ID;
    private String title;
    private String author;
    private String category;
    private int pages;

    public Book(String title, String author, String category, int pages, int id) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.ID = id;
        this.pages = pages;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public boolean setPages(int pages) {
        if (pages >= 0) {
            this.pages = pages;
            return true;
        } else {
            return false;
        }
    }

    public int getID() {
        return ID;
    }

}
