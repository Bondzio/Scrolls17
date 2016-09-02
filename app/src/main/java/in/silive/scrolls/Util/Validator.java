package in.silive.scrolls.Util;

/**
 * Created by AKG002 on 31-08-2016.
 */
public class Validator {
    public static boolean isValidEmail(String email){
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}
