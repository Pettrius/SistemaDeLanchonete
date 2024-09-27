package Lanchonete;

import Usuario.AuthenticationService;
import Usuario.InputHandler;
import Usuario.UserAccountManager;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, String> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccountManager accountManager = new UserAccountManager();
        AuthenticationService authService = new AuthenticationService(accountManager);
        InputHandler inputHandler = new InputHandler(scanner);

        // Adiciona a conta inicial
        accountManager.createAccount("admin", "password");

        while (true) {
            inputHandler.showMenu();
            int option = inputHandler.getOption();

            switch (option) {
                case 1:
                    String[] loginDetails = inputHandler.getLoginDetails();
                    if (authService.login(loginDetails[0], loginDetails[1])) {
                        System.out.println("Login bem-sucedido.");
                    } else {
                        System.out.println("Nome de usuário ou senha incorretos.");
                    }
                    break;
                case 2:
                    String[] accountDetails = inputHandler.getNewAccountDetails();
                    if (accountManager.createAccount(accountDetails[0], accountDetails[1])) {
                        System.out.println("Conta criada com sucesso.");
                    } else {
                        System.out.println("Nome de usuário já existe.");
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}