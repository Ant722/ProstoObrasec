package ru.aston.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.aston.dto.response.PaginationDto;
import ru.aston.model.Employee;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaginationMapperTest {

    @InjectMocks
    private PaginationMapperImpl paginationMapper;

    @Test
    void should_mapPage_toPaginationDto() {
        PaginationDto expectedDto = getExpectedPaginationDto();
        PaginationDto actualDto = paginationMapper.mapPageToPaginationDto(getPageEmployee());

        Assertions.assertEquals(expectedDto, actualDto);
    }

    private PaginationDto getExpectedPaginationDto() {
        PaginationDto dto = new PaginationDto();
        dto.setCount(50L);
        dto.setPages(2);
        dto.setPageNext(2);
        dto.setPagePrev(null);
        return dto;
    }

    private Page<Employee> getPageEmployee() {
        Page<Employee> page = Mockito.mock(Page.class);
        Pageable pageable = Mockito.mock(Pageable.class);

        Mockito.when(pageable.getPageNumber()).thenReturn(0);

        Mockito.when(page.getTotalElements()).thenReturn(50L);
        Mockito.when(page.getTotalPages()).thenReturn(2);
        Mockito.when(page.getPageable()).thenReturn(pageable);
        Mockito.when(page.hasPrevious()).thenReturn(false);
        Mockito.when(page.hasNext()).thenReturn(true);
        return page;
    }
}
