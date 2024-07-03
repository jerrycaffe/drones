CREATE TABLE IF NOT EXISTS drones (
    serial_number INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    weight_limit DECIMAL(10, 2) NOT NULL,
    battery_capacity INT NOT NULL,
    state VARCHAR(30) NOT NULL
);


CREATE TABLE IF NOT EXISTS medication(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
weight DECIMAL(10, 2) NOT NULL,
code VARCHAR(100) NOT NULL,
image VARCHAR,
drone_serial_number INT NOT NULL,
CONSTRAINT fk_drones_medication_drone_serial_number FOREIGN KEY (drone_serial_number) REFERENCES drones(serial_number) ON DELETE CASCADE
)


