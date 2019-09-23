package www.book.vo;

import www.book.api.Book;

public class BookVO {

    private Long id;
    private String title;
    private String description;
    private String publicationDate;
    private float price;
    private boolean isActive;
    private String author;

    public BookVO() {
    }

    public BookVO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.publicationDate = book.getPublicationDate().toLocaleString();
        this.price = book.getPrice();
        this.isActive = book.getActive();
        if(book.getAuthor() != null){
            this.author = book.getAuthor().getLastName();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
