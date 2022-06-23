package at.petshome.Miscellaneous;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private static Regex mSingleton;

    private Regex() {

    }

    public static Regex getInstance() {
        if (mSingleton == null) {
            mSingleton = new Regex();
        }

        return mSingleton;
    }

    public boolean email(String email) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean password(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
