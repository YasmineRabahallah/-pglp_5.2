package fr.uvsq.exercice5;

/** 
 * class DaoFactoryJdbc.
 * 
 * @author rabahallah yasmine.
 *
 */

public class DaoFactoryJdbc  implements AbstractFactoryDao {
  public Dao<Personnel> createPersonnelDao() {
    return new PersonnelDaoJdbc();
  }

  public Dao<Personnelcomposite> createPersonnelcompositeDao() {
    return new PersonnelCopositeDaoJdbc();
  }
}
