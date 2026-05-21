package RiosTech.SpringBlogEngine.mapper;

import RiosTech.SpringBlogEngine.dto.ArticleResponse;
import RiosTech.SpringBlogEngine.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleResponse toDto(Article a) {
        ArticleResponse dto = new ArticleResponse();

        dto.setTitle(a.getTitle());
        dto.setSlug(a.getSlug());
        dto.setContent(a.getContent());
        dto.setImageUrl(a.getImageUrl());
        dto.setViews(a.getViews());

        return dto;
    }
}