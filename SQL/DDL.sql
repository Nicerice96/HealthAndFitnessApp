-- Member table
CREATE TABLE "member" (
    member_id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255),
    "password" VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    date_of_birth DATE,
    "address" VARCHAR(255)
);

-- Trainer table
CREATE TABLE Trainer (
    trainer_id SERIAL PRIMARY KEY NOT NULL,
    "name" VARCHAR(255),
    specialization VARCHAR(255),
    start_availability DATE,
    end_availability DATE
);

-- Personal Trainer table
CREATE TABLE Personal_Training (
    training_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    trainer_id INT REFERENCES Trainer(trainer_id),
    "start_date" DATE,
    end_date DATE
);

-- Billing table
CREATE TABLE Billing (
    billing_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    billing_date DATE,
    amount FLOAT
);

-- Class Schedule table
CREATE TABLE Class_Schedule (
    class_id SERIAL PRIMARY KEY NOT NULL,
    class_name VARCHAR(255),
    start_time TIME,
    end_time TIME
);

-- Room Booking table
CREATE TABLE Room_Booking (
    booking_no SERIAL PRIMARY KEY NOT NULL,
    room_number INT,
    "start_date" DATE,
    end_date DATE
);

-- Equipment Maintenance table
CREATE TABLE Equipment_Maintenance (
    maintenance_id SERIAL PRIMARY KEY NOT NULL,
    maintenance_date DATE,
    "description" VARCHAR(255)
);

-- Member Fitness Metric table
CREATE TABLE Member_Fitness_Metric (
    fitness_metric_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    "weight" FLOAT,
    height FLOAT,
    bmi FLOAT,
    body_fat_percentage FLOAT,
    measurement_date DATE
);
