package club.odty.odty.base.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;
    @Column(name = "create_time", nullable = false)
    private Long createTime;
    @Column(name = "create_by", nullable = false)
    private String createBy;
    @Column(name = "update_time")
    private Long updateTime;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "is_void", nullable = false)
    private Integer isVoid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    public Integer getIsVoid() {
        return isVoid;
    }

    public void setIsVoid(Integer isVoid) {
        this.isVoid = isVoid;
    }
}


