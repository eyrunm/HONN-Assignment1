package Java.is.ru.honn.Parse.ParseJson;

import Java.is.ru.honn.FriendsAndFamily;
import Java.is.ru.honn.Loan;
import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Eyrún Magnúsdóttir on 20.9.2017.
 */
public class FriendParser {

    private String source;
    private ArrayList<FriendsAndFamily> friends;
    private ArrayList<Loan> loans;

    public FriendParser(String source){
        this.source = source;
    }

    public ArrayList getFriends(){
        return this.friends;
    }

    public ArrayList getLoans(){
        return this.loans;
    }

    public boolean parse(){
        FileReader reader = null;

        try {
            reader = new FileReader(source);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            JsonArray obj = (JsonArray) Jsoner.deserialize(reader);
            this.loans = new ArrayList<>();
            this.friends = new ArrayList<>();


            for(int i = 0; i < obj.size(); i++) {
                JsonObject jObj = (JsonObject) obj.get(i);
                String fornafn = jObj.getString("fornafn");
                String eftirnafn = jObj.getString("eftirnafn");
                int id = ((BigDecimal) jObj.get("vinur_id")).intValue();
                String netfang = jObj.getString("netfang");
                String heimilisfang = jObj.getString("heimilisfang");
                JsonArray lanasafn = (JsonArray) jObj.get("lanasafn");
                FriendsAndFamily f = new FriendsAndFamily(fornafn, eftirnafn, id, netfang, heimilisfang, lanasafn);
                friends.add(f);

                JsonArray friendBooks = (JsonArray) f.getLanasafn();
                if (lanasafn != null) {
                    Iterator<Object> it = friendBooks.iterator();

                    while (it.hasNext()) {
                        JsonObject loansBook = (JsonObject) it.next();
                        this.loans.add(new Loan(id, ((BigDecimal) loansBook.get("bok_id")).intValue(),
                                (String) loansBook.get("bok_lanadagsetning")));
                    }
                }
            }

        } catch (DeserializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
