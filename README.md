# This is RS2 evaluation project

I tried to structure the project in a self contained manner
which means each entity ,service ,dto,and controller class are defined 
in a separate packages.

For this project the following packages are used:
- Spring Data JPA 
- Spring Security
- ModelMapper
- An embedded H2 database
- Log4j
- Jwt

## How to set up this project

after cloning the repository you have to define an environment variable by the name `SECRET_KEY`

Then you can easily build image using `mvnw spring-boot:build-image` command from spring boot 

## Database setup
- Username `sa`
- Password `password`
- Name `rs2`
- url `jdbc:h2:mem:rs2`

You can access the browser based **h2 console** at `http://{host}:{port}/h2`
