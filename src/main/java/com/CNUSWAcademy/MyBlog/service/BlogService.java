package com.CNUSWAcademy.MyBlog.service;

import com.CNUSWAcademy.MyBlog.domain.Article;
import com.CNUSWAcademy.MyBlog.dto.AddArticleRequest;
import com.CNUSWAcademy.MyBlog.dto.UpdateArticleRequest;
import com.CNUSWAcademy.MyBlog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록하여 서블릿 컨테이너에 등록함
public class BlogService {

    private final BlogRepository blogRepository;

    // save()는 JpaRepository에서 지원하는 저장 메소드. 정확히는 JpaRepository의 부모 클래스인 CrudRepository에 선언되어 있음.
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> finaAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }
    
    @Transactional
    // 트랜잭션 메서드 (트랜잭션을 적용하기 위해 다른 작업을 할 필요 없이 @Transactional 애너테이션 사용)
    // update() 메서드는 엔티티의 필드 값이 바뀌면 중간에 에러가 발생해도 제대로 된 값 수정을 보장함
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
