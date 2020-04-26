package fr.uvsq.exercice5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonnelCopositeDaoJdbc implements Dao<Personnelcomposite> {
    private Connection conn=null;
	@Override
	public Personnelcomposite create(Personnelcomposite obj) {
		conn=this.getConnection();
		String sql = "INSERT INTO personnelGroupes (id_groupe) VALUES (?)";
		 
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,obj.getid()); 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new group was inserted successfully!");
			}
			List<Ipersonnel> l = obj.getPersonnes();
			int verifie = 0;
		      while (verifie < l.size()) {
		        if (l.get(verifie) instanceof Personnelcomposite) {
		           this.create((Personnelcomposite)l.get(verifie));
		        }
		        verifie++;
		      }
		      statement.close();
		      conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return obj;
	}

	@Override
	public Personnelcomposite retrieve(String s) {
		Personnelcomposite p = null ;
        conn=this.getConnection();
		String sql = "select * from personnelGroupes where id_groupe = (?)";
		 try {
		
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, s);
			statement.execute();
		      ResultSet result = statement.getResultSet();
			

			if (result.next()){
			    int id_groupe = result.getInt("id_groupe");
			    p = new Personnelcomposite(id_groupe);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Personnelcomposite update(Personnelcomposite p) {
		conn=this.getConnection();
		String sql = "UPDATE personnelGroupes SET  id_groupe=?";
		 
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,p.getid());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing group was updated successfully!");
			}
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return p;
	}

	@Override
	public void delete(Personnelcomposite p) {
		conn=this.getConnection();
		String sql = "Delete from personnelGroupes where  id_groupe=?";
		 
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,p.getid());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("A group was deleted successfully!!");
			}
			statement.close();
			conn.close();
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
