package Java.is.ru.honn.Report;

import Java.is.ru.honn.Book;
import Java.is.ru.honn.BookLoanObject;
import Java.is.ru.honn.FriendsAndFamily;
import Java.is.ru.honn.Loan;
import Java.is.ru.honn.Parse.ParseProcess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eyrún Magnúsdóttir on 25.9.2017.
 *
 *
 *
 */
public class getReportService {
    /*Hægt að taka út skýrslu sem sýnir hverjir eru með hvaða bækur á hvaða tíma
    Fyrir gefna dagsetningu, sjá lista yfir bækur sem eru í láni og hverjir eru með þær í láni
    */

    private ArrayList<Loan> allLoans;
    private ArrayList<FriendsAndFamily> allFriends;
    private ArrayList<Book> allBooks;
    private ArrayList<BookLoanObject> currentLoans;
    private ArrayList<BookLoanObject> oneMonthLoans;
    private String date;

    public getReportService(String date){
        ParseProcess parseProcess = new ParseProcess();
        parseProcess.parse();
        this.allLoans = parseProcess.getLoans();
        this.allFriends = parseProcess.getFriends();
        this.allBooks = parseProcess.getBooks();
        this.date = date;
    }

    public ArrayList<BookLoanObject> getLoansByUserID(){
        ArrayList<Loan> loan = new ArrayList<>();
        this.currentLoans = new ArrayList<>();
        Date newDate = null;
        Date today = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate Ltoday = LocalDate.now();
        try {
            newDate = sdf.parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try{
            today = sdf.parse(Ltoday.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String bookName = null;
        String friendName = null;
        for(Loan l: allLoans){
            if(isWithinRange(l.getDateBorrowed(), newDate, today)){
                loan.add(l);
            }
        }

        for(Loan l: loan){
            Iterator<FriendsAndFamily> friendIT = allFriends.iterator();
            Iterator<Book> bookIT = allBooks.iterator();
            //finna nafn á frien
            while(friendIT.hasNext()) {
                FriendsAndFamily f = friendIT.next();
                if (f.getId() == l.getFriendID()) {
                    friendName = f.getNafn();
                }
            }
            while(bookIT.hasNext()) {
                //finna nafn á friend
                Book b = (Book) bookIT.next();
                if (b.getID() == l.getBookID()) {
                    bookName = b.getTitle();
                }
            }
            this.currentLoans.add(new BookLoanObject(bookName, friendName, l.getDateBorrowed()));
            }

        if(this.currentLoans != null) {
            Collections.sort(this.currentLoans);
            return this.currentLoans;
        }
        else{
            return null;
        }
    }

    public ArrayList<BookLoanObject> fillMoreThanOneMonthLoans(){
        this.currentLoans = getLoansByUserID();
        this.oneMonthLoans = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0;

            try {
                Date date1 = sdf.parse(this.date);
                for(BookLoanObject l : this.currentLoans) {
                    Date date2 = l.getDateBorrowed();
                    days = (int) (( date1.getTime() - date2.getTime() ) / 8640000);
                    System.out.println(days);
                    if( Math.abs(days) > 30) {
                        this.oneMonthLoans.add(l);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Collections.sort(this.oneMonthLoans);
        return this.oneMonthLoans;
    }

    private boolean isWithinRange(Date dateBorrowed, Date startDate, Date today) {
        return !(dateBorrowed.before(startDate) || dateBorrowed.after(today));
    }
}
