package fr.uvsq.exercice5;

public class DaoFactorySerl implements AbstractFactoryDao{

	public Dao<Personnel> createPersonnelDao() {
		return new PersonnelDao();
	}

	public Dao<Personnelcomposite> createPersonnelcompositeDao() {
		return new PersonnelcompositeDao();
	}


}
