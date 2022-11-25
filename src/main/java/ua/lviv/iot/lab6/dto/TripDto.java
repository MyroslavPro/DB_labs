package ua.lviv.iot.lab6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "trip", collectionRelation = "trips")
public class TripDto extends RepresentationModel<TripDto> {
    private final Integer id;
    private final Date dateStart;
    private final Double guideSalary;
}
