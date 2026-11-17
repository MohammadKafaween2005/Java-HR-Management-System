package decorators;

public abstract class SalaryDecorator implements SalaryComponent {
    protected SalaryComponent decoratedSalary;

    public SalaryDecorator(SalaryComponent decoratedSalary) {
        this.decoratedSalary = decoratedSalary;
    }

    public abstract double calculateSalary();
}
