package RiosTech.SpringBlogEngine.entity;

import RiosTech.SpringBlogEngine.util.Category;
import RiosTech.SpringBlogEngine.util.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    private Long views = 0L;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime publishedAt;

    private boolean deleted = false;
}