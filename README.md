# Marvel Character REST API

## Project Description

This project is a RESTful CRUD API developed for CSC 340 at the University of North Carolina at Greensboro. The application manages Marvel superhero characters from my previous Character Gallery website.

The five original characters are Black Panther, Thor, Iron Man, Hulk, and Vision.

The API allows users to create, retrieve, update, delete, filter, and search character records. It uses Spring Boot with Spring Data JPA and a PostgreSQL database hosted on Neon. The application is containerized using Docker and deployed on Render.

## Technologies Used

- Java 25
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA
- Jakarta Bean Validation
- PostgreSQL
- Neon
- Maven
- Docker
- Render
- Postman
- Git and GitHub

## Character Entity

The application uses a `MarvelCharacter` entity mapped to the `characters` database table.

| Attribute | Type | Rules |
|---|---|---|
| characterId | long | Unique, auto-generated primary key |
| name | String | Required, cannot be null or blank |
| description | String | Required, cannot be null or blank; database column length 2000 |
| universe | String | Optional; used for category filtering |
| role | String | Optional; identifies the character's role |

The `name` and `description` fields use `@NotBlank` validation. Character IDs are generated automatically by PostgreSQL.

## Project Structure

The application follows a layered architecture with separate Java files:

- **Entity:** `MarvelCharacter.java` defines the character attributes and database mapping.
- **Repository:** `MarvelCharacterRepository.java` extends JpaRepository and handles database queries.
- **Service:** `MarvelCharacterService.java` implements CRUD operations and handles missing character IDs.
- **Controller:** `MarvelCharacterController.java` defines REST endpoints and handles HTTP requests.

The Controller communicates with the Service, and the Service communicates with the Repository.

## API Endpoints

Base URL:

https://character-rest-api-z043.onrender.com

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/api/characters` | Retrieve all characters | 200 OK |
| GET | `/api/characters/{id}` | Retrieve one character by ID | 200 OK |
| POST | `/api/characters` | Create a new character | 201 Created |
| PUT | `/api/characters/{id}` | Update an existing character | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a character | 204 No Content |
| GET | `/api/characters?universe=Marvel` | Filter characters by universe | 200 OK |
| GET | `/api/characters?name=thor` | Search characters by partial name, case-insensitive | 200 OK |

The universe filter uses a case-insensitive exact match. The name search uses a case-insensitive partial match.

## Example POST Request

**Method:** POST

**Endpoint:** `/api/characters`

**Request Body:**

```json
{
  "name": "Captain America",
  "description": "A super soldier who fights to protect the world.",
  "universe": "Marvel",
  "role": "Avenger"
}
```

**Success Status:** 201 Created

The API saves the new character and generates a unique characterId.

## Example PUT Request

**Method:** PUT

**Endpoint:** `/api/characters/7`

**Request Body:**

```json
{
  "name": "Captain America",
  "description": "Steve Rogers is a super soldier and leader of the Avengers who protects the world with his vibranium shield.",
  "universe": "Marvel",
  "role": "Avengers Leader"
}
```

**Success Status:** 200 OK

The API updates the character associated with the specified ID. The example ID must correspond to an existing character.

## Error Handling and Validation

| Status | Description |
|---|---|
| 400 Bad Request | POST or PUT contains a missing or blank name or description |
| 404 Not Found | A requested character ID does not exist |

The Controller uses `@Valid` to validate POST and PUT request bodies.

The Service throws a `ResponseStatusException` with HTTP 404 when a character cannot be found.

## Database Configuration

The application uses PostgreSQL hosted by Neon.

Spring Data JPA manages persistence, while Hibernate updates the database schema based on the entity.

The database connection is configured using the `SPRING_DATASOURCE_URL` environment variable.

Database credentials are not hardcoded in the application source code.

## Running Locally

Clone the repository:

```bash
git clone https://github.com/mck5ly24/character-rest-api.git
```

Navigate to the project:

```bash
cd character-rest-api
```

Configure `SPRING_DATASOURCE_URL` with a valid PostgreSQL JDBC connection string from Neon.

Run the application:

```bash
./mvnw spring-boot:run
```

The local API is available at:

http://localhost:8080/api/characters

On Windows, the Maven wrapper can also be run using `mvnw.cmd`.

## API Testing

Postman was used to test the API locally.

The following CRUD operations were successfully tested:

- GET: Retrieved Marvel characters from PostgreSQL.
- POST: Created Captain America and received HTTP 201.
- PUT: Updated Captain America's information and received HTTP 200.
- DELETE: Removed Captain America and received HTTP 204.

Additional required test cases include:

- Retrieving a character by ID
- Filtering characters by universe
- Searching characters by partial name
- Requesting a nonexistent ID to verify HTTP 404
- Submitting invalid POST and PUT requests to verify HTTP 400

## Docker and Render Deployment

The application uses a multi-stage Dockerfile to build and run the Spring Boot application.

The Docker image builds the application using the Maven wrapper and starts the generated JAR file.

The application is deployed as a Render Web Service.

The `SPRING_DATASOURCE_URL` environment variable is configured in Render, and the application uses the port assigned by Render.

**Live API:**

https://character-rest-api-z043.onrender.com/api/characters

**Cold Start Note:** Render's free services may spin down after inactivity. The first request after a period of inactivity can take up to a minute or longer while the service starts.

## GitHub Repository

https://github.com/mck5ly24/character-rest-api

The project uses Git and GitHub for version control, with commits documenting the development of the application.

## Course Information

CSC 340 — Software Engineering
University of North Carolina at Greensboro
Fall 2026