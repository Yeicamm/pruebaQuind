package com.technical.test.quind.hexagonal.infrastructure.adapter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Audit {
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_modified")
    private LocalDateTime dateModified;
}
