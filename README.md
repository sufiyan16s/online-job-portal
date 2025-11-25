# LabourIQ — Online Job Portal (JavaFX Desktop Application)

LabourIQ is a complete JavaFX-based desktop job portal system featuring role-based dashboards for **Admin**, **Employer**, and **Job Seeker**. The system supports job posting, job applications, user management, messaging, and profile handling — built fully using **Java**, **JavaFX**, **SQLite**, **OOP**, and **JDBC** as required for GUVI’s Java project.

---

## ⭐ Features

### 👨‍💼 Employer
- Post new job openings  
- Manage job listings  
- View applications  
- Accept / Reject candidates  
- Message job seekers  

### 🧑‍💻 Job Seeker
- Search jobs with filters  
- Apply for jobs (upload resume)  
- Track application status  
- Manage profile & resume  
- Message employers  

### 🛡 Admin
- Approve / Reject job posts  
- Manage users (view, edit role, delete)  
- View job statistics  

### 🔧 Core Technologies
- Java 17  
- JavaFX 19  
- SQLite (lightweight file DB)  
- JDBC + DAO pattern  
- MVC architecture  
- Maven (JavaFX Maven plugin)  
- IntelliJ IDEA  

---

## 📁 Project Structure

# Folder structure

labouriq/
-├── pom.xml
-├── LICENSE
-├── README.md
-├── .gitignore
-├── FOLDER_STRUCTURE.md
-│
-├── database/
-│   └── labouriq.db                 # auto-created by DBInit
-│
-└── src/
-    └── main/
-        ├── java/
-        │   └── com/labouriq/
-        │       ├── controllers/
-        │       │   ├── AdminController.java
-        │       │   ├── EmployerController.java
-        │       │   ├── JobSeekerController.java
-        │       │   ├── LoginController.java
-        │       │   ├── SignupController.java
-        │       │   ├── FXRouter.java
-        │       │   └── ...other controllers
-        │       │
-        │       ├── dao/
-        │       │   ├── UserDAO.java
-        │       │   ├── JobDAO.java
-        │       │   ├── ApplicationDAO.java
-        │       │   └── MessageDAO.java
-        │       │
-        │       ├── db/
-        │       │   ├── DBConnection.java
-        │       │   └── DBInit.java
-        │       │
-        │       ├── model/
-        │       │   ├── User.java
-        │       │   ├── Job.java
-        │       │   ├── Application.java
-        │       │   └── Message.java
-        │       │
-        │       └── MainApp.java
-        │
-        └── resources/
-            ├── database/
-            │   └── database.sql
-            │
-            ├── fxml/
-            │   ├── login.fxml
-            │   ├── signup.fxml
-            │   ├── admin_dashboard.fxml
-            │   ├── employer_dashboard.fxml
-            │   ├── jobseeker_dashboard.fxml
-            │   ├── post_job.fxml
-            │   └── ...other FXML files
-            │
-            ├── css/
-            │   └── styles.css
-            │
-            └── images/
-                └── ...branding & screenshots



---

## 🚀 Build & Run Instructions (For GUVI Reviewers)

### ✅ Prerequisites
- **Java 17** installed  
- **Maven** installed and configured in PATH  
- **IntelliJ IDEA** recommended  

### ▶️ Run the application
From project root:

```bash
mvn clean compile
mvn javafx:run
---
##This automatically:

Initializes SQLite DB

Loads JavaFX modules

Starts the Login screen

Database Auto-Initialization

DBInit.java creates the DB file automatically and executes:

src/main/resources/database/database.sql

Default demo users created:

Role	Email	Password
Admin	admin@demo	admin123
Employer	employer@demo	emp123
Job Seeker	seeker@demo	seek123

This project is licensed under the MIT License.
See the LICENSE file for details.

🏁 Author

Md Sufiyan
GitHub: sufiyan16s
