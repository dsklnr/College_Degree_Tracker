This is an android application that was created by using Java in Android Studio.

This application was created to learn recycer views, how to implement drawable icons, 
and learn the basics of android development.

The main screen displays three buttons (terms, courses, and assessments for the user 
to click on. The terms button directs the user to a list of their added terms. The 
courses button directs the user to a list of their added courses. The assessments 
button directs the user to a list of their added assessments.

The terms page displays a term name, term number, a start date, and an end date. At 
the top left is a back arrow that redirects the user back to the previous screen. At 
the top right is a plus button that allows the user to create a new term. Each term 
is displayed in a recycler view. This allows the user to click on a term's card and 
see the details and edit the term.  

After clicking on a term, the terms information is displayed. This includes the term 
name, start date, end date, a recycler view that shows each course in the term, an 
add course button, and a save button. In action bar the user can click the back button 
to return to the previous screen. On the right side of the action bar is a button with 
three dots. This button can be clicked to display a delete button.

The courses page displays a course name, course number, course status, start date, 
end date, professor's name, professor's phone number, and the professor's email address 
within a card in a recycler view. The action bar contains a back arrow to go back to 
the previous page and a plus button to add a new course. Each course card can be clicked
on to see the details and edit the course.

After clicking on a course, the course information is displayed. This includes the course
name, term number, course status, start date, end date, a recycler view with assessment
cards for this course, an add assessment button, the professor's name, the professor's phone
number, the professor's email address, notes for the course, and a save button. The action 
bar contains a back button and an icon with three dots that allows the user to delete the 
course, set a start date reminder notification, and share the course notes via email.

The assessments page displays an assessment name, assessment type, and a due date within 
a card in a recycler view. The action bar contains a back arrow to go back to the previous
page and a plus button to add a new assessment. Each assessment card can be clicked on to
see the details and edit the assessment.

After clicking on an assessment, the assessment information is displayed. This includes 
the assessment name, course ID for the assessment, assessment type, a due date, and a 
save button. In action bar the user can click the back button to return to the previous 
screen. On the right side of the action bar is a button with three dots. This button can 
be clicked to display a delete button and a button to set a due date reminder notification.
