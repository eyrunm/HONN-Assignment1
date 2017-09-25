import Java.is.ru.honn.BookLoanObject;
import Java.is.ru.honn.Loan;
import Java.is.ru.honn.Parse.ParseJson.BookParser;
import Java.is.ru.honn.Parse.ParseJson.FriendParser;
import Java.is.ru.honn.Parse.ParseProcess;
import Java.is.ru.honn.Report.getReportByUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Eyrún Magnúsdóttir on 20.9.2017.
 */
public class LibraryRunner {
    public static void main (String[] args) throws ParseException {

        Scanner scan = new Scanner(System.in);
        getReportByUser rep;
        ArrayList<BookLoanObject> loans;

        /*Hægt að taka út skýrslu sem sýnir hverjir eru með hvaða bækur á hvaða tíma
            Fyrir gefna dagsetningu, sjá lista yfir bækur sem eru í láni og hverjir eru með þær í láni
            Fyrir gefna dagsetningu, sjá lista yfir einstaklinga sem eru með bækur í láni, og hvaða bækur það eru
            Fyrir gefna dagsetningu sjá lista yfir einstaklinga sem eru búnir að vera með eina (eða fleiri) bækur í láni í meira en mánuð
        */

        System.out.println("Velkomin í Bókasafn Jóhönnu  \n\n");
        System.out.println("Vinsamlegast sláðu in dagsetningu á forminu YYYY-MM-dd \n");
        String date = scan.nextLine();
        rep = new getReportByUser();
        try {
            loans = rep.getLoansByUserID(date);
            if(loans.isEmpty()){
                System.out.println("Það eru engar bækur í leigu á þessum degi");
            }
            else{
                for(BookLoanObject l : loans){
                    System.out.println(l);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
