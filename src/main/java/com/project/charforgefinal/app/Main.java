package com.project.charforgefinal.app;

import com.project.charforgefinal.config.impl.AppControllerInitializer;
import com.project.charforgefinal.dao.impl.*;
import com.project.charforgefinal.dao.interfaces.*;
import com.project.charforgefinal.db.ConnectionProvider;
import com.project.charforgefinal.db.SQLiteConnectionProvider;
import com.project.charforgefinal.service.impl.characters.CharacterInventoryService;
import com.project.charforgefinal.service.impl.characters.CharacterService;
import com.project.charforgefinal.service.impl.items.EquipmentService;
import com.project.charforgefinal.service.impl.items.InventoryService;
import com.project.charforgefinal.service.impl.items.ItemService;
import com.project.charforgefinal.service.impl.process.MessageService;
import com.project.charforgefinal.service.impl.process.NavigationService;
import com.project.charforgefinal.service.impl.process.ValidationService;
import com.project.charforgefinal.service.impl.stats.EncumbranceService;
import com.project.charforgefinal.service.impl.stats.StatCalculator;
import com.project.charforgefinal.service.interfaces.characters.ICharacterInventoryService;
import com.project.charforgefinal.service.interfaces.characters.ICharacterService;
import com.project.charforgefinal.service.interfaces.items.IEquipmentService;
import com.project.charforgefinal.service.interfaces.items.IInventoryService;
import com.project.charforgefinal.service.interfaces.items.IItemService;
import com.project.charforgefinal.service.interfaces.process.IMessageService;
import com.project.charforgefinal.service.interfaces.process.INavigationService;
import com.project.charforgefinal.service.interfaces.process.IValidationService;
import com.project.charforgefinal.service.interfaces.stats.IEncumbranceService;
import com.project.charforgefinal.service.interfaces.stats.IStatCalculator;
import com.project.charforgefinal.utils.Logs;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Databases & DAOs
        ConnectionProvider provider = new SQLiteConnectionProvider();

        RaceDao raceDao = new RaceDaoImpl(provider);
        CharClassDao classDao = new CharClassDaoImpl(provider);
        CharacterDao characterDao = new CharacterDaoImpl(provider);
        InventoryDao inventoryDao = new InventoryDaoImpl(provider);
        ItemDao itemDao = new ItemDaoImpl(provider);

        // Services
        IMessageService messageService = new MessageService();
        IItemService itemService = new ItemService(itemDao);
        IInventoryService inventoryService = new InventoryService(inventoryDao, itemDao);
        ICharacterInventoryService characterInventoryService = new CharacterInventoryService(inventoryService);
        IValidationService validationService = new ValidationService();
        IEquipmentService equipmentService = new EquipmentService(inventoryDao, validationService, messageService);
        IEncumbranceService encumbranceService = new EncumbranceService();
        IStatCalculator statCalculator = new StatCalculator(encumbranceService);
        ICharacterService characterService = new CharacterService(characterDao, inventoryDao, raceDao, classDao);

        // Controller Initialize
        AppControllerInitializer appInitializer =
                new AppControllerInitializer(
                        equipmentService,
                        itemService,
                        characterInventoryService,
                        statCalculator,
                        characterService,
                        messageService
                );

        // Navigation & Wiring
        INavigationService navigationService = new NavigationService(stage, appInitializer, messageService);
        appInitializer.setNavigationService(navigationService);

        // Stage Setup
        stage.setTitle("CharForge - RPG Character Maker");
        stage.setMaximized(true);

        try {
            var iconStream = getClass().getResourceAsStream("/com/project/charforgefinal/images/app_icon.png");
            if (iconStream != null) {
                stage.getIcons().add(new Image(iconStream));
            } else {
                Logs.printWarning("Warning: app_icon.png not found. Continuing without icon.");
            }
        } catch (Exception e) {
            Logs.printError("Could not load icon: ", e);
        }

        // Start
        navigationService.goToMainMenu();
    }
}