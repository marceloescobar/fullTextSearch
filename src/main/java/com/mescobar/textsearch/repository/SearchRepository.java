package com.mescobar.textsearch.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.mescobar.textsearch.dto.PageDTO;

@NoRepositoryBean
public interface SearchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

  List<T> searchBy(String text, int limit, String... fields);
  
  PageDTO<T> searchPageBy(String text, int limit, int offset, String... fields);
}
