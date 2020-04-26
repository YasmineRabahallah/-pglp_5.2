package fr.uvsq.exercice5;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Before;
import org.junit.Test;


public class PersonnelcompositeSgbdTest {
	Sgbd sgbd = new  Sgbd();
	DaoFactorySgbd dfs= new  DaoFactorySgbd();
	Dao<Personnelcomposite> dao1 = dfs.CreatePersonnelcompositeDao();
	
	@Test
	public void createtest(){
		sgbd.droptables();
		sgbd.CreateTables();
		
		
		Personnelcomposite g1 = new Personnelcomposite(1);
		Personnelcomposite g2 = new Personnelcomposite(2);
		assertNotNull(dao1.create(g1));
		assertNotNull(dao1.create(g2));
		
		}
	@Test
	public void retrievetest(){
		Personnelcomposite p = dao1.retrieve("1");
		assertEquals(p.getid(),1);
		}
	
		
	@Test
	    public void deletetest(){
		Personnelcomposite g10 = new Personnelcomposite(10);
		assertNotNull(dao1.create(g10));
		Personnelcomposite p = dao1.retrieve("10");
		assertEquals(p.getid(),10);
		dao1.delete(g10);
		assertNull(dao1.retrieve("10"));
		}
		
	
	
}
