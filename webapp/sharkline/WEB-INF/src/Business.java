public class Business {

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
		if(description == null){
			return true;
		}
		return description.equals(desc);

	}


	public String getBusinessAbstract() {
		return businessAbstract;
	}


	public boolean setBusinessAbstract(String abs) {
		businessAbstract = abs;
		if(businessAbstract == null){
			return true;
		}
		return businessAbstract.equals(abs);
	}


	public String getLogoPath() {
		return logoPath;
	}


	public boolean setLogoPath(String logo) {
		logoPath = logo;
		if(logoPath == null){
			return true;
		}
		return logoPath.equals(logo);

	}


	public Size getSize() {
		return size;
	}


	public boolean setSize(Size s) {
		size = s;
		if(size == null){
			return true;
		}
		return size.equals(s);

	}


	public int getYear() {
		return year;
	}


	public boolean setYear(int y) {
		year = y;
		if(year > 0){
			return true;
		}
		return year == y;
	}


	public int getInvestmentAsk() {
		return investmentAsk;
	}


	public boolean setInvestmentAsk(int ask) {
		investmentAsk = ask;
		if(investmentAsk > 0){
			return true;
		}
		return investmentAsk == ask;
	}


	public int getEquityOffer() {
		return equityOffer;
	}


	public boolean setEquityOffer(int offer) {
		equityOffer = offer;
		if(equityOffer <= 0){
			return false;
		}
		return equityOffer == offer;
	}


	public String getWebsite() {
		return website;
	}


	public boolean setWebsite(String site) {
		website = site;
		if(website == null){
			return true;
		}
		return website.equals(site);
	}


	public String getCeoName() {
		return ceoName;
	}


	public boolean setCeoName(String name) {
		ceoName = name;
		if(ceoName == null){
			return true;
		}
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
