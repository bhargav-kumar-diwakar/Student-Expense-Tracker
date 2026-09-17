# Student Expense Tracker API

## Project Description

Student Expense Tracker is a secure backend REST API built with Spring Boot that helps students manage and monitor their daily expenses. Students often struggle to keep track of where their money goes — this application allows them to register, login securely, and manage their own expense records completely isolated from other users.

Each student can log expenses, categorize them (Food, Travel, Study, Entertainment), filter by category or date range, and perform full CRUD operations — all secured with JWT based authentication so no student can access another student's data.

---

## Tech Stack

| Technology      | Purpose                          |
|-----------------|----------------------------------|
| Java 17         | Core programming language        |
| Spring Boot 3.2 | Backend framework                |
| Spring Security | Authentication and authorization |
| JWT             | Stateless token based auth       |
| Spring Data JPA | ORM and database abstraction     |
| Hibernate       | JPA implementation               |
| MySQL           | Relational database              |
| Lombok          | Reduce boilerplate code          |
| Maven           | Build and dependency management  |
| Postman         | API testing                      |

---

## Features

- User registration with BCrypt password hashing
- JWT based login — token required for all expense endpoints
- Full CRUD operations on expenses
- Filter expenses by category
- Filter expenses by date range
- Per-user data isolation — each student only sees their own expenses
- Global exception handling with clean JSON error responses
- Input validation on all request bodies

---

## How to Run Locally

### Prerequisites
- Java 17 installed
- MySQL installed and running
- Maven installed
- Postman (for testing)

### Steps

**1. Clone the repository**
```
git clone https://github.com/bhargav-kumar-diwakar/student-expense-tracker.git
```

**2. Open the project in IntelliJ IDEA**

**3. Create the database in MySQL**
```sql
CREATE DATABASE expense_tracker_db;
```

**4. Update your database credentials in:**
```
src/main/resources/application.properties
```
```properties
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

**5. Run the application**
```
Right click ExpenseTrackerApplication.java → Run
```

**6. The server starts at:**
```
http://localhost:8080
```

Hibernate will automatically create the `users` and `expenses` tables inside `expense_tracker_db` when the app starts for the first time.

---

## API Endpoints

### Auth Endpoints (Public — no token required)

| Method | Endpoint           | Description       |
|--------|--------------------|-------------------|
| POST   | /api/auth/register | Register new user |
| POST   | /api/auth/login    | Login and get JWT |

### Expense Endpoints (Protected — JWT token required)

| Method | Endpoint                                     | Description                        |
|--------|----------------------------------------------|------------------------------------|
| POST   | /api/expenses                                | Create a new expense               |
| GET    | /api/expenses                                | Get all expenses of logged in user |
| GET    | /api/expenses/{id}                           | Get a single expense by ID         |
| PUT    | /api/expenses/{id}                           | Update an existing expense         |
| DELETE | /api/expenses/{id}                           | Delete an expense                  |
| GET    | /api/expenses/category/{category}            | Filter expenses by category        |
| GET    | /api/expenses/daterange?startDate=&endDate=  | Filter expenses by date range      |

---

## How to Authenticate

All expense endpoints require a JWT token. Follow these steps:

**Step 1 — Register**
```
POST /api/auth/register
```
```json
{
  "name": "Alex",
  "email": "alex@gmail.com",
  "password": "123456"
}
```

**Step 2 — Login and copy the token**
```
POST /api/auth/login
```
```json
{
  "email": "alex@gmail.com",
  "password": "123456"
}
```

**Step 3 — Add token to every expense request in Postman**
```
Authorization tab → Bearer Token → paste token here
```

---

## Sample Request and Response

### Register — POST /api/auth/register

**Request Body:**
```json
{
  "name": "Alex",
  "email": "alex@gmail.com",
  "password": "123456"
}
```

**Response — 201 Created:**
```json
"User registered successfully"
```

---

### Login — POST /api/auth/login

**Request Body:**
```json
{
  "email": "alex@gmail.com",
  "password": "123456"
}
```

**Response — 200 OK:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9......",
  "email": "alex@gmail.com",
  "name": "Alex"
}
```

---

### Create Expense — POST /api/expenses

**Headers:** `Authorization: Bearer {token}`

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

**Headers:** `Authorization: Bearer {token}`

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

**Headers:** `Authorization: Bearer {token}`

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

**Headers:** `Authorization: Bearer {token}`

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

**Headers:** `Authorization: Bearer {token}`

**Response — 200 OK:**
```json
"Expense deleted successfully"
```

---

### Validation Error — POST /api/expenses with empty title

**Request Body:**
```json
{
  "title": "",
  "amount": 120.0,
  "category": "Food",
  "date": "2024-01-15"
}
```

**Response — 400 Bad Request:**
```json
{
  "status": 400,
  "timestamp": "2024-01-15T10:30:00",
  "errors": {
    "title": "Title cannot be empty"
  }
}
```

---

### Access Another User's Expense — GET /api/expenses/5

**Headers:** `Authorization: Bearer {token}`

**Response — 404 Not Found:**
```json
{
  "status": 404,
  "message": "Expense not found or does not belong to you",
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## Author

**Bhargav Kumar Diwakar**
GitHub: https://github.com/bhargav-kumar-diwakar