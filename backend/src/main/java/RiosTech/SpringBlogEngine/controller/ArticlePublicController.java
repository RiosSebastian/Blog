package RiosTech.SpringBlogEngine.controller;

import RiosTech.SpringBlogEngine.dto.ArticleResponse;
import RiosTech.SpringBlogEngine.dto.ArticleSummary;
import RiosTech.SpringBlogEngine.mapper.ArticleMapper;
import RiosTech.SpringBlogEngine.service.ArticleService;
import RiosTech.SpringBlogEngine.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlePublicController {

    private final ArticleService service;
    private final ArticleMapper mapper;

    @GetMapping
    public Page<ArticleSummary> list(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(required = false) Category category,
            Pageable pageable) {
        return service.search(q, category, pageable).map(mapper::toSummary);
    }

    @GetMapping("/categories")
    public Category[] categories() {
        return Category.values();
    }

    @GetMapping("/{slug}")
    public ArticleResponse get(@PathVariable String slug) {
        return mapper.toDto(service.readPublic(slug));
    }
}