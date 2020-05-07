package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	private ObservableList<Nerc> NERC= FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Nerc> bntNerc;

    @FXML
    private TextField txtAnni;

    @FXML
    private TextField txtOre;

    @FXML
    private Button btnCase;

    @FXML
    void doNerc(ActionEvent event) {
    }

    @FXML
    void doWorstCase(ActionEvent event) {

    	txtAnni.setDisable(false);
    	txtOre.setDisable(false);
    	txtResult.setDisable(false);
    	
    	Nerc nerc=bntNerc.getValue();
    	
    	if(nerc==null) {
    		txtResult.setText("Devi scegliere una zona");
    		return ;
    	}
    	
    	String mAnni=txtAnni.getText();
    	
    	Integer anni;
    	
    	try {
    		anni=Integer.parseInt(mAnni);
    	}catch(NumberFormatException e) {
    		
    		txtResult.setText("Devi inserire solo numeri nel campo Max years");
    		return ;
    	}
    	
        String mOre=txtOre.getText();
    	
    	Integer ore;
    	
    	try {
    		ore=Integer.parseInt(mOre);
    	}catch(NumberFormatException e) {
    		
    		txtResult.setText("Devi inserire solo numeri nel campo Max hours");
    		return ;
    	}
    	
    	Nerc n= bntNerc.getValue();
    	
    	List<PowerOutages> p=this.model.calcoloBlackOut(n, anni, ore);
    	
    	txtResult.appendText("Caso peggiore per un massimo di "+anni+" anni e "+ore+" ore\n");
    	txtResult.appendText("Totale persone colpite: "+this.model.getFolla(p)+"\n");
    	txtResult.appendText("Totale ore di blackout: "+this.model.getDurata(p)+"\n");
    	
    	for(PowerOutages po: p) {
    		txtResult.appendText(po.toString()+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bntNerc != null : "fx:id=\"bntNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnni != null : "fx:id=\"txtAnni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOre != null : "fx:id=\"txtOre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCase != null : "fx:id=\"btnCase\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
        txtResult.setDisable(true);
		
		NERC.addAll(model.getNercList());
    	bntNerc.setItems(NERC);
    	bntNerc.setValue(NERC.get(0));
		
		this.model=model;        
	}
}

