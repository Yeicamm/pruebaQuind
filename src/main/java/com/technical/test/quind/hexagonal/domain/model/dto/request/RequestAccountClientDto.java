package com.technical.test.quind.hexagonal.domain.model.dto.request;

import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAccountClientDto {
    private String accountType;
    private ProductDto productDto;
}
