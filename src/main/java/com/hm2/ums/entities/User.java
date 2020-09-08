package com.hm2.ums.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hm2.common.entities.BaseEntity;
import com.hm2.common.utils.DateUtils;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_UMS_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Data
@AttributeOverride(name = "id", column = @Column(name = "USER_ID", nullable = false))
public class User extends BaseEntity {

    private static final long serialVersionUID = 146134598360995282L;
    @Column(length = 100)
    private String userCode;
    @Column(length = 60)
    private String username;
    @Column(length = 500)
    private String password;
    @Column(length = 50)
    private String nickname;
    @Column(length = 100)
    private String email;
    @Column(length = 11)
    private String tel;
    private Date lastLoginDate;
    @Column(length = 2000)
    private String accessMenu;
    @Column(length = 2000)
    private String remark;
    private Date changePasswordDate;
    @Column(length = 50)
    private String forgotPasswordKey;

    public String getLastLoginDateStr() {
        return DateUtils.formatDate(lastLoginDate, DateUtils.DD_MM_YYYY_HHMMSS);
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickname + '\'' +
                ", tel='" + tel + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", accessMenu='" + accessMenu + '\'' +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", isDeleted='" + isDeleted + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                "}";
    }
}
