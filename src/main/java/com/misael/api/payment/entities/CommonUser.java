package com.misael.api.payment.entities;

import com.misael.api.payment.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "common_user")
public class CommonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String completeName;
    @Column(length = 60)
    private String password;
    @Column(length = 60, unique = true)
    private String email;
    @Column(length = 11, unique = true)
    private String cpf;
    @Column
    private Double wallet;
    @Column
    private UserType userType;
}
