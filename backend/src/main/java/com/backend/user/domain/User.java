package com.backend.user.domain;

import java.sql.Date;
import java.util.Set;

import org.springframework.data.annotation.Transient;

import com.backend.franchise.domain.Franchise;
import com.backend.usertype.domain.UserType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "user_id", unique = true, length = 40)
    private String id;

    @Column(length = 50)
    private String name;

    @NonNull
    @EqualsAndHashCode.Include
    private String password;

    @Column(length = 50, name = "last_name")
    private String lastName;

    @Column(name = "register_date", updatable = false, insertable = false)
    private Date registerDate;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @OneToMany(mappedBy = "user_type_id")
    @JsonManagedReference
    private Set<UserType> userType;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isAdmin;

    @PrePersist
    private void registerUser() {
        this.setRegisterDate(new Date(0));
    }
}
