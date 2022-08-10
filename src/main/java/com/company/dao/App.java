package com.company.dao;

import com.company.dao.controller.ControllerApp;
import com.company.dao.controller.ControllerBook;
import com.company.dao.controller.ControllerUsers;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        ControllerApp controllerApp = new ControllerApp();
        controllerApp.commandApp();
    }
}




