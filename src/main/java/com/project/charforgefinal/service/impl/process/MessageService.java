package com.project.charforgefinal.service.impl.process;

import com.project.charforgefinal.service.interfaces.process.IMessageService;
import com.project.charforgefinal.utils.AlertUtils;

public class MessageService implements IMessageService {

    @Override
    public void warning(String title, String message) {
        AlertUtils.showWarning(title, message);
    }

    @Override
    public void error(String title, String message) {
        AlertUtils.showError(title, message);
    }

    @Override
    public void info(String title, String message) {
        AlertUtils.showInfo(title, message);
    }

    @Override
    public boolean confirm(String title, String headerMessage, String message) {
        return AlertUtils.showConfirmation(title, headerMessage, message);
    }

    @Override
    public boolean confirm(String title, String message) {
        return AlertUtils.showConfirmation(title, message);
    }
}
