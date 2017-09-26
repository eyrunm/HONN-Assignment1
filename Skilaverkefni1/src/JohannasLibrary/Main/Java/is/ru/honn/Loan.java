package Java.is.ru.honn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eyrún Magnúsdóttir on 19.9.2017.
 *
 * Stores the information about a loan instance
 */
public class Loan {
    private int friendID;
    private int bookID;
    private String dateBorrowed;

    public Loan(int friendID, int bookID, String dateBorrowed){
        this.friendID = friendID;
        this.bookID = bookID;
        this.dateBorrowed = dateBorrowed;
    }
    public int getFriendID(){
        return this.friendID;
    }
    public void setFriendID(int friendID){
        this.friendID = friendID;
    }

    public int getBookID(){
        return this.bookID;
    }
    public void setBookID(int bookID){
        this.bookID = bookID;
    }

    public Date getDateBorrowed() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = sdf.parse(this.dateBorrowed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public void setDateBorrowed(String dateBorrowed){
        this.dateBorrowed = dateBorrowed;
    }

    public String toString(){
        return "FriendID: " + this.friendID + ", BookID: " + this.bookID + ", Date Borrowed: " + this.getDateBorrowed();
    }

}
