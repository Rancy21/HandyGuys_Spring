# HandyGuys

A Spring Boot application for connecting users with handy service providers.

## Overview

HandyGuys is a platform that connects people who need help with local handy professionals. Users can register as either regular users seeking services or as "handy" providers offering skills and services.

## Features

- **User Management**: Registration, authentication, profile management
- **Skill Management**: Handy users can add their skills and services
- **Appointments**: Schedule appointments between clients and handy providers
- **Messaging**: Chat and conversation system between users
- **Reviews & Ratings**: Rate and review handy providers
- **Email Notifications**: OTP-based email verification and password reset

## Tech Stack

- **Framework**: Spring Boot
- **Language**: Java
- **Database**: JPA/Hibernate (configurable)
- **Build Tool**: Maven

## Project Structure

```
src/main/java/com/handy/web/HandyGuys/
├── HandyGuysApplication.java    # Main application entry point
├── Controller/                  # REST API controllers
│   ├── UserController.java
│   ├── SkillController.java
│   ├── AppointmentController.java
│   ├── ChatController.java
│   ├── ConversationController.java
│   ├── LoginTrackerController.java
│   └── ReviewController.java
├── Models/                      # JPA entities
│   ├── User.java
│   ├── Skill.java
│   ├── Appointment.java
│   ├── Chat.java
│   ├── Conversation.java
│   ├── Review.java
│   ├── Rating.java
│   ├── LoginTracker.java
│   └── SignUpTracker.java
├── service/                     # Business logic
│   ├── UserService.java
│   ├── SkillService.java
│   ├── EmailService.java
│   └── ...
├── repository/                 # Data access layer
└── config/
    └── WebConfig.java
```

## API Endpoints

### Users
- `POST /users/saveUser` - Register a new user
- `GET /users/getUser?email={email}` - Get user by email
- `POST /users/updateUser?email={email}` - Update user profile
- `POST /users/updatePassword?email={email}` - Update password
- `POST /users/deleteUser?email={email}` - Delete user
- `GET /users/gethelpers` - Get all handy providers
- `GET /users/getAllUsers` - Get all users
- `GET /users/sendOTPbyEmail?to={email}&condition={login|reset}` - Send OTP via email
- `GET /users/getLatestSignUpList` - Get latest signups

## Building the Project

```bash
./mvnw clean install
```

## Running the Application

```bash
./mvnw spring-boot:run
```

## Configuration

Configure your database and email settings in `application.properties` (to be created in `src/main/resources/`).

## Models

- **User**: Contains user details including name, email, phone, password, and whether they are a handy provider
- **Skill**: Skills/services offered by handy providers
- **Appointment**: Scheduled appointments between users and providers
- **Conversation**: Chat conversations between users
- **Chat**: Individual chat messages
- **Review**: Reviews for handy providers
- **Rating**: Ratings for services