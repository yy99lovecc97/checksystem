package domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name = "employee_type" , discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
//对应系统的员工信息
public class Employee implements Serializable {
    private static final long serialVersionUID = 8668523702321530975L;
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "employee_name" , nullable = false , unique = true , length = 50)
    private String name;
    @Column(name = "employee_pass" , nullable = false , length = 50)
    private String pass;
    @Column(name = "employee_salary" , nullable = false)
    //员工工资
    private double salary;
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name = "manager_id")
    //员工对应的经理
    private Manager manager;
    @OneToMany(targetEntity = Attend.class , mappedBy = "employee")
    //对应的出勤记录
    private Set<Attend> attends = new HashSet<>();
    @OneToMany(targetEntity = Payment.class , mappedBy = "employee")
    //对应的工资
    private Set<Payment> payments = new HashSet<>();

    public Employee() {
    }

    public Employee(int id,String name, String pass, double salary, Manager manager, Set<Attend> attends, Set<Payment> payments) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.salary = salary;
        this.manager = manager;
        this.attends = attends;
        this.payments = payments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Set<Attend> getAttends() {
        return attends;
    }

    public void setAttends(Set<Attend> attends) {
        this.attends = attends;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
    // 根据name、pass来重写hashCode()方法
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pass == null) ? 0 : pass.hashCode());
        return result;
    }
    // 根据name、pass来重写equals()方法，只要name、pass相同的员工即认为相等。
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        if (name == null)
        {
            if (other.name != null) return false;
        }
        else if (!name.equals(other.name)) return false;
        if (pass == null)
        {
            if (other.pass != null) return false;
        }
        else if (!pass.equals(other.pass)) return false;
        return true;
    }
}
