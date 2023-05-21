package in.library.dto;

import java.io.Serializable;

public class book implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	private int ISBN;
	private String authorname;
	private int status;
	
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "book [name=" + name + ", ISBN=" + ISBN + ", authorname=" + authorname + ", status=" + status + "]";
	}
}
