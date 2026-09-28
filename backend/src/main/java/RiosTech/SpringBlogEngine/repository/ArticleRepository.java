package RiosTech.SpringBlogEngine.repository;


import RiosTech.SpringBlogEngine.entity.Article;
import RiosTech.SpringBlogEngine.util.Category;
import RiosTech.SpringBlogEngine.util.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findBySlugAndStatusAndDeletedFalse(String slug, Status status);

    Page<Article> findByDeletedFalse(Pageable pageable);

    boolean existsBySlug(String slug);

    @Query("""
        SELECT a FROM Article a
        WHERE a.status = :status AND a.deleted = false
          AND (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%'))
               OR LOWER(a.content) LIKE LOWER(CONCAT('%', :q, '%')))
        ORDER BY a.publishedAt DESC
        """)
    Page<Article> search(@Param("status") Status status,
                         @Param("q") String q,
                         Pageable pageable);

    @Query("""
        SELECT a FROM Article a
        WHERE a.status = :status AND a.deleted = false
          AND a.category = :category
          AND (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%'))
               OR LOWER(a.content) LIKE LOWER(CONCAT('%', :q, '%')))
        ORDER BY a.publishedAt DESC
        """)
    Page<Article> searchByCategory(@Param("status") Status status,
                                   @Param("category") Category category,
                                   @Param("q") String q,
                                   Pageable pageable);
}