package Java.is.ru.honn.Report;

import Java.is.ru.honn.Book;
import Java.is.ru.honn.BookLoanObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eyrún Magnúsdóttir on 26.9.2017.
 *
 * returns all submitted loans by friends from a single date until today
 *
 * fyrir gefna dagsetningu, sjá lista yfir einstaklinga sem eru með bækur í láni, og hvaða bækur það eru
 */
public class getReport {

    ArrayList<BookLoanObject> loans;
    ArrayList<BookLoanObject> loansMoreThan1Month;

    getReportService reportByBook;

    private String date;

    public getReport(String date){
            this.date = date;
            reportByBook = new getReportService(this.date);
            this.loans = reportByBook.getLoansByUserID();
            this.loansMoreThan1Month = reportByBook.fillMoreThanOneMonthLoans();
    }

    public boolean getLoansByUser(){
        for(BookLoanObject l : loans){
            System.out.println(l);
        }
        if(!loans.isEmpty()){
            return true;
        }
        else{
            System.out.println("loans empty");
            return false;
        }
    }

    public boolean getLoansByBook(){
        if(!loans.isEmpty()){
            for(BookLoanObject l: loans){
                System.out.println("Leigjandi: " + l.getFriendName() + ", Bók:" + l.getBookName() + ", Útlánadagsetning: " + l.getDateBorrowed());
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean getMoreThan1MonthLoans(){
        /*  Fyrir gefna dagsetningu sjá lista yfir einstaklinga
            sem eru búnir að vera með eina (eða fleiri) bækur
             í láni í meira en mánuð
         */

        if(!loansMoreThan1Month.isEmpty()) {
            for (BookLoanObject l : loansMoreThan1Month) {
                System.out.println(l);
            }
            return true;
        }
        else {
            return false;
        }
    }

}
