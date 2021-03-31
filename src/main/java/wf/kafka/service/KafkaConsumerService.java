package wf.kafka.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import wf.kafka.rest.PaymentMessage;

import java.util.Arrays;

@Service
public class KafkaConsumerService {
    @Autowired
    SimpMessagingTemplate template;

    final StreamsBuilder builder = new StreamsBuilder();

    @KafkaListener(topics = "${kafka.topic}")
    public void consume(@Payload PaymentMessage message) {
        KStream<String, String> textLines = builder.stream("quickstart-events");

        KTable<String, Long> wordCounts = textLines
                .flatMapValues(line -> Arrays.asList(line.toLowerCase().split(" ")))
                .groupBy((keyIgnored, word) -> word)
                .count();


//        wordCounts.toStream().to("/topic/temperature", Produced.with(Serdes.String(), Serdes.Long()));
        template.convertAndSend("/topic/temperature", message.toString());
//        template.convertAndSend("/topic/temperature", wordCounts.toString());
    }

}