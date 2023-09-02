package com.lotrel.ltserwer.projectModule.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SprintDto {

    private Long id;
    private String description;
    @JsonFormat(pattern="dd.MM.yyyy")
    private OffsetDateTime beginDate;
    @JsonFormat(pattern="dd.MM.yyyy")
    private OffsetDateTime endDate;
}
