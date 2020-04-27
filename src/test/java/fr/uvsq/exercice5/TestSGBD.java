package fr.uvsq.exercice5;



import org.junit.Test;

public class TestSGBD {
	@Test
	public void test(){
		 Sgbd bdd = new  Sgbd();
		 bdd.createTables();
		 bdd.droptables();
		

	
}
}
