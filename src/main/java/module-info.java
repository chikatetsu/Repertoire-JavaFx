module com.example.repertoire {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens com.example.repertoire to javafx.graphics, javafx.fxml;
}
