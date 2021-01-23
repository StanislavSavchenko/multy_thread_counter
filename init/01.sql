
CREATE TABLE increment_thread_request (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
consumer INT,
producer  INT,
request_dt TIMESTAMP DEFAULT NOW()
);

CREATE TABLE set_counter_request (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
counter INT,
request_dt TIMESTAMP DEFAULT NOW()
);

