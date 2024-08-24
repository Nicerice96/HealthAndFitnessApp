-- Sample data for Member table
INSERT INTO "member" (username, "password", email, date_of_birth, "address")
VALUES
    ('arun_karki', 'password123', 'karki@example.com', '2003-02-04', '123 Main St'),
    ('zarif_khan', 'abc123', 'khan@example.com', '2003-02-23', '456 Elm St');

-- Sample data for Trainer table
INSERT INTO trainer ("name", specialization, start_availability, end_availability)
VALUES
    ('Mike Johnson', 'Weightlifting', '2024-01-01', '2024-12-31'),
    ('Emily White', 'Yoga', '2024-01-01', '2024-12-31');

-- Sample data for Class_Schedule table
INSERT INTO class_schedule (class_name, start_time, end_time)
VALUES
    ('Yoga Class', '10:00:00', '11:00:00'),
    ('Weightlifting Class', '14:00:00', '15:00:00');

-- Sample data for Room_Booking table
INSERT INTO room_booking (room_number, "start_date", end_date)
VALUES
    (101, '2024-04-10', '2024-04-12'),
    (102, '2024-04-15', '2024-04-17');

-- Sample data for Equipment_Maintenance table
INSERT INTO equipment_maintenance (maintenance_date, "description")
VALUES
    ('2024-04-10', 'Treadmill maintenance'),
    ('2024-04-15', 'Elliptical machine repair');



