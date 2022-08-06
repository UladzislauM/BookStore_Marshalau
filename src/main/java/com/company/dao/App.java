package com.company.dao;

import com.company.dao.controller.ControllerBook;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ControllerBook controllerBook = new ControllerBook();
        controllerBook.consoleInterface();
    }
}




