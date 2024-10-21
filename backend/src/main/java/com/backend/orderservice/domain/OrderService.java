package com.backend.orderservice.domain;

import java.time.LocalTime;
import java.util.List;

import com.backend.orderstate.domain.OrderState;
import com.backend.orderwork.domain.OrderWork;
import com.backend.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
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
    @Size(max = 100, message = "the name is too large")
    @NotNull(message = "name shouldn't be null")
    @NotBlank(message = "the field is blank")
    private String name;

	private LocalTime dateOrder;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User clientId;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User employeeId;

	@ManyToOne
	@JoinColumn(name = "order_state_id")
	private OrderState orderStateId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderServiceId")
	private List<OrderWork> orderWorkList;
}
