package timeSheet.service;

import timeSheet.model.*;

import java.util.List;

public interface Service {
    List<Member> getMembetList() throws Exception;

    List<TimeSheet> getTimeSheetList(long memberId, long monthId) throws Exception;

    List<Monthes> getMonthesList() throws Exception;



    List<Role> getRoleList() throws Exception;

    Member  getMemberById(long memberId) throws  Exception;

    List<Member> getMemberListByTeamLeaderId(long teamLeaderId) throws Exception;

    boolean addTimeSheet (TimeSheet timeSheet) throws Exception;




}
