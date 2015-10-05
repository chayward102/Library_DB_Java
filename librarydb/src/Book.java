
public class Book {
        
      
        private int bookID;
        private String bookTitle;
        private String bookAuthor;
        private String dateAdded;
        private double bookPrice;
        private int supplierID;
        
        public Book() {

            this.bookID = -1;
            this.bookTitle = "";    
            this.bookAuthor = "";
            this.dateAdded = "";
            this.bookPrice = -1;
            this.supplierID = -1;
        }
        
        public Book(int bookID, String bookTitle,String bookAuthor,String dateAdded,double bookPrice,int supplierID){
                
            this.bookID = bookID;
            this.bookTitle = bookTitle;    
            this.bookAuthor = bookAuthor;
            this.dateAdded = dateAdded;
            this.bookPrice = bookPrice;
            this.supplierID = supplierID;
        }
        
        public int getbookID(){
               return bookID; 
        }
        
        public void setbookID(int bookID){
                this.bookID = bookID;
        }
        
        public String getbookTitle(){
               return bookTitle; 
        }
        
        public void setbookTitle(String bookTitle){
                this.bookTitle = bookTitle;
        }
        
        public String getbookAuthor(){
            return bookAuthor; 
        }
     
        public void setbookAuthor(String bookAuthor){
             this.bookAuthor = bookAuthor;
        }
        
        public String getdateAdded(){
            return dateAdded; 
        }
     
        public void setdateAdded(String dateAdded){
             this.dateAdded = dateAdded;
        }
        
        public double getbookPrice(){
               return bookPrice; 
        }
        
        public void setbookPrice(double bookPrice){
                this.bookPrice = bookPrice;
        }
       
        public int getsupplierID(){
            return supplierID; 
        }
     
        public void setsupplierID(int supplierID){
             this.supplierID = supplierID;
        }    
            
            
        
            
        
        
}

