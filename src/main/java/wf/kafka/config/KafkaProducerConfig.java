package wf.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import wf.kafka.rest.PaymentMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapserver}")
    public String bootstrapServer;

    @Bean
    public ProducerFactory<String, PaymentMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, PaymentMessageSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PaymentMessageSerializer.class);
        return props;
    }

    @Bean
    @Primary
    public KafkaTemplate<String, PaymentMessage> kafkaTemplate() {
        KafkaTemplate<String, PaymentMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }

}
