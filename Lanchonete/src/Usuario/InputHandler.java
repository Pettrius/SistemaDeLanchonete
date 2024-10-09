package Usuario;

import java.util.Scanner;

public class InputHandler {

    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    // Exibe o menu para o usuário
    public void showMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Criar nova conta");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Lê a opção do usuário
    public int getOption() {
        return scanner.nextInt();
    }

    // Obtém os detalhes de login (nome de usuário e senha)
    public String[] getLoginDetails() {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    // Obtém os detalhes para a criação de uma nova conta
    public String[] getNewAccountDetails() {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o novo nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite a nova senha: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    // Obtém as informações adicionais do usuário (Nome, Telefone, Endereço, CPF)
    public String[] getUserInfo() {
        System.out.print("Digite o seu nome: ");
        String name = scanner.nextLine();
        System.out.print("Digite o seu telefone: ");
        String phone = scanner.nextLine();
        System.out.print("Digite o seu endereço: ");
        String address = scanner.nextLine();
        System.out.print("Digite o seu CPF: ");
        String cpf = scanner.nextLine();
        return new String[]{name, phone, address, cpf};
    }
}