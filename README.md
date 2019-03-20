# FormationSpringBoot
API Rest, unit testing, integration testing with Mongo DB on a Docker container


# Setting up  the environment 

docker pull mongo:latest
#
docker run -p 27017:27017 -v /path/to/my/mongo/mongodb/db:/data/db --name crud-mongo -d mongo
