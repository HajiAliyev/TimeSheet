package timeSheet.web;

import org.json.JSONObject;
import timeSheet.dao.Dao;
import timeSheet.dao.DaoImpl;
import timeSheet.dao.LoginDao;
import timeSheet.dao.LoginDaoImpl;
import timeSheet.model.Member;
import timeSheet.model.Monthes;
import timeSheet.model.Status;
import timeSheet.model.TimeSheet;
import timeSheet.service.LoginService;
import timeSheet.service.LoginServiceImpl;
import timeSheet.service.Service;
import timeSheet.service.ServiceImpl;
import util.Constants;
import util.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@WebServlet(name = "ControllerServlet", urlPatterns = "/cs")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Dao dao = new DaoImpl();
        Service service = new ServiceImpl(dao);

        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter pw = response.getWriter();
        String action = null;
        String addressForJspPage = null;
        String mName = null;
        String[] monthName = {"January","February","March",
                "April", "May","June","July", "August",
                "September","October","November","December"};

        if (request.getParameter("action") != null) { //mene lazim olan parametr-action yeri buradir, burdan action evezine nese yazsam,
            action = request.getParameter("action"); //gerek client-de qiymet atanda action yerine gerek tezeni qoyum!!! 2 setr-de de action sozu deyisir!!!
        }

        try {
            if (action.equalsIgnoreCase(Constants.GET_MEMBER_LIST)) {
                List<Member> memberList = service.getMembetList();
                request.setAttribute("memberList", memberList);
                addressForJspPage = "/WEB-INF/pages/memberList.jsp";
                //System.out.println("action=getMemberList   "+ memberList);
            }
            else if (action.equalsIgnoreCase(Constants.GET_MEMBER_BY_ID)) {
                String member_Id = request.getParameter("memberId");
                System.out.println("member Id=" + member_Id);
                if (member_Id != null) {
                    long memberId = Long.parseLong(member_Id);
                    Member member = service.getMemberById(memberId);
                    request.setAttribute("member", member);
                    addressForJspPage = "/WEB-INF/pages/memberList.jsp";
                    //System.out.println("action getMemberById Member = " + member);
                }
            } else if (action.equalsIgnoreCase(Constants.GET_TEAM_LIST_BY_TEAMLEADER_ID)) {
                String teamLeader_Id = request.getParameter("teamLeaderId");
                System.out.println("TEAMLIST member Id=" + teamLeader_Id);
                    long teamLeaderId = Long.parseLong(teamLeader_Id);
                    List<Member> teamList = service.getMemberListByTeamLeaderId(teamLeaderId);
                    request.setAttribute("teamList", teamList);
                    addressForJspPage = "/WEB-INF/pages/memberList.jsp";
                    System.out.println("action getTeamListByTeamLeaderId = "+teamList);
            } else if (action.equalsIgnoreCase(Constants.GET_TIMESHEET_LIST)) {
                String member_Id = request.getParameter("rowId");
                String month_Id = request.getParameter("monthId");
                //System.out.println("member Id=" + member_Id);
                if (member_Id != null && month_Id != null) {
//                    Calendar c = Calendar.getInstance();
//                    mName = monthName[c.get(Calendar.MONTH)];
//                    int year = c.get(Calendar.YEAR);
//                    int month = c.get(Calendar.MONTH);
//                    int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//                    int day = c.get(Calendar.DATE);
//
//                    YearMonth yearMonthObject = YearMonth.of(year, month);
//                    int daysInMonth = yearMonthObject.lengthOfMonth();

                    long memberId = Long.parseLong(member_Id);
                    long monthId = Long.parseLong(month_Id);
//                    long monthId = 7;
                    List<TimeSheet> timeSheetList = service.getTimeSheetList(memberId, monthId);
                    List<Monthes> monthesList = service.getMonthesList();
                    List<Member> memberList = service.getMembetList();

                    request.setAttribute("timeSheetList", timeSheetList);
                    request.setAttribute("monthesList", monthesList);
                    request.setAttribute("memberList", memberList);

                    //GUNLERI YARADIR
//                    request.setAttribute("mName",mName);
//                    request.setAttribute("monthId",month);
//                    request.setAttribute("daysInMonth",daysInMonth);
//                    request.setAttribute("dayOfMonth",dayOfMonth);
//                    request.setAttribute("day",day);

                    addressForJspPage = "/WEB-INF/pages/timeSheet.jsp";

                    System.out.println("Servlet timesheet = " + timeSheetList);
                    System.out.println("Servlet memberList = " + memberList);
                    System.out.println("Servlet monthesList  = " + monthesList);

//                    System.out.println("contasdfgf=" + timeSheetList);
                } else
                    System.out.println("member_id is null!");
            }

            /*else if (action.equalsIgnoreCase("getStatusCombo")) {
                List<Status> statusList = service.getStatusList();
                request.setAttribute("statusList", statusList);
                addressForJspPage = "/WEB-INF/pages/statusCombo.jsp";
                System.out.println("status list action = "+statusList);
            }*/

            else if (action.equalsIgnoreCase(Constants.EDTI_TIMESHEET)) {
                Long memberId = Long.parseLong(request.getParameter("memberId"));
                String data = request.getParameter("myArray");
                JSONObject json = new JSONObject(data);
                for(int i = 0; i<json.length(); i++){
                    String a = Integer.toString(i);
                    JSONObject jsonObject = json.getJSONObject(a);

                    String monthId = jsonObject.getString("monthId");
                    String day = jsonObject.getString("day");
                    String status = jsonObject.getString("status");
                    String description = jsonObject.getString("description");
                    String hour = jsonObject.getString("hour");

                    TimeSheet timeSheet = new TimeSheet();

                    Member member =  new Member();
                    member.setId(memberId);
                    timeSheet.setMember(member);

                    Monthes monthes = new Monthes();
                    monthes.setId(Long.parseLong(monthId));

                    timeSheet.setMonthes(monthes);
                    timeSheet.setDay(Long.parseLong(day));
                    timeSheet.setStatus(status);
                    timeSheet.setDescription(description);
                    timeSheet.setHour(Integer.parseInt(hour));
                    System.out.println(timeSheet);
                    boolean isUpdated = service.addTimeSheet(timeSheet);
                    if (isUpdated) {
                        pw.print("Timesheet is successfully updated");
                    } else {
                        pw.print("error Servlet, timesheet is not updated");
                    }
                }
                response.setContentType("text/html");


            } else if (action.equalsIgnoreCase(Constants.FORGET_VIEW)) {
                addressForJspPage = "/forget.jsp";
            } else if (action.equalsIgnoreCase(Constants.FORGET_PASSWORD)) {
                String email = request.getParameter("email");
                if (email != null && !email.isEmpty()) {
                    String token = UUID.randomUUID().toString();
                    boolean isUpdate = loginService.updateTokenForEmail(token, email);
                    if (isUpdate) {
                        String changeMailUrl = "http://localhost:9595/TimeSheet_war_exploded/cs?action=changePasswordView&token=" + token;
                        boolean isSendMail = Method.sendMail(email, "Change Mail", changeMailUrl);
                        if (isSendMail) {
                            request.setAttribute("message", "success");
                            addressForJspPage = "/success.jsp";
                            System.out.println("Mail is sent ! + ");
                        } else {
                            request.setAttribute("invalid", "Mail göndərilə bilmədi!");
                            addressForJspPage = "/forget.jsp";
                        }
                    } else {
                        request.setAttribute("invalid", "Gizli kod yaradıla bilmədi!");
                        addressForJspPage = "forget.jsp";
                    }
                } else {
                    request.setAttribute("invalid", "Email boşdur!");
                    addressForJspPage = "/forget.jsp";
                }
                addressForJspPage = "/forget.jsp";
            } else if (action.equalsIgnoreCase(Constants.CHANGE_PASSWORD_VIEW)) {
                String token = request.getParameter("token");
                request.setAttribute("token", token);
                addressForJspPage = "changePassword.jsp";
            } else if (action.equalsIgnoreCase(Constants.CHANGE_PASSWORD)) {
                String password = request.getParameter("password");
                String token = request.getParameter("token");
                boolean isChangedPassword = loginService.changePasswordWithToken(password, token);
                if (isChangedPassword) {
                    request.setAttribute("message", "success");
                    addressForJspPage = "login.jsp";
                } else {
                    request.setAttribute("invalid", "Şifrəni dəyişən zaman problem baş verdi!");
                    addressForJspPage = "changePassword.jsp";
                }
            } else if (action.equalsIgnoreCase(Constants.GET_MONTHES_COMBO)){
                    List<Monthes> monthesList = service.getMonthesList();
                    request.setAttribute("monthesList", monthesList);
                    addressForJspPage ="/WEB-INF/pages/monthesCombo.jsp";

            } /*else if (action.equalsIgnoreCase("getStatusCombo")){
                List<Status> statusList = service.getStatusList();
                request.setAttribute("statusList",statusList);
                addressForJspPage = "/WEB-INF/pages/statusCombo.jsp";
            }*/



            } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(addressForJspPage != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(addressForJspPage);
            requestDispatcher.forward(request, response);
        }
    }
}
