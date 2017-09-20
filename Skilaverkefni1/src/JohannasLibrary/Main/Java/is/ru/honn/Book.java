package JohannasLibrary.Main.Java.is.ru.honn;

/**
 * Created by Eyrún Magnúsdóttir on 19.9.2017.
 *
 * Stores the information about one book
 */
public class Book {
    private String titill;
    private String hofundur;
    private String utgafudagur;
    private String ISBN;

    public Book (String titill, String hofundur, String utgafudagur, String ISBN){
        this.titill = titill;
        this.hofundur = hofundur;
        this.utgafudagur = utgafudagur;
        this.ISBN = ISBN;
    }
}
