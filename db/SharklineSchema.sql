CREATE DATABASE IF NOT EXISTS sharklineDB;
USE sharklineDB;

DROP TABLE IF EXISTS chat_log;
DROP TABLE IF EXISTS account_connections;
DROP TABLE IF EXISTS investor_accounts;
DROP TABLE IF EXISTS company_project;
DROP TABLE IF EXISTS business_accounts;
DROP TABLE IF EXISTS accounts;

# accounts table includes information on all the accounts,
# whether they are verified or not (marked by the verification
# variable, 1 = verified, 0 = not verified). For future reference
# for all img files they relate to a filepath, not the image itself.
# All passwords stored are hashed.

CREATE TABLE accounts (
	account_email VARCHAR(50) NOT NULL PRIMARY KEY,
    account_password VARCHAR(25) NOT NULL,
    account_name VARCHAR(50) NOT NULL,
    type ENUM('Investor', 'Business'),
    verification TINYINT DEFAULT 0,
    img_proof VARCHAR(1024)
);

# business_accounts includes information related to business accounts.
# The business_email is a foreign key which references the accounts table

CREATE TABLE business_accounts (
	business_email VARCHAR(50) NOT NULL PRIMARY KEY,
    business_name VARCHAR(50) NOT NULL UNIQUE,
    business_description VARCHAR(1000),
    business_abstract VARCHAR(200),
    logo VARCHAR(1024),
    size ENUM ('1-10', '11-30', '31-50', '51-100', '101-200', '200+'),
    established YEAR,
    investment_ask INT UNSIGNED CHECK (investment_ask >= 1000),	# investment ask
    equity_offer INT UNSIGNED CHECK (equity_offer > 0),			# percent of the company to give
    website VARCHAR(2083),
    name_CEO VARCHAR(50),
    industry ENUM('Industrial', 'Health', 'Software/Tech', 'Entertainment', 'Food', 'Retail',
    'Finance', 'Marketing', 'Sales', 'Automotive', 'Education', 'Law', 'Hotel', 'Travel',
    'Energy', 'Environment', 'Transportation', 'Other') NOT NULL,

    FOREIGN KEY (business_email) REFERENCES accounts(account_email) ON DELETE CASCADE
);

# investor_accounts table stores all the data for investor type accounts.
# investor_email reference the accounts table

CREATE TABLE investor_accounts (
	investor_email VARCHAR(50) NOT NULL PRIMARY KEY,
    investor_name VARCHAR(50) NOT NULL,
    investor_description VARCHAR(1000),
    investor_abstract VARCHAR(200),
    image VARCHAR(1024),
    investment_range_init INT UNSIGNED CHECK (investment_range_init >= 1000),
    investment_range_end INT UNSIGNED CHECK (investment_range_end > 0),
    website VARCHAR(2083),
    name_CEO VARCHAR(50),

    FOREIGN KEY (investor_email) REFERENCES accounts(account_email) ON DELETE CASCADE
);

# account_connections table refers to currently matched companies
# and investors on the website. The connected attribute shows if
# the user has accepted the request or if it is still pending.

CREATE TABLE account_connections (
    business_email VARCHAR(50) NOT NULL,
    investor_email VARCHAR(50) NOT NULL,
	connection_id int NOT NULL UNIQUE AUTO_INCREMENT,
    date_connected DATE,
		connected TINYINT DEFAULT 0, # 1 if accepted, 0 otherwise
		sender TINYINT DEFAULT 1, # 0= sent by business, 1 = sent by investor

    PRIMARY KEY(business_email, investor_email),
    FOREIGN KEY (business_email) REFERENCES business_accounts(business_email) ON DELETE CASCADE,
    FOREIGN KEY (investor_email) REFERENCES investor_accounts(investor_email) ON DELETE CASCADE
);

# chat_log stores the information regarding messages sent
# between parties on the website. business_email and investor_email
# each reference the business and investor accounts.
# sender is 0 if the business sent the message, and
# 1 if the investor sent the message

CREATE TABLE chat_log (
	connection_id int NOT NULL,
    datetime_sent DATETIME,
    sender TINYINT DEFAULT 1, #0=sent by business, 1=sent by investor
    message VARCHAR(900) NOT NULL,

    PRIMARY KEY(connection_id, datetime_sent)
);
