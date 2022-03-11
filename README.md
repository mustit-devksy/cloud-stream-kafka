## 카프카 테스트
- 로컬에서 브로커 3개 구성해서 간단 테스트
### Kafka Run
- https://github.com/wurstmeister/kafka-docker
```shell
$ docker-compose -f ./docker-compose/docker-compose.yml up -d
```

### Command
- https://kafka.apache.org/quickstart

```shell
-- 토픽 조회
$ ./kafka-topics.sh --list --bootstrap-server localhost:9092

-- 토픽 상세 조회
$ ./kafka-topics.sh --bootstrap-server localhost:9092 --topic <topic_name> --describe

-- 토픽 생성
$ ./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic <topic_name>
-- 에러 zookeeper deprecated
-- zookeeper is not a recognized option

-- 발행
$ ./kafka-console-producer.sh --broker-list localhost:9092 --topic <topic_name>

-- 소비
$ ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic <topic_name> --from-beginning

-- 토픽 제거
$ ./kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic <topic_name>

-- 컨슈머 그룹 리스트 확인
$ ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

-- 컨슈머 상태 및 오프셋
$ ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group <consumer_name> --describe
```

## 오류
### kafka-manager
- https://github.com/yahoo/CMAK/issues/748
- https://github.com/yahoo/CMAK/issues/731#issuecomment-643880544

```shell
$ docker exec -ti zookeeper bash
$ ./bin/zkCli.sh

[zk: localhost:2181(CONNECTED) 2] ls /kafka-manager
[configs, deleteClusters, clusters]
[zk: localhost:2181(CONNECTED) 3] create /kafka-manager/mutex ""
Created /kafka-manager/mutex
[zk: localhost:2181(CONNECTED) 5] create /kafka-manager/mutex/locks ""
Created /kafka-manager/mutex/locks
[zk: localhost:2181(CONNECTED) 6] create /kafka-manager/mutex/leases ""
Created /kafka-manager/mutex/leases
```

