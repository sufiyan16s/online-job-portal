CREATE DATABASE IF NOT EXISTS labouriq_db;
USE labouriq_db;

-- ================= USERS =================
CREATE TABLE IF NOT EXISTS users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash VARCHAR(64) NOT NULL,
                       role ENUM('ADMIN', 'EMPLOYER', 'JOBSEEKER') NOT NULL,
                       resume_path VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================= JOBS =================
CREATE TABLE IF NOT EXISTS jobs (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      employer_id INT NOT NULL,
                      title VARCHAR(150) NOT NULL,
                      location VARCHAR(100) NOT NULL,
                      salary VARCHAR(50),
                      description TEXT,
                      status ENUM('OPEN', 'CLOSED') DEFAULT 'OPEN',
                      posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (employer_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ================= APPLICATIONS =================
CREATE TABLE IF NOT EXISTS applications (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              job_id INT NOT NULL,
                              seeker_id INT NOT NULL,
                              status ENUM('PENDING', 'SHORTLISTED', 'REJECTED') DEFAULT 'PENDING',
                              applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE,
                              FOREIGN KEY (seeker_id) REFERENCES users(id) ON DELETE CASCADE,
                              UNIQUE(job_id, seeker_id)
);

-- ================= MESSAGES =================
CREATE TABLE IF NOT EXISTS messages (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          sender_id INT NOT NULL,
                          receiver_id INT NOT NULL,
                          message TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_chat ON messages(sender_id, receiver_id);
