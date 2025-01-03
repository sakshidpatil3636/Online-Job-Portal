# **Online Job Portal**

## **Project Description**  
The **Online Job Portal** is a web-based platform where users can:  
- Browse and apply filters to find suitable jobs by location, category, and job status (active/inactive).  
- View job details and keep track of job statuses.  
- Edit their profiles to keep their information up-to-date.  

Admins have a separate login, allowing them to:  
- Post new job listings.  
- Manage jobs by updating their statuses or deleting them.  
- View all job postings.  

This portal provides a streamlined job search and management experience for users and administrators.  

---

## **Technologies and Tools Used**  
- **Backend**: Java Servlets  
- **Frontend**: JSP Pages, HTML, CSS, Bootstrap  
- **Database**: MySQL  

---

## **Database Details**  
The project uses a MySQL database with the following two tables:  

### **1. `user` Table**  
- **Columns**:  
  - `id` (Primary Key, Integer): Unique identifier for the user.  
  - `name` (String): User's full name.  
  - `qualification` (String): User's educational or professional qualification.  
  - `email` (String): User's email address.  
  - `password` (String): Encrypted password for authentication.  
  - `role` (String): User role (`user` or `admin`).  

### **2. `jobs` Table**  
- **Columns**:  
  - `id` (Primary Key, Integer): Unique identifier for the job.  
  - `title` (String): Job title.  
  - `description` (String): Job description with details.  
  - `category` (String): Job category (e.g., IT, Marketing, etc.).  
  - `status` (String): Status of the job (`active` or `inactive`).  
  - `location` (String): Job location.  
  - `pdate` (Date): Posting date of the job.  

---

## **How to Use**  

### **For Users:**  
1. Sign up or log in to the portal.  
2. Browse jobs using filters such as location, category, and status.  
3. View job details and track their status.  
4. Update your profile as needed.  

### **For Admins:**  
1. Log in using admin credentials.  
2. Post new job listings with all relevant details.  
3. Update the status of existing jobs (e.g., active/inactive).  
4. Delete outdated or invalid job listings.  

---

## **Features**  
### **User Role:**  
- Browse and filter job listings.  
- Track job statuses.  
- Edit and manage user profile.  

### **Admin Role:**  
- Post, update, and delete job listings.  
- Manage job statuses.  

### **User-Friendly Interface:**  
- Built with Bootstrap for a responsive design.  

---

