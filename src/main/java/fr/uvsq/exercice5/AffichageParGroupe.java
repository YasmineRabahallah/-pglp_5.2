package fr.uvsq.exercice5;

import java.util.ArrayList;
/**
 * Classe AffichageParGroupe.
 * 
 * @author rabahallah yasmine.
 *
 */

public class AffichageParGroupe   {
  private  ArrayList<Ipersonnel> list;
  /**
   * methode pour affichge.
   *
   *@param root  la racine de hiérarchique de l’organisation.
   */

  public AffichageParGroupe(Ipersonnel root) {
    list = new ArrayList<Ipersonnel>();
    this.list.add(root);
  }
  /**
  * retuen iterator.
  *
  *@return Iretator.
  */

  public Iterator getIterator() {
    return new GroupeIterator();
  }
  /**
   * classe prive GroupeIterator.
   * 
   * @author rabahallah yasmine.
   *
   */

  private class GroupeIterator implements Iterator {
    public GroupeIterator() {
      int verifie = 0;
      while (verifie < list.size()) {
        if (list.get(verifie) instanceof Personnelcomposite) {
          for (Ipersonnel p : list.get(verifie).getPersonnes()) {
            list.add(p);
          }
        }
        verifie++;
      }
    }

    private int position = 0;

    public boolean hasNext() {
      if (position < list.size()) {

        return true;
      }
      return false;
    }

    public Ipersonnel next() {
      if (this.hasNext()) {
        Ipersonnel p = list.get(position++);
        return p;
      } else {
        return null;
      }
    }
  }
}
