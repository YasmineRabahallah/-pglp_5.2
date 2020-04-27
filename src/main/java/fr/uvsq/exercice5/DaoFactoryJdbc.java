package fr.uvsq.exercice5;

public class DaoFactoryJdbc  implements AbstractFactoryDao{
	public Dao<Personnel> CreatePersonnelDao() {
		return new PersonnelDaoJdbc();
	}

	public Dao<Personnelcomposite> CreatePersonnelcompositeDao() {
		return new PersonnelCopositeDaoJdbc();
	}
}
