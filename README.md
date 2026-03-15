# RestoranPlanner

## Prerequisites

To run this project, make sure you have the following installed:  

**Java 25 (or higher)** for the backend (Spring Boot)   
Verify that it is installed with:
```
java -version
```
If not install it from the [official website](https://www.oracle.com/java/technologies/downloads/)  

**Maven** (to build and run the backend)   
Verify that it is installed with:
```
mvn -v
```
If not install it from the [official website](https://maven.apache.org/download.cgi)    

**npm** (for the frontend)   
Verify that it is installed with:
```
npm -v
```
If not install it from the [official website](https://nodejs.org/)    

**PostgreSQL** (database for the backend)   
Verify that it is installed with:
```
psql --version
```
If not install it from the [official website](https://www.postgresql.org/download/)    

**Git** (for version control)   
Verify that it is installed with:
```
git --version
```
If not install it from the [official website](https://git-scm.com/downloads)  

## Cloning the project

Clone the project to your local machine by running this command:
```
git clone https://github.com/Asttar/RestoranPlanner
```

## Running the backend

Run the following commands to open the psql terminal and create the database for the backend (Replace postgres with your PostgreSQL username if it differs.):
```
psql -U postgres
```

```
CREATE DATABASE seats_db;
```

Navigate to **src/main/resources/application.properties** and update the following lines with your database's username and password:  
**spring.datasource.username=\<your-db-username\>**  
**spring.datasource.password=\<your-db-password\>** 


Then in the folder where the project is cloned navigate to the backend and start it:
```
cd RestoranPlanner/backend
```
```
mvn spring-boot:run
```

## Running the frontend

In the folder where the project is cloned navigate to the frontend and start it:


```
cd RestoranPlanner/frontend
```
```
npm install
```
```
npm run dev
```

The website will be running on http://localhost:8080

## Documentation

## Project Overview
RestoranPlanner is a web application that allows users to search and reserve a table in a restaurant, filter results based on different criteria (area, date, time, number of people and area specific filters), and receive seat recommendations. The backend is built using **Spring Boot** and a **PostgreSQL** database, while the frontend is built with **Vue**.

## Features
- **Table Search:** Users can search for a table to reserve.
- **Filtering Options:** Users can filter the table for reservation based on:
  - Area
  - Date
  - Time
  - Number of people
  - More filters based on the area the user chose
- **Daily special recommendation:** User recives a daily special meal recommendation based on the date.
- **Table Recommendation System:** When choosing a table, the system suggests the table based on:
  - The number of people (most seat effective option availble)
  - Area specific preferences chosen by the user (prioritised over the last one)
- **Viewing your booking:** User can go to the "View Booked" subpage to view all the bookings that have been made.
- **Information about the restaurant and booking:** User can go to the "About" subpage to view the opening times and read information about the restaurant.

## Technologies Used
- **Vue** for frontend
- **Java Spring Boot** for backend
- **PostgreSQL** for database

## How It Works
1. The user lands on the homepage and enters their preferred area, date, time and number of people. The user also enters their name and phone number for booking. User can book up to 30 days ahead. There are fewer times avaible for booking on the weekends as per closing times
2. The user is shown a table plan based on the chosen area.
3. The tables on the plan are colored based on their availibility. Red for a currently booked table, orange for a table that will be booked in the span of the next 2 hours and is therefore not available for booking, green for available tables and yellow for a table chosen for the user based on the numenbr of people and area specific filters chosen later.
4. The user can then choose area specific preferences and the recommendation is modified to match that.
5. The user can click the "Book table" button to confirm their booking.
6. The user can then navigate to the "View Booked" subpage to view all booked times.

### Time Taken to Complete
- The project took approximately **30 hours** to complete.  

### Challenges Faced
- Had an issue where the filters weren't properly reset when no available seats matched the selected filters, or when the number of tickets exceeded 10.  
  → **Solution:** Using ChatGPT's recommended `this.$nextTick(() => {` fixed the problem.  
- Struggled with design choices and took inspiration from real travel planners.  
- The most challenging part was integrating automatic seat recommendations with manual selection. It was difficult to ensure that the ticket count remained accurate when switching between the two, and that seat filters could still be applied to manually selected seats.  


### Assumptions Made
- I assumed that user doesn't need to be able to manually choose their table, to avoid wasting table space.
- I assumed that the recommendation algorithm should prioritise preferences over the amount of seats potentially wasted.
- Since viewing only your bookings would've required account creation, I assumed that letting user see all the bookings is fine for this system.
- I assumed that since the idea was reservation of specific tables, that is still the case in the Private area, even though people usually rent out the entire room in that case.


