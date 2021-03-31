package wf.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import wf.kafka.rest.PaymentMessage;

import java.util.Map;

public class PaymentMessageDeSerializer implements Deserializer {

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        PaymentMessage user = null;
        try {
            user = mapper.readValue(data, PaymentMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
