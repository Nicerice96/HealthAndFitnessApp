-- Sample data for Member table
INSERT INTO Member (username, "password", email, date_of_birth, "address") VALUES
    ('john_doe', 'password123', 'john@example.com', '1990-05-15', '123 Main St'),
    ('jane_smith', 'p@ssw0rd', 'jane@example.com', '1985-10-20', '456 Elm St');

-- Sample data for Billing table
INSERT INTO Billing (member_id, billing_date, amount) VALUES
    (1, '2024-04-01', 50.00),
    (2, '2024-04-01', 60.00);

-- Sample data for Personal_Training table
INSERT INTO Personal_Training (member_id, trainer_id, "start_date", end_date) VALUES
    (1, 1, '2024-04-01', '2024-04-30'),
    (2, 2, '2024-04-01', '2024-04-30');

-- Sample data for Trainer table
INSERT INTO Trainer ("name", specialization, start_availability, end_availability) VALUES
    ('Trainer 1', 'Weightlifting', '2024-01-01', '2024-12-31'),
    ('Trainer 2', 'Yoga', '2024-01-01', '2024-12-31');

-- Sample data for Class_Schedule table
INSERT INTO Class_Schedule (class_name, start_time, end_time) VALUES
    ('Yoga Class', '2024-04-10 09:00:00', '2024-04-10 10:00:00'),
    ('Zumba Class', '2024-04-11 18:00:00', '2024-04-11 19:00:00');

-- Sample data for Room_Booking table
INSERT INTO Room_Booking (room_number, "start_date", end_date) VALUES
    (1, '2024-04-10', '2024-04-10'),
    (2, '2024-04-11', '2024-04-11');

-- Sample data for Equipment_Maintenance table
INSERT INTO Equipment_Maintenance (maintenance_date, "description") VALUES
    ('2024-04-05', 'Treadmill maintenance'),
    ('2024-04-06', 'Elliptical machine calibration');

-- Sample data for Member_Fitness_Metric table
INSERT INTO Member_Fitness_Metric (member_id, "weight", height, bmi, body_fat_percentage, measurement_date) VALUES
    (1, 180.5, 175, 25.5, 18.5, '2024-04-01'),
    (2, 150.0, 165, 22.0, 20.0, '2024-04-01');
