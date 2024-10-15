package com.backend.usertelephone.domain;

import com.backend.telephonetype.domain.TelephoneType;
import com.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="users_telephones")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserTelephone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_telephone_id")
  @EqualsAndHashCode.Include
  private Long id;

  @ManyToOne
  @JoinColumn(name = "telephone_type_id")
  private TelephoneType telephoneType;

  @Column(length=20)
  @EqualsAndHashCode.Include
  private String number;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
}
