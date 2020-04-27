package fr.uvsq.exercice5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * class createSGBD.
 * 
 * @author rabahallah yasmine.
 *
 */
public class Sgbd {
	 private Connection conn = null;
	 /**
	  * 
	  * @param tableName nom de la table.
	  * @param conn la connexion.
	  * @return  renvoie une valeur booléenne spécifiant si l'objet ResultSet contient plus de lignes.
	  * @throws SQLException Exception sql.
	  */
	 boolean doesTableExists(String tableName, Connection conn)
				throws SQLException {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);

			return result.next();
		}
	 public Connection getConnection() {
		  Connection conn=null;
	    try {
	    	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    try {
	    	conn = DriverManager.getConnection("jdbc:derby:sarradb;create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	  }
	 
	 public void createTables(){
		 Statement statement = null ;
		 conn=this.getConnection();
		 try  {
			   statement = conn.createStatement();
			  if (!doesTableExists("personnelGroupes", conn)) {
	            	statement.addBatch("CREATE TABLE personnelGroupes ("
	    					+ "id_groupe Integer  PRIMARY KEY NOT NULL , "
	            			+ " nom_groupe varchar(40) NOT NULL "
	    					+ ")");
			  }
			  if (!doesTableExists("personnels", conn)) {
	            	statement.addBatch("CREATE TABLE personnels ("
					+ "nom VARCHAR(100) PRIMARY KEY NOT NULL ,"
					+ "prenom VARCHAR(100) NOT NULL,"
					+ "fonction VARCHAR(100) NOT NULL,"
			        + "id_groupe integer references personnelGroupes(id_groupe)"
					+ ")");
			  }
	            	statement.executeBatch();
	 
           conn.close() ;    
		 } catch (SQLException  e) {
			e.printStackTrace();
		}
		 
		 try{
				if (statement != null) {
					statement.close();
			      }
			    } catch (SQLException e1) {
			      e1.printStackTrace();
			    }
	 }
	public void droptables(){
		Statement statement = null ;
		conn=this.getConnection();
		try {
			   statement = conn.createStatement();
			 
			  if (doesTableExists("personnels", conn)) {
		           	statement.addBatch("drop TABLE personnels ");
				  }
			  if (doesTableExists("personnelGroupes", conn)) {
		           	statement.addBatch("drop TABLE personnelGroupes ");
				  }
			  statement.executeBatch();
			  conn.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			if (statement != null) {
				statement.close();
		      }
		    } catch (SQLException e1) {
		      e1.printStackTrace();
		    }
		
		
	}
	 

}
