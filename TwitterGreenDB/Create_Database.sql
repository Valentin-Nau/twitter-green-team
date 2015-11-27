CREATE DATABASE TwitterGreenDB;
GRANT USAGE ON *.* TO admin@localhost IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON TwitterGreenDB.* TO admin@localhost;
FLUSH PRIVILEGES;