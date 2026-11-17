package decorators;

public class OvertimeDecorator extends SalaryDecorator {
    private double overtimePay;

    public OvertimeDecorator(SalaryComponent decoratedSalary, double overtimePay) {
        super(decoratedSalary);
        this.overtimePay = overtimePay;
    }

    @Override
    public double calculateSalary() {
        return decoratedSalary.calculateSalary() + overtimePay;
    }
}
