package RiosTech.SpringBlogEngine.controller;

import RiosTech.SpringBlogEngine.dto.ArticleRequest;
import RiosTech.SpringBlogEngine.entity.Article;
import RiosTech.SpringBlogEngine.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/articles")
@RequiredArgsConstructor
    public class ArticleAdminController {

        private final ArticleService service;

    @PostMapping
    public Article create(@RequestBody ArticleRequest req) {
        return service.create(req);
    }

        @PutMapping("/{id}/publish")
        public Article publish(@PathVariable Long id) {
            return service.publish(id);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            service.softDelete(id);
        }
    }

