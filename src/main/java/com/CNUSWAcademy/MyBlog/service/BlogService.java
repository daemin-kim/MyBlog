package com.CNUSWAcademy.MyBlog.service;

import com.CNUSWAcademy.MyBlog.domain.Article;
import com.CNUSWAcademy.MyBlog.dto.AddArticleRequest;
import com.CNUSWAcademy.MyBlog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록하여 서블릿 컨테이너에 등록함
public class BlogService {

    private final BlogRepository blogRepository;

    // save()는 JpaRepository에서 지원하는 저장 메소드. 정확히는 JpaRepository의 부모 클래스인 CrudRepository에 선언되어 있음.
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
