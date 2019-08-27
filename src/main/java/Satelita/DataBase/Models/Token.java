package Satelita.DataBase.Models;

public class Token {

    private final String token;
    private final int expiryTime;

    public Token(String token, int expiryTime) {
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
