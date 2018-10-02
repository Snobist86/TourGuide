package Tour.Dto.sight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SightDto {

    private String name;
    private Long cityId;
    private Integer yearOfBuilding;
    private Integer categoryId;
    private Integer styleId;
    private Boolean isListUNESCO;
    private Double ratingOfTripAdvisor;
}
