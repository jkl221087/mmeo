package com.example.memowebapp;

import com.example.memowebapp.model.Memo;
import com.example.memowebapp.model.User;
import com.example.memowebapp.repository.InMemoryMemoRepository;
import com.example.memowebapp.repository.MemoRepository;
import com.example.memowebapp.repository.UserRepository;
import com.example.memowebapp.service.MemoService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainUI extends Application {

    private User currentUser;
    private MemoService memoService;

    public MainUI(User user) {
        this.currentUser = user;
        UserRepository userRepository = new UserRepository();
        MemoRepository memoRepository = new InMemoryMemoRepository();
        this.memoService = new MemoService(memoRepository, userRepository);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox memoBox = new VBox();
        memoBox.setPadding(new Insets(10));

        // 設置備忘錄顯示區域
        refreshMemoList(memoBox, currentUser.getId());

        // 創建登出按鈕
        Button logoutButton = new Button("登出");
        logoutButton.setOnAction(e -> {
            currentUser = null;
            LoginUI loginUI = new LoginUI();
            loginUI.start(primaryStage);  // 返回登入介面
        });

        root.setTop(logoutButton);
        root.setCenter(memoBox);

        Scene loggedInScene = new Scene(root, 1270, 600);
        primaryStage.setScene(loggedInScene);
    }

    private void refreshMemoList(VBox vbox, Long userId) {
        vbox.getChildren().clear();

        // 獲取用戶的備忘錄
        List<Memo> memos = memoService.getMemosByUserId(userId);
        for (Memo memo : memos) {
            Label memoLabel = new Label(memo.getTitle() + ": " + memo.getContent());
            vbox.getChildren().add(memoLabel);
        }
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
