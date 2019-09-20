package www.book.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.Type;

@Entity(name ="Book")
@Table(name = "Book")
public class Book implements Serializable {

    private static final boolean IS_ACTIVE = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "price")
    private float price;

    @Type(type="yes_no")
    @Column(name = "is_active")
    private boolean isActive;

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

    public Book() {
    }

    public Book(String title, String description, Date publicationDate, float price) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.price = price;
        this.isActive = IS_ACTIVE;;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                ", price=" + price +
//                ", isActive=" + isActive +
                '}';
    }
}
