CREATE TABLE IF NOT EXISTS drones(
 serial_number INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 model NOT NULL VARCHAR,
 weight_limit DECIMAL NOT NULL,
 battery_capacity INT NOT NULL,
 state NOT NULL VARCHAR
)

CREATE TABLE IF NOT EXISTS medication(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name NOT NULL VARCHAR,
weight NOT NULL DECIMAL,
code VARCHAR NOT NULL,
image VARCHAR,
drone_serial_number INT NOT NULL,
CONSTRAINT fk_drones_medication_drone_serial_number FOREIGN KEY (drone_serial_number) REFERENCES drones(serial_number) ON DELETE CASCADE
)

