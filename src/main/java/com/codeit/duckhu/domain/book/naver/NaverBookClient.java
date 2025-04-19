package com.codeit.duckhu.domain.book.naver;

import com.codeit.duckhu.domain.book.dto.NaverApiResponse;
import com.codeit.duckhu.domain.book.dto.NaverApiResponse.Item;
import com.codeit.duckhu.domain.book.dto.NaverBookDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NaverBookClient {

  // Naver Open API 인증 정보 (application.yml에 설정되어 있음)
  @Value("${naver.client-id}")
  private String clientId;

  @Value("${naver.client-secret}")
  private String clientSecret;

  // HTTP 요청을 보낼 RestTemplate 인스턴스
  private final RestTemplate restTemplate = new RestTemplate();

  /**
   * 주어진 ISBN을 기반으로 네이버 책 검색 API를 호출하여 책 정보를 조회합니다.
   *
   * @param isbn 검색할 도서의 ISBN
   * @return 조회된 도서 정보를 담은 NaverBookDto
   * @throws RuntimeException ISBN에 해당하는 도서 정보가 없는 경우
   */
  public NaverBookDto searchByIsbn(String isbn) {
    // 고급 검색 API 사용 (ISBN 기반 검색)
    String url = "https://openapi.naver.com/v1/search/book_adv.json?d_isbn=" + isbn;

    // HTTP 요청 헤더에 Naver API 인증 정보 추가
    HttpHeaders headers = new HttpHeaders();
    headers.set("X-Naver-Client-Id", clientId);
    headers.set("X-Naver-Client-Secret", clientSecret);

    // 인증 헤더를 포함한 요청 객체 생성
    HttpEntity<Void> request = new HttpEntity<>(headers);

    // GET 요청 실행 및 응답 수신
    ResponseEntity<NaverApiResponse> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        request,
        NaverApiResponse.class
    );

    // 응답에서 검색 결과 항목 추출
    List<Item> items = response.getBody().items();
    if (items == null || items.isEmpty()) {
      // 검색 결과가 없는 경우 예외 처리
      throw new RuntimeException("ISBN으로 책 정보를 찾을 수 없습니다.");
    }

    // 첫 번째 검색 결과를 기준으로 NaverBookDto 생성
    NaverApiResponse.Item item = items.get(0);
    return new NaverBookDto(
        item.title(),
        item.author(),
        item.description(),
        item.publisher(),
        LocalDate.parse(item.pubdate(), DateTimeFormatter.ofPattern("yyyyMMdd")), // yyyyMMdd → LocalDate로 변환
        isbn,
        item.image()
    );
  }
}