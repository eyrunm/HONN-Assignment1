package Java.is.ru.honn.Parse;

import Java.is.ru.honn.Book;
import Java.is.ru.honn.FriendsAndFamily;
import Java.is.ru.honn.Parse.ParseJson.BookParser;
import Java.is.ru.honn.Parse.ParseJson.FriendParser;

import java.util.ArrayList;

/**
 * Created by Eyrún Magnúsdóttir on 25.9.2017.
 *
 * handles parsing JSON files.
 * uses FriendParser for parsing information about friends
 * uses BookParser for parsing information about Books
 */
public class ParseProcess {
    FriendParser friendParser;
    BookParser bookParser;
    private ArrayList<FriendsAndFamily> friends;
    private ArrayList<Book> books;
    private ArrayList<Book> loans;
    public ParseProcess()
    {
        friendParser = new FriendParser("friends.json");
        bookParser = new BookParser("books.json");
    }

    public void parse(){
        friendParser.parse();
        bookParser.parse();
        this.friends = friendParser.getFriends();
        this.books = bookParser.getBooks();
        this.loans = friendParser.getLoans();
    }

    public ArrayList getFriends(){
        return this.friends;
    }
    public ArrayList getBooks(){
        return this.books;
    }
    public ArrayList getLoans(){
        return this.loans;
    }
}
