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
		PreparedStatement statement =null ;
		int rowsInserted =0;
		conn=this.getConnection();
		String sql = "INSERT INTO personnelGroupes (id_groupe,nom_groupe) VALUES (?,?)";
		 
		try {
			 statement = conn.prepareStatement(sql);
			statement.setInt(1,obj.getid()); 
			statement.setString(2,obj.getNom_groupe()); 
		   rowsInserted = statement.executeUpdate();
			
			List<Ipersonnel> l = obj.getPersonnes();
			int verifie = 0;
		      while (verifie < l.size()) {
		        if (l.get(verifie) instanceof Personnelcomposite) {
		           this.create((Personnelcomposite)l.get(verifie));
		        }
		        verifie++;
		      }
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
		if (rowsInserted > 0) {
		    System.out.println("A new group was inserted successfully!");
		    return obj;
		} else {
			 return null ;	
		}
	   
		
	}

	@Override
	public Personnelcomposite retrieve(String id) {
		PreparedStatement statement =null ;
		PreparedStatement statement_p = null;
		int id_g =  Integer.parseInt(id);
		Personnelcomposite pc = null ;
		Personnel p = null ;
        conn=this.getConnection();
		String sql = "select * from personnelGroupes where id_groupe = (?)";
		 try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id_g);
			statement.execute();
		    ResultSet result = statement.getResultSet();
			if (result.next()){
			    int id_groupe = result.getInt("id_groupe");
			    String nom_groupe = result.getString("nom_groupe");
			    pc = new Personnelcomposite(id_groupe,nom_groupe);
			 
			    String sql_p =  "SELECT * FROM personnels where  id_groupe = (?)";
			     statement_p = conn.prepareStatement(sql_p);
				statement_p.setInt(1,pc.getid());
				statement_p.execute();
			      ResultSet result_p = statement_p.getResultSet();

				while(result_p.next()){
				    String nom = result_p.getString("nom");
				    String prenom= result_p.getString("prenom");
				    String fonction = result_p.getString("fonction");
				    int groupeId = result_p.getInt("id_groupe");
				    p = new Personnel.Builder(nom,prenom,fonction).groupeId(groupeId).build();
				    pc.add(p);
			    }
			}
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
		 try{
				if (statement_p != null) {
					statement_p.close();
			      }
			    } catch (SQLException e1) {
			      e1.printStackTrace();
			    }
		return pc;
	}

	@Override
	public Personnelcomposite update(Personnelcomposite p) {
		PreparedStatement statement =null ;
		conn=this.getConnection();
		String sql = "UPDATE personnelGroupes SET nom_groupe=(?) where  id_groupe= (?)";
		 
		try {
			 statement = conn.prepareStatement(sql);
			statement.setString(1,p.getNom_groupe());
			statement.setInt(2,p.getid());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing group was updated successfully!");
			}
			statement.close();
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
		
		return p;
	}

	@Override
	public void delete(Personnelcomposite p) {
		PreparedStatement statement =null ;
		conn=this.getConnection();
		String sql = "Delete from personnelGroupes where  id_groupe=?";
		 
		try {
			 statement = conn.prepareStatement(sql);
			statement.setInt(1,p.getid());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("A group was deleted successfully!!");
			}
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
