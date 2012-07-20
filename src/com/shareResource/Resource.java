package com.shareResource;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: hwang4
 * Date: 7/19/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Resource implements Serializable{
    private String resourceName;
    private Person owner;
    private Set<Person> currentUsers;

    public Resource(String pResourceName, Person pOwner){
        resourceName = pResourceName;
        owner = pOwner;
        currentUsers = new HashSet<Person>();
        currentUsers.add(owner);
    }
    void changeResourceUser(Person requester){
        currentUsers.clear();
        currentUsers.add(requester);
    }
    void removeResourceUser(){
        currentUsers.clear();
        currentUsers.add(owner);
    }
    void addUsers(Set<Person> requesters){
        currentUsers.addAll(requesters);
    }
    boolean isCurrentUser(Person user) {
        return currentUsers.contains(user);
    }
	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * @return the owner
	 */
	public Person getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	/**
	 * @return the currentUsers
	 */
	public Set<Person> getCurrentUsers() {
		return currentUsers;
	}
	/**
	 * @param currentUsers the currentUsers to set
	 */
	public void setCurrentUsers(Set<Person> currentUsers) {
		this.currentUsers = currentUsers;
	}
	
}
