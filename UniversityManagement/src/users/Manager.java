package University Management;


/**
* @generated
*/
public class Manager extends Employee implements InfoTeachers, CanViewCourses, InfoRequests, InfoStudents {
    
    /**
    * @generated
    */
    private ManagerType managerType;
    
    
    /**
    * @generated
    */
    private Request request;
    
    

    /**
    * @generated
    */
    private ManagerType getManagerType() {
        return this.managerType;
    }
    
    /**
    * @generated
    */
    private ManagerType setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }
    
    
    
    /**
    * @generated
    */
    public Request getRequest() {
        return this.request;
    }
    
    /**
    * @generated
    */
    public Request setRequest(Request request) {
        this.request = request;
    }
    
    
    

    //                          Operations                                  
    
    
}
