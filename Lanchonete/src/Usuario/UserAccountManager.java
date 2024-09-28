package Usuario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UserAccountManager {
    private HashMap<String, String> accounts = new HashMap<>();
    private static final String FILE_NAME = "usuarios.xlsx";

    public UserAccountManager() {
        // Carrega os usuários do arquivo Excel
        loadAccountsFromExcel();
    }

    // Método para criar uma nova conta
    public boolean createAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            return false; // Usuário já existe
        }
        accounts.put(username, password);
        saveAccountToExcel(username, password);
        return true;
    }

    // Verifica se o usuário e senha estão corretos
    public boolean isValidAccount(String username, String password) {
        return accounts.containsKey(username) && accounts.get(username).equals(password);
    }

    // Carrega as contas de um arquivo Excel
    private void loadAccountsFromExcel() {
        try (FileInputStream file = new FileInputStream(new File(FILE_NAME));
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell usernameCell = row.getCell(0);
                Cell passwordCell = row.getCell(1);
                if (usernameCell != null && passwordCell != null) {
                    String username = usernameCell.getStringCellValue();
                    String password = passwordCell.getStringCellValue();
                    accounts.put(username, password); // Carrega na HashMap
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados do arquivo Excel: " + e.getMessage());
        }
    }

    // Salva uma nova conta no arquivo Excel
    private void saveAccountToExcel(String username, String password) {
        try (FileInputStream file = new FileInputStream(new File(FILE_NAME));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;  // Nova linha
            Row row = sheet.createRow(rowNum);

            Cell usernameCell = row.createCell(0);
            usernameCell.setCellValue(username);

            Cell passwordCell = row.createCell(1);
            passwordCell.setCellValue(password);

            try (FileOutputStream outFile = new FileOutputStream(new File(FILE_NAME))) {
                workbook.write(outFile);  // Salva o arquivo
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo Excel: " + e.getMessage());
        }
    }

    // Inicializa um arquivo Excel vazio caso ele não exista
    public void initializeExcelFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Usuários");
                try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                    workbook.write(fileOut);
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo Excel: " + e.getMessage());
            }
        }
    }
}