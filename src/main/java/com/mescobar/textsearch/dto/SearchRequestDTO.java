package com.mescobar.textsearch.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchRequestDTO {
  @NotBlank
  private String text;

  private List<String> fields = new ArrayList<>();

  @Min(1)
  private int limit;
}
