package com.broadlyapplicable.splitpane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

public class SplitPaneViewController implements Initializable {
    
    private static final String SPLIT_PANE_DIVIDER = "SplitPaneDivider";
    private static final String NODE = "FXMLDocumentController";
    
    @FXML
    private SplitPane splitPane;
    
    @FXML
    private Label dividerLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Preferences pref = Preferences.userRoot().node(NODE);
                double splitPaneDivider = pref.getDouble(SPLIT_PANE_DIVIDER, .25);
                splitPane.setDividerPositions(splitPaneDivider);
            }
        });
        
        DoubleProperty dividerPositionProperty = splitPane.getDividers().get(0).positionProperty();
        dividerPositionProperty.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            dividerLabel.setText(newValue.toString());
            Preferences pref = Preferences.userRoot().node(NODE);
            pref.putDouble(SPLIT_PANE_DIVIDER, newValue.doubleValue());
        });
    }    
    
}
