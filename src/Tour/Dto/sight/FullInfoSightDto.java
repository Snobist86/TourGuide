package Tour.Dto.sight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullInfoSightDto {

    private String name;
    private String city;
    private Integer yearOfBuilding;
    private String category;
    private String style;
    private Boolean isListUNESCO;
    private Double ratingOfTripAdvisor;
}
