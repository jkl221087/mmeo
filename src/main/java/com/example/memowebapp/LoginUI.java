package com.example.memowebapp;

import com.example.memowebapp.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUI extends Application {

    private User currentUser;

    @Override
    public void start(Stage primaryStage) {
        // 设置界面主要布局
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 400, 300);

        // 加载CSS样式表
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // 创建用户界面元素
        Label titleLabel = new Label("Memo");
        titleLabel.getStyleClass().add("title-label");

        // 帳號密碼輸入框
        TextField usernameField = new TextField();
        usernameField.setPromptText("帳號");
        usernameField.getStyleClass().add("input-field");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("密碼");
        passwordField.getStyleClass().add("input-field");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("確認密碼");
        confirmPasswordField.getStyleClass().add("input-field");

        // 註冊邏輯
        Button registerButton = new Button("註冊");
        registerButton.getStyleClass().add("button");
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (!password.equals(confirmPassword)) {
                showAlert("密碼不一致", "請確認密碼一致性");
                return;
            }

            DatabaseHelper.registerUser(username, password);
            showAlert("註冊成功", "您已經成功註冊");

            // 重設輸入框
            usernameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();

            confirmPasswordField.setVisible(false);
        });

        // 登入邏輯
        Button loginButton = new Button("登入");
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // 查询用户
            String query = "SELECT * FROM user WHERE username = ?";
            try (Connection conn = DatabaseHelper.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");

                        if (storedPassword.equals(password)) {
                            User user = new User();
                            user.setUsername(username);
                            user.setPassword(storedPassword);
                            currentUser = user;
                            showAlert("登入成功", "歡迎回來，" + user.getUsername() + "！");

                            MainUI mainUI = new MainUI(currentUser);
                            mainUI.start(primaryStage);  // 切換到主界面
                        } else {
                            showAlert("登入失敗", "密碼不正確。");
                        }
                    } else {
                        showAlert("登入失敗", "找不到用戶 " + username + "。");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert("登入錯誤", "登入過程中發生錯誤。");
            }
        });

        HBox buttonsBox = new HBox(10, registerButton, loginButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // 将输入框和按钮添加到布局
        root.getChildren().addAll(titleLabel, usernameField, passwordField, confirmPasswordField, buttonsBox);

        // 显示场景
        primaryStage.setScene(scene);
        primaryStage.setTitle("商業備忘錄");
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
