package timeSheet.service;

import timeSheet.dao.Dao;
import timeSheet.model.*;

import java.util.List;

public class ServiceImpl implements Service{

    private Dao dao;
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<Member> getMembetList() throws Exception {
        return dao.getMembetList();
    }

    @Override
    public List<TimeSheet> getTimeSheetList(long memberId , long monthId) throws Exception {
        return dao.getTimeSheetList(memberId, monthId);
    }


    @Override
    public List<Monthes> getMonthesList() throws Exception {
        return dao.getMonthesList();
    }





    @Override
    public List<Role> getRoleList() throws Exception {
        return dao.getRoleList();
    }

    @Override
    public Member getMemberById(long memberId) throws Exception {
        return dao.getMemberById(memberId);

    }


    @Override
    public List<Member> getMemberListByTeamLeaderId(long teamLeaderId) throws Exception {
        return dao.getMemberListByTeamLeaderId(teamLeaderId);
    }

    @Override
    public boolean addTimeSheet(TimeSheet timeSheet) throws Exception {
        return dao.addTimeSheet(timeSheet);
    }


}
