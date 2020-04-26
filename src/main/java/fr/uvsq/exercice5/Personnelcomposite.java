package fr.uvsq.exercice5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 * Classe personnelcomposite.
 * 
 * @author rabahallah yasmine.
 *
 */

public class Personnelcomposite  implements Ipersonnel,Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * liste des personnels.
   */

  private  ArrayList<Ipersonnel>  personnes;
  /**
   * id de groupe de personnels. 
   */

  private int id;
  /**
   * constructeur de personnel_composite.
   * 
   * @param id de groupe. 
   */

  public Personnelcomposite(int id) {
    this.id = id;
    personnes = new ArrayList<Ipersonnel>();
  }
  /**
   * ajouter un personnel.
   *
   * @param personnel personnel a ajouter.
   */

  public void add(Ipersonnel personnel) {
    personnes.add(personnel);
  }
  /**
   * supprimer un personnel.
   *
   * @param personnel personnel a supprimer.
   */
     
  public void remove(Ipersonnel personnel) {
    if (personnes.contains(personnel)) {
      personnes.remove(personnel);
    } else {
      System.out.println("l'element n'existe pas ");
    }

  }

  /**
   * methode getID.
   * 
   * @return id de groupe personnels.
   */

  public int getid() {
    return id;
  }
  public void setid(int id) {
	    this.id =id ;
	  }
  /**
   * affichage les informations de tous les personnels du groupe. 
   */

  public String print() {
    StringBuffer sb = new StringBuffer();
    for (Ipersonnel p :personnes) {
      sb.append(" ");
      sb.append(p.print());
    }
    return "id groupe =" + id + " " + sb;
  }

  /**
   * methode retourne liste.
   * 
   * @return listePersonnel liste des personnels. 
   */
  
  public ArrayList<Ipersonnel> getPersonnes() {
    return personnes;
  }
}


