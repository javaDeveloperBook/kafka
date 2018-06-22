# kafka
kafka开发使用

window中操作Kafka

启动zookeeper
.\bin\zkServer

启动kafka服务
.\bin\windows\kafka-server-start.bat .\config\server.properties

创建主题
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic01

创建生产者
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic topic01


创建消费者
.\bin\windows\kafka-console-consumer.bat --zookeeper localhost:2181 --topic topic01