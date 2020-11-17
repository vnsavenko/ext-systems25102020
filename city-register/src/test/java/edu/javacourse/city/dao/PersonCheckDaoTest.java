package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckExeption;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonCheckDaoTest {

    @Test
    public void checkPerson() throws PersonCheckExeption {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Васильев");
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995, 03, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");


        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse response = dao.checkPerson(pr);
        Assert.assertTrue(response.isRegistered());
        Assert.assertFalse(response.isTemporal());


//        System.out.println(response.isRegistered());
//        System.out.println(response.isTemporal());


    }

    @Test
    public void checkPerson2() throws PersonCheckExeption {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Васильева");
        pr.setGivenName("Ирина");
        pr.setPatronymic("Петровна");
        pr.setDateOfBirth(LocalDate.of(1997, 8, 21));
        pr.setStreetCode(1);
        pr.setBuilding("271");
        pr.setApartment("4");


        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse response = dao.checkPerson(pr);
        Assert.assertTrue(response.isRegistered());
        Assert.assertFalse(response.isTemporal());

    }

}

//'Васильев', 'Павел', 'Николаевич', '1995-03-18', '1234',
//        '123456', '2015-04-11', null, null)