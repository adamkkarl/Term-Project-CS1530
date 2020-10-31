public class Business {

	public enum Industry
	{
	  INDUSTRIAL,
	  HEALTH,
	  SOFTWARE_TECH,
	  ENTERTAINMENT,
	  FOOD,
	  FINANCE,
	  MARKETING,
	  AUTOMOTIVE,
	  EDUCATION,
	  LAW,
	  HOTEL,
	  TRAVEL,
	  ENERGY,
	  ENVIRONMENT,
	  TRANSPORTATION,
	  OTHER;
	}

	public enum Size
	{
	  ONE_TO_TEN,
	  ELEVEN_TO_THIRTY,
	  THIRTYONE_TO_ONEHUNDRED,
	  ONEHUNDREDANDONE_TO_TWOHUNDRED,
	  TWOHUNDREDPLUS;
	}

	private String businessEmail;
	private String businessName;
	private String description;
	private String businessAbstract;
	private String logoPath;
	private Size size;
	private int year;
	private int investmentAsk;
	private int equityOffer;
	private String website;
	private String ceoName;
	private Industry businessIndustry;


	public Business(String businessEmail, String businessName,
			String description, String businessAbstract, String logoPath,
			Size size, int year, int investmentAsk, int equityOffer,
			String website, String ceoName, Industry businessIndustry) {

		this.businessEmail = businessEmail;
		this.businessName = businessName;
		this.description = description;
		this.businessAbstract = businessAbstract;
		this.logoPath = logoPath;
		this.size = size;
		this.year = year;
		this.investmentAsk = investmentAsk;
		this.equityOffer = equityOffer;
		this.website = website;
		this.ceoName = ceoName;
		this.businessIndustry = businessIndustry;


	}


	public String getBusinessEmail() {
		return businessEmail;
	}


	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBusinessAbstract() {
		return businessAbstract;
	}


	public void setBusinessAbstract(String businessAbstract) {
		this.businessAbstract = businessAbstract;
	}


	public String getLogoPath() {
		return logoPath;
	}


	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}


	public Size getSize() {
		return size;
	}


	public void setSize(Size size) {
		this.size = size;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getInvestmentAsk() {
		return investmentAsk;
	}


	public void setInvestmentAsk(int investmentAsk) {
		this.investmentAsk = investmentAsk;
	}


	public int getEquityOffer() {
		return equityOffer;
	}


	public void setEquityOffer(int equityOffer) {
		this.equityOffer = equityOffer;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getCeoName() {
		return ceoName;
	}


	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}


	public Industry getBusinessIndustry() {
		return businessIndustry;
	}


	public void setBusinessIndustry(Industry businessIndustry) {
		this.businessIndustry = businessIndustry;
	}

}
