package RiosTech.SpringBlogEngine.service;

import RiosTech.SpringBlogEngine.config.SlugUtil;
import RiosTech.SpringBlogEngine.dto.ArticleRequest;
import RiosTech.SpringBlogEngine.entity.Article;
import RiosTech.SpringBlogEngine.repository.ArticleRepository;
import RiosTech.SpringBlogEngine.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repo;
    private final SlugUtil slugUtil;

    public Article create(ArticleRequest req) {

        Article article = new Article();

        article.setTitle(req.getTitle());
        article.setContent(req.getContent());
        article.setImageUrl(req.getImageUrl());

        return repo.save(article);
    }


    public Article publish(Long id) {
        Article a = repo.findById(id).orElseThrow();

        if (a.getTitle().length() < 5 || a.getContent().length() < 50) {
            throw new RuntimeException("Contenido insuficiente");
        }

        a.setStatus(Status.PUBLISHED);
        a.setPublishedAt(LocalDateTime.now());

        return repo.save(a);
    }

    public void softDelete(Long id) {
        Article a = repo.findById(id).orElseThrow();
        a.setDeleted(true);
        repo.save(a);
    }
}
