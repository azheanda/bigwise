use BigWise
drop table HistoryData;
create table HistoryData(code varchar(10),StockDate varchar(10),OpenPrice double,ClosePrice double,MaxPrice double,MinPrice double,TradeAccount double,totalTrade double,primary key(code,StockDate));