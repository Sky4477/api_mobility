package sig.agrogeomarket.app.Web.Secrutiy;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class UrlEncoderConfig {

    public String encodeUrl(String url) {
        return UriComponentsBuilder.fromUriString(url).build().toUriString();
    }
}
