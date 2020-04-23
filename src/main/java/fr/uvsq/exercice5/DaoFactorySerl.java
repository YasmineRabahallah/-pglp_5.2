package fr.uvsq.exercice5;

public class DaoFactorySerl implements AbstractFactoryDao{

	public Dao<Personnel> CreatePersonnelDao() {
		return new PersonnelDao();
	}

	public Dao<Personnelcomposite> CreatePersonnelcompositeDao() {
		return new PersonnelcompositeDao();
	}


}
