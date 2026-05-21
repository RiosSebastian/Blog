package RiosTech.SpringBlogEngine.repository;

import RiosTech.SpringBlogEngine.entity.Article;
import RiosTech.SpringBlogEngine.util.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findBySlugAndStatus(String slug, Status status);

    Page<Article> findByStatusAndDeletedFalse(Status status, Pageable pageable);

    boolean existsBySlug(String slug);
}
