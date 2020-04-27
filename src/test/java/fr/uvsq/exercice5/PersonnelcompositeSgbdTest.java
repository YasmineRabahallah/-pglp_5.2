package fr.uvsq.exercice5;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Before;
import org.junit.Test;




public class PersonnelcompositeSgbdTest {
	Sgbd sgbd = new  Sgbd();
	DaoFactoryJdbc dfs= new  DaoFactoryJdbc();
	Dao<Personnelcomposite> dao1 = dfs.createPersonnelcompositeDao();
	Dao<Personnel> dao2 = dfs.createPersonnelDao();
	
	@Test
	public void createtest(){
		sgbd.droptables();
		sgbd.createTables();
		
		
		Personnelcomposite g1 = new Personnelcomposite(1,"groupe1");
		Personnel p1 = new Personnel.Builder("rabahallah", "yasmine","chargé de mission").groupeId(1).build();
		Personnelcomposite g2 = new Personnelcomposite(2,"groupe2");
		g2.add(p1);
		assertNotNull(dao1.create(g1));
		assertNotNull(dao1.create(g2));
		
		}
	@Test
	public void retrievetest(){
		Personnelcomposite g3 = new Personnelcomposite(3,"groupe3");
		assertNotNull(dao1.create(g3));
		Personnel p2 = new Personnel.Builder("arkoub", "achour","chargé de mission").groupeId(3).build();
		Personnel p3 = new Personnel.Builder("khait", "hayet","chargé de mission").groupeId(3).build();
		assertNotNull(dao2.create(p2));
		assertNotNull(dao2.create(p3));
		g3.add(p2);
		g3.add(p3);
		Personnelcomposite p = dao1.retrieve("3");
		assertEquals(p.getid(),3);
		assertEquals(p.getNom_groupe(),"groupe3");
		assertEquals(p.getPersonnes().size(),2);
		}
	
	@Test
    public void updatetest(){
	Personnelcomposite g11 = new Personnelcomposite(11,"groupe11");
	assertNotNull(dao1.create(g11));
	Personnelcomposite pc = new Personnelcomposite(11,"nomgroupe11");
	g11=pc ;
	Personnelcomposite p = dao1.update(g11);
	assertEquals(p.getid(),11);
	assertEquals(p.getNom_groupe(),"nomgroupe11");
	
	}
	
	
	@Test
	    public void deletetest(){
		Personnelcomposite g10 = new Personnelcomposite(10,"groupe10");
		assertNotNull(dao1.create(g10));
		Personnelcomposite p = dao1.retrieve("10");
		assertEquals(p.getid(),10);
		dao1.delete(g10);
		assertNull(dao1.retrieve("10"));
		}
		
	
	
}
