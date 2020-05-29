package domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "attend_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//对应每天的考勤
public class Attend implements Serializable {
    private static final long serialVersionUID = -5935792977935812014L;
    @Id
    @Column(name = "attend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //出勤日期
    @Column(name = "duty_day" , nullable = false , length = 50)
    private String dutyDay;
    //打卡时间
    @Column(name = "punch_time")
    private Date punchTime;
    //代表本次打卡是否为上班打卡
    @Column(name = "is_come" , nullable = false)
    private boolean isCome;
    @ManyToOne(targetEntity = AttendType.class)
    @JoinColumn(name = "attend_type_id" , nullable = false)
    private AttendType attendType;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id" , nullable = false)
    private Employee employee;

    public Attend(String dutyDay, Date punchTime, boolean isCome, AttendType attendType, Employee employee) {
        this.dutyDay = dutyDay;
        this.punchTime = punchTime;
        this.isCome = isCome;
        this.attendType = attendType;
        this.employee = employee;
    }

    public Attend() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public boolean isCome() {
        return isCome;
    }

    public void setCome(boolean come) {
        isCome = come;
    }

    public AttendType getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendType attendType) {
        this.attendType = attendType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // 根据employee、isCome、dutyDay来重写hashCode()方法
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dutyDay == null) ? 0 : dutyDay.hashCode());
        result = prime * result
                + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + (isCome ? 1231 : 1237);
        return result;
    }
    // 根据employee、isCome、dutyDay来重写equals()方法
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Attend other = (Attend) obj;
        if (dutyDay == null)
        {
            if (other.dutyDay != null) return false;
        }
        else if (!dutyDay.equals(other.dutyDay)) return false;
        if (employee == null)
        {
            if (other.employee != null) return false;
        }
        else if (!employee.equals(other.employee)) return false;
        if (isCome != other.isCome) return false;
        return true;
    }
}
