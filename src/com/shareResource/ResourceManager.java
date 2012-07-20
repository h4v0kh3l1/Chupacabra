package com.shareResource;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: hwang4
 * Date: 7/19/12
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
class ResourceManager {
    private String resourceName;
    private String description; //or any other metadata we want to store. Be sure to add to constructor.
    private Set<Resource> availableResources;
    private Queue<WaitlistUnit> waitlist;
    private class WaitlistUnit implements Comparable<WaitlistUnit>{
        Person waiter;
        Date startDate;
        Date requestDate;
        private final int MSINDAY = 86400000;

        WaitlistUnit(Person pWaiter){
            Calendar c = new GregorianCalendar();
            waiter = pWaiter;
            startDate = c.getTime();
            Calendar d = new GregorianCalendar();
            d.setTime(startDate);
            d.add(Calendar.DATE, 60);
            requestDate = d.getTime();
        }
        WaitlistUnit(Person pWaiter, Date pRequestDate) {
            Calendar c = new GregorianCalendar();
            waiter = pWaiter;
            startDate = c.getTime();
            requestDate = pRequestDate;
        }
        Integer getPriority() {
            Calendar c = new GregorianCalendar();
            long daysElapsed = (c.getTimeInMillis() - startDate.getTime())/ MSINDAY;
            long daysRemaining = (requestDate.getTime() - c.getTimeInMillis())/MSINDAY;
            return (int) (daysElapsed / (10 * daysRemaining + 1));
        }
        public int compareTo(WaitlistUnit otherWaiter){
            return this.getPriority().compareTo(otherWaiter.getPriority());
        }
        Person getWaiter(){
            return waiter;
        }
    };

    ResourceManager(String pResourceName){
        resourceName = pResourceName;
        availableResources = new HashSet<Resource>();
        waitlist = new PriorityBlockingQueue<WaitlistUnit>();
    }

    void addRequest(Person requester, Date dayRequestedBy){
        waitlist.add(new WaitlistUnit(requester, dayRequestedBy));
    }
    void addRequest(Person requester){
        waitlist.add(new WaitlistUnit(requester));
    }
    void addResource(Person owner){
        availableResources.add(new Resource(resourceName, owner));
    }
    boolean removeResource(Person owner){
        availableResources.remove(isPersonUsingResource(owner));
        if (availableResources != null){
            return true;
        } else {
            return false;
        }
    }
    void next(Person oldUser){
        Resource targetRsc = isPersonUsingResource(oldUser);
        if (targetRsc != null) {
            WaitlistUnit next = waitlist.poll();
            if (next != null) {
                targetRsc.changeResourceUser(next.getWaiter());
            }
            else {
                targetRsc.removeResourceUser();
            }
        }
    }
    private Resource isPersonUsingResource(Person user){
        Iterator<Resource> checker = availableResources.iterator();
        while (checker.hasNext()){
            Resource r = checker.next();
            if (r.isCurrentUser(user)) {return r;}
        }
        return null;
    }
    void share(Person user, Set<Person> otherUsers){
        Resource targetRsc = isPersonUsingResource(user);
        if (targetRsc != null){
            targetRsc.addUsers(otherUsers);
        }
    }
    public Set<Resource> getAvailableResources(){
        return availableResources;
    }
}
