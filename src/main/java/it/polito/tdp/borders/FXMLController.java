
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryAndNumber;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    @FXML
    private Button buttonRicerca;

    @FXML
    private ComboBox<Country> cmbNazioni;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int anno;
    	try {
    	 anno= Integer.parseInt(txtAnno.getText());
    	 if ((anno < 1816) || (anno > 2016)) {
				txtResult.setText("Inserire un anno nell'intervallo 1816 - 2016");
				return;
    	 }
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserire un anno nell'intervallo 1816 - 2016");
    		return;
    	}
    	this.model.buildgraph(anno);
    	List<CountryAndNumber> result=this.model.elencoStati();
    	System.out.println(result);
    	for(CountryAndNumber cn: result) {
    		cmbNazioni.getItems().add(cn.getCountry());
    		txtResult.appendText(cn+"\n");
    	}
    	txtResult.appendText("La quantità di componenti connesse è : "+this.model.componenteConnessa()+"\n");
    	}
    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	Country partenza = cmbNazioni.getValue();
		if(partenza==null) {
			txtResult.appendText("Errore: devu selezionare una nazione \n");
			return;
		}
		List<Country> result=this.model.paesiViciniIterativo(partenza);
		txtResult.appendText(result+"\n");

    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonRicerca != null : "fx:id=\"buttonRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbNazioni != null : "fx:id=\"cmbNazioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
