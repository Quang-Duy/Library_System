package Library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

public class DVD
{
	private String title, author, publication, year, language, subject;
	private ItemStatus status;
	
	private String borrowerID;
	private LocalDate dueDate;
	
	/**
	 * Default Constructor
	 * Initialize all variables to empty String
	 * Initialize the DVD's status to available
	 */
	public DVD() {
		this.title = "";
		this.author = "";
		this.publication = "";
		this.year = "";
		this.language = "";
		this.subject = "";
		this.status = ItemStatus.Available;
		
		this.borrowerID = "None";
		this.dueDate = null;
	}
	
	/**
	 * Copy Constructor
	 * Set the variables to user's input
	 * Initialize the DVD's status to available
	 * @param info the info of the journal; user's input
	 */
	public DVD(List<String> info) {
		update(info);
		this.status = ItemStatus.Available;
		
		this.borrowerID = "None";
		this.dueDate = null;
	}
	
	/**
	 * Set the variables to user's input
	 * @param info the info of the DVD; user's input
	 */
	public void update(List<String> info) {
		this.title = info.get(0);
		this.author = info.get(1);
		this.publication = info.get(2);
		this.year = info.get(3);
		this.language = info.get(4);
		this.subject = info.get(5);
	}
	
	/**
	 * Borrow a DVD
	 * Set the due date to 7 days after today
	 * Remember the member's ID who borrows this DVD
	 * Set the status of this DVD to on loan
	 * @param borrowerID the member who borrow this DVD
	 */
	public void borrow(String borrowerID) {
		this.dueDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusDays(7); //Due date is 7 days after borrowed date
		
		this.borrowerID = borrowerID;
		this.status = ItemStatus.OnLoan;
	}
	
	/**
	 * Return a DVD
	 * Set the due date back to null
	 * Set the borrowerID to none
	 * Set the status of this DVD back to available
	 */
	public void returnDVD() {
		this.dueDate = null;
		
		this.borrowerID = "None";
		this.status = ItemStatus.Available;
	}
	
	/**
	 * Print the info of the DVD
	 */
	public String toString() {
		String description = "Title: " + this.title + " *** Author: " + this.author + " *** Publication: " + this.publication +
							 " *** Year: " + this.year + " *** Language: " + language + " *** Subject: " + this.subject +
							 " *** Status: " + this.status + " *** Borrower: " + this.borrowerID + " *** Due date: " + this.dueDate;
		return description;
	}

	/****** Getter Functions ******/
	public ItemStatus getStatus() { return this.status; }
	
	public String getTitle() { return this.title; }
	
	public LocalDate getDueDate() { return this.dueDate; }
	
	public String getBorrowerID() { return this.borrowerID; }
}
