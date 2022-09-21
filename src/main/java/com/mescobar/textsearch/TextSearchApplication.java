package com.mescobar.textsearch;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.mescobar.textsearch.index.Indexer;
import com.mescobar.textsearch.model.Plant;
import com.mescobar.textsearch.repository.PlantRepository;
import com.mescobar.textsearch.repository.impl.SearchRepositoryImpl;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(repositoryBaseClass = SearchRepositoryImpl.class)
public class TextSearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(TextSearchApplication.class, args);
  }

  @Bean
  public ApplicationRunner initializeData(PlantRepository plantRepository) throws Exception {
    return (ApplicationArguments args) -> {
      List<Plant> plants = Arrays.asList(new Plant("subalpine fir", "abies lasiocarpa", "pinaceae"),
          new Plant("sour cherry", "prunus cerasus", "rosaceae"),
          new Plant("asian pear", "pyrus pyrifolia", "rosaceae"),
          new Plant("chinese witch hazel", "hamamelis mollis", "hamamelidaceae"),
          new Plant("silver maple", "acer saccharinum", "sapindaceae"),
          new Plant("cucumber tree", "magnolia acuminata", "magnoliaceae"),
          new Plant("korean rhododendron", "rhododendron mucronulatum", "ericaceae"),
          new Plant("water lettuce", "pistia", "araceae"),
          new Plant("sessile oak", "quercus petraea", "fagaceae"),
          new Plant("common fig", "ficus carica", "moraceae"));
      plantRepository.saveAll(plants);
    };
  }

  @Bean
  public ApplicationRunner buildIndex(Indexer indexer) {
    return (ApplicationArguments args) -> {
      indexer.indexPersistedData("com.mescobar.textsearch.model.Plant");
    };
  }

}
