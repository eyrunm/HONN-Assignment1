package Java.is.ru.honn;

import java.util.Date;

/**
 *Created by Eyrún Magnúsdóttir on 25.9.2017.
 *
 * lista yfir bækur sem eru í láni og hverjir eru með þær í láni
 */
public class BookLoanObject implements Comparable<BookLoanObject>{
    private String bookName;
    private String friendName;
    private Date dateBorrowed;
    //private int bookID;
    //private int friendID;

    public BookLoanObject(String bookName, String friendName, Date dateBorrowed){
        this.bookName = bookName;
        this.friendName = friendName;
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDateBorrowed(){
        return this.dateBorrowed;
    }

    public String getFriendName(){
        return this.friendName;
    }

    public String getBookName(){
        return this.bookName;
    }

    public String toString(){
        return "Bók: " + this.bookName + " , Leigjandi: " + this.friendName + " Útlánadagsetning: " + this.dateBorrowed;
    }

    @Override
    public int compareTo(BookLoanObject o) {
        return getDateBorrowed().compareTo(o.getDateBorrowed());
    }
}
