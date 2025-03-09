# Library Management System

A Spring Boot application for managing library operations including book management and lender tracking. This system provides a solution for libraries to digitize their basic operations.

## 🎯 Overview

The Library Management System is designed to help libraries:
- Manage their book inventory
- Track book lending operations
- Handle basic CRUD operations for books and lenders
- Provide a RESTful API interface
- Document API endpoints using Swagger/OpenAPI

## 🏗️ Architecture & Design

### Layered Architecture
The application follows a clean layered architecture:
1. **Presentation Layer (Controllers)**
   - REST endpoints for client interaction
   - Request/Response DTOs for data transfer
   - Input validation
   - Error handling through Global Exception Handler

2. **Business Layer (Services)**
   - Core business logic implementation
   - Transaction management
   - Data validation and processing
   - Business rules enforcement

3. **Data Access Layer (Repositories)**
   - Database operations through JPA
   - Entity management
   - Data persistence logic

4. **Domain Layer (Entities & DTOs)**
   - Business entities (Book, Lender)
   - Data Transfer Objects for API communication
   - Domain-specific validation rules

### Design Patterns
- **DTO Pattern**: Separates API contracts from domain models
- **Repository Pattern**: Abstracts data access logic
- **Dependency Injection**: Promotes loose coupling
- **Builder Pattern**: For object construction (via Lombok)
- **Factory Pattern**: For object creation where needed

## 🔧 Technical Implementation

### Code Organization
```
src/main/java/com/example/library/
├── config/         # Configuration classes
│   └── OpenAPIConfig.java
├── constant/       # Constants and enums
│   └── LibraryConstants.java
├── controller/     # REST API controllers
│   ├── BookController.java
│   └── LenderController.java
├── dto/           # Data Transfer Objects
│   ├── BookRequestDTO.java
│   └── BookResponseDTO.java
├── entity/        # JPA entities
│   ├── Book.java
│   └── Lender.java
├── exception/     # Custom exceptions
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── ErrorResponse.java
├── repository/    # JPA repositories
│   ├── BookRepository.java
│   └── LenderRepository.java
├── service/       # Business logic
│   ├── BookService.java
│   └── LenderService.java
└── util/          # Utility classes
    └── BookMapper.java
```

### Code Quality & Best Practices
1. **Clean Code Principles**
   - Meaningful naming conventions
   - Single Responsibility Principle
   - DRY (Don't Repeat Yourself)
   - SOLID principles adherence

2. **Error Handling**
   - Global exception handling
   - Custom exceptions for business cases
   - Proper error messages and status codes
   - Structured error responses

3. **Validation**
   - Input validation at DTO level
   - Business validation in service layer
   - Database constraints

4. **Documentation**
   - OpenAPI/Swagger documentation
   - Code comments for complex logic
   - README and setup instructions
   - API endpoint documentation

### Annotations Guide
1. **Spring Framework Annotations**
   ```java
   @SpringBootApplication     // Main application class
   @Configuration            // Configuration classes
   @RestController           // REST API controllers
   @Service                  // Service layer components
   @Repository              // Data access layer components
   @Component               // Generic Spring components
   @Autowired              // Dependency injection
   @Qualifier              // Specific bean selection
   @Value                  // Property injection
   ```

2. **JPA Annotations**
   ```java
   @Entity                 // JPA entity classes
   @Table                  // Database table mapping
   @Id                     // Primary key
   @GeneratedValue        // Auto-generation strategy
   @Column                // Column mapping
   @Transient            // Non-persistent field
   @OneToMany            // One-to-many relationship
   @ManyToOne            // Many-to-one relationship
   @JoinColumn           // Foreign key mapping
   ```

3. **Validation Annotations**
   ```java
   @NotNull              // Field must not be null
   @NotEmpty             // Collection must not be empty
   @NotBlank             // String must not be blank
   @Size                 // Size constraints
   @Min                  // Minimum value
   @Max                  // Maximum value
   @Pattern              // Regex pattern matching
   @Email               // Email format validation
   ```

4. **Lombok Annotations**
   ```java
   @Data                 // Getters, setters, equals, hashCode, toString
   @Builder              // Builder pattern implementation
   @NoArgsConstructor   // No-args constructor
   @AllArgsConstructor  // All-args constructor
   @Getter              // Getter methods
   @Setter              // Setter methods
   @Slf4j               // Logger field
   ```

5. **Documentation Annotations**
   ```java
   @Api                  // Swagger API documentation
   @ApiOperation        // API operation documentation
   @ApiParam            // API parameter documentation
   @ApiResponse         // API response documentation
   ```

### Code Comments & Documentation

1. **Class-Level Documentation**
   ```java
   /**
    * Represents a book entity in the library system.
    * This class handles the book's basic information and relationships.
    *
    * @author [Author Name]
    * @version 1.0
    * @since 2024-03-21
    */
   @Entity
   public class Book {
       // Class implementation
   }
   ```

2. **Method-Level Documentation**
   ```java
   /**
    * Retrieves a book by its unique identifier.
    *
    * @param id The unique identifier of the book
    * @return The book if found
    * @throws ResourceNotFoundException if book not found
    */
   public Book getBookById(Long id) {
       // Method implementation
   }
   ```

3. **Field Documentation**
   ```java
   /** The unique identifier for the book */
   @Id
   private Long id;

   /** The title of the book - must not be empty */
   @NotBlank
   private String title;
   ```

4. **Implementation Comments**
   ```java
   // Calculate late fees based on days overdue
   int daysOverdue = calculateDaysOverdue(returnDate);
   
   /* Complex business logic explanation
    * 1. Check if book is available
    * 2. Validate user eligibility
    * 3. Process lending transaction
    */
   ```

### Code Organization Conventions

1. **Package Structure**
   ```
   com.example.library
   ├── annotation/     # Custom annotations
   │   └── Auditable.java
   ├── aspect/        # AOP aspects
   │   └── LoggingAspect.java
   ├── config/        # Configurations
   ├── constant/      # Constants
   ├── controller/    # Controllers
   ├── dto/           # DTOs
   ├── entity/        # Entities
   ├── exception/     # Exceptions
   ├── repository/    # Repositories
   ├── service/       # Services
   └── util/          # Utilities
   ```

2. **Class Structure**
   ```java
   public class BookService {
       // Constants
       private static final int MAX_BOOKS = 5;
       
       // Dependencies
       private final BookRepository bookRepository;
       
       // Constructors
       public BookService(BookRepository bookRepository) {
           this.bookRepository = bookRepository;
       }
       
       // Public methods
       public Book findBook(Long id) { }
       
       // Private helper methods
       private void validateBook(Book book) { }
   }
   ```

3. **Naming Conventions**
   - Classes: PascalCase (BookService)
   - Methods: camelCase (findBookById)
   - Variables: camelCase (bookRepository)
   - Constants: UPPER_SNAKE_CASE (MAX_BOOKS)
   - Packages: lowercase (com.example.library)

## 🚀 Technology Stack

### Core Technologies & Their Benefits

1. **Java 23**
   - Latest LTS version providing modern language features
   - Benefits:
     - Pattern matching for switch expressions
     - Record patterns and classes
     - Virtual threads for improved scalability
     - Enhanced string templates
     - Improved garbage collection
   - Potential Enhancements:
     - Upgrade to future LTS versions
     - Utilize more Java 23 specific features
     - Implement virtual threads for async operations

2. **Spring Boot 3.2.4**
   - Modern, production-ready framework
   - Benefits:
     - Auto-configuration
     - Embedded server
     - Production-ready metrics
     - Easy dependency management
     - Rich ecosystem of starters
   - Potential Enhancements:
     - Implement Spring Security
     - Add Spring Actuator for monitoring
     - Implement caching with Spring Cache
     - Add Spring Cloud for microservices

3. **Spring Data JPA**
   - Simplified data access layer
   - Benefits:
     - Reduced boilerplate code
     - Automatic query generation
     - Pagination and sorting support
     - Auditing support
     - Transaction management
   - Potential Enhancements:
     - Implement query optimization
     - Add custom repositories
     - Implement specification pattern
     - Add query caching

4. **PostgreSQL**
   - Robust, open-source database
   - Benefits:
     - ACID compliance
     - JSON support
     - Full-text search
     - Concurrent access
     - Rich data types
   - Potential Enhancements:
     - Implement connection pooling (HikariCP)
     - Add database indexing strategy
     - Implement partitioning
     - Set up replication

5. **Lombok**
   - Boilerplate code reduction
   - Benefits:
     - Cleaner code
     - Reduced development time
     - Less error-prone
     - Better maintainability
   - Potential Enhancements:
     - Custom Lombok annotations
     - Builder pattern implementation
     - Add validation annotations

6. **Maven**
   - Dependency management and build tool
   - Benefits:
     - Declarative dependencies
     - Standardized build lifecycle
     - Rich plugin ecosystem
     - Dependency scope management
   - Potential Enhancements:
     - Custom Maven plugins
     - Multi-module project structure
     - Build profiles for different environments

7. **SpringDoc OpenAPI**
   - API documentation
   - Benefits:
     - Interactive API documentation
     - API testing capability
     - Standards compliance
     - Easy integration
   - Potential Enhancements:
     - Custom documentation templates
     - Security documentation
     - Response examples
     - API versioning

8. **JUnit & Testing Framework**
   - Testing infrastructure
   - Benefits:
     - Comprehensive testing support
     - Assertion libraries
     - Mocking capabilities
     - Test lifecycle management
   - Potential Enhancements:
     - Add TestContainers for integration tests
     - Implement BDD with Cucumber
     - Add performance tests with JMeter
     - Code coverage with JaCoCo

### Additional Technologies to Consider

1. **Security Stack**
   - Spring Security
   - JWT Authentication
   - OAuth2
   - Role-based access control

2. **Caching Solutions**
   - Redis
   - Caffeine
   - Hazelcast
   - EhCache

3. **Monitoring & Observability**
   - Spring Actuator
   - Prometheus
   - Grafana
   - ELK Stack

4. **DevOps Tools**
   - Docker
   - Kubernetes
   - Jenkins/GitHub Actions
   - SonarQube

### Dependencies & Versions

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- Latest version from Spring Boot parent -->
        <!-- Provides: -->
        <!-- - Embedded Tomcat -->
        <!-- - Spring MVC -->
        <!-- - Jackson for JSON -->
        <!-- - Logging -->
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <!-- Provides: -->
        <!-- - Hibernate ORM -->
        <!-- - Connection pooling -->
        <!-- - Transaction management -->
        <!-- - JPA repositories -->
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
        <!-- Latest version compatible with Spring Boot -->
        <!-- Features: -->
        <!-- - JDBC compliance -->
        <!-- - Connection pooling -->
        <!-- - SSL support -->
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
        <!-- Features: -->
        <!-- - @Data annotation -->
        <!-- - @Builder support -->
        <!-- - @Slf4j logging -->
    </dependency>

    <!-- SpringDoc OpenAPI UI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
        <!-- Features: -->
        <!-- - Swagger UI -->
        <!-- - OpenAPI 3.0 -->
        <!-- - API documentation -->
    </dependency>

    <!-- Recommended Additional Dependencies -->
    
    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Technology Upgrade Path

1. **Short-term Improvements**
   - Add Spring Security
   - Implement connection pooling
   - Add validation framework
   - Implement basic monitoring

2. **Mid-term Enhancements**
   - Implement caching
   - Add message queuing
   - Set up CI/CD pipeline
   - Add comprehensive testing

3. **Long-term Evolution**
   - Microservices architecture
   - Container orchestration
   - Advanced monitoring
   - Performance optimization

## 🧪 Testing Strategy

### Unit Testing
- Service layer testing with JUnit
- Mocking dependencies using Mockito
- Testing business logic and edge cases
- Validation testing

### Integration Testing
- Testing API endpoints
- Database integration tests
- End-to-end flow testing
- Configuration testing

### Test Coverage
- Unit test coverage for business logic
- Integration tests for API endpoints
- Error handling scenarios
- Edge cases and validation

## 📈 Scalability & Performance

### Scalability Features
1. **Horizontal Scalability**
   - Stateless application design
   - RESTful architecture
   - Database connection pooling
   - Containerization-ready

2. **Performance Optimization**
   - JPA query optimization
   - Proper indexing strategy
   - Connection pooling
   - Lazy loading where appropriate

### Future Scalability Considerations
- Caching implementation (e.g., Redis)
- Message queuing for async operations
- Load balancing
- Microservices architecture

## 🔌 Frontend Integration

### API Design
- RESTful endpoints
- Consistent response formats
- Proper HTTP status codes
- CORS configuration

### Integration Points
1. **API Endpoints**
   ```
   Books:
   GET    /books      - List all books
   GET    /books/{id} - Get book by ID
   POST   /books      - Add new book
   PUT    /books/{id} - Update book
   DELETE /books/{id} - Delete book

   Lenders:
   GET    /lenders      - List all lenders
   GET    /lenders/{id} - Get lender by ID
   POST   /lenders      - Add new lender
   DELETE /lenders/{id} - Delete lender
   ```

2. **Response Format**
   ```json
   {
     "data": {
       // Response data
     },
     "message": "Success message",
     "timestamp": "2024-03-21T10:00:00Z"
   }
   ```

3. **Error Format**
   ```json
   {
     "message": "Error message",
     "details": "Detailed error description",
     "timestamp": "2024-03-21T10:00:00Z"
   }
   ```

## ⚙️ Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/librarydb
spring.datasource.username=library_db_username
spring.datasource.password=1234

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Swagger UI Configuration
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
```

## 🛠️ Maintainability

### Code Maintenance
1. **Version Control**
   - Git for source control
   - Meaningful commit messages
   - Branch strategy for features/fixes

2. **Documentation**
   - In-code documentation
   - API documentation
   - Setup and deployment guides

3. **Code Quality Tools**
   - Lombok for boilerplate reduction
   - OpenAPI for API documentation
   - JUnit for testing

### Development Workflow
1. **Local Development**
   - Clone repository
   - Configure database
   - Run application
   - Access Swagger UI

2. **Testing**
   - Run unit tests
   - Run integration tests
   - Manual API testing

3. **Deployment**
   - Build application
   - Configure environment
   - Deploy and verify

## 🚀 Getting Started

### Prerequisites
- JDK 23
- Maven 3.8+
- PostgreSQL
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
   ```

3. Configure application.properties with your database credentials

4. Build the project
   ```bash
   mvn clean install
   ```

5. Run the application
   ```bash
   mvn spring-boot:run
   ```

6. Access the API documentation
   ```
   http://localhost:8080/swagger-ui.html
   ```

## 📄 License

This project is licensed under the MIT License. 