package com.mescobar.textsearch.dto;

import javax.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageableSearchRequestDTO extends SearchRequestDTO {

  @Min(0)
  private int pageOffset;
}
