# This is RS2 evaluation project

For this project the following packages are used:
- Spring Data JPA 
- Spring Security
- ModelMapper
- An embedded H2 database

##How to set up this project

after cloning the repository you have to define an environment variable by the name `SECRET_KEY`

Then you can easily build image using `mvnw spring-boor:build-image` command from spring boot 

## Database setup
- Username `sa`
- Password `password`
- Name `rs2`
- url `jdbc:h2:mem:rs2`
