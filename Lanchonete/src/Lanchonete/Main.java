package Usuario;

import Pedido.Menu;
import Pedido.Pedido;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccountManager accountManager = new UserAccountManager();
        AuthenticationService authService = new AuthenticationService(accountManager);
        InputHandler inputHandler = new InputHandler(scanner);
        ExcellDataReader excelDataReader = new ExcellDataReader(); // Nova instância da classe

        // Criar conta padrão de admin
        accountManager.createAccount("admin", "password");

        while (true) {
            inputHandler.showMenu();
            int option = inputHandler.getOption();

            switch (option) {
                case 1:
                    String[] loginDetails = inputHandler.getLoginDetails();
                    if (authService.login(loginDetails[0], loginDetails[1])) {
                        System.out.println("Login bem-sucedido.");

                        // Adiciona uma opção para visualizar os dados da conta
                        System.out.println("Digite 1 para fazer um pedido ou 2 para visualizar seus dados:");
                        int action = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha

                        if (action == 1) {
                            // Exibe o menu e cria o pedido
                            Menu menu = new Menu();
                            menu.showMenu();

                            Pedido pedido = new Pedido(menu);

                            // Coleta itens do pedido
                            System.out.println("Digite o nome dos itens que deseja adicionar (ou 'sair' para finalizar):");
                            String item;
                            while (!(item = scanner.nextLine()).equalsIgnoreCase("sair")) {
                                pedido.addItem(item);
                            }

                            // Exibe o resumo do pedido
                            pedido.showOrder();

                            // Coleta as informações adicionais do cliente
                            String[] userInfo = inputHandler.getUserInfo();
                            try {
                                saveUserDataToExcel(loginDetails[0], loginDetails[1], userInfo, pedido.getTotal());
                                System.out.println("Informações e pedido salvos com sucesso.");
                            } catch (IOException e) {
                                System.out.println("Erro ao salvar informações: " + e.getMessage());
                            }
                        } else if (action == 2) {
                            // Exibe os dados da conta do usuário logado
                            try {
                                excelDataReader.displayUserData(loginDetails[0]);
                            } catch (IOException e) {
                                System.out.println("Erro ao exibir dados: " + e.getMessage());
                            }
                        }

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

    // Função para salvar as informações do usuário e o total do pedido em um arquivo Excel
    public static void saveUserDataToExcel(String username, String password, String[] userInfo, double totalPedido) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("UserData");

        // Criar o cabeçalho
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Username", "Password", "Nome", "Telefone", "Endereço", "CPF", "Total Pedido"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Adicionar dados do usuário
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(username);
        dataRow.createCell(1).setCellValue(password);
        dataRow.createCell(2).setCellValue(userInfo[0]);
        dataRow.createCell(3).setCellValue(userInfo[1]);
        dataRow.createCell(4).setCellValue(userInfo[2]);
        dataRow.createCell(5).setCellValue(userInfo[3]);
        dataRow.createCell(6).setCellValue(totalPedido);

        // Salvar o arquivo
        try (FileOutputStream fileOut = new FileOutputStream("UserData.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}
