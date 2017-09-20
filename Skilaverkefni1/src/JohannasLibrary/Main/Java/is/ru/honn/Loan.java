package JohannasLibrary.Main.Java.is.ru.honn;

import java.util.Date;

/**
 * Created by Eyrún Magnúsdóttir on 19.9.2017.
 *
 * Stores
 */
public class Loan {
    private FriendsAndFamily friend;
    private Book book;
    private Date dateBorrowed;

    public Loan(FriendsAndFamily friend, Book book, Date dateBorrowed){
        this.friend = friend;
        this.book = book;
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDateBorrowed() {
        return this.dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed){
        this.dateBorrowed = dateBorrowed;
    }

    public FriendsAndFamily getFriend(){
        return this.friend;
    }
    public void setFriend(FriendsAndFamily friend){
        this.friend = friend;
    }

}
