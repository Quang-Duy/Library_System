package Library;

import Helper.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	/**
	 * The display menu
	 * Only print once
	 */
	public static void displayMenu() {
		System.out.println("Welcome to QuickLib!!!");
		System.out.println("********************************");
		System.out.println("1. Search items by keywords \n2. Add new item \n3. Update item info \n4. Search members by keywords \n5. Register new member "
				+ "\n6. Update member info \n7. Borrow items \n8. Return items \n9. Save data \n10. Quit");
		System.out.println("*************************************");
	}

	/**
	 * Load in pre-existed items from a file to the library system
	 * @param library the library system
	 * @throws FileNotFoundException throws exception if file not found
	 */
	public static void read_in_items(LibrarySystem library) throws FileNotFoundException {
		String fileName = "Items.txt";
		Scanner scan = new Scanner(new File(fileName), "UTF-8");

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			List<String> info = Arrays.asList(line.trim().split(","));

			String category = info.get(info.size() - 1);
			if (category.equalsIgnoreCase("Book")) {
				library.addNewBook(info);
			} else if (category.equalsIgnoreCase("DVD")) {
				library.addNewDVD(info);
			} else if (category.equalsIgnoreCase("Journal")) {
				library.addNewJournal(info);
			}
		}

		scan.close();
	}
	
	/**
	 * Load in pre-existed members from a file to the library system
	 * @param library the library system
	 * @throws FileNotFoundException throws exception if file not found
	 */
	public static void read_in_members(LibrarySystem library) throws FileNotFoundException {
		String fileName = "Members.txt";
		Scanner scan = new Scanner(new File(fileName), "UTF-8");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			List<String> info = Arrays.asList(line.trim().split(","));
			
			library.addMember(info);
		}
		
		scan.close();
	}

	public static void main(String[] args) {
		LibrarySystem library = new LibrarySystem();
		
		try {
			read_in_items(library);
			read_in_members(library);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner scan = new Scanner(System.in);
		int choice = 0;

		displayMenu();

		do {
			library.updateExpiredDate(); //Keep updating expired date of items and members, as long as the program is running 
			
			System.out.print("Enter a function (1-10): ");
			try {
				choice = scan.nextInt();

				if (choice < 1 || choice > 10) {
					System.out.println("Input Error! Please try again!\n");
				} else {
					scan.nextLine(); //Get ready to scan a new line

					String category = "";
					String title = "";
					String ID = "";
					
					switch (choice) {
					case 1: //Search items by keywords
						System.out.print("Category: ");
						category = scan.nextLine();

						System.out.print("Keywords: ");
						title = scan.nextLine();

						if (category.equalsIgnoreCase("Book")) {
							if (title.length() > 0) {
								System.out.println(library.printBookInfo(title));
							} else {
								System.out.println(library.getItem().bookToString());
							}
							continue;
						} else if (category.equalsIgnoreCase("DVD")) {
							if (title.length() > 0) {
								System.out.println(library.printDVDInfo(title));
							} else {
								System.out.println(library.getItem().DVDToString());
							}
							continue;
						} else if (category.equalsIgnoreCase("Journal")) {
							if (title.length() > 0) {
								System.out.println(library.printJournalInfo(title));
							} else {
								System.out.println(library.getItem().JournalToString());
							}
							continue;
						} else {
							System.out.println("No such category!\n");
							continue;
						}
						
					case 2: //Add new item
						System.out.print("Category: ");
						category = scan.nextLine();

						if (category.equalsIgnoreCase("Book")) {
							library.addNewBook(Helper.getBookInfo(scan));
							System.out.println("***** Successfully added a new book! *****\n");
							continue;
						} else if (category.equalsIgnoreCase("DVD")) {
							library.addNewDVD(Helper.getDVDInfo(scan));
							System.out.println("***** Successfully added a new DVD! *****\n");
							continue;
						} else if (category.equalsIgnoreCase("Journal")) {
							library.addNewJournal(Helper.getJournalInfo(scan));
							System.out.println("***** Successfully added a new journal! *****\n");
							continue;
						} else {
							System.out.println("No such category!\n");
							continue;
						}
						
					case 3: //Update item info
						System.out.print("Category: ");
						category = scan.nextLine();

						System.out.print("Title: ");
						title = scan.nextLine();

						if (category.equalsIgnoreCase("Book")) {
							if (library.searchBook(title)) {
								library.updateBook(Helper.getBookInfo(scan), title);
								System.out.println("***** Successfully updated book(s)! *****\n");
							} else {
								System.out.println("** No book found! **\n");
							}
							continue;
						} else if (category.equalsIgnoreCase("DVD")) {
							if (library.searchDVD(title)) {
								library.updateDVD(Helper.getDVDInfo(scan), title);
								System.out.println("***** Successfully updated DVD(s)! *****\n");
							} else {
								System.out.println("** No DVD found! **\n");
							}
							continue;
						} else if (category.equalsIgnoreCase("Journal")) {
							if (library.searchJournal(title)) {
								library.updateJournal(Helper.getJournalInfo(scan), title);
								System.out.println("***** Successfully updated journal(s)! *****\n");
							} else {
								System.out.println("** No journal found! **\n");
							}
							continue;
						} else {
							System.out.println("No such category!\n");
							continue;
						}
						
					case 4: //Search members by keywords
						System.out.print("Search members by keywords: ");
						String members_keyword = scan.nextLine();
						
						if (members_keyword.length() > 0) {
							System.out.println(library.printMemberInfo(members_keyword));
						} else {
							System.out.println(library.memberToString());
						}
						continue;
						
					case 5: //Register new member
						library.addMember(Helper.getMemberInfo(scan));
						
						System.out.println("***** Successfully added new member! *****\n");
						continue;
						
					case 6: //Update member info
						System.out.print("Your ID: ");
						ID = scan.nextLine();
						
						library.updateMemberInfo(Helper.getMemberInfo(scan), ID);
						System.out.println("***** Successfully updated your info! *****\n");
						
					case 7: //Borrow item
						System.out.print("Your ID: ");
						ID = scan.nextLine();
						
						System.out.print("Category: ");
						category = scan.nextLine();
						
						System.out.print("Title (separate items by comma): ");
						title = scan.nextLine();
						
						library.borrowItems(ID, category, title);
						continue;
						
					case 8: //Return item
						System.out.print("Your ID: ");
						ID = scan.nextLine();
						
						System.out.print("Category: ");
						category = scan.nextLine();
						
						System.out.print("Title (separate items by comma): ");
						title = scan.nextLine();
						
						library.returnItems(ID, category, title);
						
						//Find the member and print out late fee
						for(int i = 0; i < library.getMember().size(); i++)
						{
							if(library.getMember().get(i).getID().equalsIgnoreCase(ID))
							{
								System.out.println("** By the way, your late fee is: " + library.getMember().get(i).getLateFee() + " **\n");
								break;
							}
						}
						continue;
						
					case 9: //Save data
						library.saveMembersData();
						library.saveItemsData();
						
						System.out.println("***** Successfully saved data! *****\n");
						continue;
					}
				}
			} catch (Exception e) {
				System.out.println("Input Error! Please try again!\n");
				scan.nextLine();
			}
		} while (choice != 10);

		System.out.println("Bye~");
	}
}
