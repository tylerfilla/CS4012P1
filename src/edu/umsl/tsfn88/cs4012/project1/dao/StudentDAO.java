/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.dao;

import edu.umsl.tsfn88.cs4012.project1.conn.ConnectionFactory;
import edu.umsl.tsfn88.cs4012.project1.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO implements DAO<Student> {

    @Override
    public List<Student> getAll() {
        List<Student> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM student");
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
                Student obj = new Student();
                obj.setSid(results.getLong("sid"));
                obj.setNum(results.getLong("num"));
                obj.setFname(results.getString("fname"));
                obj.setLname(results.getString("lname"));
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
    public void insert(Student obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO student (sid, num, fname, lname) VALUES (0, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            new RuntimeException("Could not prepare insert statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getNum());
            stmt.setString(2, obj.getFname());
            stmt.setString(3, obj.getLname());
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
    public void update(Student obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE student SET num = ?, fname = ?, lname = ? WHERE sid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare update statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getNum());
            stmt.setString(2, obj.getFname());
            stmt.setString(3, obj.getLname());
            stmt.setLong(4, obj.getSid());
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
    public void delete(Student obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM student WHERE sid = ?");
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

    public Optional<Student> getBySid(long sid) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM student WHERE sid = ?");
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
                Student obj = new Student();
                obj.setSid(results.getLong("sid"));
                obj.setNum(results.getLong("num"));
                obj.setFname(results.getString("fname"));
                obj.setLname(results.getString("lname"));
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

    public Optional<Student> getByNum(long num) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM student WHERE num = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, num);
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
                Student obj = new Student();
                obj.setSid(results.getLong("sid"));
                obj.setNum(results.getLong("num"));
                obj.setFname(results.getString("fname"));
                obj.setLname(results.getString("lname"));
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
