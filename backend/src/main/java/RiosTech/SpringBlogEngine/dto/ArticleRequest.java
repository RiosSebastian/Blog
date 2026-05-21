package RiosTech.SpringBlogEngine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequest {

    private String title;
    private String content;
    private String imageUrl;
}