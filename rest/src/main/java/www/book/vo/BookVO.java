package www.book.vo;

import www.book.api.Book;
import java.util.Date;

public class BookVO {

    private Long id;
    private String title;
    private String description;
    private Date publicationDate;
    private float price;
    private boolean isActive;
    private Long author;
    private String authorFirstName;
    private String authorLastName;

    public BookVO() {
    }

    public BookVO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.publicationDate = book.getPublicationDate();
        this.price = book.getPrice();
        this.isActive = book.getActive();
        if(book.getAuthor() != null){
            this.author = book.getAuthor().getId();
            this.authorFirstName = book.getAuthor().getFirstName() ;
            this.authorLastName = book.getAuthor().getLastName() ;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
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

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
}
