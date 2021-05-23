import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileOperations {

	public static void createfolder(String folderName) {
		
		File file = new File(folderName);

		// If file doesn't exist, then create it
		if (!file.exists()) {
			file.mkdirs();
		}
		
	}
	
	// -----------------------------
	
	public static void createfile(String filenameAd) {
		
		
		Path path = Paths.get("./Main/"+User.getName()+"/"+ filenameAd);
		Scanner sc = new Scanner(System.in);
		try {
			
			Files.createDirectories(path.getParent());
			Files.createFile(path);
			System.out.println(filenameAd + " created successfully");
			
			System.out.println("Would you like to write in the file? (Y/N)");
			String choice = sc.next().toLowerCase();

			sc.nextLine();
			if (choice.equals("y")) {
				System.out.println("\n Write your content and press enter\n");
				String content = sc.nextLine();
				Files.write(path, content.getBytes());
				System.out.println("\nContent written successfully at " + filenameAd);
//				sc.close();
			}

			
		}catch(IOException e){
			System.out.println("Failed to create file " + filenameAd);
			System.out.println(e.getClass().getName());
			
		}
		
		
		
	}
	// -----------------------------
	
	public static void showAllFiles(String path) {
		
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<File> filesList = Arrays.asList(files);
		List<String> fileListName = new ArrayList<>();

		Collections.sort(filesList);
		
		System.out.println("This is all file you have:\n");
		if(files!=null && files.length>0) {
			for (File file : filesList) {
				fileListName.add(file.getName());

			}
			
			fileListName.stream().forEach(System.out::println);
		}else {
			System.out.println("The Directory is Empty");
		}
		
		
		
	}
		
	
	
	
	// -----------------------------
	
	public static List<String> showFileLocations(String filenameser, String path){
		List<String> fileList = new ArrayList<>();
		FileOperations.searchFile(path, filenameser, fileList);
		
		
		if(fileList.isEmpty()){
			
			System.out.println("\n* Couldn't find any file with given file name \"" + filenameser + "\" *\n");
			
		}else {
			
			System.out.println("\nThe file is found at location:");

			List<String> files = IntStream.range(0, fileList.size())
					.mapToObj(index -> (index + 1) + ": " + fileList.get(index)).collect(Collectors.toList());

			files.forEach(System.out::println);
		}
		
		
		return fileList;
		
	}
	
	// -----------------------------
	
	public static void searchFile(String path, String filenameser, List<String> fileListN) {
		
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<File> filesList = Arrays.asList(files);
		
		if(files!=null && files.length>0) {
			for (File file : filesList) {
				
			   if(file.getName().startsWith(filenameser)) {
				   
				   fileListN.add(file.getAbsolutePath());
			   }
					   
			 }	
			
		 }
		
	}
	
	// -----------------------------
	
	public static void deleteFile(String path) {
		
		File filedel = new File(path);

		 
		if (filedel.delete()) {
	            System.out.println("Deleted the file: " + filedel.getName());
	        } else {
	            System.out.println("Failed to delete the file.");
	        }
		
		
	}
	
	
	
	
}
