package test;

import java.sql.*;
import java.util.*;
import test.Data;

public class smdb2 {

	static HashMap<String,Data> struct = new HashMap<String,Data>();
	public static void main(String[] args)
	{
		String usr ="postgres";
		String pwd ="kDes@i27";
		String url ="jdbc:postgresql://localhost:5432/postgres";
		Integer i = null;

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
			
			 System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s ", "CUSTOMER","PRODUCT","NJ_MAX","DATE","NY_MIN","DATE","CT_MIN","DATE"));
		     System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			
			while (rs.next())
			{
				String comb=String.format("%-15s %-15s", rs.getString("cust"),rs.getString("prod"));
				if(struct.containsKey(comb)){
					//System.out.println(rs.getString("state").equals("CT"));
					if(struct.get(comb).getCTMin() > rs.getInt("quant") && rs.getString("state").equals("CT") ){
						struct.get(comb).setCTMin(rs.getInt("quant"));
						struct.get(comb).setCTMonth(rs.getInt("month"));
						struct.get(comb).setCTDay(rs.getInt("day"));
						struct.get(comb).setCTYear(rs.getInt("year"));
					}
					if(struct.get(comb).getNYMin() > rs.getInt("quant") && rs.getString("state").equals("NY") && rs.getInt("year") < 2009){
						struct.get(comb).setNYMin(rs.getInt("quant"));
						struct.get(comb).setNYMonth(rs.getInt("month"));
						struct.get(comb).setNYDay(rs.getInt("day"));
						struct.get(comb).setNYYear(rs.getInt("year"));
					}
					if(struct.get(comb).getNJMax() < rs.getInt("quant") && rs.getString("state").equals("NJ") && rs.getInt("year") < 2009){
						struct.get(comb).setNJMax(rs.getInt("quant"));
						struct.get(comb).setNJMonth(rs.getInt("month"));
						struct.get(comb).setNJDay(rs.getInt("day"));
						struct.get(comb).setNJYear(rs.getInt("year"));
					}
				}
				else {
					if( rs.getString("state").equals("NJ")){
						struct.put(comb,new Data(rs.getInt("quant"), rs.getInt("month"), rs.getInt("day"), rs.getInt("year"), 0, 0, 0, 0, 0, 0, 0, 0));
					}
					else if( rs.getString("state").equals("NY")){
						struct.put(comb,new Data(0, 0, 0, 0,rs.getInt("quant"), rs.getInt("month"), rs.getInt("day"), rs.getInt("year"),  0, 0, 0, 0));
					}
					else if( rs.getString("state").equals("CT")){
						struct.put(comb,new Data(0, 0, 0, 0,  0, 0, 0, 0,rs.getInt("quant"), rs.getInt("month"), rs.getInt("day"), rs.getInt("year")));
					}
				}
				
			}
			 Set set1 = struct.entrySet();
		      
		      // Get an iterator
		      Iterator i1 = set1.iterator();
		      
		      // Display elements
		      while(i1.hasNext()) {
		    	  Map.Entry me1 = (Map.Entry)i1.next();
			      System.out.print(me1.getKey() + "\t ");
			     /* if(((Data) me1.getValue()).getCTMin() == 0)
			      	System.out.print(String.format("%-12s", i));
			      else*/
			    	System.out.print(String.format("%-12s", ((Data) me1.getValue()).getCTMin()));  
			      
			     /* if(((Data) me1.getValue()).getCTMonth() == 0){
			    	  System.out.print(String.format("%-15s", i));
			      }
			      else{*/
				      System.out.print(((Data) me1.getValue()).getCTMonth());
				      System.out.print("/");
				      System.out.print(((Data) me1.getValue()).getCTDay());
				      System.out.print("/");
				      System.out.print(String.format("%-15s", ((Data) me1.getValue()).getCTYear()));
			     /* }
			      if(((Data) me1.getValue()).getNYMin() == 0)
			    	  System.out.print(String.format("%-15s", i));
				  else*/
					  System.out.print(String.format("%-15s", ((Data) me1.getValue()).getNYMin()));
			     
			      System.out.print(((Data) me1.getValue()).getNYMonth());
			      System.out.print("/");
			      System.out.print(((Data) me1.getValue()).getNYDay());
			      System.out.print("/");
			      System.out.print(String.format("%-15s", ((Data) me1.getValue()).getNYYear()));
			    
			      System.out.print(String.format("%-15s", ((Data) me1.getValue()).getNJMax()));
			      
			      System.out.print(((Data) me1.getValue()).getNJMonth());
			      System.out.print("/");
			      System.out.print(((Data) me1.getValue()).getNJDay());
			      System.out.print("/");
			      System.out.print(((Data) me1.getValue()).getNJYear());
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
