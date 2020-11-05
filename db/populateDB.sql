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

INSERT INTO accounts
VALUES ('business@starship.xyz', 'robotsrule', 'Starship Technologies', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('business@starship.xyz', 'Starship Technologies',
'Launched in 2014 by Skype co-founders, Ahti Heinla and Janus Friis, Starship Technologies 
today operates in several cities across the world. Our business headquarters are in 
San Francisco with our main engineering office in Estonia. \n\n
Starship robots are advanced devices that can carry items within a 4-mile (6km) radius. 
Our delivery platform enables a new era of instant delivery that works around your schedule 
at much lower costs. \n\n
We believe our robots will revolutionise food and package deliveries, offering people 
convenient new services that improve everyday life. Our proven ability to harness technology 
combined with our experience providing services to millions of people make this a reality.\n\n
We surpassed 500,000 deliveries in August 2020.',
'Autonomous delivery robotics company founded in 2014 by Skype co-founders, Ahti Heinla and Janus Friis',
null, '11-30', 2014, 15000, 5, 'www.starship.xyz', 'Ahti Heinla', 'Software/Tech');

INSERT INTO accounts
VALUES ('piadaHQ@gmail.com', 'piadaAdmin1234', 'Piada Italian Street Food', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('piadaHQ@gmail.com', 'Piada Italian Street Food',
'Chris Doody, owner of the company, is a co-founder of the Bravo Brio Restaurant Group chain.
He sold his stake to investors in 2006 and launched the Piada concept around the Italian 
"wrap-like sandwich" known as a Piada or Piadina, a street food he sampled in northern Italy. \n\n
We have 22 locations in Ohio, two in Indiana, one in Kentucky, three in Minnesota, eight in Texas, and two in Pennsylvania. ',
'Piada Italian Street Food is a fast casual Italian cuisine restaurant chain with 38 locations in six states',
null, '11-30', 2017, 12000, 15, 'mypiada.com', 'Chris Doodyr', 'Food');

INSERT INTO accounts
VALUES ('appleUS@gmail.com', 'stevejobs11', 'Apple', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('appleUS@gmail.com', 'Apple',
'Apple was founded in April 1976 by American icons Steve Jobs and Steve Wozniak. \n
The company\'s hardware products include the iPhone smartphone, the iPad tablet computer, the Mac personal computer, 
the iPod portable media player, the Apple Watch smartwatch, the Apple TV digital media player, the AirPods wireless 
earbuds and the HomePod smart speaker. Apple\'s software includes macOS, iOS, iPadOS, watchOS, and tvOS operating systems, 
the iTunes media player, the Safari web browser, the Shazam music identifier and the iLife and iWork creativity and 
productivity suites, as well as professional applications like Final Cut Pro, Logic Pro, and Xcode. Its online services 
include the iTunes Store, the iOS App Store, Mac App Store, Apple Music, Apple TV+, iMessage, and iCloud. Other services 
include Apple Store, Genius Bar, AppleCare, Apple Pay, Apple Pay Cash, and Apple Card. \n
Apple is considered one of the Big 5 Tech companies.',
'Apple is an American multinational technology that designs, develops and sells consumer electronics, computer software, and online services.',
null, '200+', 1976, 1000000000, 1, 'www.apple.com', 'Tim Cook', 'Software/Tech');

INSERT INTO accounts
VALUES ('HelloGames@gmail.com', 'dontoverhypegames', 'Hello Games', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('HelloGames@gmail.com', 'Hello Games',
'Hello Games Ltd is a British video game company based in Guildford, England. The company was founded by Sean Murray, Grant Duncan, 
Ryan Doyle and David Ream in February 2008 and has developed the Joe Danger series, No Man\'s Sky, and The Last Campfire.',
'Hello Games is an indie video game studio best known for No Man\'s Sky (2017) and The Last Campfire (2020).',
null, '11-30', 2008, 50000, 5, 'hellogames.org', 'Sean Murray', 'Software/Tech');


INSERT INTO accounts
VALUES ('YachtClubGames@gmail.com', 'shovelknight1337', 'Yacht Club Games', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('YachtClubGames@gmail.com', 'Yacht Club Games',
'Yacht Club Games, LLC is an American independent video game development studio and publisher founded in 2011 by former WayForward 
Technologies director Sean Velasco. The company announced their first title, Shovel Knight, on March 14, 2013, and released it on 
June 26, 2014, after a successful Kickstarter campaign. In 2016, the company announced that it would start publishing games 
from other companies, and that their first published game would be Azure Striker Gunvolt: Striker Pack, a compilation containing 
Azure Striker Gunvolt and Azure Striker Gunvolt 2. The second will be Cyber Shadow, a game developed by Mechanical Head Studios.\n
Yacht Club Games currently has 17 employees.',
'Yacht Club Games is an indie games studio based in California. We are best known for Shovel Knight, released in 2013 after a successful 
Kickstarter campaign.',
null, '11-30', 2011, 10000, 10, 'yachtclubgames.com', 'Sean Velasco', 'Software/Tech');

INSERT INTO accounts
VALUES ('zynga@corporate', 'casualgamersLUL', 'Zynga', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('zynga@corporate', 'Zynga',
'Our mission statement is to "connect the world through games."\n
Zynga launched its best-known game, FarmVille, on Facebook in June 2009, reaching 10 million daily active users (DAU) within six weeks. 
As of August 2017, Zynga had 30 million monthly active users. In 2017 its most successful games were Zynga Poker and Words with Friends 
2, with about 57 million games being played at any given moment;',
'Zynga is an American social game developer focusing primarily on mobile and social networking platforms. We are best known for Farmville and Words With Friends',
null, '200+', 2007, 20000000, 1, 'www.zynga.com', 'Frank Gibeau', 'Software/Tech');

INSERT INTO accounts
VALUES ('Nicalis@aol.com', 'VVVVVV', 'Nicalis', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('Nicalis@aol.com', 'Nicalis',
'Nicalis, Inc. is an American video game developer and publisher based in Santa Ana, California. The company focuses primarily on indie games and 
has developed and published both original games as well as ports of existing games. Nicalis was founded in 2007 by Tyrone Rodriguez, a former editor for IGN. \n
In 2017, Nicalis announced that they had acquired SuperVillain Studios and Cowboy Color. \n
Additionally, we have co-published VVVVVV, Castle, and NightSky, among others',
'Nicalis is a California based indie video game publisher best known for Cave Story, Ikaruga, and The Binding of Isaac: Rebirth (and Afterbirth+)',
null, '31-50', 2007, 100000, 5, 'www.zynga.com', 'Tyrone Rodriguez', 'Software/Tech');

INSERT INTO accounts
VALUES ('ZachBarth@gmail.com', 'zachattack926', 'Zachtronics', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('ZachBarth@gmail.com', 'Zachtronics',
'Zachtronics was founded by Zach Barth, who serves as its lead designer.\n
Zach\'s initial games were generally free browser games offered on his website. One of these was Infiniminer, the block-building precursor game of Minecraft by Mojang.
We have also published SpaceChem, Infinifactory, TIS-100, Shenzehen I/O, Opus Magnum, and Exapunks.',
'Zachtronics LLC is an American independent video game development studio, best known for their engineering puzzle games and programming games.',
null, '1-10', 2000, 50000, 10, 'www.zachtronics.com', 'Zach Barth', 'Software/Tech');




# TEMPLATE FOR COMPANY PROJECTS

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
