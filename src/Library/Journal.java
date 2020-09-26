package Library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Journal
{
	private String title, publication, year, ISSN, language, subject;
	private ItemStatus status;
	
	private String borrowerID; //Keep track of who borrow this journal
	private LocalDate dueDate; //Keep track of the due date
	
	/**
	 * Default Constructor
	 * Initialize all variables to empty String
	 * Initialize the journal's status to available
	 */
	public Journal() {
		this.title = "";
		this.publication = "";
		this.year = "";
		this.ISSN = "";
		this.language = "";
		this.subject = "";
		this.status = ItemStatus.Available;
		
		this.borrowerID = "None";
		this.dueDate = null;
	}
	
	/**
	 * Copy Constructor
	 * Set the variables to user's input
	 * Initialize the journal's status to available
	 * @param info the info of the journal; user's input
	 */
	public Journal(List<String> info) {
		update(info);
		this.status = ItemStatus.Available;
		
		this.borrowerID = "None";
		this.dueDate = null;
	}
	
	/**
	 * Set the variables to user's input
	 * @param info the info of the journal; user's input
	 */
	public void update(List<String> info) {
		this.title = info.get(0);
		this.publication = info.get(1);
		this.year = info.get(2);
		this.ISSN = info.get(3);
		this.language = info.get(4);
		this.subject = info.get(5);
	}
	
	/**
	 * Borrow a journal
	 * Set the due date to 14 days after today
	 * Remember the member's ID who borrows this journal
	 * Set the status of this journal to on loan
	 * @param borrowerID the member who borrow this journal
	 */
	public void borrow(String borrowerID) {
		this.dueDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusDays(7); //Due date is 7 days after borrowed date
		
		this.borrowerID = borrowerID;
		this.status = ItemStatus.OnLoan;
	}
	
	/**
	 * Return a journal
	 * Set the due date back to null
	 * Set the borrowerID to none
	 * Set the status of this journal back to available
	 */
	public void returnJournal() {
		this.dueDate = null;
		
		this.borrowerID = "None";
		this.status = ItemStatus.Available;
	}
	
	/**
	 * Print the info of the journal
	 */
	public String toString() {
		String description = "Title: " + this.title + " *** Publication: " + this.publication + " *** Year: " + this.year + 
							 " *** ISSN: " + this.ISSN + " *** Language: " + language + " *** Subject: " + this.subject +
							 " *** Status: " + this.status + " *** Borrower: " + this.borrowerID + " *** Due date: " + this.dueDate;
		return description;
	}
	
	/****** Getter Functions ******/
	public ItemStatus getStatus() { return this.status; }
	
	public String getTitle() { return this.title; }
	
	public LocalDate getDueDate() { return this.dueDate; }
	
	public String getBorrowerID() { return this.borrowerID; }
}
