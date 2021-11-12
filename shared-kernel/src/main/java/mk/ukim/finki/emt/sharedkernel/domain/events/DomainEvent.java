package mk.ukim.finki.emt.sharedkernel.domain.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.time.Instant;

@Getter
public class DomainEvent {

    private String topic;

    private Instant occurredOn;

    public DomainEvent() {
    }

    public DomainEvent(String topic) {
        this.occurredOn = Instant.now();
        this.topic = topic;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String output = null;

        try {
            output = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {

        }

        return output;
    }

    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.readValue(json, eventClass);
    }
}
