package com.example.clinica.repository.db;

import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.IRepository;
import com.example.clinica.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectieRepoDB implements IRepository<Integer, Sectie> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<Sectie> findAll() {
        List<Sectie> sectii = new ArrayList<>();
        String query = "SELECT * FROM sectii";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                int id = resultSet.getInt("sectieid");
                String nume = resultSet.getString("nume");
                int sefSectie = resultSet.getInt("sefsectie");
                int pret = resultSet.getInt("pret");
                int durataCons = resultSet.getInt("durataconsultatie");

                Sectie sectie = new Sectie(id, nume, sefSectie, pret, durataCons);
                sectii.add(sectie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sectii;
    }

    @Override
    public Sectie save(Sectie sectie) {
        String query = "INSERT INTO sectii(sectieid, nume, sefsectie, pret, durataconsultatie) values (?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sectie.getId());
            statement.setString(2, sectie.getNume());
            statement.setInt(3, sectie.getSefSectieID());
            statement.setInt(4, sectie.getPret());
            statement.setInt(5, sectie.getDurataConsultatie());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sectie;
    }
}
