//declaring a class structure for 3rd report
import java.util.ArrayList;
import java.util.List;

public class R3_Structure {
	
			String product;
			int month;
			int monthMaxSale;
			public List<Integer> One_Month_sales = new ArrayList<Integer>();  // Contains all the sales for one month
			
			public int getAvgSalesForOneMonth() {
				int totalSales = 0;
				for(int i=0; i<One_Month_sales.size(); i++) {
					totalSales = totalSales + One_Month_sales.get(i);
				}
				if(One_Month_sales.size() == 0) return 0;
				else return totalSales / One_Month_sales.size();
			}
			public List<Integer> getSalesForOneMonth() {
				return this.One_Month_sales;
			}
			public void setSalesForOneMonth(Integer al){
				
				this.One_Month_sales.add(al);
				
			}	
			
			public String getProduct() {
				return product;
			}
			public void setProduct(String product) {
				this.product = product;
			}
			public int getMonth() {
				return month;
			}
			public void setMonth(int month) {
				this.month = month;
			}
			public int getMonthMaxSale() {
				return monthMaxSale;
			}
			public void setMonthMaxSale(int monthMaxSale) {
				this.monthMaxSale = monthMaxSale;
			}
		
	}

