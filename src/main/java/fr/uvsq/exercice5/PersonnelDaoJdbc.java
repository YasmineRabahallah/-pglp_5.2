package fr.uvsq.exercice5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * class PersonnelDaoJdbc.
 * 
 * @author rabahallah yasmine.
 *
 */

public class PersonnelDaoJdbc implements Dao<Personnel> {

  /**
   * un attribut pour établire la connexion.
   */

  private Connection conn = null;
  /**
   * methode pour insérer un tuple dans la table personnels.
   * @param p Personnel.
   * @return p crée sinon null. 
   */

  @Override
  public Personnel create(Personnel p) {
    PreparedStatement statement = null;
    int rowsInserted = 0;
    conn = this.getConnection();
    String sql = "INSERT INTO personnels (nom, prenom, fonction, id_groupe) VALUES (?, ?, ?, ?)";
    try {
      statement = conn.prepareStatement(sql);
      statement.setString(1, p.getNom());
      statement.setString(2, p.getPrenom());
      statement.setString(3, p.getFonction());
      statement.setInt(4, p.getgroupeId());
      rowsInserted = statement.executeUpdate();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (statement != null) {
        statement.close();
      }

    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    if (rowsInserted > 0) {
      System.out.println("A new group was inserted successfully!");
      return p;
    } else {
      return null;
    }


  }

  /**
   * methode pour chercher un tuple dans la table personnels.
   * @param s le  nom du personnel.
   * @return p l'objet rechercher.
   */

  @Override
  public Personnel retrieve(String s) {
    PreparedStatement statement = null;
    Personnel p = null;
    conn = this.getConnection(); 
    String sql = "SELECT * FROM personnels where nom = (?)";
    try {
 
      statement = conn.prepareStatement(sql);
      statement.setString(1, s);
      statement.execute();
      ResultSet result = statement.getResultSet();

      if (result.next()) {
        String nom = result.getString("nom");
        String prenom = result.getString("prenom");
        String fonction = result.getString("fonction");
        int groupeId = result.getInt("id_groupe");
        p = new Personnel.Builder(nom,prenom,fonction).groupeId(groupeId).build();
      }
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }



    return p;
  }

  /**
   * methode pour modifier des tuples dans la table personnels.
   * @param p Personnel.
   * @return p .
   */

  @Override
  public Personnel update(Personnel p) {
    PreparedStatement statement = null;
    conn = this.getConnection();
    String sql = "UPDATE personnels SET  prenom=?, fonction=? , id_groupe=?  WHERE nom=?";

    try {
      statement = conn.prepareStatement(sql);
      statement.setString(1,p.getPrenom());
      statement.setString(2,p.getFonction());
      statement.setInt(3, p.getgroupeId());
      statement.setString(4,p.getNom());

      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("An existing personnel was updated successfully!");
      }
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }


    return p;
  }
  /**
   * methode pour supprimer des tuples dans la table personnels.
   * @param p Personnel.
   */

  @Override
  public void delete(Personnel p) {
    PreparedStatement statement = null;
    conn = this.getConnection();
    String sql = "DELETE FROM personnels WHERE nom=?";


    try {
      statement = conn.prepareStatement(sql);
      statement.setString(1, p.getNom());
      int rowsDeleted = statement.executeUpdate();
      if (rowsDeleted > 0) {
        System.out.println("A personnel was deleted successfully!");
      } else {
        System.out.println("element does not exist");
      }
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * methode pour Connecter à la base de données.
   * 
   */

  public Connection getConnection() {
    Connection conn = null;
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
