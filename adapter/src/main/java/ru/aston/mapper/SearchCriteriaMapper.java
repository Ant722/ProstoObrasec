package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.aston.dto.request.EmployeeSearchCriteriaDto;
import ru.aston.request.EmployeeSearchCriteria;

@Mapper(componentModel = "spring")
public interface SearchCriteriaMapper {

    /**
     * Page numbering starts at number 1
     */
    @Mapping(target = "role", source = "role.role")
    @Mapping(target = "status", source = "status.status")
    @Mapping(target = "page", source = "page", qualifiedByName = "getPage")
    EmployeeSearchCriteria mapEmployeeSearchCriteriaDtoToEmployeeSearchCriteriaRequest(EmployeeSearchCriteriaDto dto);

    @Named("getPage")
    default Integer getPage(Integer page) {
        return page - 1;
    }
}
