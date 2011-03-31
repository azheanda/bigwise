use BigWise
drop table QuoteData;
create table QuoteData(ID int,code varchar(10),name varchar(10), date varchar(10),time varchar(10),OpenPrice double,ClosePrice double,CurrentPrice double,MaxPrice double,MinPrice double,TradeAccout double, TotalTrade double, b1 double, bp1 double,b2 double, bp2 double,b3 double, bp3 double,b4 double, bp4 double,b5 double, bp5 double,s1 double, sp1 double,s2 double, sp2 double,s3 double, sp3 double,s4 double, sp4 double,s5 double, sp5 double, Color int,primary key(code,ID));

