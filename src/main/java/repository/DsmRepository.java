package repository;

import config.JdbcTemplate;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import domain.SqlReader;
import repository.dsm.SaveDsm;

public class DsmRepository {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void createTable(SqlReader sqlReader) {
        TableCreator tableCreator = new TableCreator();
        try (Connection conn = jdbcTemplate.getConnection()) {
            tableCreator.create(conn, sqlReader);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(File[] dsms) {
        SaveDsm saveDsm = new SaveDsm();
        try (Connection conn = jdbcTemplate.getConnection()) {
            saveDsm.save(conn, dsms);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}