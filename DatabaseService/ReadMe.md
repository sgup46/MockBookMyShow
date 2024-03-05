# running the docker
got to D:\microservices-projects\DatabaseService
if you are on wiondows , ensure docker desktop is running
docker-compose up -d

# creating a topic
docker exec -it databaseservice-kafka-1 kafka-topics.sh --create --topic city --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it databaseservice-kafka-1 kafka-topics.sh --create --topic movie --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it databaseservice-kafka-1 kafka-topics.sh --create --topic city_theatre --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it databaseservice-kafka-1 kafka-topics.sh --create --topic show --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

2. Produce Messages to the Topic:
   docker exec -it databaseservice-kafka-1 kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092

3. Consume Messages from the Topic:
   docker exec -it databaseservice-kafka-1 kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server localhost:9092
2. Produce Messages to the Topic:
   docker exec -it databaseservice-kafka-1 kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092

3. Consume Messages from the Topic:
   docker exec -it databaseservice-kafka-1 kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server localhost:9092

4. Check Kafka Server Logs:
   tail -n 100 <kafka_installation_directory>/logs/server.log | grep -i "Kafka Server started"



#h2 console
http://localhost:8010/h2-console/
url : jdbc:h2:file:D:/data/demo
username as "sa"


6. to stop all running docker images
   docker rmi -f $(docker images -q)

docker ps -q       // list all running container
docker stop <image_id>

for /f "delims=" %i in ('docker images -q') do docker rmi -f %i                 // remove all docker images
for /f "delims=" %i in ('docker ps -q') do docker stop %i                     // stopping all containers
