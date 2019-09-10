package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manager_inf")
@DiscriminatorValue("2")
public class Manager extends Employee implements Serializable {
    private static final long serialVersionUID = 7281397082251120724L;
    @Column(name = "department_name" , length = 50)
    //经理所管理的部门
    private String department;
    @OneToMany(targetEntity = Employee.class , mappedBy = "manager")
    private Set<Employee> employees = new HashSet<>();
    @OneToMany(targetEntity = CheckBack.class , mappedBy = "manager")
    //经理所签署的所有批复
    private Set<CheckBack> checkBacks = new HashSet<>();

    // 无参数的构造器
    public Manager() {
    }

    // 初始化全部成员变量的构造器
    public Manager(String department , Set<Employee> employees
            , Set<CheckBack> checkBacks)
    {
        this.department = department;
        this.employees = employees;
        this.checkBacks = checkBacks;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<CheckBack> getCheckBacks() {
        return checkBacks;
    }

    public void setCheckBacks(Set<CheckBack> checkBacks) {
        this.checkBacks = checkBacks;
    }
}
