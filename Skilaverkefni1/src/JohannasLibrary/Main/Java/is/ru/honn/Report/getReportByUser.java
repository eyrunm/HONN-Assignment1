package Java.is.ru.honn.Report;

import Java.is.ru.honn.Book;
import Java.is.ru.honn.BookLoanObject;
import Java.is.ru.honn.FriendsAndFamily;
import Java.is.ru.honn.Loan;
import Java.is.ru.honn.Parse.ParseJson.FriendParser;
import Java.is.ru.honn.Parse.ParseProcess;
import com.sun.org.apache.xpath.internal.operations.Lt;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Eyrún Magnúsdóttir on 25.9.2017.
 */
public class getReportByUser {

    /*Hægt að taka út skýrslu sem sýnir hverjir eru með hvaða bækur á hvaða tíma
    Fyrir gefna dagsetningu, sjá lista yfir bækur sem eru í láni og hverjir eru með þær í láni
    Fyrir gefna dagsetningu, sjá lista yfir einstaklinga sem eru með bækur í láni, og hvaða bækur það eru
    Fyrir gefna dagsetningu sjá lista yfir einstaklinga sem eru búnir að vera með eina (eða fleiri) bækur í láni í meira en mánuð
    */

    private ArrayList<Loan> allLoans;
    private ArrayList<FriendsAndFamily> allFriends;
    private ArrayList<Book> allBooks;

    public getReportByUser(){
        ParseProcess parseProcess = new ParseProcess();
        parseProcess.parse();
        this.allLoans = parseProcess.getLoans();
        this.allFriends = parseProcess.getFriends();
        this.allBooks = parseProcess.getBooks();
    }

    public ArrayList<BookLoanObject> getLoansByUserID(String date) throws ParseException {
        ArrayList<Loan> loan = new ArrayList<>();
        ArrayList<BookLoanObject> loanObjects = new ArrayList<>();
        Date newDate = null;
        Date today = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate Ltoday = LocalDate.now();
        try {
            newDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try{
            today = sdf.parse(Ltoday.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(Loan l: allLoans){
            if(isWithinRange(l.getDateBorrowed(), newDate, today)){
                loan.add(l);
            }
        }
        String bookName = null;
        String friendName = null;
        for(Loan l: loan){
            Iterator<FriendsAndFamily> friendIT = allFriends.iterator();
            Iterator<Book> bookIT = allBooks.iterator();
            //finna nafn á frien
                while(friendIT.hasNext()) {
                    FriendsAndFamily f = (FriendsAndFamily) friendIT.next();
                    if (f.getId() == l.getFriendID()) {
                        friendName = f.getNafn();
                        while(bookIT.hasNext()) {
                            //finna nafn á friend
                            Book b = (Book) bookIT.next();
                            if (b.getID() == l.getBookID()) {
                                bookName = b.getTitle();
                            }
                        }
                    }
                }
                loanObjects.add(new BookLoanObject(bookName, friendName, l.getDateBorrowed()));
            }

        if(loanObjects != null) {
            return loanObjects;
        }
        else {
            return null;
        }
    }

    boolean isWithinRange(Date dateBorrowed, Date startDate, Date today) {
        return !(dateBorrowed.before(startDate) || dateBorrowed.after(today));
    }
}
