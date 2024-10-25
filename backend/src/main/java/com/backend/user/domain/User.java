package com.backend.user.domain;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.emailuser.domain.EmailUser;
import com.backend.franchise.domain.Franchise;
import com.backend.orderservice.domain.OrderService;
import com.backend.orderwork.domain.OrderWork;
import com.backend.serviceapproval.domain.ServiceApproval;
import com.backend.userreagent.domain.UserReagent;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "user_id", unique = true, length = 40)
    private String id;

    private String name;

    private String lastname;

    @Column(nullable = false)
    private String username;

    @Column(length = 500, nullable = false)
    private String password;

    private String repeatedPassword;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @Column(name = "register_date", updatable = false, insertable = false)
    private Date registerDate;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client") 
    private List<OrderService> ordersServicesClient;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee") 
    private List<OrderService> orderServicesEmployee;

    @JsonIgnore
	@OneToOne(mappedBy = "user")
	private EmailUser emailUser;

    @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	private List<OrderWork> ordersWorks;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<ServiceApproval> serviceApprovals;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserReagent> userReagents;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
}
