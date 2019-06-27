public class HR_Coordinator {

    private String username = null;
    private String password = null;


    public void matchItoA(Interviewer interviewer, Applicant applicant){
        interviewer.interviewees.add(applicant);

    }

}
