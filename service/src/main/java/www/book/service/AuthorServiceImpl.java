package www.book.service;

import www.book.api.Author;
import www.book.dao.AuthorDAO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO m_authorDAO;

    public AuthorServiceImpl(AuthorDAO bookingDAO) {
        this.m_authorDAO = bookingDAO;
    }

    public AuthorDAO getBookingDAO() {
        return m_authorDAO;
    }

    public void setBookingDAO(AuthorDAO bookingDAO) {
        m_authorDAO = bookingDAO;
    }

    public void init() {
        System.out.println("Init Author Service");

//        Date cDate = new Date();
////
//        Author author1 = new Author("tma", "toan nguyen", cDate, "addresss1", "decription1");
//        Author author2 = new Author("", "name1", cDate, "addresss1", "decription2");
//        Author author3 = new Author("nguyen ", "name2", cDate, "phu yen", "decription3");
//        Author author4 = new Author("nguyen ", "name3", cDate, "addresss1", "decription4");
//        Author author5 = new Author("dbas ", "nam5", cDate, "vung tau", "no thing");
//
//
//        add(author1);
//        add(author2);
//        add(author3);
//        add(author4);
////
//        long id = 1;
//        Author authorID1 = get(id);
//        System.out.println("info author id: "+ id + authorID1.toString());
////
//       Author author6 = new Author("update", "name5", cDate, "addresss1", "success");
//       update(1, author6);
//
//        delete(4);

//        List<Author> authors = listAll();
//        System.out.println("List Author -> " + authors);
    }

    public void destroy() {
        System.out.println("Destroy Author Service");
    }

    @Override
    public Author get(long id) {
        Author author = null;
        try{
            author = m_authorDAO.get(id);
        }catch (Exception e){
            System.out.println("Error while find id "+ id +'.' + e);
        }
        return author;
    }

    @Override
    public void add(Author author) {
        try{
            m_authorDAO.add(author);
        }catch (Exception e){
            System.out.println("Error while adding book " + e);
        }
    }

    @Override
    public void update(long id, Author author) {

        try{
            Author authorFromDb = (Author) get(id);
            if(authorFromDb !=null){
                authorFromDb.setFirstName(author.getFirstName());
                authorFromDb.setLastName(author.getLastName());
                authorFromDb.setDescription(author.getDescription());
                authorFromDb.setAddress(author.getAddress());
                authorFromDb.setDob(author.getDob());
                m_authorDAO.update(authorFromDb);
            }
        }catch (Exception e){
            System.out.println("error while updating "+ e);
        }

    }

    @Override
    public void delete(long id) {
        try {
            if(m_authorDAO.get(id)!= null){
                m_authorDAO.delete(id);
            } else {
                throw new Exception("Author id " + id + "does not exists");
            }
        } catch (Exception e) {
            System.out.println("Error while deleting book with id " + id + ". " + e);
        }
    }

    @Override
    public List<Author> listAll() {

        List<Author> authors = new LinkedList<>();
        try{
            authors = m_authorDAO.listAll();
        }catch (Exception e){
            System.out.println("Error while listAll book "+ e);
        }
        return authors;
    }
}
