
# create topic
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092

# publish to topic
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092

# consume from topic
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092