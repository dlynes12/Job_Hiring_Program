Class to run: JobPortal

**** Assumptions: *****
1) You can'st have more interviewers than applicants
2) You cannot approve and decline the same applicant in the same round



Create a new Applicant:
----------------------------------------------------------------------
From the main menu:

1) Click the calender icon and enter the Date
2) Press the "Applicant Access" button
3) Press the "Create New User" button
4) Fill in the desired username and password corresponding fields
4) Press the "Create" button

**** Assumptions: No two users can have the same username, no user can go back in time *****


Create a new HR Coordinator or Interviewer:
----------------------------------------------------------------------
1) Click the calender icon and enter the Date
2) Press the "Company Access" button
3) Create a new Company by pressing "Create New Company" button if necessary
4) In the Company Access page, select a company from the dropdown & press "Select Company"
5) Select "Create User"
6) Enter a username and password, select the appropriate account type
7) Press the create button


Login to HR Coordinator or Interviewer:
----------------------------------------------------------------------
1) Click the calender icon and enter the Date
2) Press the "Company Access" button
3) In the Company Access page, select a company from the dropdown & press "Select Company"
4) Enter a username and password, select the appropriate account type
5) Press the "Log In" button


Create a job:
-----------------------------------------------------------------------
Assuming an HR Coordinator has been created:
1) Login into a valid HR account
2) Press the "Add a job" button
3) Specify the closing date by pressing on the calendar icon and selecting
    the desired date
4) Specify the job position being created in the corresponding field
5) Enter an integer representing to the number of jobs to be created. More than one
   available position indicates hiring for multiple locations.
6) Enter a stage in the interview process one at a time, press "Add Stage" button.
    The order you enter the stages in is the same order applicants are interviewed in.
7) Choose an interviewers from the dropdown menu and press "Add Interviewer" button. This
    selects the interviewers who interview applicants in the hiring process.
8) Add a tag: either full time or part time
9) Press the "Create job" button

*** Assumptions: Name of job positions are unique ***



View the jobs an Applicant has applied to (through HR interface):
-----------------------------------------------------------------------
1) Login into a valid HR account
2) Press "View all applicants"
3) Click the drop down menu, and select the desired applicant
3) Press the "View applicants" button




View all Applicants for a specific job posting (through HR interface):
-----------------------------------------------------------------------
1) Login into a valid HR account
2) Press the "Manage Closed jobs" button
3) Click the list menu to select the job you wish to view
4) Press the "See applicants" button



Apply for a job (through Applicant interface)
-----------------------------------------------------------------------
1) Login into a valid Applicant account
2) Press the "Apply to jobs" button
3) Click the desired radiobutton that corresponds to the desired job
    you wish to apply to
4) Press the "Apply" button

*** Assumptions: There are already pre-existing jobs created by HR ***


View list of Applicants to be Interviewed:
----------------------------------------------------------------------
Each Interviewer has their own list of Applicants that they must interview.
This list is visible after the job posting has closed. Before the job
posting is closed all interviews can see the list of applicants who have
applied.

1) Login to a valid Interviewer account
2) Choose a job that you would like interview candidates for
3) Press the "Get Interviewees" button


Hiring Process:
----------------------------------------------------------------------
1) Create a job
2) Have applicants apply to a job
3) Login to valid HR account a date following the date a job closes
4) Go to the "Managed Closed Jobs" section
5) Select a job and press "Distribute Applicants"
6) Login to the Interviewer Accounts who have been selected to interview
    for the particular job you chose.
7) Choose a job from the dropdown and press "Get Interviewees"
8) Approve or decline applicants. Program will not move forward unless all applicants
    have either been approved or declined for that round.
9)Login to a valid HR account, and advance the round in the "Manage Closed Jobs" section
10) Repeat steps 6-9 until you are at the beginning of the final round
11) In the final round the approve button in Interviewer functions as a hire button.
    You will only be able to hire the number of positions available for the job.
12) In the final round only HR will be able to see who has been hired in the
    "Manage Closed Jobs" section, after choosing a job and clicking "View Hires"


