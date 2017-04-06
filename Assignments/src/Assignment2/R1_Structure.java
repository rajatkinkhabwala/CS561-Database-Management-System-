//declaring a class structure for 1st report
class R1_Structure {
	String customer;
	String product;
	int custProdAvgSale;
	int custProdCount;
	int set_Cust_Prod_Avg_Count;
	int set_Cust_Prod_Avg_Sale;
	int get_Cust_Prod_Avg_Sale;
	int get_Cust_Prod_Sale_Avg;

		public int getCustProdCount() {
			return custProdCount;
		}
		//this method sets the value of sum
		public void set_Cust_Prod_Avg_Sale(int quantity,int i){
			if(i==0){
				
				this.custProdAvgSale = quantity;
			}
			else{
				this.custProdAvgSale = this.custProdAvgSale + quantity;
			}
			
		}
		//this method sets the value for count
		public void set_Cust_Prod_Avg_Count(int i){
			if(i==0){
				
				this.custProdCount = 1;
			}
			else{
				this.custProdCount = this.custProdCount + 1;
			}
			
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
			public int get_Cust_Prod_Sale_Avg() {
				if(custProdCount == 0) return 0;
				else return custProdAvgSale / custProdCount;
			}
		
			public int get_Cust_Prod_Avg_Sale() {
				return custProdAvgSale;
			}
	
	
}
