package fr.uvsq.exercice5;

public interface Iterator {
  /**
   * methode pour retourner s'il y a encore d 'element.
   *
   * @return un booleen si il ya un element dans notre liste.
   */

  boolean hasNext();
  /**
   * methode qui retourne l'element suivant.
   *
   * @return return l'element suivant.
   */

  Ipersonnel next();
}
