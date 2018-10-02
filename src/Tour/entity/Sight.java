package Tour.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sight {

    private Long id;
    private String name;
    private City city;
    private Integer yearOfBuilding;
    private Category category;
    private Style style;
    private Boolean isListUNESCO;
    private Double ratingOfTripAdvisor;
}
