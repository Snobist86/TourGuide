package Tour.Dto.sight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListUnescoSightDto {

    private String name;
    private Boolean isListUnesco;
}
