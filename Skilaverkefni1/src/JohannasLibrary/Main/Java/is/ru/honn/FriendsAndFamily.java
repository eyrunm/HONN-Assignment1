package Java.is.ru.honn;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import java.lang.reflect.Array;

/**
 * Created by Eyrún Magnúsdóttir on 19.9.2017.
 *
 * Class for storing information about friends and family
 *
 */
public class FriendsAndFamily {

    private String fornafn;
    private int id;
    private String eftirnafn;
    private String netfang;
    private String heimilisfang;
    private String nafn;

    private JsonArray lanasafn;

    public FriendsAndFamily (String fornafn, String eftirnafn, int id, String netfang, String heimilisfang, JsonArray lanasafn) {
        this.fornafn = fornafn;
        this.eftirnafn = eftirnafn;
        this.id = id;
        this.netfang = netfang;
        this.heimilisfang = heimilisfang;
        this.lanasafn = lanasafn;
    }

    public void setNafn(String fornafn, String eftirnafn){
        this.nafn = fornafn + " " + eftirnafn;
    }

    public String getNafn (){
        return this.fornafn + " " + this.eftirnafn;
    }

    public void setID(int id){ this.id = id; }

    public int getId (){ return this.id;  }

    public void setNetfang(String netfang) {
        this.netfang = netfang;
    }

    public String getNetfang() {
        return this.netfang;
    }

    void setHeimilisfang(String heimilisfang) {
        this.heimilisfang = heimilisfang;
    }

    public String getHeimilisfang(){
        return this.heimilisfang;
    }

    public void setLanasafn(JsonArray lanasafn){
        this.lanasafn = lanasafn;
    }

    public JsonArray getLanasafn(){
        return this.lanasafn;
    }

    public String toString() {
        return "Nafn: " + this.fornafn + " " + this.eftirnafn + " , Heimilisfang: " + this.getHeimilisfang()+ "\n";
    }
}
