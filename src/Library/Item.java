package Library;

import java.util.ArrayList;
import java.util.List;

public class Item
{
    private ArrayList<Book> books;
    private ArrayList<DVD> DVDs;
    private ArrayList<Journal> journals;

    /**
     * Default Constructor
     * Create new ArrayList for books, DVDs and journals
     */
    public Item() {
        this.books = new ArrayList<>();
        this.DVDs = new ArrayList<>();
        this.journals = new ArrayList<>();
    }

    /**
     * Add a new book
     * @param info the info of a book
     */
    public void addBook(List<String> info) {
        this.books.add(new Book(info));
    }

    /**
     * Add a new DVD
     * @param info the info of a DVD
     */
    public void addDVD(List<String> info) {
        this.DVDs.add(new DVD(info));
    }

    /**
     * Add a new journal
     * @param info the info of a journal
     */
    public void addJournal(List<String> info) {
        this.journals.add(new Journal(info));
    }

    /**
     * Search a book in the system 
     * @param target the book's title
     * @return true if found; false otherwise
     */
    public Boolean searchBook(String target)
    {
        Boolean found = false;
        for(int i = 0; i < this.books.size(); i++)
        {
            if(this.books.get(i).getTitle().equalsIgnoreCase(target)) {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * Search a DVD in the system 
     * @param target the DVD's title
     * @return true if found; false otherwise
     */
    public Boolean searchDVD(String target)
    {
        Boolean found = false;
        for(int i = 0; i < this.DVDs.size(); i++)
        {
            if(this.DVDs.get(i).getTitle().equalsIgnoreCase(target)) {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * Search a journal in the system 
     * @param target the journal's title
     * @return true if found; false otherwise
     */
    public Boolean searchJournal(String target)
    {
        Boolean found = false;
        for(int i = 0; i < this.journals.size(); i++)
        {
            if(this.journals.get(i).getTitle().equalsIgnoreCase(target)) {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * Print the book's info by keywords
     * @param target the keywords
     * @return the book's info if keywords match
     */
    public String printBookInfo(String target)
    {
        String description = "";
        for(int i = 0; i < this.books.size(); i++)
        {
            if(this.books.get(i).getTitle().contains(target)) {
                description += this.books.get(i).toString() + "\n";
            }
        }

        if(description.length() == 0)
            description = "** No such book found! **\n";

        return description;
    }

    /**
     * Print the DVD's info by keywords
     * @param target the keywords
     * @return the DVD's info if keywords match
     */
    public String printDVDInfo(String target)
    {
        String description = "";
        for(int i = 0; i < this.DVDs.size(); i++)
        {
            if(this.DVDs.get(i).getTitle().contains(target)) {
                description += this.DVDs.get(i).toString() + "\n";
            }
        }

        if(description.length() == 0)
            description = "** No such DVD found! **\n";

        return description;
    }

    /**
     * Print the journal's info by keywords
     * @param target the keywords
     * @return the journal's info if keywords match
     */
    public String printJournalInfo(String target)
    {
        String description = "";
        for(int i = 0; i < this.journals.size(); i++)
        {
            if(this.journals.get(i).getTitle().contains(target)) {
                description += this.journals.get(i).toString() + "\n";
            }
        }

        if(description.length() == 0)
            description = "** No such journal found! **\n";

        return description;
    }

    /**
     * Print all the books' info
     * @return all the books' info
     */
    public String bookToString() {
        String booksDescription = "";

        for(int i = 0; i < books.size(); i++) {
            booksDescription += books.get(i).toString() + "\n";
        }

        return booksDescription;
    }

    /**
     * Print all the DVDs' info
     * @return all the DVDs' info
     */
    public String DVDToString() {
        String DVDsDescription = "";

        for(int i = 0; i < DVDs.size(); i++) {
            DVDsDescription += DVDs.get(i).toString() + "\n";
        }

        return DVDsDescription;
    }

    /**
     * Print all the journals' info
     * @return all the journals' info
     */
    public String JournalToString() {
        String journalsDescription = "";

        for(int i = 0; i < journals.size(); i++) {
            journalsDescription += journals.get(i).toString() + "\n";
        }

        return journalsDescription;
    }


    /****** Getter Functions ******/
    public ArrayList<Book> getBooks() { return this.books; }

    public ArrayList<DVD> getDVDs() { return this.DVDs; }

    public ArrayList<Journal> getJournals() { return this.journals; }
}
