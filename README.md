# Library Management System

A robust Spring Boot application for managing library operations including book management, user management, and borrowing functionality. This system provides a complete solution for libraries to digitize their operations and manage their resources efficiently.

## 🎯 Overview

The Library Management System is designed to help libraries:
- Manage their book inventory efficiently
- Handle member registrations and profiles
- Track book borrowing and returns
- Generate reports and analytics
- Implement fine management system
- Send notifications for due dates and overdue books

## 🚀 Technology Stack

- **Java 23** - Latest LTS version for optimal performance
- **Spring Boot 3.2.4** - For building production-ready applications
- **Spring Data JPA** - For simplified data persistence
- **PostgreSQL** - Robust, open-source database
- **Lombok** - For reducing boilerplate code
- **Maven** - For dependency management and build automation
- **Spring Security** - For authentication and authorization
- **Swagger/OpenAPI** - For API documentation
- **JUnit & Mockito** - For unit and integration testing
- **Logback** - For application logging

## 📁 Project Structure

```
src/main/java/com/example/library/
├── config/         # Configuration classes
│   ├── SwaggerConfig.java
│   ├── SecurityConfig.java
│   └── WebConfig.java
├── constant/       # Constants and enums
│   ├── BookStatus.java
│   ├── UserRole.java
│   └── ErrorMessages.java
├── controller/     # REST API controllers
│   ├── BookController.java
│   ├── UserController.java
│   └── BorrowingController.java
├── dto/           # Data Transfer Objects
│   ├── request/
│   └── response/
├── entity/        # JPA entities
│   ├── Book.java
│   ├── User.java
│   └── Borrowing.java
├── exception/     # Custom exceptions
│   ├── GlobalExceptionHandler.java
│   └── CustomExceptions.java
├── model/         # Domain models
├── repository/    # JPA repositories
├── security/      # Security configurations
│   ├── JwtTokenProvider.java
│   └── UserDetailsServiceImpl.java
├── service/       # Business logic
│   ├── impl/
│   └── interfaces/
└── util/          # Utility classes
    ├── DateUtils.java
    └── ValidationUtils.java
```

## 🔧 Dependencies

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- JWT Token -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Swagger UI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.7.0</version>
    </dependency>

    <!-- Testing Dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## ⚙️ Configuration

### Application Properties
```properties
# Application Configuration
spring.application.name=LibraryApplication
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/librarydb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Security Configuration
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000

# Logging Configuration
logging.level.root=INFO
logging.level.com.example.library=DEBUG
```

## 🏗️ Project Components

### 1. Entity Layer
Located in `entity/` directory, contains JPA entities with relationships:

```java
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String author;
    
    @Column(unique = true, nullable = false)
    private String isbn;
    
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Borrowing> borrowings;
    
    @Column(name = "publication_year")
    private Integer publicationYear;
    
    private String publisher;
    private String description;
    private Integer totalCopies;
    private Integer availableCopies;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

### 2. Repository Layer
Advanced repository methods:

```java
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByPublicationYearBetween(Integer startYear, Integer endYear);
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAvailableBooks();
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies = 0")
    List<Book> findUnavailableBooks();
}
```

## 🔒 Security Implementation

### JWT Authentication
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/books/**").hasRole("USER")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
```

## 🌟 Features

### 1. Book Management
- CRUD operations for books
- ISBN validation
- Book availability tracking
- Search by multiple criteria
- Book categorization
- Publication management

### 2. User Management
- Role-based access control (ADMIN, LIBRARIAN, USER)
- User profile management
- Password encryption
- Session management
- Activity logging

### 3. Borrowing System
- Book checkout and return
- Due date management
- Fine calculation
- Reservation system
- Email notifications
- History tracking

### 4. Reports and Analytics
- Popular books report
- Overdue books report
- User activity analysis
- Fine collection reports
- Inventory status

## 🚀 Getting Started

### Prerequisites
- JDK 23
- Maven 3.8+
- PostgreSQL 15+
- Git

### Installation Steps

1. Clone the repository
```bash
git clone [repository-url]
cd library-management-system
```

2. Create PostgreSQL database
```sql
CREATE DATABASE librarydb;
CREATE USER library_db_username WITH PASSWORD '1234';
GRANT ALL PRIVILEGES ON DATABASE librarydb TO library_db_username;
```

3. Configure application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/librarydb
spring.datasource.username=library_db_username
spring.datasource.password=1234
```

4. Build the project
```bash
mvn clean install
```

5. Run the application
```bash
mvn spring-boot:run
```

## 📝 API Documentation

### Available Endpoints

#### Authentication
- POST `/api/auth/signup` - Register new user
- POST `/api/auth/login` - User login
- POST `/api/auth/refresh` - Refresh token

#### Books
- GET `/api/books` - List all books
- GET `/api/books/{id}` - Get book by ID
- POST `/api/books` - Add new book
- PUT `/api/books/{id}` - Update book
- DELETE `/api/books/{id}` - Delete book
- GET `/api/books/search` - Search books

#### Users
- GET `/api/users` - List all users
- GET `/api/users/{id}` - Get user by ID
- PUT `/api/users/{id}` - Update user
- DELETE `/api/users/{id}` - Delete user

#### Borrowings
- POST `/api/borrowings` - Create borrowing
- PUT `/api/borrowings/{id}/return` - Return book
- GET `/api/borrowings/user/{userId}` - Get user borrowings
- GET `/api/borrowings/overdue` - Get overdue borrowings

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify
```

### Test Coverage
```bash
mvn test jacoco:report
```

## 📦 Build and Deployment

### Local Build
```bash
mvn clean package
```

### Docker Build
```bash
docker build -t library-management-system .
docker run -p 8080:8080 library-management-system
```

## 🔍 Logging

The application uses SLF4J with Logback for logging. Logs are written to:
- Console
- `logs/application.log`
- Error logs: `logs/error.log`

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style Guidelines
- Follow Java naming conventions
- Use meaningful variable and method names
- Write unit tests for new features
- Document public APIs
- Keep methods small and focused

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and queries, please open an issue in the repository or contact the maintainers.

## 📚 Interview Preparation Guide

### Core Concepts

#### 1. Spring Boot Fundamentals
- **What is Spring Boot?**
  - Spring Boot is a framework that simplifies Spring application development
  - Provides auto-configuration and embedded server
  - Reduces boilerplate code through "Convention over Configuration"

- **Key Features of Spring Boot**
  - Autoconfiguration
  - Standalone applications
  - Embedded servers (Tomcat, Jetty, or Undertow)
  - Opinionated approach to configuration
  - Production-ready features (metrics, health checks)

- **Spring Boot Annotations**
  ```java
  @SpringBootApplication        // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
  @RestController              // Creates RESTful web services
  @Service                     // Business logic layer
  @Repository                  // Data access layer
  @Component                   // Generic Spring-managed component
  @Autowired                   // Dependency injection
  @Configuration              // Configuration class
  @Bean                       // Method-level annotation for bean definition
  ```

#### 2. JPA (Java Persistence API)
- **Key Concepts**
  - Object-Relational Mapping (ORM)
  - Entity lifecycle (Persist, Merge, Remove, Refresh)
  - JPQL (Java Persistence Query Language)
  - Criteria API

- **Important JPA Annotations**
  ```java
  @Entity                     // Marks class as JPA entity
  @Table                      // Specifies table details
  @Id                         // Primary key
  @GeneratedValue            // Auto-generation strategy
  @Column                     // Column properties
  @OneToMany                  // One-to-many relationship
  @ManyToOne                  // Many-to-one relationship
  @ManyToMany                // Many-to-many relationship
  ```

#### 3. Spring Security
- **Core Concepts**
  - Authentication vs Authorization
  - UserDetailsService
  - SecurityFilterChain
  - JWT (JSON Web Tokens)

- **Security Implementation**
  ```java
  // Authentication Provider
  @Bean
  public AuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(userDetailsService);
      provider.setPasswordEncoder(passwordEncoder());
      return provider;
  }

  // Password Encoder
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  ```

#### 4. RESTful Web Services
- **REST Principles**
  - Stateless
  - Client-Server architecture
  - Uniform interface
  - Cacheable
  - Layered system

- **HTTP Methods**
  ```
  GET     - Retrieve resource
  POST    - Create resource
  PUT     - Update resource
  DELETE  - Remove resource
  PATCH   - Partial update
  ```

- **Status Codes**
  ```
  200 OK              - Successful request
  201 Created         - Resource created
  400 Bad Request     - Client error
  401 Unauthorized    - Authentication required
  403 Forbidden       - Authorization failed
  404 Not Found       - Resource not found
  500 Server Error    - Internal server error
  ```

### Common Interview Questions

#### 1. Spring Boot Questions
1. **Q: What is Spring Boot and how is it different from Spring?**
   - A: Spring Boot is a framework built on top of Spring that simplifies development by:
     - Providing auto-configuration
     - Eliminating boilerplate configuration
     - Including an embedded server
     - Offering production-ready features

2. **Q: Explain Spring Boot's auto-configuration.**
   - A: Auto-configuration automatically configures a Spring application based on:
     - Dependencies on the classpath
     - Properties defined
     - Beans in the context
     - Can be customized or overridden as needed

#### 2. JPA and Database Questions
1. **Q: Explain the difference between @OneToMany and @ManyToOne.**
   - A: 
     - @OneToMany: One entity instance relates to multiple instances of another entity
     - @ManyToOne: Multiple instances of an entity relate to one instance of another entity
     - Example: One Book can have many Borrowings (OneToMany), while many Borrowings relate to one Book (ManyToOne)

2. **Q: What are the different types of entity relationships in JPA?**
   - A:
     - One-to-One (@OneToOne)
     - One-to-Many (@OneToMany)
     - Many-to-One (@ManyToOne)
     - Many-to-Many (@ManyToMany)

#### 3. Security Questions
1. **Q: How does JWT authentication work in this application?**
   - A: JWT authentication follows these steps:
     1. User provides credentials
     2. Server validates and generates JWT
     3. Token is sent back to client
     4. Client includes token in subsequent requests
     5. Server validates token for each request

2. **Q: Explain the difference between Authentication and Authorization.**
   - A:
     - Authentication: Verifies who the user is (login process)
     - Authorization: Determines what resources a user can access (roles and permissions)

#### 4. Architecture Questions
1. **Q: Explain the layered architecture in this application.**
   - A: The application follows a layered architecture:
     - Controller Layer (REST APIs)
     - Service Layer (Business Logic)
     - Repository Layer (Data Access)
     - Entity Layer (Data Model)

2. **Q: What are the benefits of using DTOs?**
   - A: DTOs (Data Transfer Objects):
     - Decouple client interface from internal data structure
     - Control data exposure
     - Optimize network traffic
     - Version API responses

### Design Patterns Used

1. **Repository Pattern**
   - Abstracts data persistence
   - Provides collection-like interface
   - Example: BookRepository

2. **Dependency Injection**
   - Loose coupling
   - Better testability
   - Example: Constructor injection in services

3. **Builder Pattern**
   - Used in entity creation
   - Flexible object construction
   - Example: @Builder in Book entity

4. **DTO Pattern**
   - Data transfer between layers
   - API response shaping
   - Example: BookDTO

### Best Practices Demonstrated

1. **Code Organization**
   - Clear package structure
   - Separation of concerns
   - Modular design

2. **Security**
   - Password encryption
   - JWT token validation
   - Role-based access control

3. **Database**
   - Proper indexing
   - Relationship mapping
   - Transaction management

4. **API Design**
   - RESTful principles
   - Proper HTTP methods
   - Meaningful status codes

### Performance Considerations

1. **Database Optimization**
   - Proper indexing
   - Query optimization
   - Connection pooling

2. **Caching Strategies**
   - Entity caching
   - Query results caching
   - Cache invalidation

3. **N+1 Problem Prevention**
   - Eager vs Lazy loading
   - Join fetching
   - Batch processing 