# API Automation Framework – Rest Assured + Cucumber

## 📌 Overview

This project demonstrates API automation using **Rest Assured** and **Cucumber (BDD)**.
It covers end-to-end testing of a RESTful service including create, retrieve, and delete operations.

---

## 🚀 Features

* BDD-based test scenarios using Cucumber
* REST API automation using Rest Assured
* End-to-end workflow validation (POST → GET → DELETE)
* Data sharing between API calls
* JSON Path assertions
* Edge case and negative testing
* Clean and maintainable code structure
* Optional API key authentication support

---

## 🛠️ Tech Stack

* Java
* Maven
* Cucumber (BDD)
* Rest Assured
* JUnit

---

## 📂 Project Structure

```
src
 ├── test
 │   ├── java
 |   |   |
 |   |   ├──api
 |   |   |   └── ObjectAPI.java  
 │   │   ├── runners
 │   │   │   └── TestRunner.java
 │   │   └── stepdefinitions
 |   |       └── Hooks.java
 │   │       └── ObjectSteps.java
 │   └── resources
 │       └── features
 │           └── object.feature
```

---

## ✅ Test Scenarios Covered

### 1. End-to-End Scenario

* Create an object (POST)
* Retrieve the object (GET)
* Delete the object (DELETE)
* Verify deletion (GET → 404)

### 2. Negative / Edge Cases

* Retrieve object with invalid ID
* Create object with empty payload

---

## 🔐 Authentication Support

The framework supports API key authentication via headers.

Example:

```
.header("x-api-key", apiKey)
```

Run with API key:

```
mvn test -DapiKey=your_api_key
```

---

## ▶️ How to Run

### Run tests:

```
mvn clean test
```

### Run with API key:

```
mvn test -DapiKey=12345
```

---

## 📊 Assertions Used

* Status code validation
* JSON Path validation:

```
response.jsonPath().getString("name")
response.jsonPath().getString("data['CPU model']")
```

---

## ⚠️ Observations

* The API allows empty payload and returns **200 OK** instead of **400 Bad Request**
* Test cases are aligned with actual API behavior

---

## 💡 Future Enhancements

* Add reporting (Extent Reports)
* Add environment configuration (dev/test/prod)
* CI/CD integration (GitHub Actions/Jenkins)
* API layer abstraction for better reusability

---

## 👨‍💻 Author

Sujal Mansuri

---

## 📎 Repository

[https://github.com/Sujal1104/api-automation/]
