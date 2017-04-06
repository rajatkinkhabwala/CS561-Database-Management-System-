import java.sql.*;
import java.util.*;

/*
 * Author:Rajat Kinkhabwala
 * CWID: 10414081
 * Purpose: DBMS Fall 2016, Assignment-2 Report 3
 * 
 * General instructions on how to execute your program:
	    1. Download the jdbc postgresql jar file at https://jdbc.postgresql.org/download.html
		2. Now open eclipse and create a JAVA project 'DBMS'.
		3. Right click on project. Go to Build Path -> Configure Build Path.
		4. In the Libraries section, click 'Add External Jars' and  add the postgresql jar file 
		   which was downloaded in step 1.
		5. Add java files to the src folder (Default package) of the Eclipse project.
		6. Modify the Static variables userName, password and dbURL with evaluator's system specific credentials.
		7. Right click on the report java file and select Run As -> Java Application, 
		   output will be seen in the eclipse console.
   
 * Justification of your choice of data structures for your program:
	   1. To hold the customer and product data along with the customer's other product data and product's other customer data a class has been created.
	   2. DataSet class is the container class which is the type for the ArrayList.
	   3. ArrayList will bind the entire data in one single container class. Also it helps in accessing the data using getters and setters of the Container class.
   
 * A detailed description of the algorithm of your program:
   	1. Retrieve each row from the database
   	2.Generate combination of product and month
   	3. if combination is present in list update the value for sum count and max
   	4 . else add the combination to list and initialize values for sum,count,max  
   	5. for each value in list
   	   find the avg and max value for product and current month
   	6. Find the values for previous and after month
   	7. For each value get values for quantities of product and previous month
   	   if quantity is between avg and max update count for month before
   	8.For each value get values for quantities of product and after month
   	   if quantity is between avg and max update count for month after
   	9.Display results

	  
 */
public class AssingmentReport3 {

	public static String userName = "postgres"; // User name of the Postgresql Connection.
	public static String password = "rk"; // Password of the Postgresql Connection.
	public static String dbURL = "jdbc:postgresql://localhost:5433/postgres"; // Database URL of the Postgresql Connection
	public static List<R3_Structure> Tempdata = new ArrayList<R3_Structure>(); // Contains the customer and product related month sales.
	
	public static void main(String[] args) {

		
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Postgresql Driver has been loaded successfully.\n");
		}catch(Exception e) {
			System.out.println("Postgresql Driver failed to load!\n");
			e.printStackTrace();
		}

		
		try {
			Connection connection = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("Connection to the server established sucessfully.\n");

			// Creating a connection statement to execute the query.
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Sales");

			while(rs.next()) {
				String product = rs.getString("prod");//declaring a variable for product value
				int month = Integer.parseInt(rs.getString("month"));//declaring a variable for month value
				int Quantity = Integer.parseInt(rs.getString("quant"));//declaring a variable for quant value
				boolean newValue = true;

				for(R3_Structure Data : Tempdata) {
					if(Data.getProduct().equals(product) && Data.getMonth() == month) {
						//add quantity to list using method
						Data.setSalesForOneMonth(Quantity);
						//update the value of max
						if(Data.monthMaxSale < Quantity) Data.monthMaxSale = Quantity;
						newValue = false;
					}
				}
				
				if(newValue) {
					//if data value is not present set the value for product,month,
					//add quantity to list
					//set the value for max
					R3_Structure data = new R3_Structure();
					data.setProduct(product);
					data.setMonth(month);
					List<Integer>salesOne = data.getSalesForOneMonth();
					salesOne.add(Quantity);
					if(data.getMonthMaxSale()< Quantity){
						data.setMonthMaxSale(Quantity);
					}
					Tempdata.add(data);
				}
			}
			System.out.println("PRODUCT		MONTH 		BEFORE_TOT		AFTER_TOT");
			System.out.println("=======		======		===========		=========");
			for(R3_Structure data : Tempdata) {
				//for each value in list calculate max and avg for current month
				int max = data.getMonthMaxSale();
				int avg = data.getAvgSalesForOneMonth();
				int beforeCount=0, aftercount=0;
				String bcS,acS;
				for(R3_Structure dataOfMonth : Tempdata) {
					//if product is same and month is previous month
						if(data.getProduct().equals(dataOfMonth.getProduct()) && dataOfMonth.getMonth() == data.getMonth()-1) {
							List<Integer> quantityList = dataOfMonth.getSalesForOneMonth();
							for(int i : quantityList) {
								//if the quantity lies between avg and max of current month update the count for before month
								if( i>= avg && i <= max) {
									beforeCount = beforeCount+1;
								}
							}
						}

						//if product is same and month is after month
						if(data.getProduct().equals(dataOfMonth.getProduct()) && dataOfMonth.getMonth() == data.getMonth()+1) {
							//if product is same and month is previous month
							List<Integer> quantityList = dataOfMonth.getSalesForOneMonth();							
							for(int i : quantityList) {
								//if the quantity lies between avg and max of current month update the count for after month
								if(i >= avg && i <= max) {
									aftercount = aftercount+1;
								}
							}
						}
				}
				//setting the condition and setting value null for 0
				if(beforeCount == 0){
					 
					bcS = "<NULL>";
				}
				else{
					bcS = ""+beforeCount;
				}
				if(aftercount == 0){
					 
					acS = "<NULL>";
				}
				else{
					acS = ""+aftercount;
				}
				//displaying output
				System.out.println(data.getProduct() + "	   " + String.format("%10s", data.getMonth())+ "		"+ String.format("%10s", bcS)+
						"		"+ String.format("%10s", acS));
			}
			
			
		}catch(Exception e) {
			System.out.println("Connection couldn't be established.\nVerify the username, password and the connection URL.\n");
			e.printStackTrace();
		}
		}
}


