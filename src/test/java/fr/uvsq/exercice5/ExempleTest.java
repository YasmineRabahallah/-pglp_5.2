package fr.uvsq.exercice5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class ExempleTest {
	Dao<Personnel> dao1;
	@Test
	public void createtest(){
		File f = new File("rabahallah");
		f.delete();
		DaoFactorySerl df= new  DaoFactorySerl();
		Dao<Personnel> dao1 = df.CreatePersonnelDao() ;
		Personnel p1 = new Personnel.Builder("rabahallah", "yasmine","chargé de mission").build();
		assertFalse(f.exists());
		dao1.create(p1);
		assertTrue(f.exists());
		
	}
}
