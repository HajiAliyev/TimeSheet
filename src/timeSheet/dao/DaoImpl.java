package timeSheet.dao;

import org.json.JSONObject;
import timeSheet.model.*;
import util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao{

    @Override
    public List<Member> getMembetList() throws Exception {
        List<Member> memberList = new ArrayList<Member>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT  ROWNUM r, M.ID MEMBER_ID, M.NAME MEMBER_NAME, M.SURNAME MEMBER_SURNAME , T.ID TEAMLEADER_ID, T.NAME TEAMLEADER_NAME, T.SURNAME TEAMLEADER_SURNAME   FROM MEMBER M\n" +
                " LEFT JOIN TEAMLEADER T ON  M.TL_ID = T.ID\n" +
                " WHERE  M.ACTIVE = 1 AND T.ACTIVE = 1 ";
        try {
            c =DBHelper.getConnection();
            if (c!= null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                    Member member = new Member();
                    member.setR(rs.getLong("r"));
                    member.setId(rs.getLong("MEMBER_ID"));
                    member.setName(rs.getString("MEMBER_NAME"));
                    member.setSurname(rs.getString("MEMBER_SURNAME"));

                    TeamLeader teamleader = new TeamLeader();
                    teamleader.setId(rs.getLong("TEAMLEADER_ID"));
                    teamleader.setName(rs.getString("TEAMLEADER_NAME"));
                    teamleader.setSurname(rs.getString("TEAMLEADER_SURNAME"));
                    member.setTeamLeader(teamleader);
                    memberList.add(member);
//                    System.out.println("getMembetList memberList" + memberList);
                }
            } else {
                System.out.println("Memmber Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return memberList;
    }



    @Override
    public List<TimeSheet> getTimeSheetList(long memberId, long monthId) throws Exception {
        List<TimeSheet> timeSheetList = new ArrayList<TimeSheet>(/*31*/);
        /*int i = 0;
            while (i<31) {
                TimeSheet ts = new TimeSheet();
                timeSheetList.add(ts);
                i++;
            }*/

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT ROWNUM r, " +
                "T.ID ID,\n" +
                "MON.ID MONTH_ID,\n" +
                "MON.NAME MONTH_NAME,\n" +
                "T.DAY DAYS,\n" +
                "M.ID MEMBER_ID,\n" +
                "M.NAME MEMBER_NAME,\n" +
                "M.SURNAME MEMBER_SURNAME,\n" +
                "STATUS STATUS_NAME,\n" +
                "T.DESCRIPTION DESCRIPTION, T.HOUR WORK_HOUR \n" +
                "FROM TIMESHEET T\n" +
                "INNER JOIN MEMBER M\n" +
                "ON T.MEMB_ID = M.ID\n" +
                "INNER JOIN MONTHES MON\n" +
                "ON T.MON_ID = MON.ID\n" +
                "WHERE T.ACTIVE = 1 AND M.ACTIVE = 1 AND M.ID = ? AND MON.ID = ?  ORDER BY DAY ";
    try {
        c = DBHelper.getConnection();
        if (c != null) {
            ps = c.prepareStatement(sql);
            ps.setLong(1,memberId);
            ps.setLong(2,monthId);
            rs = ps.executeQuery();
            while (rs.next()) {
                TimeSheet timeSheet = new TimeSheet();
                Monthes monthes =  new Monthes();
                Member member = new Member();


                timeSheet.setR(rs.getLong("r"));
                timeSheet.setId(rs.getLong("ID"));
                monthes.setId(rs.getLong("MONTH_ID"));
                monthes.setName(rs.getString("MONTH_NAME"));
                timeSheet.setDay(rs.getLong("DAYS"));
                member.setId(rs.getLong("MEMBER_ID"));
                member.setName(rs.getString("MEMBER_NAME"));
                member.setSurname(rs.getString("MEMBER_SURNAME"));
                timeSheet.setStatus(rs.getString("STATUS_NAME"));
                timeSheet.setDescription(rs.getString("DESCRIPTION"));
                timeSheet.setHour(rs.getInt("WORK_HOUR"));

                timeSheet.setMonthes(monthes);
                timeSheet.setMember(member);

//                timeSheetList.add(rs.getInt("DAY"), timeSheet);
                timeSheetList.add(timeSheet);

            }

        } else{
            System.out.println("Connection is null!");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        JdbcUtility.close(c, ps , rs);
    }
        return timeSheetList;
    }

    @Override
    public List<Monthes> getMonthesList() throws Exception {
        List<Monthes> monthesList = new ArrayList<Monthes>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT ROWNUM r, ID, NAME, DAYS, DATA_DATE FROM MONTHES ";
        try {
            c =DBHelper.getConnection();
            if (c!= null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                    Monthes monthes = new Monthes();
                    monthes.setR(rs.getLong("r"));
                    monthes.setId(rs.getLong("ID"));
                    monthes.setName(rs.getString("NAME"));
                    monthes.setDays(rs.getString("DAYS"));
                    monthesList.add(monthes);
                }
//                System.out.println("monthes = " + monthesList);
            } else {
                System.out.println("Memmber Connenction is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return monthesList;
    }

    /*@Override
    public List<Status> getStatusList() throws Exception {
        List<Status> statusList = new ArrayList<Status>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT  ID, NAME, DESCRIPTION, DATA_DATE FROM STATUS  ";
        try {
            c =DBHelper.getConnection();
            if (c!= null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                    Status status = new Status();
                    status.setId(rs.getLong("ID"));
                    status.setName(rs.getString("NAME"));
                    status.setDescription(rs.getString("DESCRIPTION"));
                    statusList.add(status);
                }
                System.out.println("status = " + statusList);
            } else {
                System.out.println("Memmber Connenction is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return statusList;
    }*/

    @Override
    public boolean addTimeSheet(TimeSheet timeSheet) throws Exception {


        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql ="  UPDATE TIMESHEET SET STATUS = ? , DESCRIPTION = ? , HOUR = ?  " +
                "      WHERE MEMB_ID = ? AND MON_ID = ? AND DAY = ? ";
        try {
            c =DBHelper.getConnection();
            if(c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, timeSheet.getStatus());
                ps.setString(2, timeSheet.getDescription());
                String caseKey = timeSheet.getStatus();
                System.out.println("Dao da olan case - in statusu: "+caseKey);
//                System.out.print(timeSheet.getStatus());
                switch(caseKey) {
                    case "R" : //Regular day
                        ps.setInt(3,8);
                        break;
                    case "R/" : //Short Day
                        ps.setInt(3,7);
                        break;
                    case "O" : //Overtime
                        ps.setInt(3,timeSheet.getHour());
                        break;
                    default :
                        ps.setInt(3,0);
                }
                ps.setLong(4, timeSheet.getMember().getId());
                ps.setLong(5, timeSheet.getMonthes().getId());
                ps.setLong(6, timeSheet.getDay());

                ps.execute();
                result = true;
//                System.out.println("SUCCESS DAO + timeSheet" + timeSheet);
            } else {
                System.out.println("Connection is  null!");
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
    public List<Role> getRoleList() throws Exception {
        List<Role> roleList = new ArrayList<Role>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT ID, SUBSTR(ROLE_NAME, 6) ROLE_NAME FROM ROLE\n" +
                " WHERE ACTIVE = 1 ";
        try {
            c =DBHelper.getConnection();
            if (c!= null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getLong("ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    roleList.add(role);
                }
            } else {
                System.out.println("Role Connenction is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return roleList;
    }

    @Override
    public Member  getMemberById(long memberId) throws Exception {
        Member member = new Member();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT  ROWNUM r, M.ID MEMBER_ID, M.NAME MEMBER_NAME, M.SURNAME MEMBER_SURNAME , T.ID TEAMLEADER_ID, T.NAME TEAMLEADER_NAME, T.SURNAME TEAMLEADER_SURNAME   FROM MEMBER M\n" +
                " LEFT JOIN TEAMLEADER T ON  M.TL_ID = T.ID\n" +
                " WHERE   M.ID = ? AND  M.ACTIVE = 1 AND T.ACTIVE = 1 ";
        try {
            c = DBHelper.getConnection();
            if (c!= null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1,memberId);
                rs = ps.executeQuery();
                while(rs.next()) {
                    member.setR(rs.getLong("r"));
                    member.setId(rs.getLong("MEMBER_ID"));
                    member.setName(rs.getString("MEMBER_NAME"));
                    member.setSurname(rs.getString("MEMBER_SURNAME"));

                    TeamLeader teamleader = new TeamLeader();
                    teamleader.setId(rs.getLong("TEAMLEADER_ID"));
                    teamleader.setName(rs.getString("TEAMLEADER_NAME"));
                    teamleader.setSurname(rs.getString("TEAMLEADER_SURNAME"));
                    member.setTeamLeader(teamleader);
//                    System.out.println("getMemberById member" + member);
                }
            } else {
                System.out.println("Memmber Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return member;
    }



    @Override
    public List<Member> getMemberListByTeamLeaderId(long teamLeaderId) throws Exception {
        List<Member> memberList = new ArrayList<Member>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT  ROWNUM r, M.ID MEMBER_ID, M.NAME MEMBER_NAME, M.SURNAME MEMBER_SURNAME , T.ID TEAMLEADER_ID, T.NAME TEAMLEADER_NAME, T.SURNAME TEAMLEADER_SURNAME   FROM MEMBER M\n" +
                " INNER JOIN TEAMLEADER T ON  M.TL_ID = T.ID\n" +
                " WHERE   T.ID = ? AND  M.ACTIVE = 1 AND T.ACTIVE = 1 ";
        try {
            c = DBHelper.getConnection();
            if (c!= null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, teamLeaderId);
                rs = ps.executeQuery();
                while(rs.next()) {
                    Member member = new Member();
                    member.setR(rs.getLong("r"));
                    member.setId(rs.getLong("MEMBER_ID"));
                    member.setName(rs.getString("MEMBER_NAME"));
                    member.setSurname(rs.getString("MEMBER_SURNAME"));

                    TeamLeader teamleader = new TeamLeader();
                    teamleader.setId(rs.getLong("TEAMLEADER_ID"));
                    teamleader.setName(rs.getString("TEAMLEADER_NAME"));
                    teamleader.setSurname(rs.getString("TEAMLEADER_SURNAME"));
                    member.setTeamLeader(teamleader);
                    memberList.add(member);
                    System.out.println("getMemberListByTeamLeaderId memberList" +memberList);
                }
            } else {
                System.out.println("Memmber Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return memberList;
    }


}
