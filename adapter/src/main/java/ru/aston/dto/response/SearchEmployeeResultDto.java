package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "DTO with pagination info and list of employees")
public class SearchEmployeeResultDto {

    @Schema(description = "Pagination info")
    private PaginationDto info;

    @Schema(description = "List of employees")
    private List<EmployeeShortInformationDto> result;
}
