
public class CustomerBorrowed {
	private int bookID;
    private String bookTitle;
    private String bookAuthor;
    private String dateBorrowed;
    private int daysBorrowed;
    private int daysOverdue;
    private int bookFine;
    
    public CustomerBorrowed(int bookID, String bookTitle, String bookAuthor, String dateBorrowed, int daysBorrowed, int daysOverdue, int bookFine) {
    	this.bookID = bookID;
    	this.bookTitle = bookTitle;
    	this.bookAuthor = bookAuthor;
    	this.daysBorrowed = daysBorrowed;
    	this.daysOverdue = daysOverdue;
    	this.bookFine = bookFine;
    }

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(String dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public int getDaysBorrowed() {
		return daysBorrowed;
	}

	public void setDaysBorrowed(int daysBorrowed) {
		this.daysBorrowed = daysBorrowed;
	}

	public int getDaysOverdue() {
		return daysOverdue;
	}

	public void setDaysOverdue(int daysOverdue) {
		this.daysOverdue = daysOverdue;
	}

	public int getBookFine() {
		return bookFine;
	}

	public void setBookFine(int bookFine) {
		this.bookFine = bookFine;
	}
    
}
