package io.github.stavshamir.springwolf.example.cloudstream.payload;

public class ConsumerPayload {

    private String payloadString;

    public ConsumerPayload(String payloadString) {
        this.payloadString = payloadString;
    }

    public String getPayloadString() {
        return payloadString;
    }

    public void setPayloadString(String payloadString) {
        this.payloadString = payloadString;
    }

    @Override
    public String toString() {
        return "ConsumerPayload{" +
                "payloadString='" + payloadString + '\'' +
                '}';
    }
}
