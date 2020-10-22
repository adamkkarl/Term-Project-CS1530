USE sharklineDB;

# TEMPLATE FOR UNVERIFIED ACCOUNTS

INSERT INTO accounts
VALUES ('fraudulentEmail@hotmail.com', 'password', 'Elon Musk', 'Investor', 0, null);
INSERT INTO accounts
VALUES ('Microsoft@ms.com', 'windows98', 'Bill Gates', 'Business', 0, null);

# TEMPLATE FOR BUSINESS ACCOUNTS

INSERT INTO accounts
VALUES ('googleHQ@gmail.com', 'myPassword',  'Google', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('googleHQ@gmail.com', 'Google',
'We are a tech company with a main focus on search engine optimization and cloud computing.',
'Cloud computing and search engine since 1998',
null, '200+', 1998, 10000000, 1, 'https://www.google.com', 'Sundar Pichai', 'Software/Tech');

INSERT INTO accounts
VALUES ('uberOfficial@gmail.com', '12341234', 'Uber', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('uberOfficial@gmail.com', 'Uber',
'We are a company that uses smartphone apps to connect riders with drivers.',
'Tech + transportation company since 2009',
null, '200+', 2009, 1000000, 5, 'https://www.uber.com', 'Dara Khosrowshahi', 'Transportation');

# TODO TEMPLATE FOR COMPANY PROJECTS

# TEMPLATE FOR INVESTOR ACCOUNTS

INSERT INTO accounts
VALUES ('johnsmith@gmail.com', 'yankees1990', 'John Smith', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('johnsmith@gmail.com', 'John Smith',
'Independently wealthy investor based in San Jose, CA.',
'Pitt 2010, currently living in CA.',
1000, 100000, 'www.johnsmithinvesting.com', null);

INSERT INTO accounts
VALUES ('citizensbank@gmail.com', 'cbinvestor', 'Citizens Bank', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('citizensbank@gmail.com', 'Citizens Bank',
'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.',
1000, 100000, 'www.citizensbank.com', 'Bruce Van Saun');

# TEMPLATE FOR CONNECTIONS
INSERT INTO account_connections
VALUES ('googleHQ@gmail.com', 'johnsmith@gmail.com', 1, STR_TO_DATE('01,10,2020','%d,%m,%Y'));

# TEMPLATE FOR chatlog
INSERT INTO chat_log
VALUES (1, DATE_SUB(NOW(), interval 3 hour), 1, 'Hi, my name is John and I\'m interested in investing.');
INSERT INTO chat_log
VALUES (1, NOW(), 0, 'Hi John, we can do 1% for $10,000,000.');
