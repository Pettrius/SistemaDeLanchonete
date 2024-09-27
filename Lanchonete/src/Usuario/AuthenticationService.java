package Usuario;

public class AuthenticationService {
    private UserAccountManager accountManager;

    // Construtor que recebe o gerenciador de contas
    public AuthenticationService(UserAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    // MÃ©todo de login
    public boolean login(String username, String password) {
        return accountManager.isValidAccount(username, password);
    }
}