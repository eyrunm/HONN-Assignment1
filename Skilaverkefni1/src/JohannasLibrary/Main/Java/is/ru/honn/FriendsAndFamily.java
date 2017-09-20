package JohannasLibrary.Main.Java.is.ru.honn;

/**
 * Created by Eyrún Magnúsdóttir on 19.9.2017.
 *
 * Class for storing information about friends and family
 *
 */
public class FriendsAndFamily {

    private String nafn;
    private String netfang;
    private String heimilisfang;
    private String numer;

    public FriendsAndFamily (String fornafn, String eftirnafn, String netfang, String heimilisfang, String numer) {
        this.nafn = fornafn + " " + eftirnafn;
        this.netfang = netfang;
        this.heimilisfang = heimilisfang;
        this.numer = numer;
    }

    void setNafn(String fornafn, String eftirnafn){
        this.nafn = fornafn + " " + eftirnafn;
    }
    String getNafn (){
        return this.nafn;
    }

    void setNetfang(String netfang) {
        this.netfang = netfang;
    }

    String getNetfang() {
        return this.netfang;
    }

    void setHeimilisfang(String heimilisfang) {
        this.heimilisfang = heimilisfang;
    }

    String getHeimilisfang(){
        return this.heimilisfang;
    }

    void setNumer(String numer){
        this.numer = numer;
    }

    String getNumer(){
        return this.numer;
    }
}
