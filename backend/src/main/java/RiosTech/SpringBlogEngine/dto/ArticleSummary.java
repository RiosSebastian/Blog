package RiosTech.SpringBlogEngine.dto;

import RiosTech.SpringBlogEngine.util.Category;
import RiosTech.SpringBlogEngine.util.Status;

import java.time.LocalDateTime;

public record ArticleSummary(
        Long id,
        String title,
        String slug,
        String excerpt,
        String imageUrl,
        Category category,
        Status status,
        Long views,
        LocalDateTime publishedAt
) {}
