package fr.uvsq.exercice5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PersonnelSgbdTest {
	Sgbd sgbd = new  Sgbd();
	DaoFactoryJdbc dfs= new  DaoFactoryJdbc();
	Dao<Personnel> dao2 = dfs.CreatePersonnelDao();
	Dao<Personnelcomposite> dao1 = dfs.CreatePersonnelcompositeDao();
	@Test
	public void createtest(){
		
		sgbd.droptables();
		sgbd.CreateTables();
		Personnelcomposite g1 = new Personnelcomposite(1,"groupe1");
		assertNotNull(dao1.create(g1));
		Personnelcomposite g4 = new Personnelcomposite(4,"groupe4");
		assertNotNull(dao1.create(g4));
		Personnel p1 = new Personnel.Builder("rabahallah", "yasmine","chargé de mission").groupeId(1).build();
		assertNotNull(dao2.create(p1));
		
		
		}
	@Test
	public void retrievetest(){
		Personnel p7 = new Personnel.Builder("arkoub", "achour","chargé de mission").groupeId(1).build();
		assertNotNull(dao2.create(p7));
		Personnel p = dao2.retrieve("arkoub");
		assertEquals(p.getNom(),"arkoub");
		assertEquals(p.getPrenom(),"achour");
		assertEquals(p.getFonction(),"chargé de mission");
		assertEquals(p.getgroupeId(),1);
		
		}
	@Test
	public void updatetest(){
		Personnel p2 = new Personnel.Builder("khait", "hayet","directrice").groupeId(1).build();
		assertNotNull(dao2.create(p2));
		Personnel p3 = new Personnel.Builder("khait", "hayet","chargé de mission").groupeId(4).build();
		p2=p3;
		Personnel p = dao2.update(p2);
		assertEquals(p.getPrenom(),"hayet");
		assertEquals(p.getFonction(),"chargé de mission");
		assertEquals(p.getgroupeId(),4);
	
	}
	@Test
    public void deletetest(){
		Personnel p3 = new Personnel.Builder("hamdis", "ferhat","chef de service").groupeId(1).build();
	assertNotNull(dao2.create(p3));
	Personnel p = dao2.retrieve("hamdis");
	assertEquals(p.getNom(),"hamdis");
	dao2.delete(p3);
	assertNull(dao2.retrieve("hamdis"));
	}
	
}
