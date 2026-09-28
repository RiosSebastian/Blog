package RiosTech.SpringBlogEngine.controller;

import RiosTech.SpringBlogEngine.dto.ArticleRequest;
import RiosTech.SpringBlogEngine.dto.ArticleResponse;
import RiosTech.SpringBlogEngine.dto.ArticleSummary;
import RiosTech.SpringBlogEngine.mapper.ArticleMapper;
import RiosTech.SpringBlogEngine.service.ArticleService;
import RiosTech.SpringBlogEngine.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/articles")
@RequiredArgsConstructor
public class ArticleAdminController {

    private final ArticleService service;
    private final ArticleMapper mapper;
    private final ImageService imageService;

    @GetMapping
    public Page<ArticleSummary> list(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.listAll(pageable).map(mapper::toSummary);
    }

    @GetMapping("/{id}")
    public ArticleResponse get(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @PostMapping
    public ArticleResponse create(@RequestBody ArticleRequest req) {
        return mapper.toDto(service.create(req));
    }

    @PutMapping("/{id}")
    public ArticleResponse update(@PathVariable Long id, @RequestBody ArticleRequest req) {
        return mapper.toDto(service.update(id, req));
    }

    @PutMapping("/{id}/publish")
    public ArticleResponse publish(@PathVariable Long id) {
        return mapper.toDto(service.publish(id));
    }

    @PutMapping("/{id}/unpublish")
    public ArticleResponse unpublish(@PathVariable Long id) {
        return mapper.toDto(service.unpublish(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/images")
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) {
        return Map.of("url", imageService.upload(file));
    }
}