package wf.kafka.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class PaymentMessage {
    public int id;
    public status currentStatus;
    public Date timeStamp;
    public String originator;
    public String receiver;
    public BigDecimal transactionAmount;

    enum status {
        INITIATED,
        VALIDATION,
        ENRICHMENT,
        PROCESSING,
        COMPLETED,
        ERROR
    }

    @JsonCreator
    public PaymentMessage(@JsonProperty("id") int id, @JsonProperty("currentStatus") status currentStatus, @JsonProperty("timeStamp") Date timeStamp, @JsonProperty("originator") String originator, @JsonProperty("receiver") String receiver, @JsonProperty("transactionAmount") BigDecimal transactionAmount) {
        this.id = id;
        this.currentStatus = currentStatus;
        this.timeStamp = timeStamp;
        this.originator = originator;
        this.receiver = receiver;
        this.transactionAmount = transactionAmount;
    }
}
