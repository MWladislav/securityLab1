package validator;

import com.mysql.jdbc.StringUtils;
import model.User;

public class UserValidator {

    public static boolean validate(String usernameFromRequest,String passwordFromRequest){
        if(StringUtils.isNullOrEmpty(usernameFromRequest) || StringUtils.isNullOrEmpty(passwordFromRequest)){
            return false;
        }
        if (!usernameFromRequest.matches("^\\w+") || !passwordFromRequest.matches("^\\w+"))
            return false;

        if (usernameFromRequest.length()>20 ||passwordFromRequest.length()>50) {
            return false;
        }

        return true;
    }

}
