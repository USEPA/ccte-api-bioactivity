# Bioactivity Microservice (ccte-api-bioactivity)

## Overview
The Bioactivity Microservice is a RESTful API designed to manage and provide bioactivity data. It interacts with a PostgreSQL database and offers various endpoints for bioactivity-related operations.

## Available Data
Data underlying the Bioactivity API comes from ToxCast’s invitrodb. US EPA’s Toxicity Forecaster (ToxCast) program makes medium- and high-throughput bioactivity screening assay data publicly available for prioritization and hazard characterization.

## Technologies Used
- **Java**: Programming language used for the development of the application.
- **Spring Boot**: Framework used to create stand-alone, production-grade Spring-based applications.
- **Spring Data JPA**: Part of the Spring Data family, used to simplify data access and persistence.
- **Spring Data REST**: Used to expose Spring Data repositories as RESTful web services.
- **PostgreSQL**: Relational database used for storing bioactivity data.
- **Maven**: Build automation tool used for managing project dependencies and build lifecycle.
- **Lombok**: Java library used to reduce boilerplate code.
- **MapStruct**: Code generator used to simplify the implementation of mappings between Java bean types.
- **Swagger/OpenAPI**: Used for API documentation and testing.
- **Testcontainers**: Java library used for integration testing with Docker containers.

## Configuration
The application configuration is managed through YAML files. The main configuration file is `application.yml`, and environment-specific configurations are in files like `application-local.yml`.

## Build and Deployment
The project uses Maven for build and deployment processes. Continuous integration and deployment can be set up using tools like GitHub Actions.

## Getting Started
To build and run the project locally, use the following Maven commands:

```sh
mvn clean install
mvn spring-boot:run
```

## Project Structure
```plaintext
src/
├── main/
│   ├── java/
│   │   └── gov/epa/ccte/api/bioactivity/
│   │       ├── projection/
│   │       │   └── assay/
│   │       ├── repository/
│   │       ├── service/
│   │       └── web/
│   │           └── rest/
│   └── resources/
│       └── application.properties
└── test/
```

## Dependencies
- `spring-boot-starter-web`
- `spring-boot-starter-data-rest`
- `spring-boot-starter-data-jpa`
- `postgresql`
- `lombok`
- `springdoc-openapi-starter-webmvc-ui`
- `mapstruct`
- `spring-boot-starter-test`
- `spring-boot-testcontainers`
- `testcontainers`
- `jackson-datatype-hibernate6`


## Contributing
Contributions are welcome! Please read the `CONTRIBUTING.md` file for guidelines.

## Contact
For any inquiries, please contact the project maintainers.

## Disclaimer
The United States Environmental Protection Agency (EPA) GitHub project code is provided on an "as is" basis and the user assumes responsibility for its use. EPA has relinquished control of the information and no longer has responsibility to protect the integrity, confidentiality, or availability of the information. Any reference to specific commercial products, processes, or services by service mark, trademark, manufacturer, or otherwise, does not constitute or imply their endorsement, recommendation or favoring by EPA. The EPA seal and logo shall not be used in any manner to imply endorsement of any commercial product or activity by EPA or the United States Government. 