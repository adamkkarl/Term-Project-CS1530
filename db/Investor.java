

public class Investor {
private String investorEmail;
private String investorName;
private String description;
private int investmentRangeInit;
private int investmentRangeEnd;
private String website;
private String ceoName;

public Investor(String investorEmail, String investorName, String description, int investmentRangeInit,
				int investmentRangeEnd, String website, String ceoName){

	this.investorEmail = investorEmail;
	this.investorName = investorName;
	this.description = description;
	this.investmentRangeInit = investmentRangeInit;
	this.investmentRangeEnd = investmentRangeEnd;
	this.website = website;
	this.ceoName = ceoName;

}
public Investor(){

	
}

public String getInvestorEmail() {
	return investorEmail;
}
public boolean setInvestorEmail(String email) {
	investorEmail = email;
	if(investorEmail == null){
		return false;
	} 
	return investorEmail.equals(email);
}
public String getInvestorName() {
	return investorName;
}
public boolean setInvestorName(String name) {
	investorName = name;
	if(investorName == null){
		return false;
	}
	return investorName.equals(name);
}
public String getInvestorDescription() {
	return description;
}
public boolean setInvestorDescription(String desc) {
	description = desc;
	return description.equals(desc);
}
public int getInvestmentRangeInit() {
	return investmentRangeInit;
}
public boolean setInvestmentRangeInit(int init) {
	investmentRangeInit = init;
	return investmentRangeInit.equals(init);
}
public int getInvestmentRangeEnd() {
	return investmentRangeEnd;
}
public boolean setInvestmentRangeEnd(int end) {
	investmentRangeEnd = end;
	return investmentRangeEnd.equals(end);
}
public String getWebsite(){
	return website;
}
public boolean setWebsite(String site){
	website = site;
	return website.equals(site);
}
public String getCeoName(){
	return nameCEO;
}
public boolean setCeoName(String name){
	ceoName = name;
	return ceoName.equals(name);
}








}
