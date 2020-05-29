package vo;

import java.io.Serializable;

public class SalaryBean implements Serializable {
    private static final long serialVersionUID = -308362920073312314L;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private String empName;
    private double amount;

    public SalaryBean() {
    }

    public SalaryBean(String empName, double amount) {
        this.empName = empName;
        this.amount = amount;
    }
}
