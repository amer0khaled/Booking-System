**Overview**

The Booking System is a Java-based application designed to manage bookings and reservations. It provides a robust platform for handling various booking scenarios with security features and database persistence.

-------------------------------

**Technology Stack**

    Spring Boot 3.4.5
    Spring Data JPA for database operations
    Spring Security for authentication and authorization
    Spring Web for RESTful API development
    PostgreSQL for database storage
    
-------------------------------------

**System Architecture**

The Booking System follows a layered architecture pattern that separates concerns and promotes maintainability:

*Key Components*

    API Layer: 
        Contains REST controllers that handle HTTP requests and responses
        Includes controllers for users, bookings, flights, and hotels
        Uses Spring Web for RESTful API development
        Implements global exception handling
        
    Service Layer: 
        Contains business logic and orchestrates operations
        Implements services for each domain entity
        Handles validation, transformation, and business rules
        
    Data Access Layer: 
        Manages data persistence and retrieval
        Uses Spring Data JPA repositories
        Includes repositories like BookingRepository
        Contains mappers to convert between entities and DTOs BookingMapperImpl.java:9-10
        
    Domain Layer: 
        Contains the core business entities and DTOs
        Defines entities like User, Booking, Flight, and Hotel
        Includes DTOs for data transfer between layers
        Contains enums for status and roles
        
    Infrastructure: 
        Provides cross-cutting concerns
        Includes security configuration using Spring Security
        Manages database connections to PostgreSQL

------------------------------------------------

**Database Design**


![image](https://github.com/user-attachments/assets/64bb9f9e-d2c4-4529-843d-e18429eb11db)


----------------------------------------

**Entity Details**

    User
        Represents system users with authentication details
        Contains fields for identification, authentication, and authorization
        Includes user role for permission management
        
    Booking
        Central entity that connects users with their hotel and flight bookings
        Tracks booking details including dates, prices, and payment status BookingRequestDto.java:8-15
        References both Hotel and Flight entities
        
    Hotel
        Stores information about available accommodations
        Managed through the HotelRepository
        Includes details like name, address, room count, and pricing
        
    Flight
        Contains flight information for bookings
        Includes details about airlines, airports, times, and pricing
        Tracks seat availability

**Data Transfer Objects (DTOs)**

The system uses DTOs to transfer data between layers:

    Request DTOs: Used for incoming data (e.g., UserRequestDto, BookingRequestDto)
    Response DTOs: Used for outgoing data (e.g., UserResponseDto, FlightDto)
    Update DTOs: Used for update operations (e.g., UserUpdateDto)

**Exception Handling**

The system implements a robust exception handling mechanism:

    Custom exceptions like BadRequestException
    Global exception handling for consistent error responses

------------------------------------------

**Request Flow**

![image](https://github.com/user-attachments/assets/c6aa5638-b040-48c8-8de7-7bdae8e1b850)

-------------------------------------

**Installation and Setup**
Clone the Repository

    git clone https://github.com/amer0khaled/Booking-System.git  
    cd Booking-System

**Build the Project**

Using Maven Wrapper (recommended):

    # On Linux/Mac  
    ./mvnw clean install  
      
    # On Windows  
    mvnw.cmd clean install

**Run the Application**

    # On Linux/Mac  
    ./mvnw spring-boot:run  
      
    # On Windows  
    mvnw.cmd spring-boot:run

-----------------------------------------

**Project Structure**

The project follows standard Spring Boot application structure:

    Booking-System/  
    ├── .mvn/wrapper/               # Maven wrapper configuration  
    ├── src/  
    │   ├── main/  
    │   │   ├── java/               # Java source files  
    │   │   │   └── com/  
    │   │   │       └── amerkhaled/  
    │   │   │           └── bookingsystem/  
    │   │   └── resources/          # Application resources  
    │   │       └── application.properties  # Application configuration  
    │   └── test/                   # Test files  
    ├── .gitignore                  # Git ignore file  
    ├── mvnw                        # Maven wrapper script for Unix  
    ├── mvnw.cmd                    # Maven wrapper script for Windows  
    ├── pom.xml                     # Maven project configuration  
    └── README.md                   # This file  

---------------------------------------------------

**Configuration**

The primary application configuration is in the application.properties file. Key configurations include:

    Database connection settings
    JPA/Hibernate configurations
    Application-specific settings

--------------------------------------------------

**License**

This project is licensed under the MIT License.



        
