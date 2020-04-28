package fr.uvsq.exercice5;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *  la classe personnel.
 *
 * @author rabahallah yasmine.
 *
 */

public  final class Personnel  implements Ipersonnel,Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * nom d une personne. 
   */

  private final String nom;
  /**
   * prenom d une personne. 
   */

  private final  String prenom;
  /**
   * telephone.
   */

  private    ArrayList<String> telephone;
  /**
   * fonctions.
   */

  private final  String fonction;
  /**
   * la date de naissance.
   */

  private  LocalDate  dateDeNaissance;
  /**
   * id_groupe.
   */
  private int groupeId;


  public String getNom() {
    return nom;
  }

  public int getgroupeId() {
    return groupeId;
  }

  public String getPrenom() {
    return prenom;
  }

  public LocalDate getDateDeNaissance() {
    return dateDeNaissance;
  }

  public String getFonction() {
    return fonction;
  }

  public ArrayList<String> getTelephone() {
    return telephone;
  }

  private Personnel(Builder builder) {

    this.nom =  builder.nom;
    this.prenom =  builder.prenom;
    this.fonction =  builder.fonction;
    this.dateDeNaissance = builder.dateDeNaissance;
    this.telephone = builder.telephone;
    this.groupeId = builder.groupeId;

  }

  public static class Builder {

    /**
     * nom de la personne. 
     */

    private final String nom;
    /**
     * prenom de la personne.
     */

    private final String prenom;
    /**
     * fonction de la personne.
     */

    private final String fonction;
    /**
     * la date de naissance.
     */
    private LocalDate dateDeNaissance; 
    /**
     * id groupe.
     */
    private int groupeId;
    /**
     * numero de tel de la personne.
     */

    private  ArrayList<String> telephone = new ArrayList<String>();
    /**
     * constructeur.
     * 
     * @param nom de personnel.
     * @param prenom de personnel.
     * @param fonction de personnel.
     */
    
    public Builder(String nom,String prenom,String fonction) {
      this.nom = nom;
      this.prenom = prenom;
      this.fonction = fonction;

    }
    /**
     * methode pour recuperer la date.
     * 
     * @param dateDeNaissance date.
     * @return instance.
     */

    public Builder date(LocalDate  dateDeNaissance) {
      this. dateDeNaissance =  dateDeNaissance;
      return this;
    }
    
    public Builder groupeId(int groupeId) {
      this. groupeId = groupeId;
      return this;
    }
    /**
     * methode pour telephone.
     * 
     * @param telephone number de telephone.
     * @return instance.
     */

    public Builder telephone(String  telephone) {
      this.telephone.add(telephone);
      return this;
    }
    /**
     * methode.
     * 
     * @return new objet personnel.
     */

    public Personnel build() {
      return new Personnel(this);
    }
  }
  
  public String print() {
    return "name " + this.getNom();
  }
  
  public ArrayList<Ipersonnel> getPersonnes() {
    return null;
  }

}
