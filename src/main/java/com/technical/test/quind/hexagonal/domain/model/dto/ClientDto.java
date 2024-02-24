package com.technical.test.quind.hexagonal.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical.test.quind.hexagonal.domain.model.enums.IdentificationTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDto {
    private IdentificationTypeEnum identificationTypeEnum;
    private String identificationNumber;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String dateOfBirth;
    @JsonIgnore
    private List<ProductDto> productDto;
}
