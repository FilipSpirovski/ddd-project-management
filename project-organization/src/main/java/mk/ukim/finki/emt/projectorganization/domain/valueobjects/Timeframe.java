package mk.ukim.finki.emt.projectorganization.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class Timeframe implements ValueObject {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Timeframe() {
    }

    public Timeframe(String startDate, String endDate) {
        this.startDate = LocalDateTime.parse(startDate);
        this.endDate = LocalDateTime.parse(endDate);
    }
}
