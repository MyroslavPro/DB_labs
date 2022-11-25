package ua.lviv.iot.lab6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "tour", collectionRelation = "tours")
public class TourDto extends RepresentationModel<TourDto> {
    private final Integer id;
    private final String name;
    private final Double price;
    private final Integer tourDays;
    private final String description;    
}
