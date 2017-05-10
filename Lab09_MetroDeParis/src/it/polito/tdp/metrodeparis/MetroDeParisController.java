package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.alg.DijkstraShortestPath;

import it.polito.tdp.metrodeparis.model.Fermata;

import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	Model m;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ComboBox<Fermata> comboArrivo;
    
    @FXML
    private ComboBox<Fermata> comboPartenza;

    @FXML
    private TextArea txtResult;

    @FXML
    void doPercorso(ActionEvent event) {
    	this.txtResult.setText(m.Dijkstra(comboPartenza.getValue(), comboArrivo.getValue()));

    }

    @FXML
    void initialize() {
        assert comboPartenza != null : "fx:id=\"comboPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert comboArrivo != null : "fx:id=\"comboArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
   

        
        
    }
    public void setModel(Model m){
    	this.m=m;
    	
   	this.comboPartenza.getItems().addAll(this.m.getFermate()) ;
    	this.comboArrivo.getItems().addAll(this.m.getFermate()) ;
    }
    
}
