package test;

public class Customer {
	

	private String sNAme = "";
	private int minQuantity = 0;
	private String minProduct ="";
	private String minState = "";
	private int maxQuantity = 0;
	private String maxProduct ="";
	private String maxState = "";
	private int averageQuantity = 0;
	private int minDay = 0;
	private int minMonth = 0;
	private int minYear = 0;
	private int maxDay = 0;
	private int maxMonth = 0;
	private int maxYear = 0;
	private float sum;
	private float totalRow;
	
	public String getsNAme() {
		return sNAme;
	}
	public void setsNAme(String sNAme) {
		this.sNAme = sNAme;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	public String getMinProduct() {
		return minProduct;
	}
	public void setMinProduct(String minProduct) {
		this.minProduct = minProduct;
	}
	public String getminState() {
		return minState;
	}
	public void setminState(String state) {
		this.minState = state;
	}
	public int getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public String getMaxProduct() {
		return maxProduct;
	}
	public void setMaxProduct(String maxProduct) {
		this.maxProduct = maxProduct;
	}
	public String getMaxState() {
		return maxState;
	}
	public void setMaxState(String maxState) {
		this.maxState = maxState;
	}
	public int getAverageQuantity() {
		return averageQuantity;
	}
	public void setAverageQuantity(int averageQuantity) {
		this.averageQuantity = averageQuantity;
	}
	
	public int getMinDay() {
		return minDay;
	}
	public void setMinDay(int minDay) {
		this.minDay = minDay;
	}
	public int getMinMonth() {
		return minMonth;
	}
	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}
	public int getMinYear() {
		return minYear;
	}
	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}
	public int getMaxDay() {
		return maxDay;
	}
	public void setMaxDay(int maxDay) {
		this.maxDay = maxDay;
	}
	public int getMaxMonth() {
		return maxMonth;
	}
	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}
	public int getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public float getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(float totalRow) {
		this.totalRow = totalRow;
	}
	public Customer(String sNAme, int string, String minProduct, String state, int string2, String maxProduct,String maxState,float sum,float total) {
		super();
		this.sNAme = sNAme;
		this.minQuantity = string;
		this.minProduct = minProduct;
		this.minState = state;
		this.maxQuantity = string2;
		this.maxProduct = maxProduct;
		this.sum = sum;
		this.totalRow = total;
		this.setMaxState(maxState);
	}
	
}
