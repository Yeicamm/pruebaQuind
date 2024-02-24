package com.technical.test.quind.hexagonal.domain.model;

import com.technical.test.quind.hexagonal.domain.model.enums.IdentificationTypeEnum;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.Audit;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
public class Client extends Audit {
    private Long id;
    private IdentificationTypeEnum identificationTypeEnum;
    private String identificationNumber;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String dateOfBirth;
    private List<ProductEntity> productEntities;
}
