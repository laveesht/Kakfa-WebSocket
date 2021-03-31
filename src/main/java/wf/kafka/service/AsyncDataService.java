package wf.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import wf.kafka.rest.DataGenerator;

@Service
public class AsyncDataService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TaskExecutor taskExecutor;

    public void executeAsynchronously() {
        DataGenerator myThread = applicationContext.getBean(DataGenerator.class);
        taskExecutor.execute(myThread);
    }
}
