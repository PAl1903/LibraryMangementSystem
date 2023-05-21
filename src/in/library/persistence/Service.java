package in.library.persistence;

import java.util.ArrayList;

import in.library.dto.book;
import in.library.dto.student;

public interface  Service {
	public int[] login(String username,String Password);
	public int signup(String useremailid,String Password);
	public ArrayList<book> search(String query);
	public ArrayList<book> booklist();
	public ArrayList<student> studentlist();
	public int issuebook(int isbn,int sid);
	public long returnbook(int isbn,int sid);
	public int addbook(book b);
	public int deletebook(int isbn);
	public int signupvalidate(String emailid);
}
