


public class LockedMeMain {
	
    public static void WelcomeScreen() {
    	
    	String Information = "\n\t** Welcome to LockedMe.com ** \n"+"\nThis application was developed by Reema Sarhan. \n\n";
    	
    	System.out.println(Information);
    	
    	String appFeatures = "This is application provide you this function :-\n"
				+ "1- Retrieve all file names in your folder.\n"
				+ "2- Add, search, or delete files in your folder.\n"
				+ "\n\t***Please be careful  when you write the filename to make sure is right file for searching or deleting files.***\n";
    	
    	System.out.println(appFeatures);
    	
    	
    }
	public static void main(String[] args) {
		
     
		WelcomeScreen();	
		
 		Menu.RunAccountMenu();
		

		
		
	}

	
	
	
}
