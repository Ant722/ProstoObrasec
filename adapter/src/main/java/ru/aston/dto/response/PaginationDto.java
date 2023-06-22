package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO with pages information")
public class PaginationDto {

    @Schema(description = "Number of records")
    private Long count;

    @Schema(description = "Number of pages")
    private Integer pages;

    @Schema(description = "Next page")
    private Integer pageNext;

    @Schema(description = "Previous page")
    private Integer pagePrev;
}
