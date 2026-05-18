#  Demo Balze Project

This repository contains a maintainable and scalable UI Test Automation Framework for the [Demo blaze](https://www.demoblaze.com/) e-commerce website. The framework automates and validates major user workflows such as sign up, login, product browsing, add-to-cart, cart validation, and checkout scenarios using Selenium WebDriver and TestNG. The project follows industry best practices including Page Object Model (POM), reusable utilities, custom waits, cross-browser execution, and structured test reporting.
---

##  Features

- **Java + TestNG** based test execution
- **Design Patterns:** Page Object Model (POM), Fluent, Bot, Factory
- **Custom Fluent Wait Utility** for stable element interactions
- **Cross-browser support** (Chrome, Edge)
- **Headless execution** support for CI environments
- **Log4j2** structured logging
- **Allure Reporting** with screenshots and screen recordings
- **Data-driven testing** using JSON And Properties files
- **API testing** using RestAssured
- **CI/CD integration** via GitHub Actions
- **Custom Listeners** for screenshots, logs, and test lifecycle management
- **Environment-based execution** (local, headless)

---

##  Project Structure

```
Demo Blaze
├── src/ 
    ├── main/ 
    │   ├── resources/ 
    │   │   ├── OS.properties
    │   │   ├── waits.properties
    │   │   ├── seleniumGrid.properties
    │   │   ├── video.properties
    │   │   ├── META-INF/ 
    │   │   │   └── services/ 
    │   │   │   │   └── org.testng.ITestNGListener
    │   │   ├── environment.properties
    │   │   ├── allure.properties
    │   │   ├── webapp.properties
    │   │   └── log4j2.properties 
    │   └── java/ 
    │   │   └── com/ 
    │   │       └── demoblaze/ 
    │   │           ├── drivers/ 
    │   │               ├── WebDriverProvider.java
    │   │               ├── APITest.java
    │   │               ├── UITest.java
    │   │               ├── Browsers.java
    │   │               ├── AbstractDriver.java 
    │   │               ├── GUIDriver.java 
    │   │               ├── ChromeFactory.java 
    │   │               └── EdgeFactory.java 
    │   │           ├── utils/ 
    │   │               ├── TimeManager.java
    │   │               ├── OSUtils.java 
    │   │               ├── TerminalUtils.java 
    │   │               ├── Logs/ 
    │   │               │   └── LogsManager.java 
    │   │               ├── report/ 
    │   │               │   ├── AllureEnvironmentManager.java 
    │   │               │   ├── AllureConstants.java 
    │   │               │   ├── AllureAttachmentManager.java 
    │   │               │   ├── AllureReportGenerator.java 
    │   │               │   └── AllureBinaryManager.java 
    │   │               ├── dataReader/ 
    │   │               │   ├── JsonReader.java 
    │   │               │   └── PropertyReader.java 
    │   │               ├── WaitManager.java 
    │   │               ├── Actions/ 
    │   │               │   ├── AlertActions.java 
    │   │               │   ├── BrowserActions.java 
    │   │               │   ├── FrameActions.java 
    │   │               │   └── ElementActions.java 
    │   │               └── FileUtils.java 
    │   │           ├── validations/ 
    │   │               ├── Verification.java 
    │   │               ├── Validation.java 
    │   │               └── BaseAssertion.java 
    │   │           ├── media/ 
    │   │               ├── ScreenShotManager.java 
    │   │               └── ScreenRecordManager.java 
    │   │           ├── Pages/ 
    │   │               ├── SignupPage.java 
    │   │               ├── NavigateBar.java 
    │   │               ├── LoginPage.java 
    │   │               ├── PlaceOrderPage.java 
    │   │               └── CartPage.java 
    │   │           └── listeners/ 
    │   │               └── TestNGListeners.java 
    └── test/ 
    │   ├── resources/ 
    │       └── test-data/ 
    │       │   ├── Register-data.json
    │       │   ├── login-data.json 
    │       │   ├── Cart-data.json 
    │       │   └── PlaceOrder-data.json 
    │   └── java/ 
    │       └── com/ 
    │           └── demoblaze/ 
    │               └── tests/ 
    │                   ├── BaseTest.java
    │                   └── ui/ 
    │                       ├── RegisterTest.java 
    │                       ├── LoginTest.java 
    │                       ├── CartTest.java 
    │                       └── PlaceOrderTest.java 
├── .gitignore 
├── .github/ 
    └── workflows/ 
    │   └── E2E Regression Pipeline.yml 
└── pom.xml 
```

---

##  How to Run

### Run all tests
```bash
TARGET: "%regex[com.demoblaze.tests.ui.*Test.*]"
run: mvn -Dtest="${TARGET}" clean test
```

### Run on a Chrome browser (headless)
```bash
TARGET: "%regex[com.demoblaze.tests.ui.*Test.*]"
mvn -Dtest="${TARGET}" -DexecutionType="LocalHeadless" -DbrowserType="Chrome" clean test

```

### Run on a Edge browser (headless)
```bash
TARGET: "%regex[com.demoblaze.tests.ui.*Test.*]"
mvn -Dtest="${TARGET}" -DexecutionType="LocalHeadless" -DbrowserType="Edge" clean test
```

---

##  CI/CD Pipeline

The project uses **GitHub Actions** to run the full regression suite automatically on every push or pull request to `master`, and Every Sunday at 9:00 PM.

![E2E Regression](https://github.com/AbdallahMaghwry/Demoblaze_project/actions/workflows/E2E%20Regression%20Pipeline.yml/badge.svg)

| Job | OS | Browser |
|---|---|---|
| Chrome_Linux_Test | Ubuntu Latest | Chrome |
| Edge_Windows_Test | Windows Latest | Edge |

---

## 🛠️ Tech Stack

| Tool | Purpose |
|---|---|
| Java 21 | Programming language |
| TestNG | Test framework |
| Selenium WebDriver 4 | UI automation |
| Allure | Test reporting |
| Log4j2 | Logging |
| Maven | Build & dependency management |
| GitHub Actions | CI/CD |
| Git | Version control |

---

## 👨‍💻 Author

**Abdallah Mohammed Maghwry**  
Junior Test Automation Engineer  
🔗 [LinkedIn](https://www.linkedin.com/in/abdallah-mohammed-maghwry/) | 📧 abdallahm23122001@gmail.com
