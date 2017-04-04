package test;

import java.sql.*;
import java.util.*;
import test.Customer;

public class smdb {

	static HashMap<String,Customer> struct = new HashMap<String,Customer>();
	public static void main(String[] args)
	{
		String usr ="postgres";
		String pwd ="kDes@i27";
		String url ="jdbc:postgresql://localhost:5432/postgres";

		try
		{
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch(Exception e)
		{
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try
		{
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			
			
			System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s ","CUSTOMER","MIN_Q","MIN_PROD","MIN_DATE","ST","MAX_Q","MAX_PROD","MAX_DATE","ST","AVG_Q"));
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			while (rs.next())
			{
				if(struct.containsKey(rs.getString("cust"))){
					if(struct.get(rs.getString("cust")).getMinQuantity()>rs.getInt("quant")){
						struct.get(rs.getString("cust")).setsNAme(rs.getString("cust"));
						struct.get(rs.getString("cust")).setMinMonth(rs.getInt("month"));
						struct.get(rs.getString("cust")).setMinDay(rs.getInt("day"));
						struct.get(rs.getString("cust")).setMinYear(rs.getInt("year"));
						struct.get(rs.getString("cust")).setminState(rs.getString("state"));
						struct.get(rs.getString("cust")).setMinQuantity(rs.getInt("quant"));
						struct.get(rs.getString("cust")).setMinProduct(rs.getString("prod"));
					}	
					if(struct.get(rs.getString("cust")).getMaxQuantity() < rs.getInt("quant")){
						struct.get(rs.getString("cust")).setsNAme(rs.getString("cust"));
						struct.get(rs.getString("cust")).setMaxMonth(rs.getInt("month"));
						struct.get(rs.getString("cust")).setMaxDay(rs.getInt("day"));
						struct.get(rs.getString("cust")).setMaxYear(rs.getInt("year"));
						struct.get(rs.getString("cust")).setMaxState(rs.getString("state"));
						struct.get(rs.getString("cust")).setMaxQuantity(rs.getInt("quant"));
						struct.get(rs.getString("cust")).setMaxProduct(rs.getString("prod"));	
					}
					float sum = struct.get(rs.getString("cust")).getSum();
					float totalRow = struct.get(rs.getString("cust")).getTotalRow();
					struct.get(rs.getString("cust")).setSum(sum+rs.getInt("quant"));
					struct.get(rs.getString("cust")).setTotalRow(totalRow+1);
					
					
				}
				else{
					Customer customer = new Customer(rs.getString("cust"), rs.getInt("quant"), rs.getString("prod"), rs.getString("state"), rs.getInt("quant"), rs.getString("prod"), rs.getString("state"),rs.getInt("quant"),1);
					//System.out.println(customer.getsNAme());
					struct.put(rs.getString("cust"), customer);
					
				}
			}
			 Set set = struct.entrySet();
		      
		      // Get an iterator
		      Iterator i = set.iterator();
		      
		      // Display elements
		      while(i.hasNext()) {
		         Map.Entry me = (Map.Entry)i.next();
		        
		         System.out.print(String.format("%-15s", me.getKey()));
		         System.out.print(String.format("%6d", ((Customer) me.getValue()).getMinQuantity()));
		         System.out.print(String.format("%19s", ((Customer) me.getValue()).getMinProduct()));
		         System.out.print(String.format("%9s",((Customer) me.getValue()).getMinMonth()));
		         System.out.print("/");
		         System.out.print(((Customer) me.getValue()).getMinDay());
		         System.out.print("/");
		         System.out.print(String.format("%-13s", ((Customer) me.getValue()).getMinYear()));
		         System.out.print(((Customer) me.getValue()).getminState());
		         
		         System.out.print(String.format("%18d", ((Customer) me.getValue()).getMaxQuantity()));
		         System.out.print(String.format("%18s", ((Customer) me.getValue()).getMaxProduct()));
		         System.out.print(String.format("%9s",((Customer) me.getValue()).getMaxMonth()));
		         System.out.print("/");
		         System.out.print(((Customer) me.getValue()).getMaxDay());
		         System.out.print("/");
		         System.out.print(String.format("%-15s", ((Customer) me.getValue()).getMaxYear()));
		         System.out.print(String.format("%-15s", ((Customer) me.getValue()).getMaxState()));
		         		        
		         System.out.print( ((Customer) me.getValue()).getSum() / (((Customer) me.getValue()).getTotalRow() ));
		         //System.out.print( ((Customer) me.getValue()).getTotalRow());
		         System.out.print("\n");
		      }
			
				
		}

		catch(SQLException e)
		{
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
