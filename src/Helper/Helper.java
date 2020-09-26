package Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Helper 
{
	/**
	 * Ask users all the info of the book
	 * @param scan scan user's input
	 * @return user's input
	 */
	public static List<String> getBookInfo(Scanner scan) {
		List<String> info = new ArrayList<>();
		System.out.println("------ Enter new info: ------");

		System.out.print("Title: ");
		info.add(scan.nextLine());

		System.out.print("Author: ");
		info.add(scan.nextLine());

		System.out.print("Edition: ");
		info.add(scan.nextLine());

		System.out.print("Publication: ");
		info.add(scan.nextLine());

		info.add(validateYear(scan));

		info.add(validateISBN(scan));

		System.out.print("Language: ");
		info.add(scan.nextLine());

		System.out.print("Subject: ");
		info.add(scan.nextLine());

		System.out.println("--------------------------------");
		return info;
	}

	/**
	 * Ask users all the info of the DVD
	 * @param scan scan user's input
	 * @return user's input
	 */
	public static List<String> getDVDInfo(Scanner scan) {
		List<String> info = new ArrayList<>();
		System.out.println("------ Enter new info: ------");

		System.out.print("Title: ");
		info.add(scan.nextLine());

		System.out.print("Author: ");
		info.add(scan.nextLine());

		System.out.print("Publication: ");
		info.add(scan.nextLine());

		info.add(validateYear(scan));

		System.out.print("Language: ");
		info.add(scan.nextLine());

		System.out.print("Subject: ");
		info.add(scan.nextLine());

		System.out.println("-------------------------");
		return info;
	}

	/**
	 * Ask users all the info of the journal
	 * @param scan scan user's input
	 * @return user's input
	 */
	public static List<String> getJournalInfo(Scanner scan) {
		List<String> info = new ArrayList<>();
		System.out.println("------ Enter new info: ------");

		System.out.print("Title: ");
		info.add(scan.nextLine());

		System.out.print("Publication: ");
		info.add(scan.nextLine());

		info.add(validateYear(scan));

		info.add(validateISSN(scan));

		System.out.print("Language: ");
		info.add(scan.nextLine());

		System.out.print("Subject: ");
		info.add(scan.nextLine());

		System.out.println("-------------------------");

		return info;
	}

	/**
	 * Ask users all the info of a member
	 * @param scan scan user's input
	 * @return user's input
	 */
	public static List<String> getMemberInfo(Scanner scan) {
		List<String> info = new ArrayList<>();
		System.out.println("------ Enter your info: ------");

		System.out.print("Full name: ");
		info.add(scan.nextLine());

		System.out.print("ID (Passport or Driver License): ");
		info.add(scan.nextLine());

		info.add(validatePhoneNumber(scan));

		info.add(validateEmail(scan));

		System.out.print("Address: ");
		info.add(scan.nextLine());

		System.out.println("-------------------------");

		return info;
	}

	/**
	 * Check if user's input for the ISBN is validated
	 * If it validates, return the ISBN. Otherwise, keep prompting the user to enter the ISBN or leave it empty to skip
	 * @param scan scan user's input
	 * @return the validated ISBN
	 */
	public static String validateISBN(Scanner scan) {
		String ISBN = "";

		do {
			System.out.print("ISBN: ");
			ISBN = scan.nextLine().replaceAll("-", ""); //Scan input and get rid of hyphens

			if(ISBN.isEmpty())
				break;

			try { //Check if input contains any characters - Only Number allows
				Double.parseDouble(ISBN); 
			} catch(NumberFormatException e) {
				System.out.println("Invalid ISBN!!! Please try again!\n");
				continue;
			}

			if(ISBN.length() != 13) {
				System.out.println("Invalid ISBN!!!! Please try again!\n");
				continue;
			}

			break;

		} while(true);

		return ISBN;
	}

	/**
	 * Check if user's input for the ISSN is validated
	 * If it validates, return the ISSN. Otherwise, keep prompting the user to enter the ISSN or leave it empty to skip
	 * @param scan scan user's input
	 * @return the validated ISSN
	 */
	public static String validateISSN(Scanner scan) {
		String ISSN = "";
		String regex = "^[X0-9]+$";

		do {
			System.out.print("ISSN: ");
			ISSN = scan.nextLine().replaceAll("-", ""); //Get rid of all hyphens

			if(ISSN.isEmpty())
				break;

			try { //First 7 digits can't contain any character, so we check if the condition is met
				String first7Num = ISSN.substring(0, ISSN.length()-1);

				Double.parseDouble(first7Num);
			} catch(NumberFormatException e) {
				System.out.println("Invalid ISSN!!! Please try again!\n");
				continue;
			}

			if(ISSN.length() != 8) {
				System.out.println("Invalid ISBN!!! Please try again!\n");
				continue;
			}

			if(!ISSN.substring(ISSN.length() - 1, ISSN.length()).matches(regex))
			{
				System.out.println("Invalid ISBN!!!!! Please try again!\n");
				continue;
			}

			break;

		} while(true);

		return ISSN;
	}

	/**
	 * Check if user's input for the year is validated
	 * If it validates, return the year. Otherwise, keep prompting the user to enter the year or leave it empty to skip
	 * @param scan scan user's input
	 * @return the validated year
	 */
	public static String validateYear(Scanner scan) {
		String year = "";
		Date date = new Date();

		do {
			System.out.print("Year: ");
			year = scan.nextLine();

			if(year.isEmpty())
				break;

			if(year.length() != 4) {
				System.out.println("Invalid year format!!! Please try again!\n");
				continue;
			}

			try {
				int _year = Integer.parseInt(year);

				if(_year > (date.getYear() + 1900)) {
					System.out.println("This year is in the future!!! Please try again!\n");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid year format!!! Please try again!\n");
				continue;
			}

			break;
		} while(true);

		return year;
	}

	/**
	 * Check if user's input for phone number is validated
	 * If it validates, return the phone number. If not, keep prompting the user to enter their phone number or leave it empty to skip
	 * @param scan scan user's input
	 * @return the validated phone numbers
	 */
	public static String validatePhoneNumber(Scanner scan) {
		String phone = "";

		do {
			System.out.print("Phone number: ");
			phone = scan.nextLine().replaceAll("\\s+", ""); //Get rid of all the white space

			if(phone.isEmpty())
				break;

			if(phone.length() <= 9 || phone.length() >= 12 || !(phone.substring(0, 2).equals("09") || phone.substring(0, 2).equals("01")))
			{
				System.out.println("Invalid Phone Number!!! Please try again!\n");
				continue;
			}

			break;

		} while(true);

		return phone;
	}

	/**
	 * Check if user's input for email is validated
	 * If it validates, return the email. Otherwise, keep prompting the user to enter their email or leave it empty to skip
	 * @param scan scan user's input
	 * @return the validated email
	 */
	public static String validateEmail(Scanner scan) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email = "";

		do {
			System.out.print("Email: ");
			email = scan.nextLine();

			if(email.isEmpty())
				break;

			if(!email.matches(regex))
			{
				System.out.println("Invalid format!!! Please try again!\n");
				continue;
			}

			break;

		} while(true);

		return email;
	}
}
