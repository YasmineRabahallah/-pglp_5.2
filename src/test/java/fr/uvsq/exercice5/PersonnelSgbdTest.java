package fr.uvsq.exercice5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class PersonnelSgbdTest {
	Sgbd sgbd = new  Sgbd();
	DaoFactoryJdbc dfs= new  DaoFactoryJdbc();
	Dao<Personnel> dao2 = dfs.createPersonnelDao();
	Dao<Personnelcomposite> dao1 = dfs.createPersonnelcompositeDao();
	@Before
	public void initialiseTable() {
		sgbd.droptables();
		sgbd.createTables();
	}
	@Test
	public void createtest(){
		
		
		Personnelcomposite g1 = new Personnelcomposite(1,"groupe1");
		assertNotNull(dao1.create(g1));
		Personnel p1 = new Personnel.Builder("rabahallah", "yasmine","chargé de mission").groupeId(1).build();
		assertNotNull(dao2.create(p1));
		
		
		}
	@Test
	public void retrievetest(){
		
		Personnelcomposite g4 = new Personnelcomposite(4,"groupe4");
		assertNotNull(dao1.create(g4));
		Personnel p2 = new Personnel.Builder("arkoub", "achour","chargé de mission").groupeId(4).build();
		assertNotNull(dao2.create(p2));
		Personnel p = dao2.retrieve("arkoub");
		assertEquals(p.getNom(),"arkoub");
		assertEquals(p.getPrenom(),"achour");
		assertEquals(p.getFonction(),"chargé de mission");
		assertEquals(p.getgroupeId(),4);
		
		}
	@Test
	public void updatetest(){
		
		Personnelcomposite g1 = new Personnelcomposite(1,"groupe1");
		assertNotNull(dao1.create(g1));
		Personnel p3 = new Personnel.Builder("khait", "hayet","directrice").groupeId(1).build();
		assertNotNull(dao2.create(p3));
		Personnel p4 = new Personnel.Builder("khait", "hayet","chargé de mission").groupeId(1).build();
		p3=p4;
		Personnel p = dao2.update(p3);
		assertEquals(p.getPrenom(),"hayet");
		assertEquals(p.getFonction(),"chargé de mission");
		assertEquals(p.getgroupeId(),1);
	
	} 
	@Test
    public void deletetest(){
		Personnelcomposite g1 = new Personnelcomposite(1,"groupe1");
		assertNotNull(dao1.create(g1));
	    Personnel p3 = new Personnel.Builder("hamdis", "ferhat","chef de service").groupeId(1).build();
	    assertNotNull(dao2.create(p3));
	    Personnel p = dao2.retrieve("hamdis");
	    assertEquals(p.getNom(),"hamdis");
	    dao2.delete(p3);
	    assertNull(dao2.retrieve("hamdis"));
	}
	
}
