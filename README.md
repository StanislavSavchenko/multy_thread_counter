# Test task #

## Requirements ##
* maven
* docker
* docker-compose


## How to configure ##

In application.yml you can configure counter 

## How to build ##

* Go to project root folder and run: 
``mvn clean package``
* Go to project root folder and run: 
``docker-compose build`` and
``docker-compose up``

## How to test ##
* Go to ``http://localhost:8001/api/swagger-ui.html``
* Inside the ``Counter API`` you can find API with parameter description