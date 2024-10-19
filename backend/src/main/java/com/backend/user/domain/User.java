package com.backend.user.domain;

import java.sql.Date;
import java.util.List;

import com.backend.emailuser.domain.EmailUser;
import com.backend.franchise.domain.Franchise;
import com.backend.orderservice.domain.OrderService;
import com.backend.orderwork.domain.OrderWork;
import com.backend.usertype.domain.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	@ManyToOne
	@JoinColumn(name = "user_type_id")
	private UserType userTypeId;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientId") 
    private List<OrderService> ordersServicesClient;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeId") 
    private List<OrderService> orderServicesEmployee;

    @JsonIgnore
	@OneToOne(mappedBy = "user")
	private EmailUser emailUser;

    @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeId")
	private List<OrderWork> ordersWorks;
}
