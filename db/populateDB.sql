USE sharklineDB;

# TEMPLATE FOR UNVERIFIED ACCOUNTS

INSERT INTO accounts
VALUES ('fraudulentEmail@hotmail.com', 'password', 'Elon Musk', 'Investor', 0, null);

# TEMPLATE FOR BUSINESS ACCOUNTS

INSERT INTO accounts
VALUES ('googleHQ@gmail.com', 'myPassword',  'Google', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('googleHQ@gmail.com', 'Google',
'We are a tech company with a main focus on search engine optimization and cloud computing.',
null, '200+', 1998, 10000000, 1, 'https://www.google.com', 'Sundar Pichai', 'Software/Tech');

INSERT INTO accounts
VALUES ('uberOfficial@gmail.com', '12341234', 'Uber', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('uberOfficial@gmail.com', 'Uber',
'We are a company that uses smartphone apps to connect riders with drivers.',
null, '200+', 2009, 1000000, 5, 'https://www.uber.com', 'Dara Khosrowshahi', 'Transportation');


# TEMPLATE FOR INVESTOR ACCOUNTS

INSERT INTO accounts
VALUES ('johnsmith@gmail.com', 'yankees1990', 'John Smith', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('johnsmith@gmail.com', 'John Smith',
'Independently wealthy investor based in San Jose, CA.',
1000, 100000);

