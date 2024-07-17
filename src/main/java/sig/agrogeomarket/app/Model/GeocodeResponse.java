package sig.agrogeomarket.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeocodeResponse {
    private String display_name;
    private double lat;
    private double lon;
}
