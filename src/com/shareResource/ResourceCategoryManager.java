package com.shareResource;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hwang4
 * Date: 7/19/12
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceCategoryManager {
    private Map<String, ResourcesManager> categories;
    private List<String> categoryNames;

    public ResourceCategoryManager(boolean populate) {
        categories = new HashMap<String, ResourcesManager>();
        categoryNames = new ArrayList<String>();
        if (populate){
            populate();
        }
    }
    private void populate(){
        String[] temp = {"Books","CDs","Clothing","Other"};
        for (String category : temp) {
            categoryNames.add(category);
            ResourcesManager newRM = new ResourcesManager();
            ResourceStaticData rsd = new ResourceStaticData();
            categories.put(category, newRM);
        }
    }
    public ResourcesManager getCategory(String category) {
        return categories.get(category);
    }
    public void reset(){
        categories.clear();
        categoryNames.clear();
        populate();
    }
}
class ResourceStaticData {

    ResourceStaticData(){

    }
    static void fill(String category, ResourcesManager resourcesManager){
        if (category.equals("Books")){
            resourcesManager.addNewResourceType("The Sound and the Fury");
            resourcesManager.addNewResourceType("Calculus I");
            resourcesManager.addNewResourceType("Calculus II");
            resourcesManager.addNewResourceType("P vs NP");
            resourcesManager.addNewResourceType("War and Peace");


            Person a = new Person("Alice");
            Person b = new Person("Bob");
            resourcesManager.addNewResource("The Sound and the Fury", a);
            resourcesManager.addNewResource("Calculus I", b);
            resourcesManager.addNewResource("Calculus II", b);
            resourcesManager.addNewResource("P vs NP", b);
            resourcesManager.addNewResource("P vs NP", a);


            Calendar d = new GregorianCalendar();
            d.add(Calendar.DATE, 10);
            resourcesManager.addRequest("The Sound and the Fury", b);
            resourcesManager.addRequest("Calculus I", a, d.getTime());
        }
    }
}