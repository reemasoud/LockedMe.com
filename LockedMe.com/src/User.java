import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.util.regex.Pattern;

public class User {
	 protected static final String user ="root";
	    protected static final String pass ="Areej198596Areej";
	    protected static String Name;
	    protected String Email;
	    protected String Password;

	 
	    public static String getName() {
	        return Name;
	      }
	    public  void setName(String newName) {
	        User.Name = newName;
	    }

	    public String getEmail() {
	        return Email;
	        
	      } 
	    public void setEmail(String newEmail) {
	        this.Email = newEmail;
	    }

	    public String getPassword() {
	    	    return Password;
	      }
	    public void setPassword(String newPassword) {
	        this.Password = newPassword;
	    }

	    //------------------------------------------------------
	    
	    public static void registration() {
	    	Scanner input = new Scanner(System.in);
	    	boolean checkEmail = false;
	    	boolean checkName = true;
	    	boolean checkPass = false;
	    	User db =new User();
	    	do {
	    		
	    	 System.out.println("\t---Registration Page---");
	    	 System.out.println("Fill out all fields,And please be careful when you write your information.\n");
	    	 System.out.printf("Username: ");
	    	 String userName=input.next();
	    	 db.setName(userName);
	    	 System.out.printf("Email: ");
	    	 String userEmail=input.next();
	    	 db.setEmail(userEmail);
	    	 System.out.printf("Confirm Email: ");
	    	 String ConfirmEmail=input.next();
	    	 System.out.printf("Password: ");
	    	 String userPassword=input.next();
	    	 db.setPassword(userPassword);
	    	 System.out.printf("Confirm Password: ");
	    	 String ConfirmPassword=input.next();
	    	 

	    	 
	    	 if(userEmail.equals(ConfirmEmail)) {
	     		checkEmail=true;
	     	 }else {
	     		System.out.println("The email is not match ");  
	     	 }
	    	 if(!isValid(userEmail)) {
	    		 checkEmail=false;
		      		System.out.println("Email is valid");
		      	 }
	    	 
	    	 if(checkUsername(userName)) {
	      		checkName=false;
	      		System.out.println("This username already exist try again");
	      	 }
	    	 if(userPassword.equals(ConfirmPassword)) {
	   		checkPass=true;
	   	 }else {
	  		System.out.println("The password is not match ");  
	  	 }
	    	System.out.println("\t------------------------");
	    	 
	    } while(checkEmail == false || checkName == false || checkPass == false);
	    	 
	    	 
	    	 // ---------------------------------------
	    	 
	    	 Connection myCon =null;
	     	 PreparedStatement   stat=null;

	    	  	try {


	        		myCon= DriverManager.getConnection("jdbc:mysql://localhost:3306/lockedme", user, pass);
	        		String insert = "insert into user (Email,Name,Password)values (?, ?, ?)";
	        		stat = myCon.prepareStatement(insert);
	        		stat.setString (1,db.getEmail());
	        		stat.setString (2,User.getName());
	        		stat.setString (3,db.getPassword());
	        		int result = stat.executeUpdate();
	        		
	        				System.out.println("register is successfully"+result);
	        	}catch (Exception e) {
	                e.printStackTrace();

	            } finally {
	                if (stat != null) {
	                    try {
	                    	stat.close();
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
	                if (myCon != null) {
	                    try {
	                    	myCon.close();
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
	            }
	    
	    }
	    
	 // ------------------------------------------
	    public static boolean isValid(String email)
	    {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	                            "[a-zA-Z0-9_+&*-]+)*@" +
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	                            "A-Z]{2,7}$";
	                              
	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
	    }
	    
	    // ------------------------------------------
	    
	    
	    public static boolean checkUsername(String username)
	    {
	    	Connection myCon =null;
	    	PreparedStatement   stat=null;
	    	ResultSet   result=null;
	    	
	       
	        boolean checkUser = false;
	       
	        
	        try {
	        	myCon= DriverManager.getConnection("jdbc:mysql://localhost:3306/lockedme", user, pass);
	        	 String query = "SELECT * FROM `user` WHERE `Name` =?";
	        	 stat = myCon.prepareStatement(query);
	        	stat.setString(1, username);
	            
	        	result = stat.executeQuery();
	            
	            if(result.next())
	            {
	                checkUser = true;
	            }
	        } catch (SQLException e) {
	        	System.out.println(e.getClass().getName());
	        }
	         return checkUser;
	    }
	    
	    
	    

	    
	    
	    // ------------------------------------------
	    
	    public static  void login() {
	    	Scanner input = new Scanner(System.in);
	    	 String DBName="";
	    	
	    	 
	    	 System.out.println("\n\t---login Page---");
	    	 User db =new User();
	    	 System.out.printf("Username: ");
	    	 String userName=input.next();
	    	 System.out.printf("Password: ");
	    	 String userPassword=input.next();
	    	
	    	 
	    	
	    		Connection myCon =null;
	        	PreparedStatement   stat=null;
	        	ResultSet   result=null;
	        	
	            
	    	try {
	    		

	    		myCon= DriverManager.getConnection("jdbc:mysql://localhost:3306/lockedme", user, pass);
	    		String query = "SELECT * FROM `user` WHERE `Name` =? AND `Password` =? ";
	    		stat = myCon.prepareStatement(query);
	    		stat.setString(1, userName);
	    		stat.setString(2, userPassword);
	    		result = stat.executeQuery();
	    		
	    		
	    		if(result.next()) {
	    			DBName = result.getString("Name");
	    			
	    			db.setName( DBName);

	    		}else {
	    			System.out.println("Incorrect Username Or Password , try again");
	    			login();
	    		}
	    		
	    				
	    	}catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stat != null) {
	                try {
	                	stat.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	            if (myCon != null) {
	                try {
	                	myCon.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }


	    	System.out.println("\n\t------------------------");
	    	System.out.println("\n\tWelcome Agian "+User.getName());
	    	System.out.println("\n\n\t------------------------");
	    	
	    }
	    
	    // ------------------------------------------
	    
	    
}
