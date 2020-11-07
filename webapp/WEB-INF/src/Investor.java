

public class Investor {
private String investorEmail;
private String investorName;
private String description;
private String investorAbstract;
private int investmentRangeInit;
private int investmentRangeEnd;
private String website;
private String ceoName;

public Investor(String investorEmail, String investorName, String description, String abs, int investmentRangeInit,
				int investmentRangeEnd, String website, String ceoName){

	this.investorEmail = investorEmail;
	this.investorName = investorName;
	this.description = description;
	this.investorAbstract = abs;
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
	if(description == null){
		return true;
	}
	return description.equals(desc);
}

public int getInvestmentRangeInit() {
	return investmentRangeInit;
}

public boolean setInvestmentRangeInit(int init) {
	investmentRangeInit = init;
	if(investmentRangeInit <= 0){
		return false;
	}
	return investmentRangeInit == init;
}

public int getInvestmentRangeEnd() {
	return investmentRangeEnd;
}

public boolean setInvestmentRangeEnd(int end) {
	investmentRangeEnd = end;
	if(investmentRangeEnd <= investmentRangeInit){
		return false;
	}
	return investmentRangeEnd == end;
}

public String getWebsite(){
	return website;
}

public boolean setWebsite(String site){
	website = site;
	if(website == null){
		return true;
	}
	return website.equals(site);
}

public String getCeoName(){
	return ceoName;
}

public boolean setCeoName(String name){
	ceoName = name;
	if(ceoName == null){
		return true;
	}
	return ceoName.equals(name);
}

public String getInvestorAbstract(){
	return investorAbstract;
}

public boolean setInvestorAbstract(String abs){
	investorAbstract = abs;
	if(investorAbstract == null)
		return true;
	return investorAbstract.equals(abs);
}
}
