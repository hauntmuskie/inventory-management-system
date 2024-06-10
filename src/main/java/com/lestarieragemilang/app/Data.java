package com.lestarieragemilang.app;

import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.lestarieragemilang.app.Entities.StockEntity;
import com.lestarieragemilang.app.Repositories.StockRepositories;
import com.lestarieragemilang.app.Utilities.CacheService;
import com.lestarieragemilang.app.Utilities.Redirect;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Data extends StockRepositories {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton stockActionButton;

    @FXML
    private TextField stockIdField;

    @FXML
    private TextField stockPurchasePriceField;

    @FXML
    private TextField stockSellPriceField;

    @FXML
    private TextField stockSizeField;

    @FXML
    private TextField stockUnitField;

    @FXML
    private TextField stockAmountField;

    @FXML
    private TextField categoryIdField;

    @FXML
    void stockActionButton(MouseEvent event) {
        StockEntity entity = new StockEntity();

        entity.setStockId(stockIdField.getText());
        entity.setCategoryId(categoryIdField.getText());
        entity.setStockSellPrice(stockSellPriceField.getText());
        entity.setStockPurchasePrice(stockPurchasePriceField.getText());
        entity.setStockSize(stockSizeField.getText());
        entity.setStockAmount(stockAmountField.getText());
        entity.setStockUnit(stockUnitField.getText());

        // Update Stock Data
        if (stockActionButton.getText().equals("Edit Data")) {
            Map<String, Object> response = this
                    .updateStockRepository(entity);

            if ((boolean) response.get("result")) {
                Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
                confirmationDialog.getDialogPane().setPrefSize(450, 250);

                confirmationDialog.setTitle("Berhasil Memperbarui Data.");
                confirmationDialog.setHeaderText((String) response.get("message"));

                confirmationDialog.getButtonTypes()
                        .setAll(ButtonType.YES);

                confirmationDialog.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        Redirect.page("stock", anchorPane, getClass());
                        CacheService.clear();
                    }
                });

            } else {
                Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
                confirmationDialog.getDialogPane().setPrefSize(450, 250);

                confirmationDialog.setTitle("Gagal Memperbarui Data.");
                confirmationDialog.setHeaderText((String) response.get("message"));

                confirmationDialog.getButtonTypes().setAll(ButtonType.YES);
                confirmationDialog.showAndWait();
            }

            // Add Stock Data
        } else if (stockActionButton.getText().equals("Tambah Data")) {
            Map<String, Object> response = this
                    .createStockRepository(entity);

            if ((boolean) response.get("result")) {
                Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
                confirmationDialog.getDialogPane().setPrefSize(450, 250);

                confirmationDialog.setTitle("Berhasil Menambahkan Data.");
                confirmationDialog.setHeaderText((String) response.get("message"));

                confirmationDialog.getButtonTypes()
                        .setAll(ButtonType.YES);

                confirmationDialog.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        Redirect.page("stock", anchorPane, getClass());
                        CacheService.clear();
                    }
                });

            } else {
                Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
                confirmationDialog.getDialogPane().setPrefSize(450, 250);

                confirmationDialog.setTitle("Gagal Menambahkan Data.");
                confirmationDialog.setHeaderText((String) response.get("message"));

                confirmationDialog.getButtonTypes().setAll(ButtonType.YES);
                confirmationDialog.showAndWait();
            }
        }
    }

    private void inputHandler() {
        String stockId = (String) CacheService.get("stockId");
        if (stockId != null) {
            stockIdField.setText(stockId);
            categoryIdField.setText((String) CacheService.get("categoryId"));
            stockSellPriceField.setText((String) CacheService.get("stockSellPrice"));
            stockPurchasePriceField.setText((String) CacheService.get("stockPurchasePrice"));
            stockSizeField.setText((String) CacheService.get("stockSize"));
            stockAmountField.setText((String) CacheService.get("stockAmount"));
            stockUnitField.setText((String) CacheService.get("stockUnit"));
        }
    }

    @FXML
    public void initialize() {
        if (CacheService.get("stockId") != null) {
            stockActionButton.setText("Edit Data");
            stockIdField.setEditable(false);

        } else {
            stockActionButton.setText("Tambah Data");
        }

        inputHandler();
    }

}
