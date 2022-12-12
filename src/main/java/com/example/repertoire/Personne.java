package com.example.repertoire;
public class Personne implements Comparable<Personne>
{
	//Atributs
	private String nom, prenom, telephone;
	private Adresse adresse;
	
	//Constructeurs
	public Personne(String nom, String prenom)
	{
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = new Adresse();
		this.telephone = "";
	}
	public Personne(String nom, String prenom, Adresse adresse, String telephone)
	{
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	
	//Accesseurs
	public String getNom() {return nom;}
	public String getPrenom() {return prenom;}
	public Adresse getAdresse() {return adresse;}
	public String getTelephone() {return telephone;}
	
	//Modificateurs
	public void setNom(String nom) {this.nom = nom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	public void setTelephone(String telephone) {this.telephone = telephone;}
	
	/**Affiche les coordonnées de la personne*/
	public void affiche()
	{
		System.out.println(nom+" "+prenom+" habite au "+adresse+". Son numéro de téléphone est le "+telephone);
	}
	
	/**Affiche tout les attibuts de la classe sous la forme nom; prenom; adresse; telephone*/
	public String toString()
	{
		return "nom="+nom+"; prenom="+prenom+"; adresse=("+adresse+"); telephone="+telephone;
	}
	
	/**Methode de l'interface Comparable
	 * - si this < parametre
	 * 0 si this = parametre
	 * + si this > parametre*/
	public int compareTo(Personne personne)
	{
		if (nom.compareTo(personne.getNom()) == 0)
			return prenom.compareTo(personne.getPrenom());
		else
			return nom.compareTo(personne.getNom());
	}
}