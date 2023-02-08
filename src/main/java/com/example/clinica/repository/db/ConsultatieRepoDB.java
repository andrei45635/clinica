package com.example.clinica.repository.db;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.repository.IRepository;
import com.example.clinica.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultatieRepoDB implements IRepository<Integer, Consultatie> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<Consultatie> findAll() {
        List<Consultatie> consultatii = new ArrayList<>();
        String query = "SELECT * FROM consultatii";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int medicID = resultSet.getInt("medicid");
                int cnp = resultSet.getInt("cnp");
                String nume = resultSet.getString("nume");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                Time ora = resultSet.getTime("ora");

                Consultatie consultatie = new Consultatie(id, medicID, cnp, nume, data, ora);
                consultatii.add(consultatie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultatii;
    }

    @Override
    public Consultatie save(Consultatie consultatie) {
        String query = "INSERT INTO consultatii(id, medicid, cnp, nume, data, ora) values (?, ?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consultatie.getId());
            statement.setInt(2, consultatie.getMedicID());
            statement.setInt(3, consultatie.getCNP());
            statement.setString(4, consultatie.getNume());
            statement.setDate(5, Date.valueOf(consultatie.getData()));
            statement.setTime(6, consultatie.getOra());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultatie;
    }
}
