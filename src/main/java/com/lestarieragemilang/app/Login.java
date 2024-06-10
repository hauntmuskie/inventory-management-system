package com.lestarieragemilang.app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.lestarieragemilang.app.Utilities.Redirect;
import com.lestarieragemilang.app.Repositories.AuthRepositories;

public class Login extends AuthRepositories {
  @FXML
  private Label greetingText;

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label localTimeApp;

  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  private static final NavigableMap<Integer, String> GREETINGS = new TreeMap<>();

  static {
    GREETINGS.put(0, "Selamat pagi, Admin!");
    GREETINGS.put(12, "Selamat siang, Admin!");
    GREETINGS.put(15, "Selamat sore, Admin!");
    GREETINGS.put(18, "Selamat malam, Admin!");
  }

  public void initialize() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
      LocalTime currentTime = LocalTime.now();
      localTimeApp.setText("Current Time: " + currentTime.format(formatter));
    }),
        new KeyFrame(Duration.seconds(1)));
    clock.setCycleCount(Timeline.INDEFINITE);
    clock.play();
  }

  @FXML
  private void loginToApp() throws IOException {
    boolean validation = loginRepo(
        username.getText(), password.getText());

    if (validation) {
      Redirect.page("dashboard", anchorPane, getClass());
    } else {
      Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
      confirmationDialog.getDialogPane().setPrefSize(450, 250);

      confirmationDialog.setTitle("Login Gagal");
      confirmationDialog.setHeaderText("Username Atau Password Anda Salah !!!...");

      confirmationDialog.getButtonTypes().setAll(ButtonType.YES);
      confirmationDialog.showAndWait();
    }
  }

  @FXML
  public void closeApp() {
    System.exit(0);
  }
}