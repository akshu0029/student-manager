\# 🎓 Student Manager



A full-stack Student Management System built with Spring MVC, MySQL, and Thymeleaf.



\## 🚀 Features



\- ✅ Login / Logout (Spring Security)

\- ✅ Add, Edit, Delete Students (Full CRUD)

\- ✅ Photo Upload

\- ✅ Welcome Email (Gmail SMTP)

\- ✅ Search by Name

\- ✅ Sort by Name / Email

\- ✅ Pagination

\- ✅ Dashboard (Student Count)

\- ✅ REST API (JSON)

\- ✅ Spring Boot Actuator (Monitoring)

\- ✅ Modern Gradient UI



\## 🛠️ Technologies Used



| Technology | Purpose |

|---|---|

| Spring Boot 4.0.6 | Backend framework |

| Spring MVC | Web layer |

| Spring Security | Authentication |

| Spring Data JPA | Database layer |

| Thymeleaf | Template engine |

| MySQL | Database |

| Maven | Build tool |

| Java 17 | Programming language |



\## ⚙️ Setup Instructions



\### Prerequisites

\- Java 17

\- Maven

\- MySQL 8.0



\### Steps



1\. Clone the repository:

&#x20;  git clone https://github.com/akshu0029/student-manager.git



2\. Create MySQL database:

&#x20;  CREATE DATABASE studentdb;



3\. Update application.properties:

&#x20;  spring.datasource.password=your\_mysql\_password

&#x20;  spring.mail.username=your\_gmail

&#x20;  spring.mail.password=your\_app\_password



4\. Run the app:

&#x20;  mvn spring-boot:run



5\. Visit:

&#x20;  http://localhost:8080



\## 🔐 Default Login



| Username | Password | Role |

|---|---|---|

| admin | admin123 | ADMIN |

| user | user123 | USER |



\## 📡 REST API Endpoints



| Method | URL | Description |

|---|---|---|

| GET | /api/students | Get all students |

| GET | /api/students/{id} | Get one student |

| POST | /api/students | Add student |

| PUT | /api/students/{id} | Update student |

| DELETE | /api/students/{id} | Delete student |



\## 📊 Actuator Endpoints



| URL | Description |

|---|---|

| /actuator/health | App health status |

| /actuator/info | App info |

| /actuator/metrics | App metrics |



\## 👨‍💻 Author



Akshay Chavare

GitHub: https://github.com/akshu0029

