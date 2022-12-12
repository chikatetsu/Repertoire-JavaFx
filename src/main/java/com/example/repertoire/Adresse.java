package com.example.repertoire;
public class Adresse
{
	//Attributs
	private String rue, codePostal, ville;
	
	//Constructeurs
	public Adresse()
	{
		this.rue = "";
		this.codePostal = "";
		this.ville = "";
	}
	public Adresse(String rue, String codePostal, String ville)
	{
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	//Accesseurs
	public String getRue() {return rue;}
	public String getCodePostal() {return codePostal;}
	public String getVille() {return ville;}
	
	//Modificateurs
	public void setRue(String rue) {this.rue = rue;}
	public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
	public void setVille(String ville) {this.ville = ville;}
	
	/**Affiche l'adresse*/
	public void affiche()
	{
		System.out.println("rue "+rue+" dans la ville de "+ville+" ("+codePostal+")");
	}
	
	/**Affiche tout les attibuts de la classe sous la forme rue, codePostal, ville*/
	public String toString()
	{
		return "rue="+rue+", codePostal="+codePostal+", ville="+ville;
	}
}