
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
 * 1.Retrieve each row from database
 * 2.generate combination for customer product and month
 * 3.if combination is present in map update the value for sum count
 * 4.else initialize the value for sum and count
 * for each value find before and after month
 * if customer and product is same and month is beforeMonth calculate avg for beforeMonth
 * if customer and product is same and month is afterMonth calculate avg for afterMonth
 * display result
   
	  
 */
public class AssignmentReport2 {

	public static String userName = "postgres"; // User name of the Postgresql Connection.
	public static String password = "rk"; // Password of the Postgresql Connection.
	public static String dbURL = "jdbc:postgresql://localhost:5433/postgres"; // Database URL of the Postgresql Connection.
	public static List<R2_Structure> TempData = new ArrayList<R2_Structure>(); // Contains the customer and product related month sales.
	public static Map<String, R2_Structure> dataMap = new HashMap<String, R2_Structure>();
	public static void main(String[] args) {

		// Loading the postgresql driver.
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Postgresql Driver has been loaded successfully.\n");
		}catch(Exception e) {
			System.out.println("Postgresql Driver failed to load!\n");
			e.printStackTrace();
		}

		// Connecting to the server.
		try {
			Connection connection = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("Connection to the server established sucessfully.\n");

			// Creating a connection statement to execute the query.
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Sales");

			while(resultSet.next()) {
				String customer = resultSet.getString("cust");
				String product = resultSet.getString("prod");
				String combKey = customer+""+product;
				int month = Integer.parseInt(resultSet.getString("month"));
				int quantity = Integer.parseInt(resultSet.getString("quant")); 
				
				//check if cust prod combination is present in map
				 if(dataMap.containsKey(combKey)){
					
					 //if present update the value
					 R2_Structure dv = dataMap.get(combKey);
						dv.updateSales(quantity);//setting values for sales
						dataMap.put(combKey, dv);//adding value to map..
					}
					else{
						R2_Structure data = new R2_Structure();//making an object to set the values...
						data.setCustomer(customer);//setting values for customer
						data.setProduct(product);//setting values for product
						data.setMonth(month);//Setting values for month
						data.updateSales(quantity);//setting values for sales
						TempData.add(data);//adding all data into list
						
					}
			}		
			System.out.println("CUSTOMER  PRODUCT	MONTH 	AVG_BEFORE AVG_AFTER");
			System.out.println("---------------------------------------------------------------");
			//for every combination in list 
			for(R2_Structure data : TempData) {
				int beforeMonthAvg = 0;
				int afterMonthAvg = 0;
				String bma,ama;
				
				for(R2_Structure monthData : TempData) {
					if(data.getCustomer().equals(monthData.getCustomer()) && data.getProduct().equals(monthData.getProduct()) && (monthData.getMonth() == data.getMonth()-1 || monthData.getMonth() == data.getMonth()+1)) {
						//if customer and product is same and month is beforeMonth
						//calculate value for beforeMonth avg
						if(data.getCustomer().equals(monthData.getCustomer()) && data.getProduct().equals(monthData.getProduct()) && monthData.getMonth() == data.getMonth()-1) {
							beforeMonthAvg = beforeMonthAvg+monthData.AvgSalesForOneMonth();
						}

						//if customer and product is same and month is afterMonth
						//calculate value for afterMonth avg
						if(data.getCustomer().equals(monthData.getCustomer()) && data.getProduct().equals(monthData.getProduct()) && monthData.getMonth() == data.getMonth()+1) {
							
							afterMonthAvg = afterMonthAvg + monthData.AvgSalesForOneMonth();
						}
					}
				}
				if(beforeMonthAvg == 0){
					 bma = "<NULL>";
				}
				else{
					bma = ""+beforeMonthAvg;
				}
				if(afterMonthAvg == 0){
					 ama = "<NULL>";
				}
				else{
					ama = ""+afterMonthAvg;
				}
				
				System.out.println(String.format("%-10s", data.getCustomer()) + String.format("%-10s", data.getProduct() )+ String.format("%10s", data.getMonth())+ String.format("%10s", bma) +String.format("%10s", ama));
			}
					
			
			
		}catch(Exception e) {
			System.out.println("Connection couldn't be established.\nVerify the username, password and the connection URL.\n");
			e.printStackTrace();
		}
	}
}


