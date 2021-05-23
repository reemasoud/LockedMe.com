import java.util.List;
import java.util.Scanner;



public class Menu {

	public static void primeMenu() {
		
		String primmenu = "\n ** Select your option number from below and press Enter **\n"
				+ "1) Retrieve all files inside your folder\n" 
				+ "2) Display the menu of File operations\n"
				+ "3) Exit program\n";
		System.out.println(primmenu);
		
	}	

	// -----------------------------------
public static void accountMenu() {
		
	String accountMenu = "\n ** Select your option number from below and press Enter **\n"
			+ "1) Login\n" 
			+ "2) Register\n"
			+ "3) Exit program\n";
	System.out.println(accountMenu);
		
	}	

	// -----------------------------------
	
	public static void FileOptionsMenu() {
		
		String fileMenu = "\n ** Select your option number from below and press Enter **\n"
				+ "1) Search a file to your folder\n"
				+ "2) Add a file from your folder\n"
				+ "3) Delete for a file from your folder\n" 
				+ "4) Back to Previous Menu\n"
				+ "5) Exit program\n";

		System.out.println(fileMenu);
	}
	
	// -----------------------------------
	
	public static void RunprimeMenu() {
		boolean run = true;
		Scanner sc = new Scanner(System.in);
	do {
		try {
			primeMenu();
			int input =sc.nextInt();
			switch(input) {
			case 1://Retrieve all files inside user folder.
				FileOperations.showAllFiles("Main/"+User.getName());
				break;				
			case 2://Display the menu of File operations.
				RunFileOptionsMenu();
				break;
			case 3://Exit program.
				System.out.println("Program exited successfully"+"\n\tsee you soon.");
				run = false;
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Please choose a right option from above.");	
			}
			
		}catch(Exception e){
			System.out.println(e.getClass().getName());
			
		}
		
		
	}while(run == true);
		
		
		
		
		
	}	
	
	// -----------------------------------
	
	
	public static void RunAccountMenu() {
		
		Scanner sc = new Scanner(System.in);
		accountMenu();
		
			try {
				
				int input =sc.nextInt();
				switch(input) {
				case 1://For Login.
					
					User.login();
					FileOperations.createfolder("Main/"+User.getName());
					Menu.RunprimeMenu();
					break;				
				case 2://for registration.
					User.registration();
					User.login();
					FileOperations.createfolder("Main/"+User.getName());
					Menu.RunprimeMenu();
					break;
				case 3://Exit program.
					System.out.println("Program exited successfully"+"\n\tsee you soon.");
					
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please choose a right option from above.");	
				}
				
			}catch(Exception e){
				System.out.println(e.getClass().getName());
				
			}
		
		
		
		
	}
	
	// -----------------------------------
	
	public static void RunFileOptionsMenu() {
		boolean run = true;
		Scanner sc = new Scanner(System.in);
	do {
		try {
			
			FileOptionsMenu();
			int input =sc.nextInt();
			
			switch(input) {
			case 1://Search a file to user folder
				System.out.println("Enter the name of the file for search");
				String filenameser =sc.next();
				FileOperations.showFileLocations(filenameser, "Main/"+User.getName());
				break;				
			case 2://Add a file from user folder.
				System.out.println("Enter the name of the file for add");
				String filenameAd =sc.next();
				FileOperations.createfile(filenameAd);
				break;
			case 3://Delete for a file from user folder.
				System.out.println("Enter the name of the file for delete");
				String filenamedel =sc.next();
				List<String> filesnamedel = FileOperations.showFileLocations(filenamedel, "Main/"+User.getName());
				if(!filesnamedel.isEmpty()) {
					FileOperations.deleteFile(filesnamedel.get(0));

				}
				break;
			case 4://Back to Previous Menu.
				return;
				
			case 5://Exit program.
				System.out.println("Program exited successfully"+"\n\tsee you soon.");
				run = false;
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Please choose a right option from above.");	
			}
			
		}catch(Exception e){
			System.out.println(e.getClass().getName());
		}
		
	}while(run == true);
		
		
		
		
		
	}
	
	
	
	
	
}
