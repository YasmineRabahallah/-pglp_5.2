package fr.uvsq.exercice5;

/** 
 * interface AbstractFactoryDao.
 * 
 * @author rabahallah yasmine.
 *
 */

public interface AbstractFactoryDao {

  Dao<Personnel> createPersonnelDao();

  Dao<Personnelcomposite> createPersonnelcompositeDao();

}
