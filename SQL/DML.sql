-- Sample data for Member table
INSERT INTO "Member" (username, "password", email, date_of_birth, "address")
VALUES
    ('arun_karki', 'password123', 'karki@example.com', '2003-02-04', '123 Main St'),
    ('zarif_khan', 'abc123', 'khan@example.com', '2003-02-23', '456 Elm St');

-- Sample data for Trainer table
INSERT INTO Trainer ("name", specialization, start_availability, end_availability)
VALUES
    ('Mike Johnson', 'Weightlifting', '2024-01-01', '2024-12-31'),
    ('Emily White', 'Yoga', '2024-01-01', '2024-12-31');

-- Sample data for Personal_Training table
INSERT INTO Personal_Training (member_id, trainer_id, "start_date", end_date)
VALUES
    (1, 1, '2024-04-01', '2024-04-30'),
    (2, 2, '2024-04-05', '2024-04-25');

-- Sample data for Billing table
INSERT INTO Billing (member_id, billing_date, amount)
VALUES
    (1, '2024-04-10', 50.00),
    (2, '2024-04-15', 75.00);

-- Sample data for Class_Schedule table
INSERT INTO Class_Schedule (class_name, start_time, end_time)
VALUES
    ('Yoga Class', '10:00:00', '11:00:00'),
    ('Weightlifting Class', '14:00:00', '15:00:00');

-- Sample data for Room_Booking table
INSERT INTO Room_Booking (room_number, "start_date", end_date)
VALUES
    (101, '2024-04-10', '2024-04-12'),
    (102, '2024-04-15', '2024-04-17');

-- Sample data for Equipment_Maintenance table
INSERT INTO Equipment_Maintenance (maintenance_date, "description")
VALUES
    ('2024-04-10', 'Treadmill maintenance'),
    ('2024-04-15', 'Elliptical machine repair');

-- Sample data for Member_Fitness_Metric table
INSERT INTO Member_Fitness_Metric (member_id, "weight", height, bmi, body_fat_percentage, measurement_date)
VALUES
    (1, 180.5, 72, 24.5, 18.5, '2024-04-10'),
    (2, 150.0, 65, 22.3, 22.0, '2024-04-15');
