package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import ru.aston.dto.response.PaginationDto;

@Mapper(componentModel = "spring")
public interface PaginationMapper {

    /**
     * Page numbering starts at number 1
     */
    @Mapping(target = "count", source = "page", qualifiedByName = "getCount")
    @Mapping(target = "pages", source = "page", qualifiedByName = "getPages")
    @Mapping(target = "pageNext", source = "page", qualifiedByName = "getNextPage")
    @Mapping(target = "pagePrev", source = "page", qualifiedByName = "getPrevPage")
    PaginationDto mapPageToPaginationDto(Page<?> page);

    @Named("getCount")
    default Long getCount(Page<?> page) {
        return page.getTotalElements();
    }

    @Named("getPages")
    default Integer getPageNumber(Page<?> page) {
        return page.getTotalPages();
    }

    @Named("getNextPage")
    default Integer getNextPage(Page<?> page) {
        return page.hasNext() ? page.getPageable().getPageNumber() + 2 : null;
    }

    @Named("getPrevPage")
    default Integer getPrevPage(Page<?> page) {
        return page.hasPrevious() ? page.getPageable().getPageNumber() : null;
    }
}
