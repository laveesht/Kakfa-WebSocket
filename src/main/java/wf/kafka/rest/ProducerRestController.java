package wf.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wf.kafka.service.KafkaSenderService;

@RestController
@RequestMapping(value = "/message")
public class ProducerRestController {

    @Autowired
    KafkaSenderService kafkaSenderService;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaSenderService.send(message);
        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }

}