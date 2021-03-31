package wf.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wf.kafka.service.AsyncDataService;

@RestController
@RequestMapping(value = "/message")
public class ProducerRestController {

    @Autowired
    AsyncDataService asyncDataService;

    @GetMapping(value = "/producer")
    public void producer(@RequestParam("message") String message) {
        asyncDataService.executeAsynchronously();
    }

}