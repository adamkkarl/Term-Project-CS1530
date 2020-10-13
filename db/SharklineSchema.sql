USE sharklineDB;
# accounts table includes information on all the accounts,
# whether they are verified or not (marked by the verification
# variable, 1 = verified, 0 = not verified). For future reference
# for all img files they relate to a filepath, not the image itself.
# All passwords stored are hashed.
CREATE TABLE accounts (
	account_email VARCHAR(50) NOT NULL PRIMARY KEY,
    account_name VARCHAR(50) NOT NULL UNIQUE,
    account_password VARCHAR(15) NOT NULL UNIQUE,
    type ENUM('Investor', 'Business'),
    verification TINYINT NOT NULL,
    img_proof VARCHAR(1024) NOT NULL
);

# business_accounts includes information related to business accounts.
# The business_email and business_name attributes are foreign keys which
# reference the accounts table with the respective account_email and account_name.
CREATE TABLE business_accounts (
	business_email VARCHAR(50) NOT NULL PRIMARY KEY,
    business_name VARCHAR(50) NOT NULL UNIQUE,
    business_description VARCHAR(1000),
    logo VARCHAR(1024),
    size ENUM ('1-10', '11-30', '31-50', '51-100', '101-200', '200+'),
    established YEAR,
    investment_Range_Init INT UNSIGNED CHECK (investment_Range_Init > 1000),
    investment_RangeE_nd INT UNSIGNED,
    website VARCHAR(2083),
    name_CEO VARCHAR(80),
    industry ENUM('Industrial', 'Health', 'Software/Tech', 'Entertainment', 'Food', 'Retail',
    'Finance', 'Marketing', 'Sales', 'Automotive', 'Education', 'Law', 'Hotel', 'Travel',
    'Energy', 'Environment', 'Transportation', 'Other') NOT NULL,
    
    FOREIGN KEY (business_email) REFERENCES accounts(account_email),
    FOREIGN KEY (business_name) REFERENCES accounts(account_name)
);

# company_project table stores all the data entries for the company
# projects they would like to list on their account page. The business_email
# references the business_accounts table and its respective attribute business_email.

CREATE TABLE company_project (
	business_email VARCHAR(50) NOT NULL,
    project_name VARCHAR(100) NOT NULL PRIMARY KEY,
    project_description VARCHAR(1500),
    
    FOREIGN KEY (business_email) REFERENCES business_accounts(business_email)
);

# investor_accounts table stores all the data for investor type accounts.
# investor_email and investor_name attributes reference the table attributes
# of accounts, with the respective account_email and account_name.
CREATE TABLE investor_accounts (
	investor_email VARCHAR(50) NOT NULL PRIMARY KEY,
    investor_name VARCHAR(50) NOT NULL UNIQUE,
    investor_description VARCHAR(1000),
    logo VARCHAR(1024),
    size ENUM ('1-10', '11-30', '31-50', '51-100', '101-200', '200+'),
    established YEAR,
    investment_Range_Init INT UNSIGNED CHECK (investment_Range_Init > 1000),
    investment_RangeE_nd INT UNSIGNED,
    website VARCHAR(2083),
    name_CEO VARCHAR(80),
    
    FOREIGN KEY (investor_email) REFERENCES accounts(account_email),
    FOREIGN KEY (investor_name) REFERENCES accounts(account_name)
);

# account_connections table refers to currently matched companies
# and investors on the website. The foreign keys are referenced by
# their respective tables.

CREATE TABLE account_connections (
	business_name VARCHAR(50) NOT NULL UNIQUE,
    business_email VARCHAR(50) NOT NULL,
    investor_name VARCHAR(50) NOT NULL UNIQUE,
    investor_email VARCHAR(50) NOT NULL,
    date_connected DATE,
    connection_id VARCHAR(36) NOT NULL UNIQUE PRIMARY KEY,
    
    FOREIGN KEY (business_name) REFERENCES business_accounts(business_name),
    FOREIGN KEY (business_email) REFERENCES business_accounts(business_email),
    FOREIGN KEY (investor_name) REFERENCES investor_accounts(investor_name),
    FOREIGN KEY (investor_email) REFERENCES investor_accounts(investor_email)
);

# chat_log stores the information regarding messages sent
# between parties on the website. sender_name and recipient_name
# can either be from a business or investor, and as such reference
# both tables. Further work may need to be done to ensure that 
# the table cannot take a sender and recipient from the same table,
# IE an investor sending a message to another investor.
CREATE TABLE chat_log (
	connection_id VARCHAR(36) NOT NULL PRIMARY KEY,
    sender_name VARCHAR(50) NOT NULL,
    recipient_name VARCHAR(50) NOT NULL,
    message VARCHAR(900) NOT NULL,
    datetime_sent DATETIME,
    
    FOREIGN KEY (connection_id) REFERENCES account_connections(connection_id),
    FOREIGN KEY (sender_name) REFERENCES account_connections(business_name),
    FOREIGN KEY (sender_name) REFERENCES account_connections(investor_name),
    FOREIGN KEY (recipient_name) REFERENCES account_connections(business_name),
    FOREIGN KEY (recipient_name) REFERENCES account_connections(investor_name)
);


