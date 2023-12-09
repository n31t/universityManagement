package University Management;


/**
* @generated
*/
public class Student extends User implements InfoTeachers, CanViewCourses {
    
    /**
    * @generated
    */
    private Vector<Courses> coursesEnrolled;
    
    /**
    * @generated
    */
    private Vector<Courses> passedCourses;
    
    /**
    * @generated
    */
    private Vector credits;
    
    /**
    * @generated
    */
    private Vector organizations;
    
    /**
    * @generated
    */
    private int studyYear;
    
    /**
    * @generated
    */
    private School school;
    
    /**
    * @generated
    */
    private Vector schedule;
    
    
    /**
    * @generated
    */
    private Course course;
    
    

    /**
    * @generated
    */
    private Vector<Courses> getCoursesEnrolled() {
        return this.coursesEnrolled;
    }
    
    /**
    * @generated
    */
    private Vector<Courses> setCoursesEnrolled(Vector<Courses> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }
    
    
    /**
    * @generated
    */
    private Vector<Courses> getPassedCourses() {
        return this.passedCourses;
    }
    
    /**
    * @generated
    */
    private Vector<Courses> setPassedCourses(Vector<Courses> passedCourses) {
        this.passedCourses = passedCourses;
    }
    
    
    /**
    * @generated
    */
    private Vector getCredits() {
        return this.credits;
    }
    
    /**
    * @generated
    */
    private Vector setCredits(Vector credits) {
        this.credits = credits;
    }
    
    
    /**
    * @generated
    */
    private Vector getOrganizations() {
        return this.organizations;
    }
    
    /**
    * @generated
    */
    private Vector setOrganizations(Vector organizations) {
        this.organizations = organizations;
    }
    
    
    /**
    * @generated
    */
    private int getStudyYear() {
        return this.studyYear;
    }
    
    /**
    * @generated
    */
    private int setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }
    
    
    /**
    * @generated
    */
    private School getSchool() {
        return this.school;
    }
    
    /**
    * @generated
    */
    private School setSchool(School school) {
        this.school = school;
    }
    
    
    /**
    * @generated
    */
    private Vector getSchedule() {
        return this.schedule;
    }
    
    /**
    * @generated
    */
    private Vector setSchedule(Vector schedule) {
        this.schedule = schedule;
    }
    
    
    
    /**
    * @generated
    */
    public Course getCourse() {
        return this.course;
    }
    
    /**
    * @generated
    */
    public Course setCourse(Course course) {
        this.course = course;
    }
    
    
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void getCoursesEnrolled() {
        //TODO
    }
    
    /**
    * @generated
    */
    public double getGpa() {
        //TODO
        return 0.0;
    }
    
    /**
    * @generated
    */
    public int getCreditsEarned() {
        //TODO
        return 0;
    }
    
    
}
