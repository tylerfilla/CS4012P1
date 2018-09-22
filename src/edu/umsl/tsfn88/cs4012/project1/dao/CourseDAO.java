/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.dao;

import edu.umsl.tsfn88.cs4012.project1.conn.ConnectionFactory;
import edu.umsl.tsfn88.cs4012.project1.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDAO implements DAO<Course> {

    @Override
    public List<Course> getAll() {
        List<Course> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM course");
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
                Course obj = new Course();
                obj.setCid(results.getLong("cid"));
                obj.setNum(results.getInt("num"));
                obj.setName(results.getString("name"));
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
    public void insert(Course obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO course (cid, num, name) VALUES (0, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            new RuntimeException("Could not prepare insert statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getNum());
            stmt.setString(2, obj.getName());
        } catch (Exception e) {
            new RuntimeException("Could not set insert parameters", e).printStackTrace();
        }

        try {
            stmt.execute();

            // Update auto-generated key
            ResultSet keyResultSet = stmt.getGeneratedKeys();
            keyResultSet.next();
            obj.setCid(keyResultSet.getInt(1));

            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not execute insert", e).printStackTrace();
        }
    }

    @Override
    public void update(Course obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE course SET num = ?, name = ? WHERE cid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare update statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getNum());
            stmt.setString(2, obj.getName());
            stmt.setLong(3, obj.getCid());
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
    public void delete(Course obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM course WHERE cid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare delete statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getCid());
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

    public Optional<Course> getByCid(long cid) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM course WHERE cid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, cid);
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
                Course obj = new Course();
                obj.setCid(results.getLong("cid"));
                obj.setNum(results.getLong("num"));
                obj.setName(results.getString("name"));
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

}
