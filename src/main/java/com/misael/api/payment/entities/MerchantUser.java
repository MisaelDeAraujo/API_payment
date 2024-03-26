package com.misael.api.payment.entities;

import com.misael.api.payment.entities.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tb")
public class MerchantUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50)
	private String completeName;
	@Column(length = 60)
	private String password;
	@Column(length = 60, unique = true)
	private String email;
	@Column(length = 14, unique = true)
	private String cnpj;
	@Column
	private Double wallet;
	@Column
	private UserType userType;
}
