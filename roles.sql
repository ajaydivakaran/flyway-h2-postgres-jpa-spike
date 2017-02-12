create user john NOINHERIT password 'john';
grant SELECT, UPDATE, INSERT ON customer, book to john;