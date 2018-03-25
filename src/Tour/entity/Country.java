package Tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    private Long id;
    private String name;
    private Set<City> cities = new HashSet<>();
    private Set<Airport> airports = new HashSet<>();
}
