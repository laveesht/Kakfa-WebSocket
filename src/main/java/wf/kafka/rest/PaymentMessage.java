package wf.kafka.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentMessage {
    public int id;
    public status currentStatus;
    public Date timeStamp;
    public String originator;
    public String receiver;
    public BigDecimal transactionAmount;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "PaymentMessage{" +
                "id=" + id +
                ", currentStatus=" + currentStatus +
                ", timeStamp=" + timeStamp +
                ", originator='" + originator + '\'' +
                ", receiver='" + receiver + '\'' +
                ", transactionAmount=" + transactionAmount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    enum status {
        INITIATED,
        VALIDATION,
        ENRICHMENT,
        PROCESSING,
        COMPLETED,
        ERROR
    }

    @JsonCreator
    public PaymentMessage(@JsonProperty("id") int id,@JsonProperty("currentStatus") status currentStatus,@JsonProperty("timeStamp") Date timeStamp,@JsonProperty("originator") String originator, @JsonProperty("receiver")String receiver, @JsonProperty("transactionAmount")BigDecimal transactionAmount) {
        this.id = id;
        this.currentStatus = currentStatus;
        this.timeStamp = timeStamp;
        this.originator = originator;
        this.receiver = receiver;
        this.transactionAmount = transactionAmount;
    }
}
