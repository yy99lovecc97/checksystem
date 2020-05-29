package domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="application_inf")
//启动二级缓存
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
//对应普通员工的考勤提出申请
public class Application implements Serializable {
    private static final long serialVersionUID = -5237400022572119997L;

    @Id
    @Column(name = "app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //标识属性
    private Integer id;
    //申请理由
    @Column(name = "app_reason" , length = 254)
    private String reason;
    //是否处理
    @Column(name = "app_result")
    private boolean result;
    //关联的出勤记录
    @ManyToOne(targetEntity = Attend.class)
    @JoinColumn(name = "attend_id" , nullable = false)
    private Attend attend;
    //考勤类别
    @ManyToOne(targetEntity = AttendType.class)
    @JoinColumn(name = "attend_type_id" , nullable = false)
    private AttendType attendType;
    //对应批复
    @OneToOne(targetEntity = CheckBack.class , mappedBy = "app")
    private CheckBack checkBack;

    public Application() {
    }
    //初始化全部成员变量的构造器
    public Application(String reason, boolean result, Attend attend, AttendType attendType, CheckBack checkBack) {
        this.reason = reason;
        this.result = result;
        this.attend = attend;
        this.attendType = attendType;
        this.checkBack = checkBack;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Attend getAttend() {
        return attend;
    }

    public void setAttend(Attend attend) {
        this.attend = attend;
    }

    public AttendType getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendType attendType) {
        this.attendType = attendType;
    }

    public CheckBack getCheckBack() {
        return checkBack;
    }

    public void setCheckBack(CheckBack checkBack) {
        this.checkBack = checkBack;
    }
}
