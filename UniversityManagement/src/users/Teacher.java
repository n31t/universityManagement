package University Management;


/**
* @generated
*/
public class Teacher extends Employee implements CanViewCourses, GiveMarks, InfoStudents {
    
    /**
    * @generated
    */
    private Vector courses;
    
    /**
    * @generated
    */
    private TeacherType teacherType;
    
    
    /**
    * @generated
    */
    private Course course;
    
    

    /**
    * @generated
    */
    private Vector getCourses() {
        return this.courses;
    }
    
    /**
    * @generated
    */
    private Vector setCourses(Vector courses) {
        this.courses = courses;
    }
    
    
    /**
    * @generated
    */
    private TeacherType getTeacherType() {
        return this.teacherType;
    }
    
    /**
    * @generated
    */
    private TeacherType setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
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
    public TeacherType getType() {
        //TODO
        return University Management.Enums.TeacherType.LECTURER;
    }
    
    
}
