package Library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Book
{
	private String title, author, edition, publication, year, ISBN, language, subject;
	private ItemStatus status;
	
	private String borrowerID; //Keep track of who borrow the book
	private LocalDate dueDate; //Keep track of the due date
	
	/**
	 * Default constructor
	 * Initialize all variables to empty String
	 * Initialize the book's status to available
	 */
	public Book() {
		this.title = "";
		this.author = "";
		this.edition = "";
		this.publication = "";
		this.year = "";
		this.ISBN = "";
		this.language = "";
		this.subject = "";
		this.status = ItemStatus.Available;
		
		this.borrowerID = "None";
		this.dueDate = null;
	}
	
	/**
	 * Default Constructor
	 * Set the variables to user's input
	 * Initialize the book's status to available
	 * @param info the info of the book; user's input
	 */
	public Book(List<String> info) {
		update(info);
		this.status = ItemStatus.Available;
		
		this.borrowerID = "None";
		this.dueDate = null;
	}
	
	/**
	 * Set the variables to user's input
	 * @param info the info of the book; user's input
	 */
	public void update(List<String> info)
	{
		this.title = info.get(0);
		this.author = info.get(1);
		this.edition = info.get(2);
		this.publication = info.get(3);
		this.year = info.get(4);
		this.ISBN = info.get(5);
		this.language = info.get(6);
		this.subject = info.get(7);
	}
	
	/**
	 * Borrow a book
	 * Set the due date to 14 days after today
	 * Remember the member's ID who borrows this book
	 * Set the status of this book to on loan
	 * @param borrowerID the member who borrow this book
	 */
	public void borrow(String borrowerID) {
		this.dueDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusDays(14); //Due date is 14 days after borrowed date
		
		this.borrowerID = borrowerID;
		this.status = ItemStatus.OnLoan;
	}
	
	/**
	 * Return a book
	 * Set the due date back to null
	 * Set the borrowerID to none
	 * Set the status of this book back to available
	 */
	public void returnBook() {
		this.dueDate = null;
		
		this.borrowerID = "None";
		this.status = ItemStatus.Available;
	}
	
	/**
	 * Print the info of the book
	 */
	public String toString() {
		String description = "Title: " + this.title + " *** Author: " + this.author + " *** Edition: " + this.edition +
							 " *** Publication: " + this.publication + " *** Year: " + this.year + " *** ISBN: " + this.ISBN +
							 " *** Language: " + language + " *** Subject: " + this.subject + " *** Status: " + this.status +
							 " *** Borrower: " + this.borrowerID + " *** Due date: " + this.dueDate;
		return description;
	}
	
	/****** Getter Functions ******/
	public ItemStatus getStatus() { return this.status; }
	
	public String getTitle() { return this.title; }
	
	public LocalDate getDueDate() { return this.dueDate; }
	
	public String getBorrowerID() { return this.borrowerID; }
	
	
	
	
}
