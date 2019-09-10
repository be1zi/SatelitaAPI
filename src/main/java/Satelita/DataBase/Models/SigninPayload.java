package Satelita.DataBase.Models;

public class SigninPayload {

    private final String token;
    private final int expiryTime;

    public SigninPayload(String token, int expiryTime) {
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public String getToken() {
        return token;
    }

    public int getExpiryTime() {
        return expiryTime;
    }
}
