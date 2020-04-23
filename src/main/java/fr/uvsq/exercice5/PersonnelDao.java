package fr.uvsq.exercice5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * class PersonnelDao qui implement Dao.
 * 
 * @author rabahallah yasmine.
 *
 */

public class PersonnelDao implements Dao<Personnel> {

  /**
   * methode pour creer l objet serialisé et le metre dans un fichier.
   */

  public Personnel create(Personnel obj) {
    try {
      FileOutputStream fos = new FileOutputStream(obj.getNom());
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(obj);
      oos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {

      e.printStackTrace();
    }
    return obj;
  }

  /**
   * methode pour trouver l objet dans le fichier s.
   */

  public Personnel retrieve(String s) {
    File f = new File(s);
    Personnel p = null;
    if (f.exists()) {
      try {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        p = (Personnel) ois.readObject();
        ois.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {

        e.printStackTrace();
      }
    } else {
      System.out.println("Does not Exists");
    }
    return p;
  }

  /**
   * methode pour modifier object obj.
   */

  public Personnel update(Personnel obj) {
    File f = new File(obj.getNom());
    if (f.exists()) {
      try {
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {

        e.printStackTrace();
      }
    } else {
      System.out.println("Does not Exists");
    }


    return obj;
  }

  /**
   * methode delete pour supprimer le ficher qui contien l'object obj.
   */

  public void delete(Personnel obj) {
    File f = new File(obj.getNom());
    if (f.exists() && f.delete()) {
      System.out.println("deleted file");
    } else {
      System.out.println("Does not Exists");
    }
  }



}
