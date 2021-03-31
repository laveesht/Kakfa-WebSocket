package wf.kafka.rest;

import com.devskiller.jfairy.Fairy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
@Scope("prototype")
public class DataGenerator implements Runnable {
    @Autowired
    private KafkaTemplate<String, PaymentMessage> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    private int count;
    private List<PaymentMessage> paymentMessageList = new ArrayList<>();

    public DataGenerator() {
        this.count = 10;
    }

    @Override
    public void run() {

        // TODO: 31/03/21  USE SPRING TASKJob creator to configure Runnable thread
        // TODO: 31/03/21 USE https://www.baeldung.com/java-enum-simple-state-machine for defining chained events for payments
        Fairy fairy = Fairy.create();
        for (int i = 1; i <= this.count; i++) {
            paymentMessageList.add(new PaymentMessage(i, fairy.baseProducer().randomElement(Arrays.asList(PaymentMessage.status.values())), Calendar.getInstance().getTime(), fairy.person().getFirstName(), fairy.person().getFirstName(), new BigDecimal(fairy.baseProducer().randomBetween(10, 10000))));
            kafkaTemplate.send(kafkaTopic, new PaymentMessage(i, fairy.baseProducer().randomElement(Arrays.asList(PaymentMessage.status.values())), Calendar.getInstance().getTime(), fairy.person().getFirstName(), fairy.person().getFirstName(), new BigDecimal(fairy.baseProducer().randomBetween(10, 10000))));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}