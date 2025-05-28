public class Book {

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

    public void setPages(int pages) {
        if (pages >= 0) {
            this.pages = pages;
        } else {
            System.out.println("Page number cannot be negative");
        }
    }

    public int getID() {
        return ID;
    }

}
