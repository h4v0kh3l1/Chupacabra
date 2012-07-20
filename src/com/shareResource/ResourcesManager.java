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
    private Map<String, ResourceManager> managers;
    private Collection<Person> groupFriends;
    private List<String> resourceTypes;

    public ResourcesManager() {
        managers = new HashMap<String, ResourceManager>();
        resourceTypes = new ArrayList<String>();
    }
    public ResourcesManager(boolean populate){
        managers = new HashMap<String , ResourceManager>();
        resourceTypes = new ArrayList<String>();
        if (populate){
            //Populate with static data

        }
    }

    public void addNewResourceType(String newType){
        if (!isResourceListed(newType)){
            resourceTypes.add(newType);
            managers.put(newType, new ResourceManager(newType));
        }
    }

    public void removeResourceType(String resourceType){//TODO complete
        if (isResourceListed(resourceType)){

        }
    }

    public void addNewResource(String newResource, Person resourceOwner){
        managers.get(newResource).addResource(resourceOwner);
    }
    public void removeResource(String resource, Person resourceOwner){
        managers.get(resource).removeResource(resourceOwner);
    }
    public void addRequest(String resource, Person person, Date dayRequestedBy){
        Calendar c = new GregorianCalendar();
        if (resourceTypes.contains(resource)){
            managers.get(resource).addRequest(person, dayRequestedBy);
        } else {
            throwDNEError();
        }
    }
    public void addRequest(String resource, Person person){
        if (resourceTypes.contains(resource)){
            managers.get(resource).addRequest(person);
        }
    }
    public void handOffResource(String resource, Person oldUser){
        try {
            managers.get(resource).next(oldUser);
        } catch (NullPointerException e){

        } catch (Exception e){

        }
    }
    public void shareResource(String resource,Person user, Set<Person> otherUsers){
        managers.get(resource).share(user, otherUsers);
    }
    public boolean isResourceListed(String resource){
        return resourceTypes.contains(resource);
    }
    public List<String> getResourceTypes(){
        return resourceTypes;
    }
    public ResourceManager getResourceManager(String resource) {
        return managers.get(resource);
    }
    public void throwDNEError(){

    }

}
