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
'Google is an American multinational technology company that specializes in Internet-related services and products, which include online advertising technologies, a search engine, and cloud computing.',
null, '200+', 1998, 10000000, 1, 'www.google.com', 'Sundar Pichai', 'Software/Tech');

INSERT INTO accounts
VALUES ('uberOfficial@gmail.com', '12341234', 'Uber', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('uberOfficial@gmail.com', 'Uber',
'We are a company that uses smartphone apps to connect riders with drivers.',
'Tech + transportation company since 2009',
null, '200+', 2009, 1000000, 5, 'www.uber.com', 'Dara Khosrowshahi', 'Transportation');

INSERT INTO accounts
VALUES ('walmart@gmail.com', 'qwertyuiop', 'Walmart', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('walmart@gmail.com', 'Walmart',
'As of July 31, 2020, Walmart has 11,496 stores and clubs in 27 countries, operating under 56 different names. 
Walmart is the world\'s largest company by revenue, with $514.405 billion. 
It is also the largest private employer in the world with 2.2 million employees.',
'Walmart Inc. is an American multinational retail corporation.',
null, '200+', 1962, 100000000, 1, 'www.walmart.com', 'Doug McMillon', 'Retail');

INSERT INTO accounts
VALUES ('amazonHQ@gmail.com', 'bezos420', 'Amazon', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('amazonHQ@gmail.com', 'Amazon',
'In 2017, Amazon acquired Whole Foods Market for $13.4 billion. 
In 2018, its two-day delivery service Amazon Prime surpassed 100 million subscribers worldwide. 
\nAmazon is known for its disruption of well-established industries through technological innovation and mass scale.',
'Amazon is an American multinational retail and technology company based in Seattle, Washington.',
null, '200+', 1994, 100000000, 2, 'www.aboutamazon.com', 'Jeff Bezos', 'Retail');

INSERT INTO accounts
VALUES ('PrimantiBrosPA@gmail.com', 'bezos420', 'Primanti Brothers', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('PrimantiBrosPA@gmail.com', 'Primanti Brothers',
'Founded in 1933 by Joe Primanti, Primanti Brothers (also known as Primanti Bros.) is most well known 
as a cultural icon of Pittsburgh, Pennsylvania. During the 2007 James Beard Foundation Awards, 
Primanti\'s was named as one of "America\'s Classic" restaurants. \n\n
The chain is known for its signature sandwiches of grilled meat, melted cheese, an oil & vinegar-based coleslaw, 
tomato slices, and French fries between two thick slices of Italian bread.',
'Primanti Brother is a chain of sandwich shops in the eastern United States.',
null, '200+', 1933, 10000, 5, 'www.primantibros.com', 'Michael Chu', 'Food');

INSERT INTO accounts
VALUES ('admin@thirstyscholarpgh.com', 'pitt>psu', 'Thirsty Scholar', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('admin@thirstyscholarpgh.com', 'Thirsty Scholar',
'Having opened in June 2017 in the former location of the Pitt Campus Bookstore, 
Thirsty Scholar provides an enjoyable atmosphere – perfect for unwinding after work, going out with friends, 
or simply grabbing that burger and wings you’ve been craving. \n\n
Thirsty Scholar has incredible, mouth-watering food options,  with ‘Daily Grille Specials’ for only $6 – 
featuring tacos, wings, quesadillas and appetizer specials on certain days of the week.',
'Local pub within walking distance of Pitt\'s Cathedral of Learning.',
null, '11-30', 2017, 12000, 15, 'thirstyscholarpgh.com', 'Mike Chizmar', 'Food');



# TODO TEMPLATE FOR COMPANY PROJECTS
INSERT INTO company_project
VALUES ('googleHQ@gmail.com', 'Wayme - The Google Self Driving Car', 'Waymo began as the Google Self-Driving Car Project in 2009. 
Fully self-driving vehicles hold the promise to improve road safety and offer new mobility options to millions of people. 
Whether they’re helping people run errands, commute to work, or drop off kids at school, fully self-driving vehicles hold enormous potential to transform people’s lives.');



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
