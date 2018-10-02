package Tour.Dto.sight;

import Tour.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchSightDto {

    private String countryId;
    private String cityId;
    private String categoryId;
    private String styleId;
}
