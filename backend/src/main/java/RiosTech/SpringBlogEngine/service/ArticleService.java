package RiosTech.SpringBlogEngine.service;

import RiosTech.SpringBlogEngine.dto.ArticleRequest;
import RiosTech.SpringBlogEngine.entity.Article;
import RiosTech.SpringBlogEngine.repository.ArticleRepository;
import RiosTech.SpringBlogEngine.util.Category;
import RiosTech.SpringBlogEngine.util.SlugUtil;
import RiosTech.SpringBlogEngine.util.Status;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repo;
    private final SlugUtil slugUtil;

    // ---------- Parte pública ----------

    public Page<Article> search(String q, Category category, Pageable pageable) {
        Pageable safe = PageRequest.of(pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), 20));
        String text = q == null ? "" : q.trim();

        return category == null
                ? repo.search(Status.PUBLISHED, text, safe)
                : repo.searchByCategory(Status.PUBLISHED, category, text, safe);
    }

    @Transactional
    public Article readPublic(String slug) {
        Article a = repo.findBySlugAndStatusAndDeletedFalse(slug, Status.PUBLISHED)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Historia no encontrada"));

        long views = a.getViews() == null ? 0 : a.getViews();
        a.setViews(views + 1);
        return a;
    }

    // ---------- Parte admin ----------

    public Page<Article> listAll(Pageable pageable) {
        return repo.findByDeletedFalse(pageable);
    }

    public Article getById(Long id) {
        return repo.findById(id)
                .filter(a -> !a.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Historia no encontrada"));
    }

    public Article create(ArticleRequest req) {
        Article a = new Article();
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());
        a.setImageUrl(req.getImageUrl());
        a.setCategory(req.getCategory());
        a.setSlug(uniqueSlug(req.getTitle()));
        return repo.save(a);
    }

    public Article update(Long id, ArticleRequest req) {
        Article a = getById(id);
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());
        a.setImageUrl(req.getImageUrl());
        a.setCategory(req.getCategory());
        return repo.save(a);
    }

    public Article publish(Long id) {
        Article a = getById(id);

        if (a.getTitle() == null || a.getTitle().length() < 5
                || a.getContent() == null || a.getContent().length() < 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Contenido insuficiente");
        }

        a.setStatus(Status.PUBLISHED);
        if (a.getPublishedAt() == null) {
            a.setPublishedAt(LocalDateTime.now());
        }
        return repo.save(a);
    }

    public Article unpublish(Long id) {
        Article a = getById(id);
        a.setStatus(Status.DRAFT);
        return repo.save(a);
    }

    public void softDelete(Long id) {
        Article a = getById(id);
        a.setDeleted(true);
        repo.save(a);
    }

    private String uniqueSlug(String title) {
        String base = slugUtil.toSlug(title);
        String slug = base;
        int i = 2;
        while (repo.existsBySlug(slug)) {
            slug = base + "-" + i++;
        }
        return slug;
    }
}