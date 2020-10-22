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
INSERT INTO company_project
VALUES ('googleHQ@gmail.com', 'Google Self Driving Car', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem.
Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim.
Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc,
blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt');

# TEMPLATE FOR INVESTOR ACCOUNTS

INSERT INTO accounts
VALUES ('johnsmith@gmail.com', 'yankees1990', 'John Smith', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('johnsmith@gmail.com', 'John Smith',
'Independently wealthy investor based in San Jose, CA.',
'Pitt 2010, currently living in CA.',
1000, 100000, 'www.johnsmithinvesting.com', null);

# TEMPLATE FOR CONNECTIONS
INSERT INTO account_connections
VALUES ('googleHQ@gmail.com', 'johnsmith@gmail.com', 1, STR_TO_DATE('01,10,2020','%d,%m,%Y'));

# TEMPLATE FOR chatlog
INSERT INTO chat_log
VALUES (1, DATE_SUB(NOW(), interval 3 hour), 1, 'Hi, my name is John and I\'m interested in investing.');
INSERT INTO chat_log
VALUES (1, NOW(), 0, 'Hi John, we can do 1% for $10,000,000.');
