package com.bridgelabz.fundoo.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.UserDetails;
@Service
public class RegistrationServiceImpl {

	private List<UserDetails> obj= new ArrayList<>(Arrays.asList(new UserDetails()));
	public List<UserDetails> getDetails()
	{
		UserDetails ob1=obj.get(0);
		if(ob1.getName()==null)
		{
			obj.remove(0);
		}
		return obj;
	}
	public void addUser(UserDetails object)
	{
		obj.add(object); 	
	}
	
	public UserDetails getData(String name)
	{
//		for(int i=0; i<obj.size();i++)
//		{
//			UserDetails ob1=obj.get(i);
//			System.out.println(ob1.getName());
//			if(ob1.getName().equals(name))
//			{
//				return ob1;
//			}
//		}
//		return null;
		return obj.stream().filter(t->t.getName().equals(name)).findFirst().get();
				
	}

	public void updateUser(UserDetails object, String name) {
		for(int i=1; i<obj.size();i++)
		{
			UserDetails ob1=obj.get(i);
			
			if(ob1.getName().equals(name))
			{
				obj.set(i, object);
				return;
			}
		}				
	}

	public void deleteUser(String name) {
//		for(int i=1; i<obj.size();i++)
//		{
//			UserDetails ob1=obj.get(i);
//			
//			if(ob1.getName().equals(name))
//			{
//				obj.remove(i);
//				return;
//			}
//		}				
		obj.removeIf(t->t.getName().equals(name));
	}
}
