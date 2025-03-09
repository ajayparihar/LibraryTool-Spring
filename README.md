# Library Management System

A robust Spring Boot application for managing library operations including book management, user management, and borrowing functionality.

## 🚀 Technology Stack

- **Java 23**
- **Spring Boot 3.2.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Spring Security** (configured)

## 📁 Project Structure

```
src/main/java/com/example/library/
├── config/         # Configuration classes
├── constant/       # Constants and enums
├── controller/     # REST API controllers
├── dto/           # Data Transfer Objects
├── entity/        # JPA entities
├── exception/     # Custom exceptions
├── model/         # Domain models
├── repository/    # JPA repositories
├── security/      # Security configurations
├── service/       # Business logic
└── util/          # Utility classes
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

    <!-- Spring Boot DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## 🏗️ Project Components

### 1. Entity Layer
Located in `entity/` directory, contains JPA entities like:
- Book
- User
- Borrowing
- etc.

Example Entity:
```java
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;
    private String isbn;
    // ... other fields
}
```

### 2. Repository Layer
Located in `repository/` directory, contains JPA repositories extending `JpaRepository`:
```java
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
}
```

### 3. Service Layer
Located in `service/` directory, contains business logic:
```java
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    
    // Constructor injection
    
    public Book saveBook(Book book) {
        // Business logic
        return bookRepository.save(book);
    }
}
```

### 4. Controller Layer
Located in `controller/` directory, contains REST endpoints:
```java
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    
    // Constructor injection
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }
}
```

### 5. DTOs
Located in `dto/` directory, used for request/response objects:
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
}
```

## 🔒 Security Configuration

The application uses Spring Security for authentication and authorization. Security configurations are located in the `security/` directory.

## 🌟 Features

1. Book Management
   - Add/Update/Delete books
   - Search books by various criteria
   - Track book availability

2. User Management
   - User registration and authentication
   - Role-based access control
   - User profile management

3. Borrowing System
   - Borrow and return books
   - Track due dates
   - Handle overdue notifications

## 🚀 Getting Started

1. Clone the repository
```bash
git clone [repository-url]
```

2. Configure PostgreSQL database in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## 📝 API Documentation

The API documentation will be available at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

## 🧪 Testing

Run tests using:
```bash
mvn test
```

## 📦 Build

Create a production build:
```bash
mvn clean package
```

The JAR file will be generated in the `target/` directory.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details. 