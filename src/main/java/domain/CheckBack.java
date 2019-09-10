package domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "check_back_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//对应批复
public class CheckBack implements Serializable {
    private static final long serialVersionUID = -4770374747139911862L;
    @Id
    @Column(name = "check_back_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "check_back_result" , nullable = false)
    //是否同意申请
    private boolean result;
    @Column(name = "check_back_reason")
    //批复理由
    private String reason;
    @OneToOne(targetEntity = Application.class)
    @JoinColumn(name = "app_id" , nullable = false , unique = true)
    //该批复对应的申请
    private Application app;
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name = "manager_id" , nullable = false)
    //批复的经理
    private Manager manager;

    public CheckBack() {
    }

    public CheckBack(boolean result, String reason, Application app, Manager manager) {
        this.result = result;
        this.reason = reason;
        this.app = app;
        this.manager = manager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
