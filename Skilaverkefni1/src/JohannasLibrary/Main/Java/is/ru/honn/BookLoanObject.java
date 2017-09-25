package Java.is.ru.honn;

import java.util.Date;

/**
 *Created by Eyrún Magnúsdóttir on 25.9.2017.
 *
 * lista yfir bækur sem eru í láni og hverjir eru með þær í láni
 */
public class BookLoanObject {
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

    public String toString(){
        return "Bók: " + this.bookName + " , nafn leigjanda: " + this.friendName + " Útlánadagsetning: " + this.dateBorrowed + "\n";
    }
}
