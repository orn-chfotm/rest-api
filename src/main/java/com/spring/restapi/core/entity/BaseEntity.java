package com.spring.restapi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updatedDate"}, allowGetters = true)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;

    @CreatedBy
    @Column(updatable = false)
    private String regBy;

    @LastModifiedBy
    private LocalDateTime modDt;

    @LastModifiedBy
    private String modBy;

    public void setModDt(LocalDateTime modDt) {
        this.modDt = modDt;
    }
}
