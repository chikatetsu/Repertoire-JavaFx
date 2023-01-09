package com.example.repertoire;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Rep_Controlleur implements Initializable {
	private Repertoire rep;
	private int numCourant;
	@FXML private TextField nom, prenom, rue, cp, ville, tel;
	@FXML private Button bAjout, bAffPrec, bAffSuiv, bQuit;
	private Connection conn;
	private Statement statement;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		rep = new Repertoire();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Chargement du driver réussi");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/repertoire","root","");
			System.out.println("Connexion à la base de données réussi");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Erreur de chargement du driver : "+e);
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la connextion à la base de données : "+e);
		}

		try {
			statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM contact");
			while(res.next()) {
				Adresse adresse = new Adresse(res.getString("adresse"), res.getString("cp"), res.getString("ville"));
				Personne personne = new Personne(res.getString("nom"), res.getString("prenom"), adresse, res.getString("tel"));
				rep.ajoutePersonne(personne);
			}
			res.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		numCourant = 0;
		setTextFromPersonne(numCourant);
	}

	/**Ajoute une personne au répertoire avec les informations du formulaire*/
	public void ajouter(ActionEvent event) {
		int res;
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO contact(nom, prenom, adresse, ville, cp, tel) values (?,?,?,?,?,?)");
			statement.setString(1, nom.getText());
			statement.setString(2, prenom.getText());
			statement.setString(3, rue.getText());
			statement.setString(4, ville.getText());
			statement.setString(5, cp.getText());
			statement.setString(6, tel.getText());
			res = statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		if(res == 1) {
			(new Alert(AlertType.INFORMATION, "L'ajout a bien été réalisé")).show();
			rep.ajoutePersonne(new Personne(nom.getText(), prenom.getText(),
					new Adresse(rue.getText(), cp.getText(), ville.getText()), tel.getText()));
			System.out.println(nom.getText()+" "+prenom.getText()+" a été ajouté au répertoire à l'index "+(rep.taille()-1));
			setTextToBlanck();
			numCourant = rep.taille();
		}
		else {
			(new Alert(AlertType.ERROR, "L'ajout n'a pas été réalisé")).show();
			System.err.println("L'ajout n'a pas été réalisé");
		}
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
	public void quitter(ActionEvent event) throws SQLException {
		System.out.println("Fermeture de la fenêtre");
		statement.close();
		conn.close();
		Platform.exit();
	}
}
