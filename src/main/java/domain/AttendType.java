package domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "attend_type_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
//考勤的类别
public class AttendType implements Serializable {
    private static final long serialVersionUID = -3225655311551640367L;
    @Id
    @Column(name = "attend_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "attend_type_name" , nullable = false , length = 50)
    //出勤类型的名称
    private String name;
    @Column(name = "attend_type_amerce" , nullable = false )
    //对应的罚款
    private double amerce;

    public AttendType() {
    }

    public AttendType(String name, double amerce) {
        this.name = name;
        this.amerce = amerce;
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

    public double getAmerce() {
        return amerce;
    }

    public void setAmerce(double amerce) {
        this.amerce = amerce;
    }
}
