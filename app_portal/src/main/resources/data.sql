-- Sample data for University Student Portal
-- This script populates the database with initial test data

-- Insert departments
INSERT INTO departments (department_code, department_name, head_of_department, building, phone_number, email) VALUES
('CS', 'Computer Science', 'Dr. Sarah Johnson', 'Tech Building', '555-0101', 'cs@university.edu'),
('MATH', 'Mathematics', 'Dr. Michael Chen', 'Science Hall', '555-0102', 'math@university.edu'),
('ENG', 'English Literature', 'Dr. Emily Davis', 'Liberal Arts', '555-0103', 'english@university.edu'),
('PHYS', 'Physics', 'Dr. Robert Wilson', 'Science Hall', '555-0104', 'physics@university.edu'),
('CHEM', 'Chemistry', 'Dr. Lisa Anderson', 'Science Hall', '555-0105', 'chemistry@university.edu');

-- Insert users (passwords are BCrypt encoded for 'password123')
INSERT INTO users (username, password, email, first_name, last_name, role) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVMFvK', 'admin@university.edu', 'Admin', 'User', 'ADMIN'),
('jdoe', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVMFvK', 'john.doe@student.edu', 'John', 'Doe', 'STUDENT'),
('asmith', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVMFvK', 'alice.smith@student.edu', 'Alice', 'Smith', 'STUDENT'),
('bwilson', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVMFvK', 'bob.wilson@student.edu', 'Bob', 'Wilson', 'STUDENT'),
('cjohnson', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVMFvK', 'carol.johnson@student.edu', 'Carol', 'Johnson', 'STUDENT'),
('dlee', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVMFvK', 'david.lee@student.edu', 'David', 'Lee', 'STUDENT');

-- Insert students
INSERT INTO students (student_id, first_name, last_name, email, phone_number, date_of_birth, major, year, gpa, status, user_id) VALUES
('STU001', 'John', 'Doe', 'john.doe@student.edu', '555-1001', '2002-03-15', 'Computer Science', 3, 3.75, 'ACTIVE', 2),
('STU002', 'Alice', 'Smith', 'alice.smith@student.edu', '555-1002', '2001-07-22', 'Mathematics', 4, 3.92, 'ACTIVE', 3),
('STU003', 'Bob', 'Wilson', 'bob.wilson@student.edu', '555-1003', '2003-01-10', 'Physics', 2, 3.45, 'ACTIVE', 4),
('STU004', 'Carol', 'Johnson', 'carol.johnson@student.edu', '555-1004', '2002-11-05', 'Chemistry', 3, 3.68, 'ACTIVE', 5),
('STU005', 'David', 'Lee', 'david.lee@student.edu', '555-1005', '2001-09-18', 'English Literature', 4, 3.85, 'ACTIVE', 6),
('STU006', 'Emma', 'Brown', 'emma.brown@student.edu', '555-1006', '2003-05-30', 'Computer Science', 1, 3.20, 'ACTIVE', NULL),
('STU007', 'Frank', 'Davis', 'frank.davis@student.edu', '555-1007', '2002-12-12', 'Mathematics', 2, 3.55, 'ACTIVE', NULL),
('STU008', 'Grace', 'Miller', 'grace.miller@student.edu', '555-1008', '2001-04-25', 'Physics', 4, 3.78, 'ACTIVE', NULL);

-- Insert courses
INSERT INTO courses (course_code, course_name, description, credit_hours, department, instructor, semester, max_capacity, current_enrollment) VALUES
('CS101', 'Introduction to Programming', 'Basic programming concepts using Java', 3, 'Computer Science', 'Dr. Sarah Johnson', 'Fall 2024', 30, 25),
('CS201', 'Data Structures', 'Advanced data structures and algorithms', 4, 'Computer Science', 'Dr. Mark Thompson', 'Fall 2024', 25, 20),
('CS301', 'Database Systems', 'Relational database design and SQL', 3, 'Computer Science', 'Dr. Jennifer Liu', 'Spring 2024', 20, 18),
('MATH101', 'Calculus I', 'Differential and integral calculus', 4, 'Mathematics', 'Dr. Michael Chen', 'Fall 2024', 40, 35),
('MATH201', 'Linear Algebra', 'Vector spaces and matrix operations', 3, 'Mathematics', 'Dr. Patricia Wong', 'Spring 2024', 30, 22),
('PHYS101', 'General Physics I', 'Mechanics and thermodynamics', 4, 'Physics', 'Dr. Robert Wilson', 'Fall 2024', 35, 28),
('CHEM101', 'General Chemistry', 'Basic principles of chemistry', 4, 'Chemistry', 'Dr. Lisa Anderson', 'Fall 2024', 30, 26),
('ENG101', 'English Composition', 'Academic writing and critical thinking', 3, 'English Literature', 'Dr. Emily Davis', 'Fall 2024', 25, 23),
('CS401', 'Software Engineering', 'Software development methodologies', 3, 'Computer Science', 'Dr. Alex Rodriguez', 'Spring 2024', 20, 15),
('MATH301', 'Statistics', 'Probability and statistical analysis', 3, 'Mathematics', 'Dr. Kevin Park', 'Spring 2024', 25, 20);

-- Insert student course enrollments
INSERT INTO student_courses (student_id, course_id) VALUES
(1, 1), (1, 2), (1, 4), (1, 8),  -- John Doe
(2, 4), (2, 5), (2, 10),         -- Alice Smith
(3, 6), (3, 4), (3, 8),          -- Bob Wilson
(4, 7), (4, 4), (4, 8),          -- Carol Johnson
(5, 8), (5, 10),                 -- David Lee
(6, 1), (6, 4), (6, 8),          -- Emma Brown
(7, 4), (7, 5), (7, 10),         -- Frank Davis
(8, 6), (8, 4), (8, 5);          -- Grace Miller

-- Insert sample grades
INSERT INTO grades (student_id, course_id, grade_value, letter_grade, assignment_type, comments) VALUES
-- John Doe grades
(1, 1, 3.7, 'A_MINUS', 'Final Exam', 'Excellent programming skills'),
(1, 2, 3.3, 'B_PLUS', 'Midterm', 'Good understanding of data structures'),
(1, 4, 4.0, 'A', 'Final Exam', 'Outstanding mathematical reasoning'),
(1, 8, 3.5, 'A_MINUS', 'Essay', 'Well-written analytical essay'),

-- Alice Smith grades
(2, 4, 4.0, 'A', 'Final Exam', 'Perfect score on calculus exam'),
(2, 5, 3.8, 'A_MINUS', 'Final Project', 'Excellent linear algebra project'),
(2, 10, 3.9, 'A_MINUS', 'Final Exam', 'Strong statistical analysis'),

-- Bob Wilson grades
(3, 6, 3.3, 'B_PLUS', 'Lab Report', 'Good experimental technique'),
(3, 4, 3.5, 'A_MINUS', 'Midterm', 'Solid calculus foundation'),
(3, 8, 3.2, 'B_PLUS', 'Essay', 'Clear writing with good analysis'),

-- Carol Johnson grades
(4, 7, 3.7, 'A_MINUS', 'Lab Practical', 'Excellent lab skills'),
(4, 4, 3.6, 'A_MINUS', 'Final Exam', 'Strong mathematical ability'),
(4, 8, 3.8, 'A_MINUS', 'Research Paper', 'Thorough research and analysis'),

-- David Lee grades
(5, 8, 3.9, 'A_MINUS', 'Final Portfolio', 'Creative and insightful writing'),
(5, 10, 3.8, 'A_MINUS', 'Final Project', 'Comprehensive statistical study'),

-- Emma Brown grades
(6, 1, 3.0, 'B', 'Midterm', 'Good progress in programming'),
(6, 4, 3.3, 'B_PLUS', 'Quiz Average', 'Consistent performance'),
(6, 8, 3.1, 'B', 'Essay', 'Developing writing skills'),

-- Frank Davis grades
(7, 4, 3.5, 'A_MINUS', 'Final Exam', 'Strong calculus performance'),
(7, 5, 3.6, 'A_MINUS', 'Final Project', 'Excellent matrix work'),
(7, 10, 3.4, 'B_PLUS', 'Midterm', 'Good statistical understanding'),

-- Grace Miller grades
(8, 6, 3.8, 'A_MINUS', 'Final Exam', 'Excellent physics knowledge'),
(8, 4, 3.7, 'A_MINUS', 'Final Exam', 'Strong mathematical foundation'),
(8, 5, 3.9, 'A_MINUS', 'Final Project', 'Outstanding linear algebra work');
