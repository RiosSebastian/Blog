package RiosTech.SpringBlogEngine.controller;

import RiosTech.SpringBlogEngine.entity.Article;
import RiosTech.SpringBlogEngine.repository.ArticleRepository;
import RiosTech.SpringBlogEngine.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlePublicController {

    private final ArticleRepository repo;

    @GetMapping
    public Page<Article> list(Pageable pageable) {
        return repo.findByStatusAndDeletedFalse(Status.PUBLISHED, pageable);
    }

    @GetMapping("/{slug}")
    public Article get(@PathVariable String slug) {
        return repo.findBySlugAndStatus(slug, Status.PUBLISHED)
                .orElseThrow();
    }
}