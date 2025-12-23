package com.project.charforgefinal.service.impl.process;

import com.project.charforgefinal.config.interfaces.ControllerInitializer;
import com.project.charforgefinal.controller.ItemLoadoutController;
import com.project.charforgefinal.controller.PaperDollController;
import com.project.charforgefinal.model.entity.character.PlayerCharacter;
import com.project.charforgefinal.service.interfaces.process.IMessageService;
import com.project.charforgefinal.service.interfaces.process.INavigationService;
import com.project.charforgefinal.utils.Logs;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationService implements INavigationService {
    private final Stage stage;
    private final ControllerInitializer initializer;
    private final IMessageService message;

    public NavigationService(Stage stage, ControllerInitializer initializer, IMessageService message) {
        this.stage = stage;
        this.initializer = initializer;
        this.message = message;
    }

    @Override
    public void goToMainMenu() {
        navigate("/com/project/charforgefinal/view/main_menu.fxml");
    }

    @Override
    public void goToCharacterCreation() {
        navigate("/com/project/charforgefinal/view/character_creation.fxml");
    }

    @Override
    public void goToPaperDoll(PlayerCharacter character) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/project/charforgefinal/view/paper_doll.fxml")
            );
            Parent root = loader.load();

            PaperDollController controller = loader.getController();
            initializer.initialize(controller);
            controller.setCharacter(character);

            setScene(root);
        } catch (IOException e) {
            Logs.printError("NavigationService Error", new Throwable());
            message.error("Error", "Navigation failed\n" + e.getMessage());
        }
    }

    @Override
    public void goToItemLoadout(PlayerCharacter character) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/project/charforgefinal/view/item_loadout.fxml")
            );
            Parent root = loader.load();

            ItemLoadoutController controller = loader.getController();
            initializer.initialize(controller);
            controller.setCharacter(character);

            setScene(root);
        } catch (IOException e) {
            Logs.printError("NavigationService Error", new Throwable());
            message.error("Navigation Error", "Navigation failed\n" + e.getMessage());
        }
    }

    @Override
    public void exitProgram() {
        Platform.exit();
    }

    private void navigate(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            initializer.initialize(loader.getController());

            setScene(root);
        } catch (IOException e) {
            message.error("Navigation Error", "Navigation failed\n" + e.getMessage());
            Logs.printError("NavigationService Error", new Throwable());
        }
    }

    private void setScene(Parent root) {
        Scene currentScene = stage.getScene();

        boolean isMaximized = stage.isMaximized();
        boolean isFullScreen = stage.isFullScreen();

        Scene nextScene;
        if (currentScene == null) {
            nextScene = new Scene(root);
        } else {
            nextScene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());
        }

        stage.setScene(nextScene);

        if (isMaximized) {
            stage.setMaximized(true);
        }
        if (isFullScreen) {
            stage.setFullScreen(true);
        }

        if (!isMaximized && !isFullScreen) {
            stage.centerOnScreen();
        }

        stage.show();
    }
}
