package com.rb.test;

import com.rb.test.Producer.KafkaProducer1;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

//This class creates topics automatically on application bootstrap
@EnableKafka
@Configuration
@EnableMongoRepositories
public class Conf {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic1}")
    private String topic1;

    @Value(value = "${kafka.topic.partitions}")
    private int topic_partitions;

    @Value(value = "${kafka.topic.replication}")
    private  short topic_replication;

    @Value(value = "{Consumer1_groupId}")
    private String Consumer1_groupId;

    @Value(value = "{gaming_consumer.groupId}")
    private String gaming_consumer_groupId;

    @Value(value = "${kafka.session-topic}")
    private String sessionTopic;

    @Value(value = "{$sessionConsumer.groupId}")
    private String sessionConsumerId;

    @Value(value = "${kafka.battle-topic}")
    static String battle_topic;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic craete_topic1(){
        return new NewTopic(topic1, topic_partitions, topic_replication);
    }

    @Bean
    public NewTopic create_SessionTopic() { return new NewTopic(sessionTopic, topic_partitions, topic_replication); }

    @Bean
    public NewTopic create_BattleTopic() { return new NewTopic(battle_topic, topic_partitions, topic_replication); }

    /* ----  Prodcuer and Consumer for for Simple Demo  ---- */
    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> confMap = new HashMap<>();
        confMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        confMap.put(ProducerConfig.ACKS_CONFIG, "all");
        confMap.put(ProducerConfig.CLIENT_ID_CONFIG, "producer1");
        confMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        confMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new DefaultKafkaProducerFactory<>(confMap);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String, Object> confMap = new HashMap<>();
        confMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        confMap.put(ConsumerConfig.GROUP_ID_CONFIG,Consumer1_groupId);
        confMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        confMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        confMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        confMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "30000");
        return new DefaultKafkaConsumerFactory<>(confMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    /* ----  Prodcuer and Consumer for for Gaming data streaming  ---- */
    @Bean
    public ProducerFactory<String, String> gamingProducerFactory(){
        Map<String, Object> confMap = new HashMap<>();
        confMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        confMap.put(ProducerConfig.ACKS_CONFIG, "all");
        confMap.put(ProducerConfig.CLIENT_ID_CONFIG, "gaming_producer");
        confMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        confMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new DefaultKafkaProducerFactory<>(confMap);
    }

    @Bean
    public KafkaTemplate<String, String> gamingKafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> gamingConsumerFactory(){
        Map<String, Object> confMap = new HashMap<>();
        confMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        confMap.put(ConsumerConfig.GROUP_ID_CONFIG,gaming_consumer_groupId);
        confMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        confMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        confMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        confMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "30000");
        return new DefaultKafkaConsumerFactory<>(confMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> gamingKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
