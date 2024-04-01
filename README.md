# VISTREAM-V2

## Overview

Une application de streaming de films et de séries, nous avons mis au point un concept innovant pour attirer les utilisateurs du monde entier.
Cette application propose un accès instantané à une vaste bibliothèque de films et de séries, que vous pouvez regarder n'importe où et à tout moment.
Vous pouvez parcourir notre catalogue en ligne, sélectionner les films et séries de votre choix, et les regarder sur votre appareil.
Notre application offre une expérience de streaming rapide et fluide, pour que vous puissiez profiter de vos films et séries préférés sans interruption.

## Backend (Spring Boot)

### User Stories

#### Admin Features

1. **Authentication**
    - As a user, I want to be able to register with my email and password.
    - As a registered user, I want to log in using my credentials.
    - As an admin, I want to manage user accounts (create, update, delete) for security purposes.
2. **Actor Management**
    - As an admin, I want to add new actors to the database.
    - As an admin, I want to update actor information such as name, bio, and image.
    - As an admin, I want to delete actors who are no longer relevant.
3. **Country Management**
    - As an admin, I want to add new countries to categorize media.
    - As an admin, I want to update country details like name, code, and flag.
    - As an admin, I want to remove countries that are not required anymore.
4. **Genre Management**
    - As an admin, I want to create new genres for movies and series.
    - As an admin, I want to update genre information like name and description.
    - As an admin, I want to delete genres that are no longer needed.
5. **Movies and Series Management**
    - As an admin, I want to add new movies and series to the platform.
    - As an admin, I want to update movie and series details such as title, description, release date, and cover image.
    - As an admin, I want to remove movies and series that are no longer available.
6. **Watchlist Management**
    - As a user, I want to create a watchlist to keep track of movies or series I want to watch.
    - As a user, I want to add or remove movies/series from my watchlist.
    - As a user, I want to update my watchlist with new content.
7. **Slider Management**
    - As an admin, I want to create sliders featuring popular or recommended movies/series.
    - As an admin, I want to update slider content regularly.
    - As an admin, I want to remove sliders that are no longer relevant.
8. **Validation Forms using Parsley.js**
    - As a user, I want form validation to ensure I input correct and required information.
    - As a developer, I want to implement form validation using Parsley.js to enhance user experience.
9. **Statistics**
    - As an admin, I want to view statistics on user engagement, popular content, and platform usage.
    - As an admin, I want to generate reports based on user interactions and media consumption.
10. **Filtering Media**
    - As a user, I want to filter movies or series based on genres, actors, countries, or release dates.
    - As a user, I want to search for specific content using various filter criteria.
11. **Comments (Disqus Plugin)**
    - As a user, I want to comment and engage with others on movie/series pages.
    - As a developer, I want to integrate Disqus plugin for efficient and interactive commenting functionality.

#### Features

- Use Spring Boot to develop the API.
- Use Liquibase for managing db
- Organize the application into layers.
- Data validation is mandatory.
- Centralized exception handling (RestControllerAdvice).
- Implement pagination for search results and media lists.
- Write unit tests for the competition results calculation service.

#### Steps to run the application
```bash
# Clone the repository
git clone https://github.com/shadowgks/VISTREAMV2.git
```
```bash
# Go to the backend directory
cd VISTREAMV2
```
```bash
# install dependencies
mvn install
```
```bash
# Run the application
mvn spring-boot:run
```
[Frontend Repository](https://github.com/shadowgks/VISTREAMV2-FE)

[Canva Presentation](https://www.canva.com/design/DAGAOdfMLds/xIBeaF-VxzDrAd2sfDy6Nw/edit)

