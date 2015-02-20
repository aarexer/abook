package com.github.aarexer.address.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by rexer on 20.02.15.
 */

@XmlRootElement(name = "persons")
public class PersonDataWrapper
{
    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons()
    {
        return persons;
    }

    public void setPersons(List<Person> persons)
    {
        this.persons = persons;
    }
}
