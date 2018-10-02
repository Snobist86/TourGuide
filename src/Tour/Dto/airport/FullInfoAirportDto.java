package Tour.Dto.airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullInfoAirportDto {

    private Long id;
    private String name;
    private String city;
}
