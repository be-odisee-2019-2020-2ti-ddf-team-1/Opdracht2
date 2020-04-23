package be.odisee.travelbase;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptSomePasswords {

    public static String hash(String wachtwoord) {
        return BCrypt.hashpw(wachtwoord, BCrypt.gensalt());
    }

    public static void main(String[] args) {

        String[] wachtwoorden = {"user"};

        for (String wwd : wachtwoorden ) {
            System.out.printf("%s is {bcrypt}%s%n", wwd, hash(wwd));
        }
    }
}
