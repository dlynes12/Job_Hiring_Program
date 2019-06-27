public class HR_Coordinator implements UserAccess {



    public void matchItoA(Interviewer interviewer, Applicant applicant){
        interviewer.interviewees.add(applicant);

    }

}
