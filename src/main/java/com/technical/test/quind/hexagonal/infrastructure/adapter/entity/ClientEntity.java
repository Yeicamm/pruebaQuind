package com.technical.test.quind.hexagonal.infrastructure.adapter.entity;

import com.technical.test.quind.hexagonal.domain.model.enums.IdentificationTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Table (name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type")
    private IdentificationTypeEnum identificationTypeEnum;
    @Column(name = "identification_number")
    private String identificationNumber;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_surname")
    private String clientSurname;
    @Column(name = "client_email")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "The format of email no is valid")
    private String clientEmail;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities;
}
