package timeSheet.service;

import timeSheet.model.Login;

public interface LoginService {
    Login login (String username, String password) throws Exception;

    boolean registration (Login login) throws Exception;


    boolean updateTokenForEmail(String token, String email) throws Exception;

    boolean changePasswordWithToken(String password, String token) throws Exception;
}
