# 🏠 AI-Driven Property Insights Backend

A RESTful microservice built with **Spring Boot** for automating rent analytics and property performance scoring. This backend powers real-time property management dashboards with PostgreSQL integration.

[![Live Demo](https://img.shields.io/badge/Live-Backend-success)](https://property-insights-backend.onrender.com)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)

## 🚀 Live Demo

- **API Base URL**: [https://property-insights-backend.onrender.com](https://property-insights-backend.onrender.com)
- **Properties Endpoint**: [/api/properties](https://property-insights-backend.onrender.com/api/properties)
- **Analytics Endpoint**: [/api/properties/analytics](https://property-insights-backend.onrender.com/api/properties/analytics)

## ✨ Features

- **RESTful API Design** - Full CRUD operations for property management
- **Automated Analytics** - Real-time calculation of revenue, occupancy rates, and performance scores
- **PostgreSQL Integration** - Schema design with JPA/Hibernate ORM for efficient data persistence
- **Performance Scoring** - AI-driven algorithm to score properties based on rent and occupancy
- **CORS Enabled** - Configured for seamless frontend integration
- **Clean Architecture** - Separation of concerns with Controller → Repository → Database pattern

## 🛠️ Tech Stack

- **Backend Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: PostgreSQL 15
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **Deployment**: Render.com (Containerized with Docker)

## 📊 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/properties` | Fetch all properties |
| GET | `/api/properties/{id}` | Fetch single property by ID |
| POST | `/api/properties` | Create new property |
| PUT | `/api/properties/{id}` | Update existing property |
| DELETE | `/api/properties/{id}` | Delete property |
| GET | `/api/properties/analytics` | Get dashboard analytics |

## 📦 Installation & Setup

### Prerequisites
- Java 17+
- Maven 3.6+
- PostgreSQL 15+

### Local Development

1. **Clone the repository**
```bash
git clone https://github.com/HaswanthVIT/property-insights-backend.git
cd property-insights-backend
```

2. **Configure Database**

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/propertydb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Build and Run**
```bash
mvn clean install
mvn spring-boot:run
```

4. **Access API**
```
http://localhost:8080/api/properties
```

## 🗄️ Database Schema

### Property Entity
```java
- id (Long) - Primary Key
- address (String) - Property address
- propertyType (String) - Type (Apartment/House/Studio)
- rent (int) - Monthly rent amount
- occupancy (int) - Occupancy percentage (0-100)
- performanceScore (int) - Calculated performance metric
```

## 🌐 Deployment

Deployed on **Render.com** with:
- Dockerized containerization
- Automated CI/CD from GitHub
- Managed PostgreSQL database
- Environment variable configuration

### Environment Variables
```
DATABASE_URL=postgresql://user:pass@host:5432/dbname
PORT=8080
```

## 📈 Sample Response

**GET /api/properties/analytics**
```json
{
  "totalRevenue": 16500,
  "averageOccupancy": 91.67,
  "averageScore": 92.5,
  "totalProperties": 6,
  "averageRent": 2750
}
```

## 🔧 Key Components

- **PropertyController** - REST API endpoints
- **Property** - JPA Entity model
- **PropertyRepository** - Data access layer
- **DataBootstrap** - Auto-loads sample data on startup
- **WebConfig** - CORS configuration

## 📝 License

MIT License - Feel free to use this project for learning and portfolio purposes.

## 👤 Author

**Haswanth**
- GitHub: [@HaswanthVIT](https://github.com/HaswanthVIT)
- Frontend: [property-insights-frontend](https://github.com/HaswanthVIT/property-insights-frontend)

## 🙏 Acknowledgments

Built as a personal project to demonstrate full-stack development skills with Spring Boot and modern cloud deployment practices.
