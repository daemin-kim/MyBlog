package com.CNUSWAcademy.MyBlog.controller;

import com.CNUSWAcademy.MyBlog.domain.Article;
import com.CNUSWAcademy.MyBlog.dto.AddArticleRequest;
import com.CNUSWAcademy.MyBlog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 방식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;
    
    // HTTP 메소드가 POST일때, 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody 애너테이션은 HTTP를 요청할 때 응답에 해당하는 값을 @RequestBody 애너테이션이 붙은 대상 객체인 AddArticleRequest에 매핑함
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        
        // 요청한 자원이 성공적으로 생성되었으며, 저장된 블로그 글 정보를 객체에 담아 전송함
        // ResponseEntity.status().body()는 응답 코드로 201을 응답하고, 테이블에 저장된 객체를 반환함
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }


}
