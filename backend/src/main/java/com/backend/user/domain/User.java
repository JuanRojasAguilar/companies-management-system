package com.backend.user.domain;

import java.sql.Date;

import com.backend.franchise.domain.Franchise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @Column(name = "user_id", unique = true, nullable = false, length = 40)
    @NonNull
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 50)
    private String name;

    @Column(length = 50, name = "last_name")
    private String lastName;

    @Column(name = "register_date", updatable = false, insertable = false)
    private Date registerDate;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}
