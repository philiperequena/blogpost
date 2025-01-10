# Blogging Platform API

This is a simple blogging platform API built with **Spring Boot**, **PostgreSQL**, and **JPA**. It allows users to create blog posts, add comments, and retrieve blog posts with the number of comments associated with them.

## Prerequisites

Before running the project, ensure that you have the following software installed:

### 1. **Java 21+**
This project uses **Java 21** or later. You can download and install the latest version from the [official Java website](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html).

### 2. **Maven**
You will need **Maven** to build and run the project. Maven can be downloaded from the [official Apache Maven website](https://maven.apache.org/download.cgi).

To verify if Maven is already installed, run the following command in your terminal:

```bash
mvn -v
```

### 3. **Docker**
Ensure **Docker** is installed on your machine. You can download Docker from the [official Docker website](https://www.docker.com/products/docker-desktop).

Docker will automatically start the **PostgreSQL** container when you run the Spring Boot application.

## Getting Started

Follow these steps to get your project up and running.

### 1. **Clone the Repository**

Start by cloning the project repository:

```bash
git clone https://github.com/philiperequena/blogpost.git
cd blogpost
```

### 2. **Configure Ports for PostgreSQL and API**

By default, **Spring Boot will automatically start PostgreSQL** in a Docker container when you run the application. If you want to customize the ports for **PostgreSQL** and the **API**, follow these steps:

#### **Change PostgreSQL Port (in Docker)**

The `docker-compose` file used by Spring Boot to run PostgreSQL defaults to port `5433`. If you want to use a different port for PostgreSQL, you can modify the `docker-compose.yml` file.

1. **Modify `docker-compose.yml`**: In the file, change the ports to expose PostgreSQL on a different port if needed.

```yaml
services:
  postgres-phr-blog-post:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=blogpost'
      - 'POSTGRES_PASSWORD=blogpost'
      - 'POSTGRES_USER=blogpost'
    ports:
      - '5433:5432'  # Expose PostgreSQL on port 5433 (on host)
```

This will map port `5433` on your host machine to port `5432` inside the Docker container.

#### **Change the API Port (Spring Boot)**

To change the API port that Spring Boot listens on, update the `application.properties` file.

```properties
server.port=8081  # The API will run on port 8081 (you can modify this if needed)
```

This will change the default Spring Boot API port to `8081` (you can modify this to any port you prefer).

#### **Update PostgreSQL Connection Configuration**

Ensure that the Spring Boot application connects to the correct PostgreSQL instance. The connection settings should match the Docker PostgreSQL container configuration:

```properties
spring.application.name=blogpost
server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:5433/blogpost
spring.datasource.username=blogpost
spring.datasource.password=blogpost

spring.flyway.enabled=true
```

This configuration ensures that Spring Boot connects to PostgreSQL on port `5433` (`localhost:5433`), using the database `blogpost`, with the username and password `blogpost`.

### 3. **Build and Run the Project**

Once you have set up the ports and the database configuration, you can build and run the Spring Boot application.

#### **Build the Project**

To build the project, run:

```bash
mvn clean install
```

#### **Run the Application**

To run the Spring Boot application, execute the following command:

```bash
mvn spring-boot:run
```

This will start the Spring Boot application and automatically run PostgreSQL inside a Docker container. The API will be available at `http://localhost:8081`, and PostgreSQL will be available at `localhost:5433`.

### 4. **Accessing the API**

You can now interact with the API at `http://localhost:8081/api`.

#### Available Endpoints:
- **`GET /api/posts`**: Retrieve a list of all blog posts, including their titles and the number of comments associated with each post.
- **`POST /api/posts`**: Create a new blog post.
- **`GET /api/posts/{id}`**: Retrieve a specific blog post by its ID, including its title, content, and a list of associated comments.
- **`POST /api/posts/{id}/comments`**: Add a new comment to a specific blog post.
- **`GET /api/posts/{id}/comments`**: Retrieve all comments associated with a specific blog post.

### 5. **Testing**

To run unit tests, you can use the following command:

```bash
mvn test
```

### 6. **H2 Console (Optional)**

If you want to inspect the H2 database (used for testing), you can enable the H2 console by setting the following properties in `application-test.properties`:

```properties
spring.h2.console.enabled=true
```

Once enabled, access the console at `http://localhost:8081/h2-console`.

### 7. **Shutting Down the Application and Docker**

To stop the Spring Boot application, press `Ctrl + C` in your terminal.

To stop and remove the Docker containers:

```bash
docker-compose down
```

## Error Handling

The API uses custom exceptions like `NotFoundException` for scenarios where a blog post or comment is not found. Errors are handled globally using `@RestControllerAdvice`.

### Example Error Response:
If a blog post is not found:

```json
{
  "message": "BlogPost not found"
}
```

## Postman Collection

A Postman collection to test the API endpoints is provided in the project root. You can import the collection into Postman to quickly test the API.

## Conclusion

You now have a blogging platform API running locally with **Spring Boot** and **PostgreSQL**. The project includes features for creating, updating and retrieving blog posts and comments, along with proper error handling.

Let me know if you need any further clarifications or improvements!
```

---
