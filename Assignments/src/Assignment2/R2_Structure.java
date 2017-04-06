//declaring a class structure for 2nd report
import java.util.ArrayList;
import java.util.List;

class R2_Structure {
	String customer;
	String product;
	int month;
	public ArrayList<Integer> oneMonthSales = new ArrayList<Integer>();  //contains one month sale for customer product ...
	public int AvgSalesForOneMonth() {
		int totalSales = 0;
		for(int i=0; i<oneMonthSales.size(); i++) {
			totalSales = totalSales + oneMonthSales.get(i);
		}
		if(oneMonthSales.size() == 0) return 0;
		else return totalSales / oneMonthSales.size();
	}
	public List<Integer> getOneMonthSale() {
		return this.oneMonthSales;
	}
	
	public void updateSales(int Quant) {
		this.oneMonthSales.add(Quant); 
	}	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	
}