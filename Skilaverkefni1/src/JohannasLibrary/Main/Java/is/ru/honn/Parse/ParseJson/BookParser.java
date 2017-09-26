package Java.is.ru.honn.Parse.ParseJson;

import Java.is.ru.honn.Book;
import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by Eyrún Magnúsdóttir on 20.9.2017.
 *
 * Parses Json file and stores the data into an ArrayList of Book objects
 *
 @Params source the location of the JSON file
 */
public class BookParser {

    private String source;
    private ArrayList<Book> books;

    public BookParser(String source)
    {
        this.source = source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean parse(){
        FileReader reader = null;

        try {
            reader = new FileReader(this.source);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        try {
            JsonArray obj = (JsonArray) Jsoner.deserialize(reader);
            this.books = new ArrayList<>();

            for(int i = 0; i < obj.size(); i++){
                JsonObject jObj = (JsonObject) obj.get(i);
                int id = ((BigDecimal) jObj.get("bok_id")).intValue();
                String Titill = jObj.getString("bok_titill");
                String fornafn = jObj.getString("fornafn_hofundar");
                String eftirnafn = jObj.getString("eftirnafn_hofundar");
                String utgafud = jObj.getString("utgafudagur");
                String ISBN = jObj.getString("ISBN");
                Book book = new Book(id, Titill, fornafn, eftirnafn, utgafud, ISBN);
                books.add(book);
            }

        } catch (DeserializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public ArrayList getBooks() {
        return this.books;
    }

}
