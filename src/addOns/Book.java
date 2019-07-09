package addOns;

public class Book {

	String isbn;
	String title;
	
	public Book(String isbn, String title){
		if(checkLength(isbn))
		{
			this.isbn = isbn;
			this.title = title;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	private boolean checkLength(String isbn2) {
		if(isbn2.length() == 13){
			return true;
		}
		else{
			return false;
		}
		
	}
	public String getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}

	
}
