package com.codeit.duckhu.review.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewCreateRequest {

  @NotNull
  @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
  @Max(value = 5, message = "평점은 5 이하여야 합니다.")
  private Integer rating;

  @NotBlank
  private String content;
}
