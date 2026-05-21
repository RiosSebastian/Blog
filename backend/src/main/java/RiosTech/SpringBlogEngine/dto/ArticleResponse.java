package RiosTech.SpringBlogEngine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleResponse {

    private String title;
    private String slug;
    private String content;
    private String imageUrl;
    private Long views;
}