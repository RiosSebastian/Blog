package RiosTech.SpringBlogEngine.mapper;

import RiosTech.SpringBlogEngine.dto.ArticleResponse;
import RiosTech.SpringBlogEngine.dto.ArticleSummary;
import RiosTech.SpringBlogEngine.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleResponse toDto(Article a) {
        ArticleResponse dto = new ArticleResponse();
        dto.setId(a.getId());
        dto.setTitle(a.getTitle());
        dto.setSlug(a.getSlug());
        dto.setContent(a.getContent());
        dto.setImageUrl(a.getImageUrl());
        dto.setCategory(a.getCategory());
        dto.setStatus(a.getStatus());
        dto.setViews(a.getViews());
        dto.setCreatedAt(a.getCreatedAt());
        dto.setPublishedAt(a.getPublishedAt());
        return dto;
    }

    public ArticleSummary toSummary(Article a) {
        String text = a.getContent() == null ? "" : a.getContent().strip();
        String excerpt = text.length() > 180 ? text.substring(0, 180) + "…" : text;

        return new ArticleSummary(
                a.getId(), a.getTitle(), a.getSlug(), excerpt,
                a.getImageUrl(), a.getCategory(), a.getStatus(),
                a.getViews(), a.getPublishedAt()
        );
    }
}