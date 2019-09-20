package www.booking.service;

import www.book.api.Book;
import www.booking.dao.AuthorDAO;
import www.booking.dao.BookDAO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO m_bookDAO;

    private AuthorDAO m_authorDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.m_bookDAO = bookDAO;
    }

    public BookDAO getBookDAO() {
        return m_bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        m_bookDAO = bookDAO;
    }

    public void init() {
        System.out.println("Init Book Service");

        Date cDate = new Date();

        Book book1 = new Book("ahihi", "success", cDate, (float) 1);
        Book book2 = new Book("book2", "dasczx", cDate, (float) 6311.6);
        Book book3 = new Book("book3", "house", cDate, (float) 10.2);
        Book book4 = new Book("book4", "hibernate", cDate, (float) 60.6);
//
        add(book1);
        add(book2);
        add(book3);
        add(book4);

        long id = 1;
        Book bookingID1 = get(id);
        System.out.println("info book id: "+ id + bookingID1.toString());

        Book book5 = new Book("aaaaaaaaaa", "hibernate", cDate, (float) 60.6);
        update(1, book5);
//
        delete(2);
//
        List<Book> books = listAll();
        System.out.println("List Book -> " + books);

//        add and list book with authorid
//        Book book1 = new Book("hahah", "jcoia", cDate, (float) 123);
//        Book book2 = new Book("telephone", "message", cDate, (float) 22);
//
//        long idAuthor = 3;
//        addBookwithAuthor(idAuthor, book1);
//        addBookwithAuthor(idAuthor, book1);
//
//        List<Book> books = listAllBookByAuthor(idAuthor);
//        System.out.println("List Book -> " + books);
    }

    public void destroy() {
        System.out.println("Destroy Book Service");
    }

    @Override
    public Book get(long id) {

        Book book = null;
        try{
            book = m_bookDAO.get(id);
        }catch (Exception e){
            System.out.println("Error while find id "+ id +'.' + e);
        }
        return book;
    }

    @Override
    public void add(Book book) {
        try{
            m_bookDAO.add(book);
        }catch (Exception e){
            System.out.println("Error while adding book " + e);
        }
    }

    @Override
    public void update(long id, Book book) {
        try{
            Book bookFromDb = (Book) get(id);
            if(bookFromDb !=null){
                bookFromDb.setTitle(book.getTitle());
                bookFromDb.setDescription(book.getDescription());
                bookFromDb.setPrice(book.getPrice());
                m_bookDAO.update(bookFromDb);
            }

        }catch (Exception e){
            System.out.println("error while updating "+ e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            if(m_bookDAO.get(id)!= null){
                m_bookDAO.delete(id);
            } else {
                throw new Exception("Book id " + id + "does not exists");
            }
        } catch (Exception e) {
            System.out.println("Error while deleting book with id " + id + ". " + e);
        }
    }

//    @Override
//    public List<Book> listAllBookByAuthor(long authorId) {
//        List<Book> books = new LinkedList<>();
//        try{
//            books = m_bookDAO.listAllBookByAuthor(authorId);
//        }catch (Exception e){
//            System.out.println("Error while list Book by Author " + e);
//        }
//        return books;
//    }
//
//    @Override
//    public void addBookwithAuthor(long authorId, Book book) {
//        try{
//            if(m_authorDAO.get(authorId)!= null){
//                m_bookDAO.addBookwithAuthor(authorId, book);
//            }else{
//                throw new Exception("Author ID: " + authorId + " does not exists");
//            }
//        }catch (Exception e){
//            System.out.println("Error while adding book " + e);
//        }
//    }

    @Override
    public List<Book> listAll() {
        List<Book> books = new LinkedList<>();
        try{
            books = m_bookDAO.listAll();
        }catch (Exception e){
            System.out.println("Error while listAll book "+ e);
        }
        return books;
    }

}
