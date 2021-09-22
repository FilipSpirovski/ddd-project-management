package mk.ukim.finki.emt.teamstructuring.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Quantity implements ValueObject {

    private final int quantity;

    protected Quantity() {
        this.quantity = 0;
    }

    public Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity valueOf(int quantity) {
        return new Quantity(quantity);
    }
}
