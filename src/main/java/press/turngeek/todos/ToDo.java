package press.turngeek.todos;

import java.util.Date;

public class ToDo {
	
	private String description;
	private Date created;

	public ToDo() {

	}

	public ToDo(String description, String created) {
		this.description = description;
		this.created = new Date(created);
	}

	public ToDo(String description, Date created) {
		this.description = description;
		this.created = created;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	

}
