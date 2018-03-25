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
    private Long cityId;
    private Integer yearOfBuilding;
    private Integer categoryId;
    private Integer styleId;
    private Boolean isListUNESCO;
    private Double ratingOfTripAdvisor;
}
