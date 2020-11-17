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
'https://imgur.com/6ujCboB.png', '200+', 1998, 10000000, 1, 'www.google.com', 'Sundar Pichai', 'Software/Tech');

INSERT INTO accounts
VALUES ('uberOfficial@gmail.com', '12341234', 'Uber', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('uberOfficial@gmail.com', 'Uber',
'We are a company that uses smartphone apps to connect riders with drivers.',
'Tech + transportation company since 2009',
'https://imgur.com/3BP6P16.png', '200+', 2009, 1000000, 5, 'www.uber.com', 'Dara Khosrowshahi', 'Transportation');

INSERT INTO accounts
VALUES ('walmart@gmail.com', 'qwertyuiop', 'Walmart', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('walmart@gmail.com', 'Walmart',
'As of July 31, 2020, Walmart has 11,496 stores and clubs in 27 countries, operating under 56 different names. 
Walmart is the world\'s largest company by revenue, with $514.405 billion. 
It is also the largest private employer in the world with 2.2 million employees.',
'Walmart Inc. is an American multinational retail corporation.',
'https://imgur.com/uFCahQ5.png', '200+', 1962, 100000000, 1, 'www.walmart.com', 'Doug McMillon', 'Retail');

INSERT INTO accounts
VALUES ('amazonHQ@gmail.com', 'bezos420', 'Amazon', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('amazonHQ@gmail.com', 'Amazon',
'In 2017, Amazon acquired Whole Foods Market for $13.4 billion. 
In 2018, its two-day delivery service Amazon Prime surpassed 100 million subscribers worldwide. 
\nAmazon is known for its disruption of well-established industries through technological innovation and mass scale.',
'Amazon is an American multinational retail and technology company based in Seattle, Washington.',
'https://imgur.com/gU9GfFW.png', '200+', 1994, 100000000, 2, 'www.aboutamazon.com', 'Jeff Bezos', 'Retail');

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
'https://imgur.com/ftvDo0B.png', '200+', 1933, 10000, 5, 'www.primantibros.com', 'Michael Chu', 'Food');

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
'https://imgur.com/oWWIaTL.png', '11-30', 2017, 12000, 15, 'thirstyscholarpgh.com', 'Mike Chizmar', 'Food');

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
'https://imgur.com/qoMXBjW.png', '11-30', 2014, 15000, 5, 'www.starship.xyz', 'Ahti Heinla', 'Software/Tech');

INSERT INTO accounts
VALUES ('piadaHQ@gmail.com', 'piadaAdmin1234', 'Piada Italian Street Food', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('piadaHQ@gmail.com', 'Piada Italian Street Food',
'Chris Doody, owner of the company, is a co-founder of the Bravo Brio Restaurant Group chain.
He sold his stake to investors in 2006 and launched the Piada concept around the Italian 
"wrap-like sandwich" known as a Piada or Piadina, a street food he sampled in northern Italy. \n\n
We have 22 locations in Ohio, two in Indiana, one in Kentucky, three in Minnesota, eight in Texas, and two in Pennsylvania. ',
'Piada Italian Street Food is a fast casual Italian cuisine restaurant chain with 38 locations in six states',
'https://imgur.com/uSLfQOD.png', '11-30', 2017, 12000, 15, 'mypiada.com', 'Chris Doodyr', 'Food');

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
'https://imgur.com/dKBTaMF.png', '200+', 1976, 1000000000, 1, 'www.apple.com', 'Tim Cook', 'Software/Tech');

INSERT INTO accounts
VALUES ('HelloGames@gmail.com', 'dontoverhypegames', 'Hello Games', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('HelloGames@gmail.com', 'Hello Games',
'Hello Games Ltd is a British video game company based in Guildford, England. The company was founded by Sean Murray, Grant Duncan, 
Ryan Doyle and David Ream in February 2008 and has developed the Joe Danger series, No Man\'s Sky, and The Last Campfire.',
'Hello Games is an indie video game studio best known for No Man\'s Sky (2017) and The Last Campfire (2020).',
'https://imgur.com/Ny3BKsx.png', '11-30', 2008, 50000, 5, 'hellogames.org', 'Sean Murray', 'Software/Tech');


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
'https://imgur.com/IEGDOL1.png', '11-30', 2011, 10000, 10, 'yachtclubgames.com', 'Sean Velasco', 'Software/Tech');

INSERT INTO accounts
VALUES ('zynga@corporate', 'casualgamersLUL', 'Zynga', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('zynga@corporate', 'Zynga',
'Our mission statement is to "connect the world through games."\n
Zynga launched its best-known game, FarmVille, on Facebook in June 2009, reaching 10 million daily active users (DAU) within six weeks. 
As of August 2017, Zynga had 30 million monthly active users. In 2017 its most successful games were Zynga Poker and Words with Friends 
2, with about 57 million games being played at any given moment;',
'Zynga is an American social game developer focusing primarily on mobile and social networking platforms. We are best known for Farmville and Words With Friends',
'https://imgur.com/RggBpP6.png', '200+', 2007, 20000000, 1, 'www.zynga.com', 'Frank Gibeau', 'Software/Tech');

INSERT INTO accounts
VALUES ('Nicalis@aol.com', 'VVVVVV', 'Nicalis', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('Nicalis@aol.com', 'Nicalis',
'Nicalis, Inc. is an American video game developer and publisher based in Santa Ana, California. The company focuses primarily on indie games and 
has developed and published both original games as well as ports of existing games. Nicalis was founded in 2007 by Tyrone Rodriguez, a former editor for IGN. \n
In 2017, Nicalis announced that they had acquired SuperVillain Studios and Cowboy Color. \n
Additionally, we have co-published VVVVVV, Castle, and NightSky, among others',
'Nicalis is a California based indie video game publisher best known for Cave Story, Ikaruga, and The Binding of Isaac: Rebirth (and Afterbirth+)',
'https://imgur.com/C2P3Qeq.jpg', '31-50', 2007, 100000, 5, 'www.zynga.com', 'Tyrone Rodriguez', 'Software/Tech');

INSERT INTO accounts
VALUES ('ZachBarth@gmail.com', 'zachattack926', 'Zachtronics', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('ZachBarth@gmail.com', 'Zachtronics',
'Zachtronics was founded by Zach Barth, who serves as its lead designer.\n
Zach\'s initial games were generally free browser games offered on his website. One of these was Infiniminer, the block-building precursor game of Minecraft by Mojang.
We have also published SpaceChem, Infinifactory, TIS-100, Shenzehen I/O, Opus Magnum, and Exapunks.',
'Zachtronics LLC is an American independent video game development studio, best known for their engineering puzzle games and programming games.',
'https://imgur.com/vj8PqCx.png', '1-10', 2000, 50000, 10, 'www.zachtronics.com', 'Zach Barth', 'Software/Tech');

INSERT INTO accounts
VALUES ('DevolverDigitalOfficial@gmail.com', 'DodgeRoll!', 'Devolver Digital', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('DevolverDigitalOfficial@gmail.com', 'Devolver Digital',
'The company was founded in June 2009 by Mike Wilson, Harry Miller and Rick Stults, alongside business partners Nigel Lowrie and Graeme Struthers. 
The three founders had previously operated Gathering of Developers and Gamecock Media Group, which published games on developer-friendly terms, 
but due to the high cost associated with releasing retail games saw themselves acquired and dissolved by larger companies. To avoid this, Devolver 
Digital instead turned to digital distribution channels.\n\n
Devolver Digital started by publishing high-definition remakes of games in the Serious Sam series of games. After success with these remakes and 
spin-off games based on the series, Devolver Digital began publishing games from other, smaller independent studios, one of the first being their 
breakout title, Hotline Miami. As of January 2020, Devolver Digital employs 20 people. ',
'Devolver Digital Inc. is an American video game publisher based in Austin, Texas, specializing in the publishing of indie games such as Enter the Gungeon.',
'https://imgur.com/322VdHu.png', '11-30', 2009, 50000, 4, 'devolverdigital.com', 'Fork Parker', 'Software/Tech');

INSERT INTO accounts
VALUES ('thatgamecompany@contact.us', 'staycalmandrelax', 'thatgamecompany', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('thatgamecompany@contact.us', 'thatgamecompany',
'thatgamecompany is an American independent video game development company founded by University of Southern California 
students Jenova Chen and Kellee Santiago in 2006.\n
We have released  Flow (2007), Flower (2009), Journey (2012), and Sky: Children of the Light(2019). Journey is commonly regarded as a masterpiece of emotional, atmospheric storytelling.\n
Austin Wintory was nominated for a Grammy Award in 2013 for Best Score Soundtrack for Journey, the first such nomination for a full video game score',
'thatgamecompany is the indie video game company behind Journey and Sky: Children of the Light.',
'https://imgur.com/Hig1xkp.png', '11-30', 2006, 30000, 5, 'thatgamecompany.com', 'Jenova Chen', 'Software/Tech');

INSERT INTO accounts
VALUES ('IntelBusiness@gmail.com', 'amdsucks', 'Intel', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('IntelBusiness@gmail.com', 'Intel',
'Intel supplies microprocessors for computer system manufacturers such as Apple, Lenovo, HP, and Dell. Intel also manufactures motherboard chipsets, network interface 
controllers and integrated circuits, flash memory, graphics chips, embedded processors and other devices related to communications and computing.\n
Intel Corporation was founded on July 18, 1968 by semiconductor pioneers Robert Noyce and Gordon Moore (of Moore\'s law), and is associated with the executive leadership 
and vision of Andrew Grove. The company\'s name was conceived as portmanteau of the words integrated and electronics, with co-founder Noyce having been a key inventor 
of the integrated circuit (the microchip).',
'Intel is the world\'s highest valued semiconductor chip manufacturer and developed the x86 series of microprocessors.',
'https://imgur.com/MP98tiu.png', '200+', 2006, 30000000, 6, '	intel.com', 'Bob Swan', 'Software/Tech');

INSERT INTO accounts
VALUES ('NvidiaBusiness@gmail.com', 'GeForceRTX3070', 'Nvidia', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('NvidiaBusiness@gmail.com', 'Nvidia',
'On May 6, 2016, Nvidia unveiled the first GPUs of the GeForce 10 series, the GTX 1080 and 1070.\n
In addition to GPU manufacturing, Nvidia provides parallel processing capabilities to researchers and scientists that allow them to efficiently run high-performance 
applications. They are deployed in supercomputing sites around the world.\n\n
In May 2020, Nvidia\'s top scientists developed an open-source ventilator in order to address the shortage resulting from the global coronavirus pandemic.\n
Second website: developer.nvidia.com',
'Nvidia designs GPUs for gaming and professional markets.',
'https://imgur.com/Gs4Hj9L.png', '200+', 2006, 30000000, 6, 'nvidia.com', 'Jensen Huang', 'Software/Tech');

INSERT INTO accounts
VALUES ('tesla@contact.us', 'ElonTusk420', 'Tesla', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('tesla@contact.us', 'Tesla',
'Founded in July 2003 by engineers Martin Eberhard and Marc Tarpenning as Tesla Motors, the company’s name is a tribute to inventor and electrical engineer Nikola Tesla. 
Eberhard said he wanted to build "a car manufacturer that is also a technology company", with its core technologies as "the battery, the computer software, and the proprietary motor".\n
Elon Musk, who has served as CEO since 2008, said in 2006 that "the overarching purpose of Tesla Motors...is to help expedite the move from a mine-and-burn hydrocarbon economy 
towards a solar electric economy" and it would build a wide range of electric vehicles, including "affordably priced family cars", and co-market SolarCity solar panels to do so. 
Tesla acquired SolarCity in 2016',
'Tesla (formerly Tesla Motors) is an American electric vehicle and clean energy company based in Palo Alto, California.',
'https://imgur.com/zaeIwWi.png', '200+', 2003, 300000000, 1, 'www.tesla.com', 'Elon Musk', 'Automotive');

INSERT INTO accounts
VALUES ('seatgeek@contact.us', 'qwertyuiop2009', 'SeatGeek', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('seatgeek@contact.us', 'SeatGeek',
'SeatGeek allows both mobile app and desktop users to browse events, view interactive color-coded seatmaps, complete purchases, and receive electronic or print tickets.
Tickets are sorted using the company\'s DealScore algorithm which finds the combination of best available price and seat location for a particular event.\n
SeatGeek was founded by Russell D\'Souza and Jack Groetzinger at DreamIT Ventures, an early stage startup accelerator program in Philadelphia and launched in September 
2009 at TechCrunch50 where it was named by VentureBeat and CNET as one of the top 5 companies from the conference.[1] In May, the company had received $20k in seed 
funding from DreamIT Ventures.\n
On April 3, 2018, the Dallas Cowboys announced an agreement with SeatGeek to serve as the team\'s primary ticket office, replacing Ticketmaster. Darren Rovell reported 
that the Cowboys received a 15% stake in the company.',
'SeatGeek is a mobile-focused ticket platform that enables users to buy and sell tickets for live sports, concerts and theater events.',
'https://imgur.com/OBlhttY.png', '200+', 2009, 1000000, 5, 'seatgeek.com', 'Clinton Smith', 'Software/Tech');

INSERT INTO accounts
VALUES ('duolingo@gmail.com', 'einszweidrei', 'Duolingo', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('duolingo@gmail.com', 'Duolingo',
'The project was initiated at the end of 2009 in Pittsburgh by Carnegie Mellon University professor Luis von Ahn (creator of reCAPTCHA) and his graduate student Severin Hacker. 
In 2013, Apple chose Duolingo as its iPhone App of the Year, the first time this honor had been awarded to an educational application.\n
Duolingo has over 300 million registered users.',
'Duolingo is Pittsburgh based company that focuses on language-learning through website and mobile apps.',
'https://imgur.com/GllRiQG.png', '200+', 2009, 1000000, 6, 'www.duolingo.com', 'Luis von Ahn', 'Education');

INSERT INTO accounts
VALUES ('instructure@gmail.com', 'canvas1234', 'Instructure', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('instructure@gmail.com', 'Instructure',
'Canvas is a Web-based learning management system, and Canvas Network, a massive open online course (MOOC) platform. Instructure was founded in 2008 by two BYU graduate 
students, Brian Whitmer and Devlin Daley, with initial funding from Mozy founder Josh Coates (currently the executive chairman of the Board) and Epic Ventures.\n
In August 2020, 13 states across the United States confirmed a partnership with Instructure in order to adopt its Canvas LMS platform across the states’ educational institutions.',
'Instructure is and educational technology company based in Utah. We are the publisher of Canvas.',
'https://imgur.com/ZCIgQbA.png', '200+', 2009, 750000, 2, 'www.instructure.com', 'Dan Goldsmith', 'Software/Tech');

INSERT INTO accounts
VALUES ('info@surveyauto.com', 'surveysurveysurvey', 'SurveyAuto', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('info@surveyauto.com', 'SurveyAuto',
'Our machine learning platform utilizes Satellite Imagery and Hyperspectral imagery to identify houses, buildings, farm lands, water bodies and road structures. 
Combining this data with publicly available maps, we are able to zoom-in to exact survey targets without the need for an expensive on-ground micro-census or a pre-survey. \n\n
We are currently backed by the Bill and Melinda Gates Foundation',
'SurveyAuto is an online survey platform to democratize data using machine learning algorithms to analyze the quality of results.',
'https://imgur.com/jZqJG3w.png', '11-30', 2018, 125000, 15, 'surveyauto.com', 'Umar Saif', 'Software/Tech');

INSERT INTO accounts
VALUES ('team@snackpass.co', 'snacksNsavings', 'Snackpass', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('team@snackpass.co',  'Snackpass',
'Why wait in line when you can simply order your food on your phone and pick your order when you get to the restaurant? It is even better than the ‘drive-in’ 
concept because you don’t have to wait for even a minute.\n
Before Snackpass, the restaurant owners were worried about the customers not collecting their orders. With Snackpass, they don’t have to fear anything. 
As soon as a customer places an order, the app automatically deducts money from his wallet. Simple, right?\n
Snackpass is now used in multiple universities across the US and is backed by some of the bigshots of the tech industry.',
'Snackpass is the best new way for customers to quickly order takeout meals at a discount.',
'https://imgur.com/pCwvDvH.png', '11-30', 2019, 115000, 10, 'www.snackpass.co', 'Kevin Tan', 'Software/Tech');

INSERT INTO accounts
VALUES ('Sinochem@gmail.com', 'chemicalCN', 'Sinochem', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('Sinochem@gmail.com', 'Sinochem',
'Sinochem Corporation is a Chinese state-owned multinational conglomerate primarily engaged in the production and trading of chemicals and fertilizer 
and exploration and production of oil for civilian and military purposes. Its majority owned fertilizer subsidiary Sinofert is involved throughout 
the chain from production of the product and procurement on international markets to distribution and retail.\n
Sinochem Group is China\'s earliest entrant in Fortune Global 500 and has entered the list for 25 times, ranking 139th in 2016',
'Sinochem is one of the world\'s largest chemical and fertilizer producer.',
'https://imgur.com/bI9MHzk.jpg', '200+', 1950, 100000000, 3, 'english.sinochem.com', 'Ning Gaoning', 'Industrial');

INSERT INTO accounts
VALUES ('UPMCpittsburgh@gmail.com', 'pittMedicine123', 'UPMC', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('UPMCpittsburgh@gmail.com', 'UPMC',
'It is considered a leading American health care provider, as its flagship facilities have ranked in U.S. News & World Report "Honor Roll" of the approximately 
15 to 20 best hospitals in America for over 15 years. As of 2016, flagship hospital, UPMC Presbyterian is ranked 12th nationally among the best hospitals 
(and first in Pennsylvania) by U.S. News & World Report and ranked in 15 of 16 specialty areas when including UPMC Magee-Womens Hospital.',
'The University of Pittsburgh Medical Center (UPMC) is a global nonprofit health enterprise.',
'https://imgur.com/undefined.png', '200+', 1901, 100000000, 8, 'upmc.com', 'Jeffrey Romoff', 'Health');

INSERT INTO accounts
VALUES ('BASFgermany@gmail.com', 'chemicalDE', 'BASF', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('BASFgermany@gmail.com', 'BASF',
'The BASF Group comprises subsidiaries and joint ventures in more than 80 countries and operates six integrated production sites and 390 other production 
sites in Europe, Asia, Australia, the Americas and Africa. Its headquarters is located in Ludwigshafen, Germany. BASF has customers in over 190 countries 
and supplies products to a wide variety of industries.',
'BASF SE is a German multinational chemical company and the largest chemical producer in the world.',
'https://imgur.com/yWdZcUE.png', '200+', 1901, 20000000, 1, 'basf.com', 'JMartin Brudermüller', 'Industrial');

INSERT INTO accounts
VALUES ('nbagiatisi@reedsmith.com', 'chemicalDE', 'Reed Smith LLP', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('nbagiatisi@reedsmith.com', 'Reed Smith LLP',
'Reed Smith LLP is a global law firm headquartered in Pittsburgh, Pennsylvania, with more than 1,500 lawyers in 30 offices throughout the United States, 
Europe, the Middle East and Asia.\n
Reed Smith was founded in Pittsburgh in 1877 by Philander C. Knox and James H. Reed.',
'Reed Smith LLP is a global law firm headquartered in Pittsburgh, Pennsylvania.',
'https://imgur.com/CR1ppzl.png', '200+', 1901, 20000000, 1, 'www.reedsmith.com', 'Nick Bagiatis', 'Law');

INSERT INTO accounts
VALUES ('marriottHQ@gmail.com', 'hotels123', 'Marriott', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('marriottHQ@gmail.com', 'Marriott',
'Founded by J. Willard Marriott, the company is now led by his son, executive chairman Bill Marriott, and president and chief executive officer Arne Sorenson. 
Marriott International is the largest hotel chain in the world by number of available rooms.',
'Marriott is an American hotel chain.',
'https://imgur.com/SyYWMqe.png', '200+', 1927, 100000000, 2, 'marriott.com', 'Arne Sorenson', 'Hotel');

INSERT INTO accounts
VALUES ('WWF@gmail.com', 'savethepandas', 'World Wildlife Fund', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('WWF@gmail.com', 'World Wildlife Fund',
'WWF is the world\'s largest conservation organization, with over five million supporters worldwide, working in more than 100 countries 
and supporting around 3,000 conservation and environmental projects.',
'The World Wide Fund for Nature (WWF) is an international non-governmental organization founded in 1961.',
'https://imgur.com/QvOYZry.png', '200+', 1927, 5000000, 7, 'worldwildlife.org', 'Pavan Sukhdev', 'Environment');

INSERT INTO accounts
VALUES ('marvel@gmail.com', 'spooderman1234', 'Marvel', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('marvel@gmail.com', 'Marvel',
'In 2009, The Walt Disney Company acquired Marvel Entertainment for US$4 billion; it has been a limited liability company (LLC) since then. 
For financial reporting purposes, Marvel is primarily reported as part of Disney\'s Consumer Products segment ever since Marvel Studios\' reorganization 
from Marvel Entertainment into Walt Disney Studios.',
'Marvel Entertainment is an American entertainment company founded in June 1998 and based in New York City.',
'https://imgur.com/wWU9pN4.png', '200+', 1998, 5000000, 9, 'www.marvel.com', 'Isaac Perlmutter', 'Entertainment');

INSERT INTO accounts
VALUES ('sonypictures@gmail.com', 'spiderman1234', 'Sony Pictures', 'Business', 1, null);
INSERT INTO business_accounts
VALUES ('sonypictures@gmail.com', 'Sony Pictures',
'Based at the former Metro-Goldwyn-Mayer lot in Culver City, California, it encompasses Sony\'s motion picture, television production and distribution units. 
Its group sales in the fiscal year 2017 (April 2017 – March 2018) has been reported to be $9.133 billion.',
'Sony Pictures is an American entertainment company that produces, acquires, and distributes filmed entertainment.',
'https://imgur.com/U234gtm.jpg', '200+', 1987, 50000000, 3, 'www.sonypictures.com', 'Tony Vinciquerra', 'Entertainment');


# TEMPLATE FOR INVESTOR ACCOUNTS

INSERT INTO accounts
VALUES ('johnsmith@gmail.com', 'yankees1990', 'John Smith', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('johnsmith@gmail.com', 'John Smith',
'Independently wealthy investor based in San Jose, CA.',
'Pitt 2010, currently living in CA.',
'https://imgur.com/Zy90G8k.jpg', 1000, 100000, 'www.johnsmithinvesting.com', null);

INSERT INTO accounts
VALUES ('citizensbank@gmail.com', 'cbinvestor', 'Citizens Bank', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('citizensbank@gmail.com', 'Citizens Bank',
'Citizens Bank operates more than 1,200 branches and approximately 3,200 ATMs across 11 states under the Citizens Bank brand.\n
Citizens Bank ranks 24th on the List of largest banks in the United States. ',
'Citizens Bank is an American bank headquartered in Rhode Island, which operates in the east coast of the US',
'https://imgur.com/RXe0DLX.png', 10000, 250000000, 'www.citizensbank.com', 'Bruce Van Saun');

INSERT INTO accounts
VALUES ('adamkkarl@gmail.com', 'mrrobot', 'Adam Karl', 'Investor', 1, null);
INSERT INTO investor_accounts
VALUES ('adamkkarl@gmail.com', 'Adam Karl',
'Pitt CS major from Lancaster, PA. Graduated from Hempfield High School.',
'Pitt 2021, CS major living in South Oakland.',
'https://imgur.com/hgziZlf.png', 1000, 10000, 'github.com/adamkkarl', null);

# TEMPLATE FOR CONNECTIONS
INSERT INTO account_connections
VALUES ('googleHQ@gmail.com', 'johnsmith@gmail.com', null, STR_TO_DATE('01,10,2020','%d,%m,%Y'), null, null);

INSERT INTO account_connections
VALUES ('YachtClubGames@gmail.com', 'citizensbank@gmail.com', null, STR_TO_DATE('11,5,2020','%d,%m,%Y'), null, null);

INSERT INTO account_connections
VALUES ('Sinochem@gmail.com', 'johnsmith@gmail.com', null, STR_TO_DATE('01,10,2020','%d,%m,%Y'), null, null);

# TEMPLATE FOR chatlog
INSERT INTO chat_log
VALUES (1, DATE_SUB(NOW(), interval 3 hour), 1, 'Hi, my name is John and I\'m interested in investing.');
INSERT INTO chat_log
VALUES (1, NOW(), 0, 'Hi John, we can do 1% for $10,000,000.');

INSERT INTO chat_log
VALUES (2, DATE_SUB(NOW(), interval 3 hour), 1, 'Hi, we are Citizen\'s Bank and we are interested in investing.');
INSERT INTO chat_log
VALUES (2, NOW(), 0, 'Hi Citize\'s, how much?');

INSERT INTO chat_log
VALUES (3, DATE_SUB(NOW(), interval 3 hour), 1, 'Hi, my name is John and I\'m interested in investing in your company Sinochem.');
INSERT INTO chat_log
VALUES (3, DATE_SUB(NOW(), interval 1 hour), 0, 'Hi John, what are you thinking?');
INSERT INTO chat_log
VALUES (3, NOW(), 1, 'Hold on one second.');
