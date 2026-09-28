package RiosTech.SpringBlogEngine.dto;

import RiosTech.SpringBlogEngine.util.Category;
import RiosTech.SpringBlogEngine.util.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleResponse {
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String imageUrl;
    private Category category;
    private Status status;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
}