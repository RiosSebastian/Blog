package RiosTech.SpringBlogEngine.dto;

import RiosTech.SpringBlogEngine.util.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequest {
    private String title;
    private String content;
    private String imageUrl;
    private Category category;
}