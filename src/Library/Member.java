package Library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Member {
	private String name, ID, phone, email, address;
	private LocalDate expiredDate;
	private MemberStatus status;
	
	private List<String> borrowedItems; //Keep track of how many items and what items this member has borrowed
	private double lateFee;

	/**
	 * Default Constructor
	 * Set all the String variables to empty String
	 * Set the expiredDate to null
	 * Set the status to Active
	 * Create a new ArrayList for borrowedItems
	 * Initialize the late fee to zero
	 */
	public Member() {
		this.name = "";
		this.ID = "";
		this.phone = "";
		this.email = "";
		this.address = "";
		this.expiredDate = null;
		this.status = MemberStatus.Active;
		
		this.borrowedItems = new ArrayList<>();
		this.lateFee = 0;
	}

	/**
	 * Copy Constructor
	 * Set all the String variables to user's input
	 * Initialize the expired date to 3 months after today
	 * Create a new ArrayList for borrowedItems
	 * Initialize the late fee to zero
	 * @param info the member's info
	 */
	public Member(List<String> info) {
		update(info);
		this.expiredDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusMonths(3); //Account is expired after 3 months
		this.status = MemberStatus.Active;
		
		this.borrowedItems = new ArrayList<>();
		this.lateFee = 0;
	}

	/**
	 * Update member's info
	 * @param info the member's info
	 */
	public void update(List<String> info) {
		this.name = info.get(0);
		this.ID = info.get(1);
		this.phone = info.get(2);
		this.email = info.get(3);
		this.address = info.get(4);
	}
	
	/**
	 * Save all the items this member borrowed
	 * @param items the items this member borrowed
	 */
	public void borrowedItems(List<String> items)
	{
		for(int i = 0; i < items.size(); i++) {
			this.borrowedItems.add(items.get(i));
		}
	}
	
	/**
	 * Reset the late fee to zero before applying a new late fee to this member
	 */
	public void resetLateFee() {
		this.lateFee = 0;
	}
	
	/**
	 * Calculate and set the late fee to this member based on how many items has passed the due dates and how many days the due date has passed
	 * @param lateFee the late fee
	 */
	public void calculateLateFee(double lateFee) {
		this.lateFee += lateFee;
	}

	/**
	 * Print member's info
	 */
	public String toString() {
		String description =  "Name: " + this.name + ", id: " + this.ID + ", phone: " + phone + ", email: " + this.email + 
				", address: " + this.address + ", expiredDate: " + this.expiredDate + ", member status: " + this.status +
				", borrowed items: " + this.borrowedItems.toString() + ", late fee: " + this.lateFee;
		return description;
	}
	
	/****** Getter Functions ******/
	public MemberStatus getStatus() { return this.status; }

	public String getName() { return this.name; }

	public String getID() { return this.ID; }

	public List<String> getBorrowedItems() { return this.borrowedItems; }

	public LocalDate getExpiredDate() { return this.expiredDate; }
	
	public double getLateFee() { return this.lateFee; }
	
	/****** Setter Functions ******/
	public void setStatus(MemberStatus status) { this.status = status; }
	
	
	

	
}
