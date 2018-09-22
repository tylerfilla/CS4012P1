/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.dao;

import edu.umsl.tsfn88.cs4012.project1.conn.ConnectionFactory;
import edu.umsl.tsfn88.cs4012.project1.model.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionDAO implements DAO<Section> {

    @Override
    public List<Section> getAll() {
        List<Section> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM section");
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
                Section obj = new Section();
                obj.setSid(results.getLong("sid"));
                obj.setCourse(results.getLong("course"));
                obj.setNum(results.getLong("num"));
                objs.add(obj);
            }
        } catch (Exception e) {
            new RuntimeException("Could not retrieve query results", e).printStackTrace();
        }

        try {
            stmt.close();
        } catch (Exception e) {
            new RuntimeException("Could not close query statement", e).printStackTrace();
        }

        return objs;
    }

    @Override
    public void insert(Section obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO section (sid, course, num) VALUES (0, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            new RuntimeException("Could not prepare insert statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getCourse());
            stmt.setLong(2, obj.getNum());
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
        } catch (Exception e) {
            new RuntimeException("Could not execute insert", e).printStackTrace();
        }
    }

    @Override
    public void update(Section obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE section SET course = ?, num = ? WHERE sid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare update statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getCourse());
            stmt.setLong(2, obj.getNum());
            stmt.setLong(3, obj.getSid());
        } catch (Exception e) {
            new RuntimeException("Could not set update parameters", e).printStackTrace();
        }

        try {
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            new RuntimeException("Could not execute update", e).printStackTrace();
        }
    }

    @Override
    public void delete(Section obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM section WHERE sid = ?");
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
        } catch (Exception e) {
            new RuntimeException("Could not execute delete", e).printStackTrace();
        }
    }

    public Optional<Section> getBySid(long sid) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM section WHERE sid = ?");
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
                Section obj = new Section();
                obj.setSid(results.getLong("sid"));
                obj.setCourse(results.getLong("course"));
                obj.setNum(results.getLong("num"));
                return Optional.of(obj);
            }
        } catch (Exception e) {
            new RuntimeException("Could not retrieve query result", e).printStackTrace();
        }

        try {
            stmt.close();
        } catch (Exception e) {
            new RuntimeException("Could not close query statement", e).printStackTrace();
        }

        return Optional.empty();
    }

}
