# Student-Expense-Tracker

## Project Description

Student Expense Tracker is a backend REST API built with Spring Boot that helps
students manage and monitor their daily expenses. Students often struggle to keep
track of where their money goes — this application allows them to log expenses,
categorize them (Food, Travel, Study, Entertainment), filter by category or date
range, and perform full CRUD operations on their expense records.

---

## Tech Stack

| Technology      | Purpose                        |
|-----------------|--------------------------------|
| Java 17         | Core programming language      |
| Spring Boot 3.2 | Backend framework              |
| Spring Data JPA | ORM and database abstraction   |
| Hibernate       | JPA implementation             |
| MySQL           | Relational database            |
| Lombok          | Reduce boilerplate code        |
| Maven           | Build and dependency management|
| Postman         | API testing                    |

---

## How to Run Locally

### Prerequisites
- Java 17 installed
- MySQL installed and running
- Maven installed
- Postman (for testing)

### Steps

1. Clone the repository
   git clone https://github.com/bhargav-kumar-diwakar/student-expense-tracker.git

2. Open the project in IntelliJ IDEA

3. Create the database in MySQL
   CREATE DATABASE expense_tracker_db;

4. Update your database credentials in:
   src/main/resources/application.properties

   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password

5. Run the application
   Right click ExpenseTrackerApplication.java → Run

6. The server starts at:
   http://localhost:8080

Hibernate will automatically create the expenses table inside
expense_tracker_db when the app starts for the first time.

---

## API Endpoints

| Method | Endpoint                              | Description                        |
|--------|---------------------------------------|------------------------------------|
| POST   | /api/expenses                         | Create a new expense               |
| GET    | /api/expenses                         | Get all expenses                   |
| GET    | /api/expenses/{id}                    | Get a single expense by ID         |
| PUT    | /api/expenses/{id}                    | Update an existing expense         |
| DELETE | /api/expenses/{id}                    | Delete an expense                  |
| GET    | /api/expenses/category/{category}     | Get expenses filtered by category  |
| GET    | /api/expenses/daterange?startDate=&endDate= | Get expenses in a date range  |

---

## Sample Request and Response

### Create Expense — POST /api/expenses

**Request Body:**
```json
{
  "title": "College Canteen Lunch",
  "amount": 120.0,
  "category": "Food",
  "date": "2024-01-15",
  "description": "Rice and dal"
}
```

**Response — 201 Created:**
```json
{
  "id": 1,
  "title": "College Canteen Lunch",
  "amount": 120.0,
  "category": "Food",
  "date": "2024-01-15",
  "description": "Rice and dal"
}
```

---

### Get All Expenses — GET /api/expenses

**Response — 200 OK:**
```json
[
  {
    "id": 1,
    "title": "College Canteen Lunch",
    "amount": 120.0,
    "category": "Food",
    "date": "2024-01-15",
    "description": "Rice and dal"
  },
  {
    "id": 2,
    "title": "Bus Pass",
    "amount": 500.0,
    "category": "Travel",
    "date": "2024-01-16",
    "description": "Monthly bus pass"
  }
]
```

---

### Get Expense By ID — GET /api/expenses/1

**Response — 200 OK:**
```json
{
  "id": 1,
  "title": "College Canteen Lunch",
  "amount": 120.0,
  "category": "Food",
  "date": "2024-01-15",
  "description": "Rice and dal"
}
```

---

### Update Expense — PUT /api/expenses/1

**Request Body:**
```json
{
  "title": "Canteen Dinner",
  "amount": 150.0,
  "category": "Food",
  "date": "2024-01-15",
  "description": "Updated to dinner"
}
```

**Response — 200 OK:**
```json
{
  "id": 1,
  "title": "Canteen Dinner",
  "amount": 150.0,
  "category": "Food",
  "date": "2024-01-15",
  "description": "Updated to dinner"
}
```

---

### Delete Expense — DELETE /api/expenses/1

**Response — 200 OK:**