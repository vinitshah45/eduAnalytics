<%@page import="com.eduAnalytics.dao.StudentDAO"%>
<%@page import="com.eduAnalytics.utilities.AnalyticsConstants"%>
<%
    String action = request.getParameter("action");
    if (action != null && !action.isEmpty()) {
        StudentDAO dao = new StudentDAO();
        if (action.equalsIgnoreCase(AnalyticsConstants.GETDISCIPLINEWISE_STUDENT_COUNT)) {
            String year = request.getParameter("year");
            out.println(dao.getDisciplineWiseStudentCount(Integer.parseInt(year)));
        }else if (action.equalsIgnoreCase(AnalyticsConstants.GETSUBDISCIPLINEWISE_STUDENT_COUNT)) {
            String year = request.getParameter("year");
            String discipline = request.getParameter("discipline");
            out.println(dao.getSubDisciplineWiseStudentCount(discipline,Integer.parseInt(year)));
        }else if (action.equalsIgnoreCase(AnalyticsConstants.GETTRAININGSCHEMEWISE_STUDENT_COUNT)) {
            String year = request.getParameter("year");
            out.println(dao.getTrainingSchemeWiseStudentCount(Integer.parseInt(year)));
        } else if (action.equalsIgnoreCase(AnalyticsConstants.GETSUBTRAININGSCHEMEWISE_STUDENT_COUNT)) {
            String year = request.getParameter("year");
            String schemetype = request.getParameter("schemetype");
            out.println(dao.getSubTrainingSchemeWiseStudentCount(schemetype,Integer.parseInt(year)));
        }else if (action.equalsIgnoreCase(AnalyticsConstants.GETINSTITUTEWISE_STUDENT_RESULT_COUNT)) {
            out.println(dao.getInstituteWiseStudentResultCount());
        }else if (action.equalsIgnoreCase(AnalyticsConstants.GETSTUDENTLISTFROMINSTITUTE)) {
            String name = request.getParameter("name");
            out.println(dao.getStudentListFromInstitute(name));
        }
        
    }
%>
