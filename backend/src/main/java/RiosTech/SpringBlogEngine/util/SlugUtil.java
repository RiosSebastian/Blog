package RiosTech.SpringBlogEngine.util;

import org.springframework.stereotype.Component;

import java.text.Normalizer;

@Component
public class SlugUtil {

    public String toSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return normalized.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}