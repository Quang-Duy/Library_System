# Library System
The code here is a mini library system, to manage the library's collection of items (books, journals, and DVDs) and lend them to registered members

## What does the Library System offer?
* Automatically loads the data from a number of default text files, which contains info about items, members and borrowing records when the program starts
* Search and display members/items containing all the keywords, or display all members/items if the keywords are empty
* Register new members/items into the library system
* Update members/items' info
* Borrow items: a registered member can borrow max 5 items and keep each item up to 14 days for books, 7 days for journals and DVD
* Return items: update that the items are returned and calculate late fee ($0.1 per day)
* Save all program data to default text files to avoid losing data in case of power outage
* Handling all possible input errors and validation for ISBN, ISSN, phone, email, year, date, etc.

## Future updates
GUI will be included in Library System to improve user's experience and friendly environment