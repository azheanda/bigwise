use BigWise
drop table QuoteData;
create table QuoteData(ID int,code varchar(10),name varchar(10), date varchar(10),time varchar(10),OpenPrice double,ClosePrice double,CurrentPrice double,MaxPrice double,MinPrice double,TradeAccout double, TotalTrade double, Color int,primary key(code,ID));

drop table HistoryData;
create table HistoryData(code varchar(10) not null primary key,StockDate varchar(10),OpenPrice double,ClosePrice double,MaxPrice double,HighPriceToday double,MinPrice double);