package timeSheet.dao;

import timeSheet.model.Login;
import timeSheet.model.Member;
import timeSheet.model.Role;
import util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {


    @Override
    public Login login(String username, String password) throws Exception {
        Login login = new Login ();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT L.ID LOGIN_ID, L.USERNAME USERNAME, L.PASSWORD PASSWORD, M.ID MEMBER_ID, M.NAME MEMBER_NAME, M.SURNAME MEMBER_SURNAME, R.ID ROLE_ID, R.ROLE_NAME ROLE_NAME FROM LOGIN L\n" +
                " INNER JOIN ROLE R ON L.ROLE_ID = R.ID\n" +
                " INNER JOIN MEMBER M ON L.MEMBER_ID = M.ID\n" +
                " WHERE L.ACTIVE = 1 AND L.USERNAME = ? AND L.PASSWORD = ?   ";
        try {
            c = DBHelper.getConnection();
            if (c!=null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    login.setId(rs.getLong("LOGIN_ID"));
                    login.setUsername(rs.getString("USERNAME"));
                    login.setPassword(rs.getString("PASSWORD"));
                    Member member = new Member();
                    member.setId(rs.getLong("MEMBER_ID"));
                    member.setName(rs.getString("MEMBER_NAME"));
                    member.setSurname(rs.getString("MEMBER_SURNAME"));
                    Role role = new Role();
                    role.setId(rs.getLong("ROLE_ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    login.setMember(member);
                    login.setRole(role);

                    System.out.println("dao user" + username + "       dao password" + password);
                } else {
                    login = null;
                }
            } else {
                System.out.println("Login connection is null! ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, rs);
        }
        return login;
    }

    @Override
    public boolean registration(Login login) throws Exception {
        String sql = " INSERT INTO LOGIN (ID,USERNAME,PASSWORD,NAME,SURNAME,ROLE_ID) \n" +
                " VALUES (LOGIN.NEXTVAL, ?, ?, ?, ?, ?) ";
        return false;
    }

    @Override
    public boolean updateTokenForEmail(String token, String email) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = " UPDATE LOGIN SET TOKEN = ? " +
                " WHERE USERNAME = ? ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                ps.setString(2, email);
                ps.execute(sql);
                result = true;

            } else{
                System.out.println("Connection is null");
            }

        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean changePasswordWithToken(String password, String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = " UPDATE LOGIN SET PASSWORD = ?, TOKEN = NULL "
                +" WHERE TOKEN = ? ";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, token);
                ps.execute(sql);
                result = true;

            } else{
                System.out.println("Connection is null");
            }

        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }
}
