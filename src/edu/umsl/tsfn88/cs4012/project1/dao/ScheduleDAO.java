/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.dao;

import edu.umsl.tsfn88.cs4012.project1.conn.ConnectionFactory;
import edu.umsl.tsfn88.cs4012.project1.model.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDAO implements DAO<Schedule> {

    @Override
    public List<Schedule> getAll() {
        List<Schedule> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM schedule");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        ResultSet results = null;
        try {
            stmt.execute();
            results = stmt.getResultSet();
        } catch (Exception e) {
            new RuntimeException("Could not execute query for results", e).printStackTrace();
        }

        try {
            while (results.next()) {
                Schedule obj = new Schedule();
                obj.setSid(results.getLong("sid"));
                obj.setStudent(results.getLong("student"));
                obj.setSection(results.getLong("section"));
                objs.add(obj);
            }
        } catch (Exception e) {
            new RuntimeException("Could not retrieve query results", e).printStackTrace();
        }

        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not close query statement", e).printStackTrace();
        }

        return objs;
    }

    @Override
    public void insert(Schedule obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO schedule (sid, student, section) VALUES (0, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            new RuntimeException("Could not prepare insert statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getStudent());
            stmt.setLong(2, obj.getSection());
        } catch (Exception e) {
            new RuntimeException("Could not set insert parameters", e).printStackTrace();
        }

        try {
            stmt.execute();

            // Update auto-generated key
            ResultSet keyResultSet = stmt.getGeneratedKeys();
            keyResultSet.next();
            obj.setSid(keyResultSet.getInt(1));

            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not execute insert", e).printStackTrace();
        }
    }

    @Override
    public void update(Schedule obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE schedule SET student = ?, section = ? WHERE sid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare update statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getStudent());
            stmt.setLong(2, obj.getSection());
            stmt.setLong(3, obj.getSid());
        } catch (Exception e) {
            new RuntimeException("Could not set update parameters", e).printStackTrace();
        }

        try {
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not execute update", e).printStackTrace();
        }
    }

    @Override
    public void delete(Schedule obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM schedule WHERE sid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare delete statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getSid());
        } catch (Exception e) {
            new RuntimeException("Could not set delete parameters", e).printStackTrace();
        }

        try {
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not execute delete", e).printStackTrace();
        }
    }

    public Optional<Schedule> getBySid(long sid) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM schedule WHERE sid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, sid);
        } catch (Exception e) {
            new RuntimeException("Could not set query parameters", e).printStackTrace();
        }

        ResultSet results = null;
        try {
            stmt.execute();
            results = stmt.getResultSet();
        } catch (Exception e) {
            new RuntimeException("Could not execute query for results", e).printStackTrace();
        }

        try {
            if (results.next()) {
                Schedule obj = new Schedule();
                obj.setSid(results.getLong("sid"));
                obj.setStudent(results.getLong("student"));
                obj.setSection(results.getLong("section"));
                return Optional.of(obj);
            }
        } catch (Exception e) {
            new RuntimeException("Could not retrieve query result", e).printStackTrace();
        }

        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not close query statement", e).printStackTrace();
        }

        return Optional.empty();
    }

    public List<Schedule> getAllByStudent(long student) {
        List<Schedule> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM schedule WHERE student = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, student);
        } catch (Exception e) {
            new RuntimeException("Could not set query parameters", e).printStackTrace();
        }

        ResultSet results = null;
        try {
            stmt.execute();
            results = stmt.getResultSet();
        } catch (Exception e) {
            new RuntimeException("Could not execute query for results", e).printStackTrace();
        }

        try {
            while (results.next()) {
                Schedule obj = new Schedule();
                obj.setSid(results.getLong("sid"));
                obj.setStudent(results.getLong("student"));
                obj.setSection(results.getLong("section"));
                objs.add(obj);
            }
        } catch (Exception e) {
            new RuntimeException("Could not retrieve query results", e).printStackTrace();
        }

        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not close query statement", e).printStackTrace();
        }

        return objs;
    }

}
