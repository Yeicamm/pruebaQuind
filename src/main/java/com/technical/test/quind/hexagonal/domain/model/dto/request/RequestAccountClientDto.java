package com.technical.test.quind.hexagonal.domain.model.dto.request;

import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestAccountClientDto {
    private String accountType;
    private ProductDto productDto;
}
