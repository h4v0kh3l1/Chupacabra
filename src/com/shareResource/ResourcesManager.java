package com.shareResource;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hwang4
 * Date: 7/19/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourcesManager {
    Map<ResourceType, ResourceManager> managers;
    Collection<Person> groupFriends;
    Map<String, ResourceType> resourceTypes;

    public ResourcesManager() {
        managers = new HashMap<ResourceType, ResourceManager>();
        resourceTypes = new HashMap<String, ResourceType>();
    }

    public void addNewResourceType(String newType){
        if (!resourceTypes.containsKey(newType)){
            ResourceType newResource = new ResourceType(newType);
            resourceTypes.put(newType, newResource);
            managers.put(newResource, new ResourceManager(newType));
        }
    }

    public void removeResourceType(String resourceType){//TODO complete

    }

    public void addNewResource(String newResource, Person resourceOwner){
        managers.get(resourceTypes.get(newResource)).addResource(resourceOwner);
    }
    public void removeResource(String resource, Person resourceOwner){
        managers.get(resourceTypes.get(resource)).removeResource(resourceOwner);
    }
    public void addRequest(String resource, Person person, Date dayRequestedBy){
        Calendar c = new GregorianCalendar();
        if (resourceTypes.containsKey(resource)){
            managers.get(resourceTypes.get(resource)).addRequest(person, dayRequestedBy);
        } else {
            throwDNEError();
        }
    }
    public void handOffResource(String resource, Person oldUser){//TODO complete
        try {
            managers.get(resourceTypes.get(resource)).next(oldUser);
        } catch (NullPointerException e){

        } catch (Exception e){

        }
    }
    public void shareResource(String resource,Person user, Set<Person> otherUsers){
        managers.get(resourceTypes.get(resource)).share(user, otherUsers);
    }
    public void throwDNEError(){

    }

}
