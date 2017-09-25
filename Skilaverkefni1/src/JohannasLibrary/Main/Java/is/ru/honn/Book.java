package Java.is.ru.honn;

/**
 * Created by Eyrún Magnúsdóttir on 19.9.2017.
 *
 * Stores the information about one book
 */
public class Book {
    private int ID;
    private String titill;
    private String hofundurFornafn;
    private String hofundurEftirnafn;
    private String utgafudagur;
    private String ISBN;

    public Book (int ID, String titill, String hofundurFornafn, String hofundurEftirnafn, String utgafudagur, String ISBN){
        this.ID = ID;
        this.titill = titill;
        this.hofundurFornafn = hofundurFornafn;
        this.hofundurEftirnafn = hofundurEftirnafn;
        this.utgafudagur = utgafudagur;
        this.ISBN = ISBN;
    }

    public int getID(){ return this.ID; }

    public String getTitle(){
        return this.titill;
    }

    public String getAuthor(){
        return this.hofundurFornafn + " " + this.hofundurEftirnafn;
    }

    public String ToString(){
        return "Titill: " + this.titill + ", Höfundur: " + this.hofundurFornafn + " " + this.hofundurEftirnafn + "\n";
    }
}
