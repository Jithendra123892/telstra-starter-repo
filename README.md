# Telstra SIM Activation Service

This repository contains a Spring Boot application developed as part of the Telstra Software Engineering program. The application provides a RESTful API for managing SIM activations, interacting with an external actuator service, and persisting activation records.

## Key Features

*   **SIM Activation API:** Exposes a `/api/sim/activate` endpoint to initiate SIM activation requests.
*   **Activation Status Tracking:** Records the status of each SIM activation (SUCCESS/FAILED) along with a descriptive message.
*   **External Service Integration:** Communicates with an external actuator service (`http://localhost:8444/actuate`) to process SIM activation requests.
*   **Data Persistence:** Stores SIM activation records in an H2 in-memory database using Spring Data JPA.
*   **Retrieve All Activations:** Provides a `/api/sim/all` endpoint to retrieve a list of all recorded SIM activations.

## Technologies Used

*   **Java 17**
*   **Spring Boot 3.0.13**
*   **Spring Data JPA**
*   **H2 Database**
*   **Maven**
*   **Mockito** (for testing)
*   **JaCoCo** (for code coverage)

## Project Structure

The project is organized into several modules, representing different tasks within the Telstra program. The core SIM activation logic is primarily located in the `Task4` module.

```
telstra-starter-repo/
├── Task1/
├── Task2/
├── Task3/
├── Task4/                  # Main SIM Activation Service
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/telstra/sim/ # Core application code
│   │   │   └── resources/
│   │   └── test/
│   │       └── java/com/telstra/sim/ # Unit and Integration Tests
│   └── pom.xml             # Maven build file for Task4
├── .gitignore
├── mvnw
├── mvnw.cmd
└── README.md               # This file
```

## Getting Started

### Prerequisites

*   Java 17
*   Maven
*   An external actuator service running on `http://localhost:8444` (e.g., a mock service or the provided `sim-actuator-service` if available).

### Building the Application

Navigate to the `Task4` directory and build the project using Maven:

```bash
cd Task4
mvn clean install
```

### Running the Application

After building, you can run the application from the `Task4` directory:

```bash
java -jar target/sim-activation-1.0.0.jar
```

The application will start on port `8080` by default.

### Running Tests

To run all tests, including unit and integration tests, navigate to the `Task4` directory and execute:

```bash
mvn test
```

## API Endpoints

All endpoints are prefixed with `/api/sim`.

*   **`POST /api/sim/activate`**
    *   **Description:** Activates a new SIM card.
    *   **Request Body (JSON):**
        ```json
        {
          "iccid": "string",
          "customerEmail": "string"
        }
        ```
    *   **Response (JSON):** Returns the `SimActivation` object with status and message.

*   **`GET /api/sim/all`**
    *   **Description:** Retrieves a list of all recorded SIM activations.
    *   **Response (JSON):** An array of `SimActivation` objects.

## License

This project is licensed under the MIT License.