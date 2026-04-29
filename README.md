# QualityHR - Automation Testing Framework

## Project Overview

**QualityHR** is a comprehensive test automation framework built using **Selenium WebDriver** and **TestNG** for automating testing workflows of an HR (Human Resource) Management System. The framework automates various HR modules including Login, Employee Management, Leave Management, and Admin User Management.

**Framework Type:** Selenium + TestNG  
**Language:** Java  
**Build Tool:** Maven  
**Reporting:** Extent Reports  

---

## Project Flow

### Workflow Overview

The automation testing follows a modular approach with distinct functional modules:

```
Start
  ↓
Initialize WebDriver (Chrome)
  ↓
Load Configuration (browser, URL, timeouts)
  ↓
Navigate to Application URL
  ↓
┌─────────────────────────────────────────────────────────┐
│                   Test Module Selection                 │
└─────────────────────────────────────────────────────────┘
  ├─→ Module 1: LOGIN TESTS
  │     ├─ Valid User Login & Dashboard Verification
  │     ├─ Invalid Credentials Error Handling
  │     ├─ Empty Fields Validation
  │     └─ Logout Functionality
  │
  ├─→ Module 2: EMPLOYEE MANAGEMENT
  │     ├─ Add New Employee
  │     ├─ Search Employee by ID
  │     ├─ Search Employee by Name
  │     ├─ View Employee Details
  │     └─ Invalid Search Handling
  │
  ├─→ Module 3: LEAVE MANAGEMENT
  │     ├─ Navigate to Leave Module
  │     ├─ Apply New Leave Request
  │     ├─ View Leave List
  │     └─ Past Date Validation
  │
  └─→ Module 4: ADMIN USER MANAGEMENT
        ├─ Add New System User
        ├─ Assign Role & Status
        ├─ Search Created User
        └─ Delete User
  ↓
Capture Screenshots (on failure)
  ↓
Generate Extent Report
  ↓
Close WebDriver
  ↓
End
```

---

## Project Structure

```
QualityHR/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/srm/QualityHR/
│   │           └── App.java
│   │
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   ├── BasePage.java              (Reusable WebDriver methods)
│       │   │   └── BaseTest.java              (Setup/Teardown configuration)
│       │   │
│       │   ├── pages/
│       │   │   ├── LoginPage.java             (Login page locators & methods)
│       │   │   ├── DashboardPage.java         (Dashboard navigation)
│       │   │   ├── EmployeePage.java          (Employee management operations)
│       │   │   ├── LeavePage.java             (Leave module operations)
│       │   │   └── AdminPage.java             (Admin user management)
│       │   │
│       │   ├── tests/
│       │   │   ├── LoginTest.java             (Login test cases)
│       │   │   ├── EmployeeTest.java          (Employee test cases)
│       │   │   ├── EmployeeValidationTest.java (Employee validation tests)
│       │   │   ├── LeaveTest.java             (Leave test cases)
│       │   │   └── AdminTest.java             (Admin user test cases)
│       │   │
│       │   ├── utils/
│       │   │   ├── ConfigReader.java          (Read config.properties file)
│       │   │   ├── DriverFactory.java         (WebDriver initialization)
│       │   │   ├── ExtentManager.java         (Extent report management)
│       │   │   ├── ScreenshotUtils.java       (Screenshot capture on failure)
│       │   │   └── JsonUtils.java             (Parse test data from JSON)
│       │   │
│       │   └── listeners/
│       │       └── TestListener.java          (TestNG listeners for reporting)
│       │
│       └── resources/
│           ├── config.properties              (Test configuration values)
│           └── loginData.json                 (Login test data)
│
├── reports/
│   └── ExtentReport.html                      (Generated HTML test report)
│
├── screenshots/
│   └── [Failed test screenshots]              (Captured on test failures)
│
├── target/                                    (Build artifacts)
│   ├── classes/
│   └── test-classes/
│
├── test-output/                               (TestNG reports)
│
├── testng.xml                                 (Test suite configuration)
├── pom.xml                                    (Maven dependencies)
├── .project                                   (Eclipse project file)
├── .classpath                                 (Eclipse classpath)
├── .gitignore                                 (Git ignore rules)
└── README.md                                  (This file)
```

---

## Test Modules Details

### 1. **LOGIN TESTS** (`LoginTest.java`)

**Test Cases:**
- `loginTest()` - Validates login with valid, invalid, and empty credentials (Data-driven from JSON)
- `logoutTest()` - Verifies successful logout functionality

**Key Features:**
- Data-driven testing using TestNG DataProvider
- Test data loaded from `loginData.json`
- Error message validation for invalid credentials

---

### 2. **EMPLOYEE TESTS** (`EmployeeTest.java`)

**Test Cases:**
- `addEmployeeVerifyById()` - Add new employee and verify by ID search
- `searchEmployeeByName()` - Search existing employee by name
- `openEmployeeAndVerifyDetails()` - Open employee record and verify personal details
- `invalidSearchTest()` - Verify "No Records" message for invalid search

**Key Features:**
- Dynamic employee creation with timestamps
- Employee details verification
- Table row validation

---

### 3. **EMPLOYEE VALIDATION TESTS** (`EmployeeValidationTest.java`)

**Test Cases:**
- `emptyFieldValidationTest()` - Verify first name and last name mandatory fields
- `invalidDateValidationTest()` - Validate date format validation
- `dropdownValidationTest()` - Test gender dropdown functionality

**Key Features:**
- Form validation testing
- Error message verification
- UI element interaction validation

---

### 4. **LEAVE TESTS** (`LeaveTest.java`)

**Test Cases:**
- `verifyLeavePage()` - Navigate to leave module and verify page loading
- `applyLeaveTest()` - Apply leave request with valid date range
- `verifyLeaveList()` - Verify leave history list
- `pastDateValidationTest()` - Validate system rejects past dates for leave

**Key Features:**
- Leave module navigation
- Date range selection
- Past date validation
- Leave request submission

---

### 5. **ADMIN TESTS** (`AdminTest.java`)

**Test Cases:**
- `userManagementTest()` - Complete user lifecycle (Create → Search → Delete)

**Operations:**
1. Create new system user with unique username
2. Assign role and status
3. Set credentials
4. Search created user
5. Delete created user
6. Verify deletion

---

## Page Objects (POM)

All page interactions are encapsulated in Page Object Model classes:

| Page Class | Purpose |
|-----------|---------|
| `LoginPage` | Handles login/logout operations |
| `DashboardPage` | Dashboard navigation to different modules |
| `EmployeePage` | Employee CRUD operations and searches |
| `LeavePage` | Leave application and management |
| `AdminPage` | User management and admin functions |
| `BasePage` | Common WebDriver interactions (click, sendKeys, getText) |

---

## Configuration & Properties

### `config.properties` Content Variables:

```properties
browser=chrome                          # Browser type
baseUrl=<HR_Application_URL>            # Application URL
timeout=10                              # WebDriver wait timeout (seconds)
admin.username=Admin                    # Admin login credentials
admin.password=admin123
employee.firstname=John                 # Employee test data
employee.middlename=Michael
employee.lastname=Doe
invalid.employee=XYZ123                 # Invalid search data
leave.type=Leave Type                   # Leave module data
leave.fromDate=2025-01-15
leave.toDate=2025-01-20
leave.pastDate=2020-01-01
user.role=Admin                         # Admin user creation
user.status=Enabled
user.password=Password@123
employee.name=John Doe                  # Valid employee for user creation
```

### `loginData.json` (Data-Driven Tests):

```json
{
  "loginData": [
    {
      "username": "Admin",
      "password": "admin123",
      "type": "valid"
    },
    {
      "username": "InvalidUser",
      "password": "InvalidPass",
      "type": "invalid"
    },
    {
      "username": "",
      "password": "",
      "type": "empty"
    }
  ]
}
```

---

## Dependencies

**Dependency Details (from pom.xml):**

| Dependency | Version | Purpose |
|-----------|---------|---------|
| **Selenium** | 4.21.0 | Browser automation |
| **TestNG** | 7.10.2 | Test framework & assertions |
| **WebDriverManager** | 5.8.0 | Automatic driver management |
| **JSON** | 20240303 | JSON data parsing |
| **Commons-IO** | 2.15.1 | File I/O operations |
| **Extent Reports** | 5.1.1 | HTML test reporting |

---

## How to Run Tests

### Prerequisites:
- Java 8 or higher
- Maven 3.6+
- Chrome browser installed

### Run All Tests:
```bash
mvn clean test
```

### Run Specific Test Suite:
```bash
mvn clean test -Dtest=LoginTest
```

### Run with TestNG.xml:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run Single Test Method:
```bash
mvn clean test -Dtest=EmployeeTest#addEmployeeVerifyById
```

---

## Test Execution & Reporting

### TestNG Suite Configuration (`testng.xml`):

**Parallel Execution:**
- Suite runs 3 tests in **parallel** mode
- Each module runs independently

**Test Modules:**
1. **Login Tests** - `tests.LoginTest`
2. **Employee Tests** - `tests.EmployeeTest`
3. **Leave Tests** - `tests.LeaveTest`
4. **Admin Tests** - `tests.AdminTest`

### Report Generation:

**Extent Report:**
- Path: `reports/ExtentReport.html`
- Contains: Test results, screenshots, execution logs
- Auto-generated after test completion

**Screenshot Capture:**
- Path: `screenshots/`
- Triggered on: Test failure
- Naming: `{TestMethodName}_{Timestamp}.png`

**TestNG Report:**
- Path: `test-output/`
- HTML report with pass/fail statistics

---

## Execution Flow - Detailed

### Test Execution Sequence:

1. **Setup Phase** (BaseTest.java - @BeforeMethod):
   - Initialize WebDriver (Chrome)
   - Load configuration from properties
   - Navigate to base URL
   - Store driver in test context

2. **Test Execution**:
   - Create Page Object instances
   - Perform user interactions
   - Assert expected vs actual results

3. **Teardown Phase** (BaseTest.java - @AfterMethod):
   - Close WebDriver

4. **Reporting** (TestListener.java):
   - Log test status (Pass/Fail/Skip)
   - Capture screenshot on failure
   - Flush Extent report

---

## Utilities & Helpers

### ConfigReader (`utils/ConfigReader.java`)
Reads configuration from `config.properties` file:
```java
new ConfigReader().getBrowser()      // Returns: "chrome"
new ConfigReader().getUrl()          // Returns: Application URL
new ConfigReader().getTimeout()      // Returns: timeout in seconds
```

### DriverFactory (`utils/DriverFactory.java`)
Initializes WebDriver:
```java
WebDriver driver = DriverFactory.initDriver("chrome");
```

### ExtentManager (`utils/ExtentManager.java`)
Manages Extent Report instances with singleton pattern.

### ScreenshotUtils (`utils/ScreenshotUtils.java`)
Captures screenshots on test failure with timestamp.

### JsonUtils (`utils/JsonUtils.java`)
Parses JSON files for data-driven testing.

---

## Design Patterns Used

1. **Page Object Model (POM)** - Separation of test logic and page locators
2. **Singleton Pattern** - ExtentManager for single report instance
3. **ThreadLocal** - Test listeners manage thread-safe report logging
4. **Data-Driven Testing** - TestNG DataProvider with JSON/Excel
5. **Factory Pattern** - DriverFactory for WebDriver initialization
6. **Base Class Pattern** - BasePage & BaseTest for code reuse

---

## Key Features

 Modular Architecture - Separate page objects for each module  
 Data-Driven Testing - External test data from JSON  
 Parallel Execution - Run multiple test suites simultaneously  
 Screenshot on Failure - Auto-capture for debugging  
 Comprehensive Reporting - Extent Reports with detailed logs  
 WebDriver Wait - Implicit and explicit waits for stability  
 Configuration Management - Externalized properties file  
 Listener Integration - TestNG listeners for reporting hooks  

---

## Troubleshooting

### Common Issues:

| Issue | Solution |
|-------|----------|
| **WebDriver not found** | Run `mvn clean test` to download dependencies via WebDriverManager |
| **config.properties not found** | Ensure file exists at `src/test/resources/config.properties` |
| **Tests timeout** | Increase timeout value in `config.properties` |
| **Chrome version mismatch** | WebDriverManager automatically downloads matching version |
| **Screenshot not captured** | Check `screenshots/` folder has write permissions |

---

## Notes

- All tests use **explicit waits** via `WebDriverWait` for better stability
- Test data is **externalized** for easy maintenance
- **Parallel execution** allows faster test suite completion
- **Screenshots** automatically capture on test failure for debugging
- Framework is **scalable** - New test modules can be added following existing patterns

---

