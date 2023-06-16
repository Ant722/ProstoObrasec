package ru.aston.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.dto.request.EmployeeRoleDto;
import ru.aston.dto.request.EmployeeSearchCriteriaDto;
import ru.aston.dto.request.EmployeeStatusDto;
import ru.aston.request.EmployeeSearchCriteria;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchCriteriaMapperTest {

    @InjectMocks
    private SearchCriteriaMapperImpl searchCriteriaMapper;

    @Test
    void should_mapEmployeeSearchCriteriaDto_toEmployeeSearchCriteria() {
        EmployeeSearchCriteria expected = getExpectedEmployeeSearchCriteria();
        EmployeeSearchCriteria actual = searchCriteriaMapper
                .mapEmployeeSearchCriteriaDtoToEmployeeSearchCriteriaRequest(getEmployeeSearchCriteriaDto());

        Assertions.assertEquals(expected, actual);
    }

    private EmployeeSearchCriteriaDto getEmployeeSearchCriteriaDto() {
        EmployeeSearchCriteriaDto searchCriteriaDto = new EmployeeSearchCriteriaDto();
        searchCriteriaDto.setRole(new EmployeeRoleDto("ADMIN"));
        searchCriteriaDto.setStatus(new EmployeeStatusDto("ACTIVE"));
        searchCriteriaDto.setSort("name");
        searchCriteriaDto.setSurname("Ivanov");
        searchCriteriaDto.setPage(2);
        return searchCriteriaDto;
    }

    private EmployeeSearchCriteria getExpectedEmployeeSearchCriteria() {
        EmployeeSearchCriteria searchCriteria = new EmployeeSearchCriteria();
        searchCriteria.setRole("ADMIN");
        searchCriteria.setStatus("ACTIVE");
        searchCriteria.setSort("name");
        searchCriteria.setSurname("Ivanov");
        searchCriteria.setPage(1);
        return searchCriteria;
    }


}
