package com.mescobar.textsearch.index;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class Indexer {

  private EntityManager entityManager;

  private static final int THREAD_NUMBER = 4;

  public Indexer(EntityManager entityManager) {
      this.entityManager = entityManager;
  }

  public void indexPersistedData(String indexClassName) throws IndexException {

      try {
          SearchSession searchSession = Search.session(entityManager);

          Class<?> classToIndex = Class.forName(indexClassName);
          MassIndexer indexer =
                  searchSession
                          .massIndexer(classToIndex)
                          .threadsToLoadObjects(THREAD_NUMBER);

          indexer.startAndWait();
      } catch (ClassNotFoundException e) {
          throw new IndexException("Invalid class " + indexClassName, e);
      } catch (InterruptedException e) {
          throw new IndexException("Index Interrupted", e);
      }
  }
}
