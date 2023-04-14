insert into users values (default, CURRENT_TIMESTAMP(), 'Jack', 'Red');
insert into users values (default, CURRENT_TIMESTAMP(), 'Bill', 'Black');
insert into users values (default, CURRENT_TIMESTAMP(), 'Molly', 'Green');

insert into accounts values (default, CURRENT_TIMESTAMP(), 1200, 'Mono-bank', 1);
insert into accounts values (default, CURRENT_TIMESTAMP(), 2700, 'Private-bank', 1);
insert into accounts values (default, CURRENT_TIMESTAMP(), 3330, 'Binance', 1);
insert into accounts values (default, CURRENT_TIMESTAMP(), 3500, 'Mono-bank', 2);
insert into accounts values (default, CURRENT_TIMESTAMP(), 4200, 'Private-bank', 2);
insert into accounts values (default, CURRENT_TIMESTAMP(), 5400, 'Mono-bank', 3);

insert into transactions values (default, CURRENT_TIMESTAMP(), 100, 'Noname transaction', 'EXPENSE', 1);
insert into transactions values (default, CURRENT_TIMESTAMP(), 200, 'Noname transaction', 'PROFIT', 1);
insert into transactions values (default, CURRENT_TIMESTAMP(), 300, 'Noname transaction', 'EXPENSE', 2);
insert into transactions values (default, CURRENT_TIMESTAMP(), 400, 'Noname transaction', 'EXPENSE', 3);
insert into transactions values (default, CURRENT_TIMESTAMP(), 500, 'Noname transaction', 'PROFIT', 3);
insert into transactions values (default, CURRENT_TIMESTAMP(), 600, 'Noname transaction', 'EXPENSE', 4);
insert into transactions values (default, CURRENT_TIMESTAMP(), 700, 'Noname transaction', 'EXPENSE', 4);
insert into transactions values (default, CURRENT_TIMESTAMP(), 800, 'Noname transaction', 'PROFIT', 5);
insert into transactions values (default, CURRENT_TIMESTAMP(), 900, 'Noname transaction', 'EXPENSE', 5);
insert into transactions values (default, CURRENT_TIMESTAMP(), 900, 'Noname transaction', 'PROFIT', 6);
insert into transactions values (default, CURRENT_TIMESTAMP(), 900, 'Noname transaction', 'PROFIT', 6);