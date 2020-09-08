package com.hm2.common.entities;

import com.hm2.common.constants.ProjectConstant;
import com.hm2.common.utils.DateUtils;
import com.hm2.common.utils.UserLoginUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5140575813889967178L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "IS_DELETED", length = 10)
    protected String isDeleted = "N";

    @Column(name = "CREATED_BY", updatable = false)
    protected String createdBy;

    @Column(name = "CREATED_DATE", updatable = false)
    protected Date createdDate;

    @Column(name = "UPDATED_BY", nullable = true)
    protected String updatedBy;

    @Column(name = "UPDATED_DATE", nullable = true)
    protected Date updatedDate;

    @PrePersist
    public void prePersist() {
        if (!ProjectConstant.Status.INACTIVE.equalsIgnoreCase(isDeleted))
            isDeleted = ProjectConstant.Flag.N;
        else
            isDeleted = ProjectConstant.Flag.Y;

        if (StringUtils.isBlank(createdBy)) {
            createdBy = UserLoginUtils.getCurrentUsername();
        }

        if (StringUtils.isBlank(updatedBy)) {
            updatedBy = UserLoginUtils.getCurrentUsername();
        }

        createdDate = new Date();
        updatedDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        if (StringUtils.isBlank(updatedBy)) {
            updatedBy = UserLoginUtils.getCurrentUsername();
        }
        updatedDate = new Date();
    }

    public String getCreatedDateStr() {
        return DateUtils.formatDate(createdDate, DateUtils.DD_MM_YYYY_HHMMSS);
    }

    public String getUpdatedDateStr() {
        return DateUtils.formatDate(updatedDate, DateUtils.DD_MM_YYYY_HHMMSS);
    }

    public String getIdStr() {
        return id.toString();
    }

    public boolean isNew() {
        return this.id == null || this.id == 0;
    }

    public boolean isNotNew() {
        return !isNew();
    }
}
