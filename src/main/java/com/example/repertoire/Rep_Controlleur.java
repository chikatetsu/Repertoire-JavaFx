package com.example.repertoire;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Rep_Controlleur {
	private Repertoire rep = new Repertoire();
	private int numCourant = 0;
	@FXML private TextField nom, prenom, rue, cp, ville, tel;
	@FXML private Button bAjout, bAffPrec, bAffSuiv, bQuit;
	
	/**Ajoute une personne au répertoire avec les informations du formulaire*/
	public void ajouter(ActionEvent event) {
		rep.ajoutePersonne(new Personne(nom.getText(), prenom.getText(),
				new Adresse(rue.getText(), cp.getText(), ville.getText()), tel.getText()));
		System.out.println(nom.getText()+" "+prenom.getText()+
				" a été ajouté au répertoire à l'index "+(rep.taille()-1));
		setTextToBlanck();
		numCourant = rep.taille();
	}
	
	/**Affiche la personne à l'index précédent*/
	public void precedent(ActionEvent event) {
		if(numCourant > 0) {
			numCourant--;
			setTextFromPersonne(numCourant);
		}
		else {
			System.out.println("Vous êtes déjà à l'index 0");
			(new Alert(AlertType.ERROR, "Vous êtes déjà à l'index 0")).show();
		}
	}
	
	/**Affiche la personne à l'index suivant*/
	public void suivant(ActionEvent event) {
		if(numCourant < rep.taille()) {
			numCourant++;

			if(numCourant == rep.taille()) {
				setTextToBlanck();
			}
			else {
				setTextFromPersonne(numCourant);
			}
		}
		else {
			System.out.println("Vous êtes déjà à l'index maximal");
			(new Alert(AlertType.ERROR, "Vous êtes déjà à l'index maximal")).show();
		}
	}

	/**Affiche à l'écran la personne à l'index numCourant*/
	private void setTextFromPersonne(int numCourant) {
		Personne p = rep.recherchePersonne(numCourant);

		nom.setText(p.getNom());
		prenom.setText(p.getPrenom());
		tel.setText(p.getTelephone());
		rue.setText(p.getAdresse().getRue());
		cp.setText(p.getAdresse().getCodePostal());
		ville.setText(p.getAdresse().getVille());

		System.out.println("Affichage de l'index "+numCourant);
	}

	/**Efface les champs de texte*/
	private void setTextToBlanck() {
		nom.setText("");
		prenom.setText("");
		rue.setText("");
		cp.setText("");
		ville.setText("");
		tel.setText("");
	}

	/**Quitte l'application lorsque le bouton Quitter est actionné*/
	public void quitter(ActionEvent event) {
		System.out.println("Fermeture de la fenêtre");
		Platform.exit();
	}
}
