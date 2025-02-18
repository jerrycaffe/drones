CREATE TABLE IF NOT EXISTS drone (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    serial_number VARCHAR(100) NOT NULL,
    model VARCHAR(50) NOT NULL,
    weight_limit DECIMAL(10, 2) NOT NULL,
    battery_capacity INT NOT NULL,
    state VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()

);


CREATE TABLE IF NOT EXISTS medication(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
weight DECIMAL(10, 2) NOT NULL,
code VARCHAR(100) NOT NULL,
image VARCHAR,
drone_id INT NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT NOW(),
CONSTRAINT fk_drones_medications_drone_id FOREIGN KEY (drone_id) REFERENCES drone(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS audit_log(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
battery_capacity INT NOT NULL,
drone_id INT NOT NULL,
timestamp TIMESTAMP NOT NULL DEFAULT NOW()
)

