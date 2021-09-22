package mk.ukim.finki.emt.teamstructuring.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.teamstructuring.domain.valueobjects.Currency;
import mk.ukim.finki.emt.teamstructuring.domain.valueobjects.Money;
import mk.ukim.finki.emt.teamstructuring.services.forms.EmployeeForm;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "employees")
public class Employee extends AbstractEntity<EmployeeId> {

    private String name;

    private String surname;

    private Money salary;

    private EmployeePosition positionInTeam;

    public Employee() {
    }

    public Employee(EmployeeForm employeeForm) {
        super(EmployeeId.randomId(EmployeeId.class));
        this.setValues(employeeForm);
    }

    public void updateDetails(EmployeeForm employeeForm) {
        this.setValues(employeeForm);
    }

    private void setValues(EmployeeForm employeeForm) {
        this.name = employeeForm.getName();
        this.surname = employeeForm.getSurname();
        this.salary = Money.valueOf(
                Currency.valueOf(employeeForm.getCurrencyOfSalary()),
                employeeForm.getAmountOfSalary()
        );
        this.positionInTeam = EmployeePosition.valueOf(employeeForm.getPositionInTeam());
    }
}
