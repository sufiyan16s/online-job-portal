-- database/database.sql
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT NOT NULL UNIQUE,
  email TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  role TEXT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS jobs (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  employer_id INTEGER,
  title TEXT,
  description TEXT,
  location TEXT,
  salary TEXT,
  status TEXT DEFAULT 'OPEN',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(employer_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS applications (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  job_id INTEGER,
  seeker_id INTEGER,
  resume TEXT,
  cover_letter TEXT,
  status TEXT DEFAULT 'PENDING',
  applied_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(job_id) REFERENCES jobs(id),
  FOREIGN KEY(seeker_id) REFERENCES users(id)
);

-- add resume_path to applications (if not present)
ALTER TABLE applications ADD COLUMN resume_path TEXT;
-- messages table
CREATE TABLE IF NOT EXISTS messages (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        sender_id INTEGER,
                                        receiver_id INTEGER,
                                        content TEXT,
                                        sent_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                        read_flag INTEGER DEFAULT 0,
                                        FOREIGN KEY(sender_id) REFERENCES users(id),
                                        FOREIGN KEY(receiver_id) REFERENCES users(id)
);


-- demo admin user (email: admin@demo, password: admin123)
INSERT OR IGNORE INTO users (username, email, password, role)
VALUES ('admin', 'admin@demo', 'admin123', 'ADMIN');

-- demo employer (optional)
INSERT OR IGNORE INTO users (username, email, password, role)
VALUES ('employer1', 'employer@demo', 'emp123', 'EMPLOYER');

-- demo job seeker
INSERT OR IGNORE INTO users (username, email, password, role)
VALUES ('seeker1', 'seeker@demo', 'seek123', 'JOBSEEKER');
