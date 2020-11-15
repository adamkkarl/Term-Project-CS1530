public class Account
{
  private String accountEmail;
  private String accountName;
  private String accountPassword;
  private boolean isVerified;
  private String imgPath;
  private Type accountType;

  public Account()
  {

  }
  public Account(String accountEmail, String accountName,
      String accountPassword, boolean isVerified,
      String imgPath, Type accountType)
  {
    this.accountEmail = accountEmail;
    this.accountName = accountName;
    this.accountPassword = accountPassword;
    this.isVerified = isVerified;
    this.imgPath = imgPath;
    this.accountType = accountType;
  }

  public String getEmail()
  {
    return accountEmail;
  }
  public String getName()
  {
    return accountName;
  }
  public String getPassword()
  {
    return accountPassword;
  }
  public boolean checkVerified()
  {
    return isVerified;
  }
  public String getImgPath()
  {
    return imgPath;
  }
  public Type getType()
  {
    return accountType;
  }
  public boolean setEmail(String email)
  {
    accountEmail = email;
    if(email == null)
      return false;
    return accountEmail.equals(email);
  }
  public boolean setName(String name)
  {
    accountName = name;
    if(name == null)
      return false;
    return accountName.equals(name);
  }
  public boolean setPassword(String password)
  {
    accountPassword = password;
    if(password == null)
      return false;
    return accountPassword.equals(password);
  }
  public boolean verify()
  {
    isVerified = true;
    return isVerified;
  }
  public boolean setImgPath(String img)
  {
    imgPath = img;
    if(imgPath == null)
      return true;
    return imgPath.equals(img);
  }
  public boolean setType(Type type)
  {
    accountType = type;
    return accountType == type;
  }
}
