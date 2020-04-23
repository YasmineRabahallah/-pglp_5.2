package fr.uvsq.exercice5;

/**
 * interface DAO.
 * 
 * @author rabahallah yasmine.
 *
 * @param <T> object.
 */

public interface Dao<T> {
  /**
   * les operation CRUD.
   *
   * @param obj object.  
   * @return object.
   */

  T create(T obj);

  T retrieve(String s);

  T update(T obj);

  void delete(T obj);
}
