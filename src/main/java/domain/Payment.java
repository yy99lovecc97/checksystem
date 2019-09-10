package domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//每月所发的薪水信息
public class Payment implements Serializable {
    private static final long serialVersionUID = 3613055087196458174L;
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "payment_payMonth" , nullable = false)
    //月份
    private String payMonth;
    @Column(name = "payment_amount" , nullable = false)
    //数量
    private double amount;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id" , nullable = false)
    //领薪水的员工
    private Employee employee;

    public Payment() {
    }

    public Payment(String payMonth, double amount, Employee employee) {
        this.payMonth = payMonth;
        this.amount = amount;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
