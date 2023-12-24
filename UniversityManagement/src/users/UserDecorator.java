package users;

public abstract class UserDecorator extends User{

	private static final long serialVersionUID = 1L;
	protected User decoratedUser;
	public UserDecorator(User decoratedUser) {
        this.decoratedUser = decoratedUser;
    }
	
	public void showCommands() {
		decoratedUser.showCommands(); 
	}
	public String getName() {
		 return decoratedUser.getName();
	}
	public int getUserId() {
		 return decoratedUser.getUserId();
	}
	public String getPassword() {
		 return decoratedUser.getPassword();
	}
}
