package com.example.repertoire;
import java.util.ArrayList;
import java.util.Collections;

public class Repertoire {
	//Attributs
	private ArrayList<Personne> list;
	
	//Constructeurs
	public Repertoire() {
		this.list = new ArrayList<Personne>();
	}
	
	/**Affiche toutes les personnes dans le répertoire*/
	public String toString() {
		String txt = "Le répertoire contient :\n";
		for(Personne i : list)
			txt += i+"\n";
		return txt;
	}
	
	/**Ajoute une personne dans le répertoire*/
	public void ajoutePersonne(Personne personne) {
		list.add(personne);
		Collections.sort(list);
	}
	
	/**Recherche une personne dans le répertoire à partir du nom donné en paramètre*/
	public Personne rechercheNom(String nom) {
		for(Personne i : list)
			if(i.getNom().equals(nom))
				return i;
		return null;
	}
	
	/**Recherche une personne dans le répertoire à partir de son nom et de son prénom donné en paramètre*/
	public Personne rechercheNomPrenom(String nom, String prenom) {
		for(Personne i : list)
			if(i.getNom().equals(nom) && i.getPrenom().equals(prenom))
				return i;
		return null;
	}
	
	/**Retourne la personne à l'index donné en paramètre*/
	public Personne recherchePersonne(int index)
	{
		return list.get(index);
	}
	
	/**Retourne la taille du répertoire courant*/
	public int taille()
	{
		return list.size();
	}
}




















