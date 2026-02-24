# 🍽️ MightyMeals – Restaurant Management System

MightyMeals is a **Java-based restaurant management and food ordering system** designed to simplify the process of browsing menus, placing orders, and managing restaurant operations.  
Built using **Java, MySQL, and JDBC**, it provides both **admin** and **customer** functionalities in a clean, efficient, and user-friendly interface.

---

## 🚀 Features

### 👩‍🍳 Customer Module
- Register and log in securely  
- Browse restaurant menus by category  
- Add meals to cart and place orders  
- Make payments and view order history  

### 🧑‍💼 Admin Module
- Manage menu items (add, update, delete)  
- View and process customer orders  
- Manage user accounts and payment records  
- Generate reports for orders and revenue  

### 💳 Order & Payment Module
- Track order status (Pending → Preparing → Delivered)  
- Store payment information securely  
- Generate receipts for completed orders  

---

## 🏗️ Tech Stack

| Layer | Technology |
|:------|:------------|
| **Frontend / GUI** | Java Swing / JavaFX |
| **Backend Logic** | Core Java (OOP, Exception Handling, Collections) |
| **Database** | MySQL |
| **Database Connectivity** | JDBC (Java Database Connectivity) |
| **IDE (Recommended)** | Android Studio |

---

## 🗄️ Database Design

### 📋 Tables Overview
1. **users** – Stores user details (user_id, name, email, password, role)  
2. **menu_items** – Contains meal details (item_id, name, category, price, description)  
3. **orders** – Stores order info (order_id, user_id, date, total_amount, status)  
4. **order_items** – Links menu items with orders (order_id, item_id, quantity)  

---

## ⚙️ How to Run the Project

### 1. Clone the Repository
```bash
git clone https://github.com/AAbdullahRajput/MightyMeals.git
```

### 2. Open in IDE
- Open the project in **NetBeans**, **Eclipse**, or **IntelliJ IDEA**.  
- Ensure **Java JDK 8+** and **MySQL** are installed on your system.

### 3. Setup Database
- Open **MySQL Workbench** or **phpMyAdmin**.  
- Create a new database:
  ```sql
  CREATE DATABASE mightymeals;
  ```
- Import the provided SQL file (e.g., `mightymeals.sql`) into your database.

### 4. Configure JDBC Connection
Update your database credentials in the connection file (e.g., `DBConnection.java`):

```java
String url = "mightymeals:mysql://localhost:3306/mightymeals";
String user = "root";
String password = "";
Connection con = DriverManager.getConnection(url, user, password);
```

### 5. Run the Application
- Compile and run the **Main.java** file.  
- The home screen should display options for **Admin** and **Customer** login.

---

## 📦 Folder Structure

```
MightyMeals/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/          # Entity classes (User, Meal, Order, etc.)
│   │   │   ├── dao/            # Database access (JDBC)
│   │   │   ├── ui/             # GUI forms
│   │   │   └── Main.java       # Entry point
│
├── README.md
└── LICENSE
```

---

## 🧠 Concepts Used
- Object-Oriented Programming (OOP)
- Exception Handling
- JDBC (Database Connectivity)
- MVC Design Pattern
- File Handling (optional)
- Swing/JavaFX GUI components

---

## 🧾 License
This project is licensed under the **MIT License** – you are free to use, modify, and distribute it with attribution.

---

## 👨‍💻 Author
**Developed by:** AAI Team 
🌐 GitHub: [github.com/AAbdullahRajput](https://github.com/AAbdullahRajput)

---

> ⭐ If you like this project, don’t forget to **star the repo** and share your feedback!
