import java.sql.*;
import java.util.*;

/*
 * Author: Rajat Kinkhabwala
 * CWID: 10414081
 * Purpose: DBMS Fall 2016, Assignment-2
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
 * 2.Generate customer and product combination
 * 3.if combination is present in map
 *   update the value for sum and count
 * 4.if combination is not present initialize values for sum and count 
 * 5. for each value in list
 *  compare the value for customer and product
 *  if customer name is same and product is different calculate avg for other products
 * 6.if customer name is different and product is same calculate avg for other customer
 * 7.display results
   
	  
 */
public class AssignmentReport1 {

	public static String userName = "postgres"; // User name of the Postgresql Connection.
	public static String password = "rk"; // Password of the Postgresql Connection.
	public static String dbURL = "jdbc:postgresql://localhost:5433/postgres"; // Database URL of the Postgresql Connection.
	public static List<R1_Structure> TempData = new ArrayList<R1_Structure>(); // Contains the customer and product related sales average.
	public static Map<String,R1_Structure> dataMap = new HashMap<String, R1_Structure>();

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
				String customer = resultSet.getString("cust");//String variable that gets the valur of customer
				String product = resultSet.getString("prod");//String variable that gets the value of product
				String Combination = customer+""+product;// Customer-Product combination will be used as KEY for the map.
				int Quantity = Integer.parseInt(resultSet.getString("quant")); // quantity.
				//checking conditions if map contains the cust-prod combination key then Data will be stored into class and then class will be stored into hashmap..
			 if(dataMap.containsKey(Combination)){
					
					R1_Structure dv = dataMap.get(Combination);//object to set data in to structured class
					dv.set_Cust_Prod_Avg_Sale(Quantity,1);//setting avg_sale
					dv.set_Cust_Prod_Avg_Count(1);//setting avg_count
					dataMap.put(Combination, dv);//adding valur to map
				}
				else{
					//setting value for customer product sum count
					R1_Structure data = new R1_Structure();
					data.setCustomer(customer);
					data.setProduct(product);
					data.set_Cust_Prod_Avg_Sale(Quantity,0);
					data.set_Cust_Prod_Avg_Count(0);
					TempData.add(data);
					dataMap.put(Combination,data);
					
				}
			}
			System.out.println("CUSTOMER       	       PRODUCT              	THE_AVG		OTHER_PROD_AVG		OTHER_CUST_AVG ");
			System.out.println("========      	       =======  	         =======	==============		============= ");
			

			for(R1_Structure DS1 : TempData) {
				int otherPsum=0;int otherPCount=0; int otherCSum=0; int otherCCount =0;
				int avg = DS1.get_Cust_Prod_Avg_Sale() / DS1.getCustProdCount();
				for(R1_Structure DS2 : TempData) {
					// Update the customer's other products sales in DataSet.
					
					if(DS1.getCustomer().equals(DS2.getCustomer()) && !DS1.getProduct().equals(DS2.getProduct())) {
						
						otherPsum = otherPsum+DS2.get_Cust_Prod_Avg_Sale();
						otherPCount = otherPCount+DS2.getCustProdCount();
						
					}
					// Update the sales for product at other customers.
					if(!DS1.getCustomer().equals(DS2.getCustomer()) && DS1.getProduct().equals(DS2.getProduct())) {
				
						otherCSum = otherCSum+DS2.get_Cust_Prod_Avg_Sale();
						otherCCount = otherCCount+DS2.getCustProdCount();
					
					}
				}
				int otherPAvg = otherPsum / otherPCount;
				int otherCAvg = otherCSum / otherCCount;
			
				System.out.println(DS1.getCustomer()  +"			"+ DS1.getProduct() + "			" + String.format("%7s",avg)+"		"+ String.format("%14s",otherPAvg)+"		"+String.format("%14s",otherCAvg)); 
			}
			
		}catch(Exception e) {
			System.out.println("Connection couldn't be established.\nVerify the username, password and the connection URL.\n");
			e.printStackTrace();
		}
	}

}


