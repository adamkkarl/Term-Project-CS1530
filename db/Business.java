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
	public Business(){

		
	}


	public String getBusinessEmail() {
		return businessEmail;
	}


	public boolean setBusinessEmail(String email) {
		businessEmail = email;
		if(businessEmail == null){
			return false;
		}
		return businessEmail.equals(email);
		

	}


	public String getBusinessName() {
		return businessName;
	}


	public boolean setBusinessName(String name) {
		businessName = name;
		if(businessName == null){
			return false;
		}
		return businessName.equals(name);
		
	}


	public String getDescription() {
		return description;
	}


	public boolean setDescription(String desc) {
		description = desc;
		return description.equals(desc);
		
	}


	public String getBusinessAbstract() {
		return businessAbstract;
	}


	public boolean setBusinessAbstract(String abstract) {
		businessAbstract = abstract;
		return businessAbstract.equals(abstract);
	}


	public String getLogoPath() {
		return logoPath;
	}


	public boolean setLogoPath(String logo) {
		logoPath = logo;
		return logoPath.equals(logo);
		
	}


	public Size getSize() {
		return size;
	}


	public boolean setSize(Size s) {
		size = s;
		return size.equals(s);
		
	}


	public int getYear() {
		return year;
	}


	public boolean setYear(int y) {
		year = y;
		return year.equals(y);
	}


	public int getInvestmentAsk() {
		return investmentAsk;
	}


	public boolean setInvestmentAsk(int ask) {
		investmentAsk = ask;
		return investmentAsk.equals(ask);
	}


	public int getEquityOffer() {
		return equityOffer;
	}


	public boolean setEquityOffer(int offer) {
		equityOffer = offer;
		return equityOffer.equals(offer);
	}


	public String getWebsite() {
		return website;
	}


	public boolean setWebsite(String site) {
		website = site;
		return website.equals(site);
	}


	public String getCeoName() {
		return ceoName;
	}


	public boolean setCeoName(String name) {
		ceoName = name;
		return ceoName.equals(name);
	}


	public Industry getBusinessIndustry() {
		return businessIndustry;
	}


	public boolean setBusinessIndustry(Industry busIn) {
		businessIndustry = busIn;
		if(businessIndustry == null){
			return false;
		}
		return businessIndustry.equals(busIn);
		

	}

}
