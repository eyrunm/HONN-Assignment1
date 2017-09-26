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

import static java.lang.Math.toIntExact;

/**
 * Created by Eyrún Magnúsdóttir on 25.9.2017.
 *
 *Service class for loans
 *
 * @param date the date for the report
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
            //find the name of the friend
            while(friendIT.hasNext()) {
                FriendsAndFamily f = friendIT.next();
                if (f.getId() == l.getFriendID()) {
                    friendName = f.getNafn();
                }
            }
            while(bookIT.hasNext()) {
                //find the name of the book
                Book b = (Book) bookIT.next();
                if (b.getID() == l.getBookID()) {
                    bookName = b.getTitle();
                }
            }
            //add to list
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
        long diff = 0;
            try {
                Date date1 = sdf.parse(this.date);
                for(BookLoanObject l : this.currentLoans) {
                    Date date2 = l.getDateBorrowed();
                    diff = date2.getTime() - date1.getTime();
                    days = (toIntExact(diff / 86400000));
                    if( days > 30) {
                        this.oneMonthLoans.add(l);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Collections.sort(this.oneMonthLoans);
        Date date1 = null;
        try {
            date1 = sdf.parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(BookLoanObject l : this.oneMonthLoans){

                Date date2 = l.getDateBorrowed();
                diff = date2.getTime() - date1.getTime();
                days = (toIntExact(diff / 86400000));
                System.out.println(l + " Dagar í útláni: " + days);
            }
        return this.oneMonthLoans;
    }

    private boolean isWithinRange(Date dateBorrowed, Date startDate, Date today) {
        return !(dateBorrowed.before(startDate) || dateBorrowed.after(today));
    }
}
