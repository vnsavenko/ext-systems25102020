package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckExeption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class PersonCheckDao
{

    private static final String SQL_REQUEST = "select temporal, upper(p.sur_name), upper('Васильев') from cr_address_person ap\n" +
            "inner join cr_person p on p.person_id = ap.person_id\n" +
            "inner join cr_address a on a.address_id = ap.address_id\n" +
            "where\n" +
            "p.sur_name = ?\n" +
            "and p.given_name = ? \n" +
            "and p.patronymic = ? \n" +
            "and p.date_of_birth = ?\n" +
            "and a.street_code = ? \n" +
            "and a.building = ? \n" +
            "and a.extension = ? \n" +
            "and a.apartment = ?";

    PersonResponse checkPerson(PersonRequest request) throws PersonCheckExeption
    {
        PersonResponse response = new PersonResponse();

        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_REQUEST))
        {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                response.setTemporal(rs.getBoolean("temporal"));
            }


        } catch (SQLException ex) {
            throw new PersonCheckExeption(ex);
        }


        return response;
    }

    private Connection getConnection() {
        return null;
    }
}
