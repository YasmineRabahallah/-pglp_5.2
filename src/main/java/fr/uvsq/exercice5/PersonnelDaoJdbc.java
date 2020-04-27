package fr.uvsq.exercice5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonnelDaoJdbc implements Dao<Personnel> {
	 private Connection conn = null;
	@Override
	public Personnel create(Personnel p) {
		conn=this.getConnection();
		String sql = "INSERT INTO personnels (nom, prenom, fonction, id_groupe) VALUES (?, ?, ?, ?)";
		 
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, p.getNom());
			statement.setString(2, p.getPrenom());
			statement.setString(3, p.getFonction());
			statement.setInt(4, p.getgroupeId());
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new personnel was inserted successfully!");
			}
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	return p;
	}

	@Override
	public Personnel retrieve(String s) {
	Personnel p = null ;
	        conn=this.getConnection();
			String sql = "SELECT * FROM personnels where nom = (?)";
			 try {
				 
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, s);
				statement.execute();
			      ResultSet result = statement.getResultSet();

				if (result.next()){
				    String nom = result.getString("nom");
				    String prenom= result.getString("prenom");
				    String fonction = result.getString("fonction");
				    int groupeId = result.getInt("id_groupe");
				    p = new Personnel.Builder(nom,prenom,fonction).groupeId(groupeId).build();
				}
				conn.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
		
		return p;
	}


	@Override
	public Personnel update(Personnel p) {
		conn=this.getConnection();
		String sql = "UPDATE personnels SET  prenom=?, fonction=? , id_groupe=?  WHERE nom=?";
		 
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,p.getPrenom());
			statement.setString(2,p.getFonction());
			statement.setInt(3, p.getgroupeId());
			statement.setString(4,p.getNom());
			 
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing personnel was updated successfully!");
			}
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return p;
	}

	@Override
	public void delete(Personnel p) {
		    conn=this.getConnection();
			String sql = "DELETE FROM personnels WHERE nom=?";
			 
			
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, p.getNom());
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
				    System.out.println("A personnel was deleted successfully!");
				} else {
					 System.out.println("element does not exist");
				}
				conn.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

}
