# restful-To-Do-List-using-spring-boot
This project included RESTful APi for a card task system
# How to run
    1. Clone the repo to a know location
    2. Ensure you have docker and docker-compose on your deployment machine
    3. Open terminal at the root of the clone project
    4. Run this command `docker-compose up`

localhost:8080/api/cards {POST, GET, UPDATE, DELETE}
     sample POST Raw JSON DATA - `{
    "name": "Change payment method to mobile money ",
    "color": "blue",
    "description":"Use local payment methods to ensure customers using mobile money are able to pay seamlessly",
    "created_by":1
}`

localhost:8080/api/users {POST, GET, DELETE, UPDATE}
    `{
    "email":"khisaham@gmail.com",
    "password":"khisaham",
    "role":"member"
}`

localhost:8080/api/cards?search=<param> e.g #ffffff or To Do  -- GET