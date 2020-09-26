package Library;

import java.time.temporal.ChronoUnit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibrarySystem {
	private ArrayList<Member> members;
	private Item item;

	/**
	 * Default Constructor
	 * Create new ArrayList for members
	 * Create new Item
	 */
	public LibrarySystem() {
		this.members = new ArrayList<>();
		item = new Item();
	}

	/***********************************************************/
	/********************** Items related **********************/
	/***********************************************************/

	/**
	 * Add a new book by calling addBook function in Item class
	 * @param info the info of a book
	 */
	public void addNewBook(List<String> info) {
		item.addBook(info);
	}

	/**
	 * Add a new DVD by calling addDVD function in Item class
	 * @param info the info of a DVD
	 */
	public void addNewDVD(List<String> info) {
		item.addDVD(info);
	}

	/**
	 * Add a new journal by calling addJournal function in Item class
	 * @param info the info of a journal
	 */
	public void addNewJournal(List<String> info) {
		item.addJournal(info);
	}

	/**
	 * Search a book by calling searchBook function in Item class
	 * @param target the book's title
	 * @return true if found; false otherwise
	 */
	public Boolean searchBook(String target) {
		return item.searchBook(target);
	}

	/**
	 * Search a DVD by calling searchBookDVD function in Item class
	 * @param target the DVD's title
	 * @return true if found; false otherwise
	 */
	public Boolean searchDVD(String target) {
		return item.searchDVD(target);
	}

	/**
	 * Search a journal by calling searchBookJournal function in Item class
	 * @param target the journal's title
	 * @return true if found; false otherwise
	 */
	public Boolean searchJournal(String target) {
		return item.searchJournal(target);
	}

	/**
     * Print the book's info based on keywords by calling printBookInfo in Item class
     * @param target the keywords
     * @return the book's info if keywords match
     */
	public String printBookInfo(String target) {
		return item.printBookInfo(target);
	}

	/**
     * Print the DVD's info based on keywords by calling printDVDInfo in Item class
     * @param target the keywords
     * @return the DVD's info if keywords match
     */
	public String printDVDInfo(String target) {
		return item.printDVDInfo(target);
	}

	/**
     * Print the journal's info based on keywords by calling printJournalInfo in Item class
     * @param target the keywords
     * @return the journal's info if keywords match
     */
	public String printJournalInfo(String target) {
		return item.printJournalInfo(target);
	}

	/**
	 * Find the book in the system and update that book's info by calling update function in Item class
	 * @param info the new info
	 * @param title the title of the book
	 */
	public void updateBook(List<String> info, String title) {
		for (int i = 0; i < item.getBooks().size(); i++) {
			if (item.getBooks().get(i).getTitle().equalsIgnoreCase(title)) {
				item.getBooks().get(i).update(info);
			}
		}

	}

	/**
	 * Find the DVD in the system and update that DVD's info by calling update function in Item class
	 * @param info the new info
	 * @param title the title of the DVD
	 */
	public void updateDVD(List<String> info, String title) {
		for (int i = 0; i < item.getDVDs().size(); i++) {
			if (item.getDVDs().get(i).getTitle().equalsIgnoreCase(title)) {
				item.getDVDs().get(i).update(info);
			}
		}
	}

	/**
	 * Find the journal in the system and update that journal's info by calling update function in Item class
	 * @param info the new info
	 * @param title the title of the journal
	 */
	public void updateJournal(List<String> info, String title) {
		for (int i = 0; i < item.getJournals().size(); i++) {
			if (item.getJournals().get(i).getTitle().equalsIgnoreCase(title)) {
				item.getJournals().get(i).update(info);
			}
		}
	}

	/***********************************************************/
	/********************* Members related *********************/
	/***********************************************************/

	/**
	 * Add new member
	 * @param info the member's info
	 */
	public void addMember(List<String> info) {
		this.members.add(new Member(info));
	}

	/**
	 * Find the member in the system and update that member's info by calling update function in Member class
	 * @param info the new info
	 * @param ID the ID of a member
	 */
	public void updateMemberInfo(List<String> info, String ID) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getID().equalsIgnoreCase(ID)) {
				members.get(i).update(info);
				break;
			}
		}
	}

	/**
	 * Let a member borrow item. Each member can only borrow 5 items
	 * Save the items that member wants to borrow by calling borrowedItems function in Member class
	 * Save the member's ID who borrowed that item, set the due date and status of the item by calling borrow function in Book/DVD/Journal class
	 * @param ID the ID of a member
	 * @param category the category of an item
	 * @param title the title of an item
	 */
	public void borrowItems(String ID, String category, String title)
	{
		Member member = null;
		for(int i = 0; i < members.size(); i++) 
		{
			if(members.get(i).getID().equalsIgnoreCase(ID)) {
				member = members.get(i);
				break;
			}
		}

		if(member == null) {
			System.out.println("ID not found!!!\n");
			return;
		}

		List<String> items = Arrays.asList(title.split(", "));

		if(items.size() > 5 || (items.size() + member.getBorrowedItems().size()) > 5) {
			System.out.println("You can only borrow maximum 5 items!!!\n");
			return;
		}

		boolean flag = false;
		boolean found = false;

		if(category.equalsIgnoreCase("Book"))
		{
			ArrayList<Book> borrowedBooks = new ArrayList<>();

			for(int i = 0; i < items.size(); i++)
			{
				for(int j = 0; j < item.getBooks().size(); j++)
				{
					if(items.get(i).equalsIgnoreCase(item.getBooks().get(j).getTitle()))
					{
						if(item.getBooks().get(j).getStatus() == ItemStatus.Available)
						{
							flag = true;
							found = true;
							borrowedBooks.add(item.getBooks().get(j));
							break;
						}
						found = true;
					}
				}

				if(!found) {
					System.out.println("** " + items.get(i) + " is not found in the system!!!\n");
					return;
				}

				if(!flag) {
					System.out.println("** " + items.get(i) + " is on loan! Sorry!\n");
					return;
				}
			}

			for(int i = 0; i < borrowedBooks.size(); i++) 
			{
				borrowedBooks.get(i).borrow(member.getID());
			}
		}
		else if(category.equalsIgnoreCase("DVD"))
		{
			ArrayList<DVD> borrowedDVDs = new ArrayList<>();

			for(int i = 0; i < items.size(); i++)
			{
				for(int j = 0; j < item.getDVDs().size(); j++)
				{
					if(items.get(i).equalsIgnoreCase(item.getDVDs().get(j).getTitle()))
					{
						if(item.getDVDs().get(j).getStatus() == ItemStatus.Available)
						{
							flag = true;
							found = true;
							borrowedDVDs.add(item.getDVDs().get(j));
							break;
						}
						found = true;
					}

				}

				if(!found) {
					System.out.println("** " + items.get(i) + " is not found in the system!!!\n");
					return;
				}

				if(!flag) {
					System.out.println("** " + items.get(i) + " is on loan! Sorry!\n");
					return;
				}
			}

			for(int i = 0; i < borrowedDVDs.size(); i++) 
			{
				borrowedDVDs.get(i).borrow(member.getID());
			}
		}
		else if(category.equalsIgnoreCase("Journal"))
		{
			ArrayList<Journal> borrowedJournals = new ArrayList<>();

			for(int i = 0; i < items.size(); i++)
			{
				for(int j = 0; j < item.getJournals().size(); j++)
				{
					if(items.get(i).equalsIgnoreCase(item.getJournals().get(j).getTitle()))
					{
						if(item.getJournals().get(j).getStatus() == ItemStatus.Available)
						{
							flag = true;
							found = true;
							borrowedJournals.add(item.getJournals().get(j));
							break;
						}
					}
					found = true;
				}

				if(!found) {
					System.out.println("** " + items.get(i) + " is not found in the system!!!\n");
					return;
				}

				if(!flag) {
					System.out.println("** " + items.get(i) + " is on loan! Sorry!\n");
					return;
				}
			}

			for(int i = 0; i < borrowedJournals.size(); i++) 
			{
				borrowedJournals.get(i).borrow(member.getID());
			}
		}
		else
		{
			System.out.println("** No such category!!! **\n");
			return;
		}


		member.borrowedItems(items);
		System.out.println("***** Successfully borrow item(s)! *****\n");
	}

	/**
	 * Let a member return item.
	 * Remove the items that member borrowed
	 * Remove the member's ID who borrowed that item, set the due date and status of the item by calling returnBook/returnDVD/returnJournal function in Book/DVD/Journal class
	 * @param ID the ID of a member
	 * @param category the category of an item
	 * @param title the title of an item
	 */
	public void returnItems(String ID, String category, String title)
	{
		Member member = null;
		for(int i = 0; i < members.size(); i++) 
		{
			if(members.get(i).getID().equalsIgnoreCase(ID)) {
				member = members.get(i);
				break;
			}
		}

		if(member == null) {
			System.out.println("ID not found!!!\n");
			return;
		}

		List<String> items = Arrays.asList(title.split(", "));

		boolean flag = false;
		boolean found = false;

		if(category.equalsIgnoreCase("Book"))
		{
			for(int i = 0; i < items.size(); i++)
			{
				//Find the item that member wants to return
				for(int j = 0; j < item.getBooks().size(); j++)
				{
					if(items.get(i).equalsIgnoreCase(item.getBooks().get(j).getTitle()))
					{
						if(item.getBooks().get(j).getStatus() == ItemStatus.OnLoan && item.getBooks().get(j).getBorrowerID().equalsIgnoreCase(member.getID()))
						{
							flag = true;
							found = true;
							item.getBooks().get(j).returnBook();
							System.out.println("***** Successfully return " + items.get(i) + "! *****");
							break;
						}
						found = true;
					}
				}

				if(!found) {
					System.out.println("** " + items.get(i) + " is not found in the system!!!");
					return;
				}

				if(!flag) {
					System.out.println("** Huh? You didn't borrow " + items.get(i) + "!! **");
					return;
				}

				//Remove the item from member's borrowedItems list
				for(int k = 0; k < member.getBorrowedItems().size(); k++)
				{
					if(items.get(i).equalsIgnoreCase(member.getBorrowedItems().get(k)))
					{
						member.getBorrowedItems().remove(k);
					}
				}
				
				flag = false;
				found = false;
			}
		}
		else if(category.equalsIgnoreCase("DVD"))
		{
			for(int i = 0; i < items.size(); i++)
			{
				//Find the item that member wants to return
				for(int j = 0; j < item.getDVDs().size(); j++)
				{
					if(items.get(i).equalsIgnoreCase(item.getDVDs().get(j).getTitle()))
					{
						if(item.getDVDs().get(j).getStatus() == ItemStatus.OnLoan && item.getDVDs().get(j).getBorrowerID().equalsIgnoreCase(member.getID()))
						{
							flag = true;
							found = true;
							item.getDVDs().get(j).returnDVD();
							System.out.println("***** Successfully return " + items.get(i) + "! *****");
							break;
						}
						found = true;
					}
				}

				if(!found) {
					System.out.println("** " + items.get(i) + " is not found in the system!!!");
					return;
				}

				if(!flag) {
					System.out.println("** Huh? You didn't borrow " + items.get(i) + "!! **");
					return;
				}

				//Remove the item from member's borrowedItems list
				for(int k = 0; k < member.getBorrowedItems().size(); k++)
				{
					if(items.get(i).equalsIgnoreCase(member.getBorrowedItems().get(k)))
					{
						member.getBorrowedItems().remove(k);
					}
				}
				
				flag = false;
				found = false;
			}
		}
		else if(category.equalsIgnoreCase("Journal"))
		{
			for(int i = 0; i < items.size(); i++)
			{
				//Find the item that member wants to return
				for(int j = 0; j < item.getJournals().size(); j++)
				{
					if(items.get(i).equalsIgnoreCase(item.getJournals().get(j).getTitle()))
					{
						if(item.getJournals().get(j).getStatus() == ItemStatus.OnLoan && item.getJournals().get(j).getBorrowerID().equalsIgnoreCase(member.getID()))
						{
							flag = true;
							found = true;
							item.getJournals().get(j).returnJournal();
							System.out.println("***** Successfully return " + items.get(i) + "! *****");
							break;
						}
						found = true;
					}
				}

				if(!found) {
					System.out.println("** " + items.get(i) + " is not found in the system!!!");
					return;
				}

				if(!flag) {
					System.out.println("** Huh? You didn't borrow " + items.get(i) + "!! **");
					return;
				}

				//Remove the item from member's borrowedItems list
				for(int k = 0; k < member.getBorrowedItems().size(); k++)
				{
					if(items.get(i).equalsIgnoreCase(member.getBorrowedItems().get(k)))
					{
						member.getBorrowedItems().remove(k);
					}
				}
				
				flag = false;
				found = false;
			}
		}
		else
		{
			System.out.println("** No such category!!! **");
			return;
		}

	}

	/**
	 * Print member's info based on keywords
	 * @param target the keywords
	 * @return the member's info
	 */
	public String printMemberInfo(String target) {
		String description = "";

		for (int i = 0; i < this.members.size(); i++) {
			if (this.members.get(i).getName().contains(target)) {
				description += this.members.get(i).toString() + "\n";
			}
		}

		if (description.length() == 0)
			description = "** No members found! **\n";

		return description;
	}

	/**
	 * Print all member's info
	 * @return all member's info
	 */
	public String memberToString() {
		String description = "";

		for (int i = 0; i < this.members.size(); i++) {
			description += this.members.get(i).toString() + "\n";
		}		

		return description;
	}

	/**
	 * Update expired dates of all members and due date of all items
	 * Apply late fees if items' due date has passed 
	 */
	public void updateExpiredDate() 
	{
		double daysBetween = 0;
		double lateFee = 0;
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));

		//Check if member's account is expired. If yes, we change the status to MemberStatus.Expired
		for(int i = 0; i < members.size(); i++)
		{
			if(members.get(i).getStatus() == MemberStatus.Active && today.isAfter(members.get(i).getExpiredDate())) 
			{
				members.get(i).setStatus(MemberStatus.Expired);
			}
		}

		//Check if the borrowed items are due. If yes, notify the librarian and update new fees in this member's account
		//But first of all, we need to reset late fee for all members. Because late fee will be different every day passed by
		for(int i = 0; i < members.size(); i++)
		{
			members.get(i).resetLateFee();
		}
		
		//Now we look into all the books to see which one is passed the due date. If yes, we find the member who borrowed that book and apply late fee for that member
		for(int i = 0; i < item.getBooks().size(); i++)
		{
			if(item.getBooks().get(i).getStatus() == ItemStatus.OnLoan && today.isAfter(item.getBooks().get(i).getDueDate()))
			{
				System.out.println(item.getBooks().get(i).getTitle() + " has passed the due date. "
						+ "Please contact this member with this ID: " + item.getBooks().get(i).getBorrowerID() + "\n");

				Member member = null;

				for(int j = 0; j < members.size(); j++)
				{
					if(item.getBooks().get(i).getBorrowerID().equalsIgnoreCase(members.get(i).getID()))
					{
						member = members.get(i);
						break;
					}
				}

				daysBetween = Math.abs(ChronoUnit.DAYS.between(today, item.getBooks().get(i).getDueDate()));

				lateFee = 0.1 * daysBetween;
				member.calculateLateFee(lateFee);
			}
		}

		//Now we look into all the DVDs to see which one is passed the due date. If yes, we find the member who borrowed that DVD and apply late fee for that member
		for(int i = 0; i < item.getDVDs().size(); i++)
		{
			if(item.getDVDs().get(i).getStatus() == ItemStatus.OnLoan && today.isAfter(item.getDVDs().get(i).getDueDate()))
			{
				System.out.println(item.getDVDs().get(i).getTitle() + " has passed the due date. "
						+ "Please contact this member with this ID: " + item.getDVDs().get(i).getBorrowerID() + "\n");

				Member member = null;

				for(int j = 0; j < members.size(); j++)
				{
					if(item.getDVDs().get(i).getBorrowerID().equalsIgnoreCase(members.get(i).getID()))
					{
						member = members.get(i);
						break;
					}
				}

				daysBetween = Math.abs(ChronoUnit.DAYS.between(today, item.getDVDs().get(i).getDueDate()));

				lateFee = 0.1 * daysBetween;
				member.calculateLateFee(lateFee);
			}
		}

		//Now we look into all the journals to see which one is passed the due date. If yes, we find the member who borrowed that journal and apply late fee for that member
		for(int i = 0; i < item.getJournals().size(); i++)
		{
			if(item.getJournals().get(i).getStatus() == ItemStatus.OnLoan && today.isAfter(item.getJournals().get(i).getDueDate()))
			{
				System.out.println(item.getJournals().get(i).getTitle() + " has passed the due date. "
						+ "Please contact this member with this ID: " + item.getJournals().get(i).getBorrowerID() + "\n");

				Member member = null;

				for(int j = 0; j < members.size(); j++)
				{
					if(item.getJournals().get(i).getBorrowerID().equalsIgnoreCase(members.get(i).getID()))
					{
						member = members.get(i);
						break;
					}
				}

				daysBetween = Math.abs(ChronoUnit.DAYS.between(today, item.getJournals().get(i).getDueDate()));

				lateFee = 0.1 * daysBetween;
				member.calculateLateFee(lateFee);
			}
		}
	}
	
	/**
	 * Write to a file that contains all members' info
	 */
	public void saveMembersData() 
	{
		try {
			File file = new File("Members_Output.txt");
			
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fileWriter = new FileWriter(file.getName(), false);
			BufferedWriter output = new BufferedWriter(fileWriter);
			
			for(int i = 0; i < members.size(); i++)
			{
				output.write(members.get(i).toString() + "\n");
			}
		
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write to a file that contains all item's info
	 */
	public void saveItemsData()
	{
		try {
			File file = new File("Items_Output.txt");
			
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fileWriter = new FileWriter(file.getName(), false);
			BufferedWriter output = new BufferedWriter(fileWriter);
			
			for(int i = 0; i < item.getBooks().size(); i++) {
				output.write(item.getBooks().get(i).toString() + "\n");
			}
			
			output.write("\n");
			
			for(int i = 0; i < item.getDVDs().size(); i++) {
				output.write(item.getDVDs().get(i).toString() + "\n");
			}
			
			output.write("\n");
			
			for(int i = 0; i < item.getJournals().size(); i++) {
				output.write(item.getJournals().get(i).toString() + "\n");
			}
			
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/****** Getter Functions ******/
	public Item getItem() {
		return this.item;
	}

	public ArrayList<Member> getMember() {
		return this.members;
	}
}
