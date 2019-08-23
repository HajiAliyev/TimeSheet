package timeSheet.service;

import timeSheet.dao.LoginDao;
import timeSheet.model.Login;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }


    @Override
    public Login login(String username, String password) throws Exception {
        return loginDao.login(username, password);
    }

    @Override
    public boolean registration(Login login) throws Exception {
        return loginDao.registration(login);
    }

    @Override
    public boolean updateTokenForEmail(String token, String email) throws Exception {
        return loginDao.updateTokenForEmail(token, email);
    }

    @Override
    public boolean changePasswordWithToken(String password, String token) throws Exception {
        return loginDao.changePasswordWithToken(password, token);
    }
}
