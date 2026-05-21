package RiosTech.SpringBlogEngine.config;

import org.springframework.stereotype.Component;

@Component
public class SlugUtil {

    public String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
