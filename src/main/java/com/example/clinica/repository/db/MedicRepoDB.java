package com.example.clinica.repository.db;

import com.example.clinica.domain.Entity;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.IRepository;
import com.example.clinica.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicRepoDB implements IRepository<Integer, Medic> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Medic> findAll() {
        List<Medic> medici = new ArrayList<>();
        String query = "SELECT * FROM medici";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                int id = resultSet.getInt("medicid");
                int sectieID = resultSet.getInt("sectieid");
                String nume = resultSet.getString("nume");
                int vechime = resultSet.getInt("vechime");
                boolean rezident = resultSet.getBoolean("rezident");

                Medic medic = new Medic(id, sectieID, nume, vechime, rezident);
                medici.add(medic);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medici;
    }

    @Override
    public Medic save(Medic medic) {
        String query = "INSERT INTO medici(medicid, sectieid, nume, vechime, rezident) values (?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medic.getId());
            statement.setInt(2, medic.getSectieID());
            statement.setString(3, medic.getNume());
            statement.setInt(4, medic.getVechime());
            statement.setBoolean(5, medic.isRezident());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medic;
    }

    public List<Medic> getMediciSectie(int sectieID){
        List<Medic> medics = new ArrayList<>();
        String query = "SELECT * FROM medici WHERE sectieid = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sectieID);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("medicid");
                int sectID = resultSet.getInt("sectieid");
                String nume = resultSet.getString("nume");
                int vechime = resultSet.getInt("vechime");
                boolean rezident = resultSet.getBoolean("rezident");

                Medic medic = new Medic(id, sectID, nume, vechime, rezident);
                medics.add(medic);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medics;
    }
}
