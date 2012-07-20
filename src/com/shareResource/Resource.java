package com.shareResource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: hwang4
 * Date: 7/19/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Resource extends ResourceType{
    private Person owner;
    private Set<Person> currentUsers;

    Resource(String resourceName, Person pOwner){
        super(resourceName);
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
}
