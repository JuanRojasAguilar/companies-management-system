package com.backend.orderservice.domain;

import java.time.LocalTime;
import java.util.List;

import com.backend.orderstate.domain.OrderState;
import com.backend.orderwork.domain.OrderWork;
import com.backend.user.domain.User;
import com.backend.utils.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "order_service_id")
    private Long id;

    @Column(length = 100)
    private String name;

	private LocalTime dateOrder;

	@Enumerated(EnumType.STRING)
	Status status;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User employee;

	@ManyToOne
	@JoinColumn(name = "order_state_id")
	private OrderState orderState;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderServiceId")
	private List<OrderWork> orderWorkList;

	@PrePersist
	protected void onCreate(){
		this.dateOrder = LocalTime.now();
	}
}
