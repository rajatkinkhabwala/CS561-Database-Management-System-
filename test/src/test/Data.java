package test;

public class Data {
	private int NJMax;
	private int NJMonth;
	private int NJDay;
	private int NJYear;
	private int NYMin;
	private int NYMonth;
	private int NYDay;
	private int NYYear;
	private int CTMin;
	private int CTMonth;
	private int CTDay;
	private int CTYear;
	public int getNJMax() {
		return NJMax;
	}
	public Data(int nJMax, int nJMonth, int nJDay, int nJYear, int nYMin, int nYMonth, int nYDay, int nYYear, int cTMin,int cTMonth, int cTDay, int cTYear) {
		super();
		NJMax = nJMax;
		NJMonth = nJMonth;
		NJDay = nJDay;
		NJYear = nJYear;
		NYMin = nYMin;
		NYMonth = nYMonth;
		NYDay = nYDay;
		NYYear = nYYear;
		CTMin = cTMin;
		CTMonth = cTMonth;
		CTDay = cTDay;
		CTYear = cTYear;
	}
	public void setNJMax(int nJMax) {
		
		NJMax = nJMax;
		
	}
	public int getNJMonth() {
		return NJMonth;
	}
	public void setNJMonth(int nJMonth) {
		NJMonth = nJMonth;
	}
	public int getNJDay() {
		return NJDay;
	}
	public void setNJDay(int nJDay) {
		NJDay = nJDay;
	}
	public int getNJYear() {
		return NJYear;
	}
	public void setNJYear(int nJYear) {
		NJYear = nJYear;
	}
	public int getNYMin() {
		return NYMin;
	}
	public void setNYMin(int nYMin) {
		NYMin = nYMin;
	}
	public int getNYMonth() {
		return NYMonth;
	}
	public void setNYMonth(int nYMonth) {
		NYMonth = nYMonth;
	}
	public int getNYDay() {
		return NYDay;
	}
	public void setNYDay(int nYDay) {
		NYDay = nYDay;
	}
	public int getNYYear() {
		return NYYear;
	}
	public void setNYYear(int nYYear) {
		NYYear = nYYear;
	}
	public int getCTMin() {
		return CTMin;
	}
	public void setCTMin(int cTMin) {
		CTMin = cTMin;
	}
	public int getCTMonth() {
		return CTMonth;
	}
	public void setCTMonth(int cTMonth) {
		CTMonth = cTMonth;
	}
	public int getCTDay() {
		return CTDay;
	}
	public void setCTDay(int cTDay) {
		CTDay = cTDay;
	}
	public int getCTYear() {
		return CTYear;
	}
	public void setCTYear(int cTYear) {
		CTYear = cTYear;
	}
}
