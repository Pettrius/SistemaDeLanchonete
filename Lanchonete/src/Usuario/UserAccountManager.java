package Usuario;

import java.util.HashMap;

public class UserAccountManager {
    private HashMap<String, String> accounts = new HashMap<>();

    // Método para criar uma nova conta
    public boolean createAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            return false; // Usuário já existe
        }
        accounts.put(username, password);
        return true;
    }

    // Verifica se o usuário e senha estão corretos
    public boolean isValidAccount(String username, String password) {
        return accounts.containsKey(username) && accounts.get(username).equals(password);
    }
}