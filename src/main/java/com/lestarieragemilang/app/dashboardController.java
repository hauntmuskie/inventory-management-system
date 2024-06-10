package com.lestarieragemilang.app;

import javax.swing.JOptionPane;

import com.lestarieragemilang.app.Utilities.CacheService;
import com.lestarieragemilang.app.Utilities.Redirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class dashboardController {

    @FXML
    private AnchorPane setScene;


    @FXML
    void kategoriTab(ActionEvent event) {

    }

    @FXML
    void pelangganTab(ActionEvent event) {

    }

    @FXML
    void pembelianTab(ActionEvent event) {

    }

    @FXML
    void penerimaanTab(ActionEvent event) {

    }

    @FXML
    void penjualanTab(ActionEvent event) {

    }

    @FXML
    void stokBesiTab(ActionEvent event) {

    }

    @FXML
    void supplierTab(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        Redirect.page("data", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void isExitApp(MouseEvent event) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @FXML
    void setSceneData(MouseEvent event) {
        Redirect.page("data", setScene, getClass());
        CacheService.clear();
    }

}
