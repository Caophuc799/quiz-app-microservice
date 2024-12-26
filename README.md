# quiz-app-microservice
This application is designed using a microservices architecture. It leverages the following components:
- Eureka Discovery Client: For service discovery.
- OpenFeign: To communicate between service.
- API Gateway: To handle client requests and route them to the appropriate target services.


## Project Structure
The project consists of the following microservices:

- service-registry: Manages service registration and discovery using Eureka.
- quiz-service: Responsible for handling quiz-related operations.
- question-service: Manages question-related operations.
- api-gateway: Serves as the entry point for client requests and routes them to the appropriate microservices.
