import Java.is.ru.honn.Report.getReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Eyrún Magnúsdóttir on 20.9.2017.
 */
public class LibraryRunner {
    public static void main (String[] args) throws ParseException {

        Scanner scan = new Scanner(System.in);
        getReport report;

        System.out.println("Velkomin í Bókasafn Jóhönnu  \n\n");
        System.out.println("Veldu 1 til þess að sjá lista yfir bækur sem eru í láni og hverjir eru með þær í láni\n");
        System.out.println("Veldu 2 til þess að sjá lista yfir einstaklinga sem eru með bækur í láni, og hvaða bækur það eru\n");
        System.out.println("Veldu 3 til þess að sjá lista yfir einstaklinga sem eru búnir að vera með eina (eða fleiri) bækur í láni í meira en mánuð\n");

        String choice = scan.nextLine();
        System.out.println("Vinsamlegast sláðu in dagsetningu á forminu YYYY-MM-dd \n");
        String date = scan.nextLine();
            report = new getReport(date);
            if (choice.equals("1")) {
                if (!report.getLoansByUser()) {
                    System.out.println("Það eru engar bækur í leigu á þessum degi");
                }
            } else if (choice.equals("2")) {
                if (!report.getLoansByBook()) {
                    System.out.println("Það eru engar bækur í leigu á þessum degi");
                }

            } else if (choice.equals("3")) {
                report.getMoreThan1MonthLoans();
                    if (!report.getMoreThan1MonthLoans()){
                        System.out.println("Það eru engar bækur í leigu á þessum degi");
                    }

            }
        }

}
