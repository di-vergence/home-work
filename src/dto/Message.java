package dto;

import java.util.Objects;

public class Message implements Comparable<Message> {
    private String payload;
    private int priority;

    public Message(String payload, int priority) {
        this.payload = payload;
        this.priority = priority;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return getPriority() == message.getPriority() &&
                Objects.equals(getPayload(), message.getPayload());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayload(), getPriority());
    }

    @Override
    public int compareTo(Message message) {
        return this.priority != 0 ?
                Integer.compare(this.priority, message.getPriority()) :
                this.payload.compareTo(message.getPayload());
    }

    @Override
    public String toString() {
        return "Message{" +
                "payload='" + payload + '\'' +
                ", priority=" + priority +
                '}';
    }
}
