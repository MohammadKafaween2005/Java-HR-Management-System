package decorators;

public class BonusDecorator extends SalaryDecorator {
    private double bonus;

    public BonusDecorator(SalaryComponent decoratedSalary, double bonus) {
        super(decoratedSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return decoratedSalary.calculateSalary() + bonus;
    }
}
