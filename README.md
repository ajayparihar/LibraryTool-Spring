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