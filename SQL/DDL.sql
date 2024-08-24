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
CREATE TABLE trainer (
    trainer_id SERIAL PRIMARY KEY NOT NULL,
    "name" VARCHAR(255),
    specialization VARCHAR(255),
    start_availability DATE,
    end_availability DATE
);

-- Personal Trainer table
CREATE TABLE personal_training (
    training_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    trainer_id INT REFERENCES Trainer(trainer_id),
    "start_date" DATE,
    end_date DATE
);

-- Billing table
CREATE TABLE billing (
    billing_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    billing_date DATE,
    amount FLOAT
);

-- Class Schedule table
CREATE TABLE class_Schedule (
    class_id SERIAL PRIMARY KEY NOT NULL,
    class_name VARCHAR(255),
    start_time TIME,
    end_time TIME
);

-- Room Booking table
CREATE TABLE room_booking (
    booking_no SERIAL PRIMARY KEY NOT NULL,
    room_number INT,
    "start_date" DATE,
    end_date DATE
);

CREATE TABLE member_routine(
    routine_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    routine_title VARCHAR(255),
    "description" VARCHAR(255),
    "start_date" DATE,
    end_date DATE,

)
-- Equipment Maintenance table
CREATE TABLE equipment_maintenance (
    maintenance_id SERIAL PRIMARY KEY NOT NULL,
    maintenance_date DATE,
    "description" VARCHAR(255)
);

-- Member Fitness Metric table
CREATE TABLE member_fitness_metric (
    fitness_metric_id SERIAL PRIMARY KEY NOT NULL,
    member_id INT REFERENCES "member"(member_id),
    "weight" FLOAT,
    height FLOAT,
    bmi FLOAT,
    body_fat_percentage FLOAT,
    measurement_date DATE
);
