public class HR_Coordinator implements UserAcess {



    public void matchItoA(Interviewer interviewer, Applicant applicant){
        interviewer.interviewees.add(applicant);

    }

}
