README:-

Report was submitted by Trysta on Brightspace. Jaden Gant submitted the project on vocareum.
The entire project is divided into 5 classes namely Runner.java, Student.java, Teacher.java, Courses.java and Quiz.java. The work distribution is as follows :
Runner.java - contains the main method with the login functionality and the interface to interact with the user.
Student.java - contains the takeQuiz() method to enable the students to attempt a quiz.
Teacher.java - creates a new teacher by adding the details to a file every time a teacher logs in. Also enables the teacher to create a new course, enter and edit a course.
Courses.java - creates a new course, enables the creation of new quizzes and also enables entry into quizzes.
Quiz.java - Helps the teacher actually make and edit the quiz i.e. add the title and quiz questions, delete a particular question, replace a particular question.
Therefore, in order to run the program, the user would need to download these five classes from github and execute the Runner.java class.
There are a few specifications for running the program. Firstly, we have provided the functionality to the teacher to either enter the questions manually or enter the path to a file that has the quiz title and the questions. But there is one important condition. For the quiz to be made successfully the file with the quiz title and quiz questions would have to be formatted in The following manner:-
QuizTitle
1. Question statement?
A) answer choice
B) answer choice
C) answer choice
D) answer choice
2. Question statement?
A) answer choice
B) answer choice
C) answer choice
D) answer choice
3. Question statement?
A) answer choice
B) answer choice
C) answer choice
D) answer choice
4. Question statement?
A) answer choice
B) answer choice
C) answer choice
D) answer choice

And so on. More specifically, there should not be any blank lines in the file, the quiz title should appear first, then the first question formatted exactly as above, i.e. the question number then a “.” and then single space followed by the question statement and question mark. Similarly for the answer choices, the option letter (A,B,C or D) followed by “)” and then a single space, followed by the question statement and question mark.

Apart from this, while entering the names of the teacher, student or course, the inputs must be case specific i.e. if the name of the teacher is Alex, then while logging in or while trying to enter a course and attempt a quiz created by Alex, the input should not be alex or something else, it should exactly be Alex. Similarly for the courses and student names. 
We implemented this functionality mainly to ensure the uniqueness of names. As this system is not very complex, the only way we can separate two teachers with the exact names is by asking for their usernames and passwords, but if a student wants to access a particular course by a teacher, how would the student know whose course to enter given that we cannot provide the username and passwords of the teachers. So in that situation it would be helpful if one teacher was Alex and the other was alex, just to differentiate between the two. But in general, we assume that the Student names and teacher names are unique.
Things that the program enables the user to do:-
The user can login with their name, username and password and specify if they are a teacher or not.
Teachers can create as many courses as they want. They can also create as any quizzes as they want inside of every course. They can enter a course and edit any of the already created quizzes i.e. add questions, delete questions, replace questions. The only type of questions they can add are MCQ questions. Teachers can also delete an existing course, or delete a specific quiz inside an existing course. The teacher can also view student submissions and the grades they received on any attempt of any quiz.
Students can enter any course by any teacher that has used the system to create a quiz. After entering the course, the student can attempt any of the quizzes that have been created. The user has infinite attempts on every quiz but gets a different set of questions and random order of the options with every attempt. The student can view their submissions for any quiz and also view the grades they received.
The questions can be submitted to the quizzes manually or as file imports in the specific format as described in the first page. 
Therefore, all core requirements and selections have been implemented. 
Apart from this, we have implemented the second optional requirement that allows the teacher to create a pool of questions for a particular quiz and everytime the quiz is attempted by the student a random set of 5 questions are chosen from the pool of questions to be given to the student.

Important details to understand the implementation - 
As the data needs to persist in between runs, we opted to distribute all contents in various files which have been named in a specific manner to identify the purpose of the file, the contents of the file and which person it pertains to.
For this program the things that we needed to keep a track of are as follows:-
The list of teachers that have an account in the system has been kept in a file named teachersList.txt. The details of every teacher is stored in the following format - teacherName$$userName$$password.
The list of students that have an account in the system has been kept in a file named studentList.txt. The details of every teacher is stored in the following format - studentName$$userName$$password.
The list of Courses that have been created by a teacher are kept in a file named teacherName$$userName$$password$$Courses.txt
The list of quizzes that are inside a particular course created by a particular teacher is stored in the file named teacherName$$CourseName$$quizList.txt
The quiz questions for a particular quizNumber for a particular course by a particular teacher is stored in the file named teacherName$$courseName$$quizNumber.txt
The quiz answers for a particular quizNumber for a particular course by a particular teacher is stored in the file named teacherName$$courseName$$quizNumber$$Answers.txt
The attempt number for a particular quiz in a particular course created by a particular teacher attempted by a particular student is stored in a file named studentName$$teacherName$$courseName$$quizNumber$$AttemptTracker.txt
The exact set of questions that were given to a student for a particular attempt of a quiz is stored in a file with the name studentName$$teacherName$$courseName$$quizNumber$$attemptNumberformQuiz.txt
The entire quiz submission of a quiz attempted by a particular student is stored in a file named studentName$$teacherName$$courseName$$quizNumber$$attemptNumber$$submission.txt
The entire quiz submission(containing only the answers the student submitted) of a quiz attempted by a particular student is stored in a file named studentName$$teacherName$$courseName$$quizNumber$$attemptNumber$$submittedAnswers.txt
If the student exits the program, then he/she would first have to attempt and finish the quiz that was left in progress and this quiz that was left in progress is stored in a file named studentName$$progressQuiz.txt
Another thing to note is that when a quiz is created that quiz is stored in the teacherName$$CourseName$$quizList.txt file in the following format:-
QuizquizNumber(number of questions in the quiz)(type of questions)(is it a pool of questions or a list of exact questions)(whether the questions were manually entered or via a file import)(are the questions randomised or not)
An example would be Quiz1(5)(MCQ)(pool)(manually)(false).

Implementation and Functionalities of specific classes:-

Quiz.java - The first constructor is used to add a quiz that is being created by a teacher in their teacherName$$CourseName$$quizList.txt file. It adds the quiz in the format that I have described above QuizquizNumber(number of questions in the quiz)(type of questions)(is it a pool of questions or a list of exact questions)(whether the questions were manually entered or via a file import)(are the questions randomised or not). Basically it includes all the details that we need to uniquely describe a quiz that is being created.

makePoolQuizFile(String filePath , String quizFileName , String answerFilePath)

This method, as the name suggests, lets the teacher create a quiz with not the exact questions but a pool of questions(a set of questions from which questions would be chosen randomly every time) using a file import. Again I would like to specify that for the quiz to be created successfully, the file should be in the exact format as I mentioned at the start of this document. 
The filePath parameter contains the path of the file the user inputs. The quizFileName parameter contains teacherName$$courseName$$quizNumber.txt which is where the contents of the file are copied and stored. The answerFilePath contains the file path (entered by the user) which contains the answers(a list of A,B,C or Ds) for all the questions in the pool of questions. 
This method basically iterates through every line of the file with the questions and stores the contents into the teacherName$$courseName$$quizNumber.txt file. Moreover, it also iterates over the file with the list of answers and copies and stores all the answers in the teacherName$$courseName$$quizNumber$$Answers.txt
File.
If any of the imported files is empty, then the method returns false to theRunner.java class where it is printed that the file paths were not valid and that new file paths must be entered.
This method is also used to edit the contents of a file in case the teacher wants to.

makeExactQuizFile(String filePath , String quizFileName , String answerFilePath)

This method is very similar to the previous one, with the only difference being that it lets the teacher create a quiz using a file import that would have the exact questions that would appear in the quiz and anot a pool of questions. 
All the other details for this method are exactly as the previous method.

makeQuizManual(String quizFileName , String type)

This method also helps to make and edit a quiz but not using a file import. It lets the user make or edit the contents of a quiz manually.
The quizFileName is again teacherName$$courseName$$quizNumber.txt
The file where the quiz title and all the quiz questions would be added in a sequential manner.
String type refers to the type of the questions in the quiz which in our case would be “MCQ”.
This method can be used to create a new quiz from scratch manually or start from where you left on a quiz that was in the process of being created.
It first checks if teacherName$$courseName$$quizNumber.txt is empty or not. If it is empty which means that the quiz needs to be created from scratch, it first asks the user for a quiz title, after which it sequentially asks the user for the question statement(note: the question mark would have to be put by the user) and then the four answer choices for the question. After entering the fourth choice it asks the user for The answer to this question which should be either A,B,C or D. If anything else is entered it prompts to enter the answer again. 
If the file is not already empty which means that the quiz is still being made, then the method runs through the file and figures out if the next thing to be entered is a question or an answer choice and the program continues from there just like before by taking the appropriate inputs from the user.
Runner.java - This is the class with the main method. Its main use is to implement the login/register functionality, and to display different prompts to the user.
At any point if the user enters a wrong option or response, the program prompts the user to enter again as it is an invalid choice.
It first asks the user if they want to register or login. For login, if the account is not present then it displays the appropriate message and if it is present then it logs in successfully. For register, if the user enters the account information and that information already exists in the system, then it directly logs the user in and displays the message that their account already existed, otherwise it just registers the and logs them in.
In the case of a teacher, it provides the option to create, enter and edit a course, delete a course and also to logout. To create or edit a course, the name of the course is asked and it is checked if the course already exists or not and a message is displayed accordingly. After entering a course, the teacher can create a quiz, edit a quiz, delete an existing quiz, view student submissions for the quiz or go back to courses. If the teacher chooses to create a quiz, then they are asked whether they want a pool of questions or exact questions, randomised or not and if they would input the questions manually or using file input. Then accordingly one of the methods in the Quiz.java class is called. Similar is the case with editing a quiz. After entering the name, the appropriate method in Quiz.java is called. 
Similarly the quiz can also be deleted. To view student submissions, the teacher needs to enter the name of the student and then they are given the option to either view the students grading report or their submission for any of the attempts that the student has taken.
On the student side once logged in, the students can log out or enter a course. Once logged in, the first thing that the program checks is the processQuiz.txt file associated with this student to ensure whether there is any quiz that was left in between or not. If yes then a block of code is executed that starts from the question where the student had left and continues to the end of the quiz after which it empties the processQuiz.txt file and sends grades the quiz to generate the grading report. After doing this the attempt number for this quiz is incremented by one by updating the attemptTracker.txt file. After this the student can again choose to enter a course or log out.
To enter a course the student first needs to enter the name of the teacher whose course they want to enter into and after that the name of the course they want to enter into. Then they are given the option to take a quiz, view their grades for the quizzes or view submissions for the quizzes. If they decided to take a quiz, they would have to enter the quizNumber as an integer. Then the takeQuiz() method in Student.java will be called.
After the quiz is over, the student can also view their submission that would include the questions and answers and they can also view the grades they got.
If the teacher has chosen to randomise the questions then the student will get a different set of questions with a different arrangement of options with every attempt.

Student.java - This class has only one method.
takeQuiz(String studentName, String teacherName, String courseName, int quizNumber, String quizIdentifier)
As the name suggests, this method lets the student take/attempt the quiz.
The String quizIdentifier is the String that uniquely identifies all the details of the quiz such as Quiz1(5)(MCQ)(pool)(manually)(false). 
This question performs the following functions in order :-
Iterate through the specified quiz file created by the user and if the the teacher selected to make this quiz random, then it would first randomize the entire quiz by generating a random sequence of the 4 options for each question and after that according to the number of questions to be asked in the quiz, would randomize the order of the questions to be asked for that specific attempt to that student.
It does both of these tasks by first storing all the quiz questions and the answers in a 2D array and then running through 2 for loops to generate a random sequence of the first n integers depending on what n is. 
Note: If the teacher had made a pool of questions for the quiz, then 5 questions would randomly be chosen from the pool of questions. But because of this, it is required that if entering a pool of questions the teacher would have to compulsorily enter at least 5 questions, until then the program would not allow the student to attempt the quiz. Also if the questions are from a pool but not randomized, the teacher would have to enter the questions until they are atleast that much in number that they had entered previously. Otherwise the student would not be able to attempt the quiz until then.
After this the program generates a unique formQuix.txt file for the particular quiz attempt for that particular student and then it iterates over the file and prints each question sequentially. After each question the student can either enter the answer manually or through a file import. This process continues until all the questions are over. While the quiz is in progress the processQuiz.txt and the submissions.txt files are constantly updated to keep a track of the students performance on the quiz for that attempt.
Once the quiz is over, it is submitted automatically and the processQuiz.txt file is emptied. The grading functionality is also implemented in this function itself. It compares the answers entered by the student with the solutions.txt file that was generated with formQuiz.txt and accordingly finds the points earned by the student given that the points associated with each question is 2. All this information is added to the gradingReport.txt file associated with that student, teacher, quiz Number and AttemptNumber. It also shows which questions did the student get wrong and which ones did he get right. After doing this the attempt number for this quiz is incremented by one by updating the attemptTracker.txt file.
The same functionality with slight modifications is implemented in four situations. 
The situations are as follows:-
If the quiz has a pool of questions and is randomised
The quiz has exact questions and is randomized
If the quiz has a pool of questions and is not randomised
The quiz has exact questions and is not randomized
Also, the functionality to add the timestamp with each submission has also been implemented. Every submission that a student makes has a time stamp on it and both the student and the teacher can view the time stamp every time they view the submission the student made.

Courses.java - This constructor of this class is called by the createQuiz() method in Teacher.java in order to create a new course and add it to the courseList.txt file.
The createQuiz() method in this class creates a new quiz by calling the constructor of the Quiz.java class. But if a quiz is already present it returns a boolean value of false which is then used by the Runner.java class to print the appropriate message. 
The enterQuiz() method enables the teacher to enter a certain quiz in order to edit it. Again if the quiz has not yet been made then the function returns a String value of “not contained” which is then used by the Runner.java class to print the appropriate message. 

Teacher.java - The constructor of this class is called by the Runner.java class in order to create a new teacher while registration into the system. There is also another constructor which is used to create a teacher object for a teacher who already has an account in the system.
The createCourse() method in this class creates a new quiz by calling the constructor of the Quiz.java class. But if a course is already present it returns a boolean value of false which is then used by the Runner.java class to print the appropriate message.
The enterCourse() method enables the teacher to enter a certain course in order to edit it. Again if the quiz has not yet been made then the function returns a boolean value of false which is then used by the Runner.java class to print the appropriate message.



Testing done :-
The majority of the working of the project resides in the Quiz.java and the Student.java files as they are the classes in which make the quizzes and let the student attempt those quizzes and also generate all the required reports that can be viewed. The testing for every method inside both these classes has been done and uploaded to Github. There are four files pertaining to these namely, testedQuiz.java, testingQuiz.java, testedStudent.java and testingStudent.java, apart from these four the three .txt files uploaded would also be used for testing the testedStudent.java and testingStudent.java files. Please read the comments in these files for more specific details.

Test 1:- this test covers every possible input and outputs for the login and registration functionality. Includes handling invalid input at any point, handling the case if a user does not have an account already and still tries to login, registration, the case when the user already had an account in the system but probably forgot and wanted to register again or mistakenly chose registration instead of login(in this case the user is directly logged in) and the creation and deletion of accounts. It also shows the logout functionality. 

Select from the following options:-
1. Register
2. Login
3
Invalid input
Select from the following options:-
1. Register
2. Login
asd
Invalid input
Select from the following options:-
1. Register
2. Login
1
Student or teacher?
1
Invalid input
Student or teacher?
lk
Invalid input
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
You have been successfully registered
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
4
Select from the following options:-
1. Register
2. Login
1
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
You were already a registered user and have been directly logged in.
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
6
This is not a valid choice!
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
wert
This is not a valid choice!
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
5
Select from the following options:-
1. Register
2. Login
2
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
name, username and password do not match any entries.
Select from the following options:-
1. Register
2. Login
1
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
You have been successfully registered
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
4
Select from the following options:-
1. Register
2. Login
2
Student or teacher?
Teacher
Enter your name
Alan
Enter the username
as
Enter the password
we
name, username and password do not match any entries.
Select from the following options:-
1. Register
2. Login



Test 2 - This test allows the user to create a quiz inside a specified course. If at any point the teacher tries to access a submission or grades or delete a course or a quiz or edit a course or a quiz that does not already exist, appropriate messages are prompted. The teacher has the option to create the quiz manually or via a file import. 

Select from the following options:-
1. Register
2. Login
2
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
Logged in
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
1
Enter the course name
Mathematics
course successfully created
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
1
Enter the course name
Physics
course successfully created
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
2
Mathematics
Physics
Enter the course name
Mathematics
you are now into the specified course
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
2
Enter the quiz number
1
This quiz has not been created yet
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
3
Enter the name of the student
Ryan
Enter the quiz number
1
Enter the attempt number
1
Do you want to :-
1. View the grades
2. view submission
2
This attempt for this quiz has not been completed yet
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
4
Enter the quiz number to be deleted
1
The quiz does not already exist.
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
1
Enter the quiz number
1
Enter the number of questions in the quiz
6
Do you want to add a pool of questions to randomly choose from or the exact questions for the quiz?
1. pool of questions
2. exact questions
1
Do you want to attach a file or add manually?
1. attach a file
2. add manually
2
Do you want to randomize this quiz or not?
1. randomize
2. don't randomize
1
Enter the title of the quiz
Arithmetic
Do you want to enter a question statement?
1. yes
2. no
1
Enter the question statement
What is 1+1?
Enter the first choice
1
Enter the second option
2
Enter the third option
3
Enter the fourth option and then the answer to this question
Enter the fourth option
4
Enter the answer as either A, B, C or D. The option will be added to the quiz only after the the answer is entered.
G
Enter a valid answer.
B
Do you want to enter a question statement?
1. yes
2. no
1
Enter the question statement
What is 1*1?
Enter the first choice
1
Enter the second option
2
Enter the third option
3
Enter the fourth option and then the answer to this question
Enter the fourth option
4
Enter the answer as either A, B, C or D. The option will be added to the quiz only after the the answer is entered.
A
Do you want to enter a question statement?
1. yes
2. no
1
Enter the question statement
What is 2+2?
Enter the first choice
1
Enter the second option
2
Enter the third option
3
Enter the fourth option and then the answer to this question
Enter the fourth option
4
Enter the answer as either A, B, C or D. The option will be added to the quiz only after the the answer is entered.
D
Do you want to enter a question statement?
1. yes
2. no
1
Enter the question statement
What is 9-9?
Enter the first choice
1
Enter the second option
0
Enter the third option
2
Enter the fourth option and then the answer to this question
Enter the fourth option
3
Enter the answer as either A, B, C or D. The option will be added to the quiz only after the the answer is entered.
B
Do you want to enter a question statement?
1. yes
2. no
1
Enter the question statement
What is 5*5?
Enter the first choice
1
Enter the second option
25
Enter the third option
3
Enter the fourth option and then the answer to this question
Enter the fourth option
34
Enter the answer as either A, B, C or D. The option will be added to the quiz only after the the answer is entered.
B
Do you want to enter a question statement?
1. yes
2. no
1
Enter the question statement
What is 2*0?
Enter the first choice
0
Enter the second option
2
Enter the third option
3
Enter the fourth option and then the answer to this question
Enter the fourth option
1
Enter the answer as either A, B, C or D. The option will be added to the quiz only after the the answer is entered.
A
Do you want to enter a question statement?
1. yes
2. no
2
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
5
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
4
Select from the following options:-
1. Register
2. Login

Test 3 - Here the user creates a quiz using a file import. Appropriate message is shown if the file is empty or has an invalid path.Apart from this the user deletes an already existing quiz. If the quiz is not already present then an appropriate message is shown. After this the user tries to edit the content of the Mathematics quiz that we created in the previous test cases.

For the purpose of this test, please add the following contents to a file named PhysicsQuiz.txt. Also create an empty file named emptyPhysicsQuiz.txt.
Contents in PhysicsQuiz.txt -
Mechanics
1. What is 3N - 2N?
A) 1N
B) 2N
C) 3N
D) 4N
2. What is 4N + 2N?
A) 1N
B) 2N
C) 6N
D) 4N
3. What is 5N - 2N?
A) 1N
B) 2N
C) 3N
D) 4N
4. What is 3m/s - 2m/s?
A) 1m/s
B) 2m/s
C) 3m/s
D) 4m/s
5. What is the unit of Force?
A) Newton
B) meters
C) flux
D) intensity
6. What is 3N + 45N?
A) 45N
B) 46N
C) 47N
D) 48N
7. Rotation is caused by?
A) torque
B) force
C) displacement
D) none of the above

Also create a file named ans.txt with the following contents in it -
A
C
C
A
A
D
A


Select from the following options:-
1. Register
2. Login
2
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
Logged in
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
2
Mathematics
Physics
Enter the course name
aw
This course has not yet been created
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
3
Mathematics
Physics
Enter the Course name
Physics
Course deleted successfully
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
1
Enter the course name
Physics
course successfully created
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
2
Mathematics
Physics
Enter the course name
Physics
you are now into the specified course
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
2
Enter the quiz number
1
This quiz has not been created yet
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
1
Enter the quiz number
1
Enter the number of questions in the quiz
7
Do you want to add a pool of questions to randomly choose from or the exact questions for the quiz?
1. pool of questions
2. exact questions
2
Do you want to attach a file or add manually?
1. attach a file
2. add manually
1
Do you want to randomize this quiz or not?
1. randomize
2. don't randomize
1
Enter the file path
emptyPhysicsQuiz.txt
Enter the file path of the answers to the quiz
ans.txt
Either the file was empty or the path was invalid. Please enter a valid path.
Enter the file path
PhysicsQuiz.txt
Enter the file path of the answers to the quiz
ans.txt
The quiz was made successfully
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
1
Enter the quiz number
2
Enter the number of questions in the quiz
1
Do you want to add a pool of questions to randomly choose from or the exact questions for the quiz?
1. pool of questions
2. exact questions
2
Do you want to attach a file or add manually?
1. attach a file
2. add manually
1
Do you want to randomize this quiz or not?
1. randomize
2. don't randomize
2
Enter the file path
PhysicsQuiz.txt
Enter the file path of the answers to the quiz
ans.txt
The quiz was made successfully
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
4
Quiz1
Quiz2
Enter the quiz number to be deleted
2
The quiz was deleted successfully.
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
5
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
2
Mathematics
Physics
Enter the course name
Mathematics
you are now into the specified course
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
2
Quiz1
Enter the quiz number
1
Select from the following options.
1. Change the title
2. Continue adding questions and respective options
3. Delete a question
4. Replace a question
5. Exit the quiz without editing
1
Enter the new title
Modular Arithmetic
Title successfully changed.
Select from the following options.
1. Change the title
2. Continue adding questions and respective options
3. Delete a question
4. Replace a question
5. Exit the quiz without editing
3
Modular Arithmetic
1. What is 1+1?
A) 1
B) 2
C) 3
D) 4
2. What is 1*1?
A) 1
B) 2
C) 3
D) 4
3. What is 2+2?
A) 1
B) 2
C) 3
D) 4
4. What is 9-9?
A) 1
B) 0
C) 2
D) 3
5. What is 5*5?
A) 1
B) 25
C) 3
D) 34
6. What is 2*0?
A) 0
B) 2
C) 3
D) 1

Enter the question number to be deleted.
5
Question deleted successfully
Select from the following options.
1. Change the title
2. Continue adding questions and respective options
3. Delete a question
4. Replace a question
5. Exit the quiz without editing
4
Modular Arithmetic
1. What is 1+1?
A) 1
B) 2
C) 3
D) 4
2. What is 1*1?
A) 1
B) 2
C) 3
D) 4
3. What is 2+2?
A) 1
B) 2
C) 3
D) 4
4. What is 9-9?
A) 1
B) 0
C) 2
D) 3
5. What is 2*0?
A) 0
B) 2
C) 3
D) 1
Enter the question number of the question to be replaced
5
Enter the new question statement.
What is 8-7?
Enter the first option
1
Enter the second option
2
Enter the third option
3
Enter the fourth option and then the answer to this question
Enter the fourth option
4
Enter the answer. The option will be added to the quiz only after the the answer is entered.
A
Question replaced successfully
Select from the following options.
1. Change the title
2. Continue adding questions and respective options
3. Delete a question
4. Replace a question
5. Exit the quiz without editing
2
Enter the question and four options for question number 6
Enter the question statement
What is 40/4?
Enter the first choice
1
Enter the second option
8
Enter the third option
9
Enter the fourth option and then the answer to this question
Enter the fourth option
10
Enter the answer. The option will be added to the quiz only after the the answer is entered.
D
Select from the following options.
1. Change the title
2. Continue adding questions and respective options
3. Delete a question
4. Replace a question
5. Exit the quiz without editing
5
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
5
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
4
Select from the following options:-
1. Register
2. Login


Test 4 - This test includes the student side of the program. The student enters the courses and attempts any course he/she likes. With every attempt the order of the questions and the options for each question is randomized. After completion both the student and teacher can view the submissions and grades for any attempt of the quiz. An invalid input at any point is also handled appropriately.
And as all of these test cases are being run sequentially, the data persists between each run.

Select from the following options:-
1. Register
2. Login
2
Student or teacher?
Student
Enter your name
Ryan
Enter the username
qw
Enter the password
we
Logged in
Choose from the following options.
1. Enter a course
2. Log out
3. Delete account
1
Enter the name of the teacher
Alex
Enter the course name
Mathematics
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
1
Quiz1
Enter the quiz number
2
This quiz does not exist
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
2
Enter the quiz number.
1
Enter the attempt number.
1
This attempt for this quiz has not been completed yet or the quiz has not been attempted at all.
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
3
Enter the quiz number.
1
Enter the attempt number.
1
This attempt for this quiz has not been completed yet or the quiz has not been attempted at all.
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
1
Quiz1
Enter the quiz number
1
Modular Arithmetic
1. What is 1*1?
A) 1
B) 3
C) 4
D) 2
1. Enter the response
2. attach a file with the response
1
Enter the answer
A
2. What is 8-7?
A) 1
B) 3
C) 4
D) 2
1. Enter the response
2. attach a file with the response
1
Enter the answer
A
3. What is 40/4?
A) 1
B) 9
C) 10
D) 8
1. Enter the response
2. attach a file with the response
1
Enter the answer
B
4. What is 2+2?
A) 1
B) 3
C) 4
D) 2
1. Enter the response
2. attach a file with the response
1
Enter the answer
C
5. What is 9-9?
A) 1
B) 2
C) 3
D) 0
1. Enter the response
2. attach a file with the response
1
Enter the answer
D
This quiz is over and is being submitted
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
2
Enter the quiz number.
1
Enter the attempt number.
1
Modular Arithmetic
1. What is 1*1?
A) 1
B) 3
C) 4
D) 2
A
2. What is 8-7?
A) 1
B) 3
C) 4
D) 2
A
3. What is 40/4?
A) 1
B) 9
C) 10
D) 8
B
4. What is 2+2?
A) 1
B) 3
C) 4
D) 2
C
5. What is 9-9?
A) 1
B) 2
C) 3
D) 0
D
2022-04-11 17:03:14
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
3
Enter the quiz number.
1
Enter the attempt number.
1
Question 1 - correct - 2 points
Question 2 - correct - 2 points
Question 3 - wrong - 0 points
Question 4 - correct - 2 points
Question 5 - correct - 2 points
total points - 8
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
1
Quiz1
Enter the quiz number
1
Modular Arithmetic
1. What is 1+1?
A) 4
B) 1
C) 2
D) 3
1. Enter the response
2. attach a file with the response
1
Enter the answer
A
2. What is 9-9?
A) 3
B) 1
C) 0
D) 2
1. Enter the response
2. attach a file with the response
1
Enter the answer
A
3. What is 1*1?
A) 4
B) 1
C) 2
D) 3
1. Enter the response
2. attach a file with the response
1
Enter the answer
B
4. What is 40/4?
A) 10
B) 1
C) 8
D) 9
1. Enter the response
2. attach a file with the response
1
Enter the answer
C
5. What is 8-7?
A) 4
B) 1
C) 2
D) 3
1. Enter the response
2. attach a file with the response
1
Enter the answer
D
This quiz is over and is being submitted
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
2
Enter the quiz number.
1
Enter the attempt number.
2
Modular Arithmetic
1. What is 1+1?
A) 4
B) 1
C) 2
D) 3
A
2. What is 9-9?
A) 3
B) 1
C) 0
D) 2
A
3. What is 1*1?
A) 4
B) 1
C) 2
D) 3
B
4. What is 40/4?
A) 10
B) 1
C) 8
D) 9
C
5. What is 8-7?
A) 4
B) 1
C) 2
D) 3
D
2022-04-11 17:03:57
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
3
Enter the quiz number.
1
Enter the attempt number.
2
Question 1 - wrong - 0 points
Question 2 - wrong - 0 points
Question 3 - correct - 2 points
Question 4 - wrong - 0 points
Question 5 - wrong - 0 points
total points - 2
Select from the following options
1. Attempt a quiz
2. View submission
3. View grades.
4. Exit
4
Choose from the following options.
1. Enter a course
2. Log out
3. Delete account
2
Select from the following options:-
1. Register
2. Login
2
Student or teacher?
Teacher
Enter your name
Alex
Enter the username
as
Enter the password
sd
Logged in
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
2
Mathematics
Enter the course name
Mathematics
you are now into the specified course
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
3
Enter the name of the student
Ryan
Enter the quiz number
1
Enter the attempt number
1
Do you want to :-
1. View the grades
2. view submission
1
Question 1 - correct - 2 points
Question 2 - correct - 2 points
Question 3 - wrong - 0 points
Question 4 - correct - 2 points
Question 5 - correct - 2 points
total points - 8
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
2
Quiz1
Enter the quiz number
1
Select from the following options.
1. Change the title
2. Continue adding questions and respective options
3. Delete a question
4. Replace a question
5. Exit the quiz without editing
5
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
3
Enter the name of the student
Ryan
Enter the quiz number
1
Enter the attempt number
2
Do you want to :-
1. View the grades
2. view submission
2
Modular Arithmetic
1. What is 1+1?
A) 4
B) 1
C) 2
D) 3
A
2. What is 9-9?
A) 3
B) 1
C) 0
D) 2
A
3. What is 1*1?
A) 4
B) 1
C) 2
D) 3
B
4. What is 40/4?
A) 10
B) 1
C) 8
D) 9
C
5. What is 8-7?
A) 4
B) 1
C) 2
D) 3
D
2022-04-11 17:03:57
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
3
Enter the name of the student
Ryan
Enter the quiz number
2
Enter the attempt number
2
Do you want to :-
1. View the grades
2. view submission
2
This attempt for this quiz has not been completed yet
Choose from the following options.
1. Create a new quiz
2. Enter and edit an existing quiz
3. View student submissions for a quiz
4. Delete an existing quiz
5. Exit to Courses
5
Choose from the following options.
1. Create a new course
2. Enter and edit a course
3. Delete an existing course
4. Log out
5. Delete account
4
Select from the following options:-
1. Register
2. Login



