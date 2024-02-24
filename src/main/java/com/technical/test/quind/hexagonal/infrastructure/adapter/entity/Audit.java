package com.technical.test.quind.hexagonal.infrastructure.adapter.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class Audit {
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_modified")
    private LocalDateTime dateModified;
}
