package com.shareResource;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: hwang4
 * Date: 7/19/12
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Person implements Serializable{
    private String name;

    public Person(String pName){
        name = pName;
    }
    public String getName(){
        return name;
    }
}
