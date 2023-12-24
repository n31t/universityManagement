package University Management;


/**
* @generated
*/
public class Course {
    
    /**
    * @generated
    */
    private int courseId;
    
    /**
    * @generated
    */
    private String courseName;
    
    /**
    * @generated
    */
    private Vector studentsEnrolled;
    
    /**
    * @generated
    */
    private Vector instructors;
    
    /**
    * @generated
    */
    private Vector prereq;
    
    /**
    * @generated
    */
    private ElectiveType elective;
    
    /**
    * @generated
    */
    private Vector schools;
    
    /**
    * @generated
    */
    private int costInCredits;
    
    /**
    * @generated
    */
    private Map<Student.id, Mark>> marks;
    
    /**
    * @generated
    */
    private Vector lessons;
    
    
    /**
    * @generated
    */
    private Teacher teacher;
    
    /**
    * @generated
    */
    private Lesson lesson;
    
    /**
    * @generated
    */
    private Student student;
    
    /**
    * @generated
    */
    private Mark mark;
    
    

    /**
    * @generated
    */
    private int getCourseId() {
        return this.courseId;
    }
    
    /**
    * @generated
    */
    private int setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    
    /**
    * @generated
    */
    private String getCourseName() {
        return this.courseName;
    }
    
    /**
    * @generated
    */
    private String setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    
    /**
    * @generated
    */
    private Vector getStudentsEnrolled() {
        return this.studentsEnrolled;
    }
    
    /**
    * @generated
    */
    private Vector setStudentsEnrolled(Vector studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }
    
    
    /**
    * @generated
    */
    private Vector getInstructors() {
        return this.instructors;
    }
    
    /**
    * @generated
    */
    private Vector setInstructors(Vector instructors) {
        this.instructors = instructors;
    }
    
    
    /**
    * @generated
    */
    private Vector getPrereq() {
        return this.prereq;
    }
    
    /**
    * @generated
    */
    private Vector setPrereq(Vector prereq) {
        this.prereq = prereq;
    }
    
    
    /**
    * @generated
    */
    private ElectiveType getElective() {
        return this.elective;
    }
    
    /**
    * @generated
    */
    private ElectiveType setElective(ElectiveType elective) {
        this.elective = elective;
    }
    
    
    /**
    * @generated
    */
    private Vector getSchools() {
        return this.schools;
    }
    
    /**
    * @generated
    */
    private Vector setSchools(Vector schools) {
        this.schools = schools;
    }
    
    
    /**
    * @generated
    */
    private int getCostInCredits() {
        return this.costInCredits;
    }
    
    /**
    * @generated
    */
    private int setCostInCredits(Integer costInCredits) {
        this.costInCredits = costInCredits;
    }
    
    
    /**
    * @generated
    */
    private Map<Student.id, Mark>> getMarks() {
        return this.marks;
    }
    
    /**
    * @generated
    */
    private Map<Student.id, Mark>> setMarks(Map<Student.id, Mark>> marks) {
        this.marks = marks;
    }
    
    
    /**
    * @generated
    */
    private Vector getLessons() {
        return this.lessons;
    }
    
    /**
    * @generated
    */
    private Vector setLessons(Vector lessons) {
        this.lessons = lessons;
    }
    
    
    
    /**
    * @generated
    */
    public Mark getMark() {
        return this.mark;
    }
    
    /**
    * @generated
    */
    public Mark setMark(Mark mark) {
        this.mark = mark;
    }
    
    
    /**
    * @generated
    */
    public Teacher getTeacher() {
        return this.teacher;
    }
    
    /**
    * @generated
    */
    public Teacher setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    
    /**
    * @generated
    */
    public Lesson getLesson() {
        return this.lesson;
    }
    
    /**
    * @generated
    */
    public Lesson setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    
    
    /**
    * @generated
    */
    public Student getStudent() {
        return this.student;
    }
    
    /**
    * @generated
    */
    public Student setStudent(Student student) {
        this.student = student;
    }
    
    
    

    //                          Operations                                  
    
    
}
