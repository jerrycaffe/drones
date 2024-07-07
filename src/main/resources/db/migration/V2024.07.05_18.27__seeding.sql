INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state, created_at)
VALUES('J234', 0, 20.60, 100,0, NOW()),
('J235', 1, 40.60, 80,1, NOW()),
('J235', 2, 90.60, 60,2, NOW()),
('J236', 3, 500.30, 20,3, NOW());

INSERT INTO medication(name, weight, code, image, drone_id, created_at)
VALUES('test1', 5.20, 'testing', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwqRVCWi62Zq8bY5m4L58DEGbwYK9zNuBUgw&s', 2, NOW()),
('test2', 5.00, 'tested', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwqRVCWi62Zq8bY5m4L58DEGbwYK9zNuBUgw&s', 2, NOW()),
('test3', 4.70, 'trying', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwqRVCWi62Zq8bY5m4L58DEGbwYK9zNuBUgw&s', 3, NOW())