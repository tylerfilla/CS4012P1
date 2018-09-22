/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.dao;

import edu.umsl.tsfn88.cs4012.project1.conn.ConnectionFactory;
import edu.umsl.tsfn88.cs4012.project1.model.Meeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MeetingDAO implements DAO<Meeting> {

    @Override
    public List<Meeting> getAll() {
        List<Meeting> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM meeting");
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
                Meeting obj = new Meeting();
                obj.setMid(results.getLong("mid"));
                obj.setSection(results.getLong("section"));
                obj.setDay(results.getInt("day"));
                obj.setStart(results.getTime("start"));
                obj.setStop(results.getTime("stop"));
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
    public void insert(Meeting obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO meeting (mid, section, day, start, stop) VALUES (0, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            new RuntimeException("Could not prepare insert statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getSection());
            stmt.setInt(2, obj.getDay());
            stmt.setTime(3, obj.getStart());
            stmt.setTime(4, obj.getStop());
        } catch (Exception e) {
            new RuntimeException("Could not set insert parameters", e).printStackTrace();
        }

        try {
            stmt.execute();

            // Update auto-generated key
            ResultSet keyResultSet = stmt.getGeneratedKeys();
            keyResultSet.next();
            obj.setMid(keyResultSet.getInt(1));

            stmt.close();
            conn.close();
        } catch (Exception e) {
            new RuntimeException("Could not execute insert", e).printStackTrace();
        }
    }

    @Override
    public void update(Meeting obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE meeting SET section = ?, day = ?, start = ?, stop = ? WHERE mid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare update statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getSection());
            stmt.setInt(2, obj.getDay());
            stmt.setTime(3, obj.getStart());
            stmt.setTime(4, obj.getStop());
            stmt.setLong(5, obj.getMid());
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
    public void delete(Meeting obj) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM meeting WHERE mid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare delete statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, obj.getMid());
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

    public Optional<Meeting> getByMid(long mid) {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM meeting WHERE mid = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, mid);
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
                Meeting obj = new Meeting();
                obj.setMid(results.getLong("mid"));
                obj.setSection(results.getLong("section"));
                obj.setDay(results.getInt("day"));
                obj.setStart(results.getTime("start"));
                obj.setStop(results.getTime("stop"));
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

    public List<Meeting> getAllBySection(long section) {
        List<Meeting> objs = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM meeting WHERE section = ?");
        } catch (Exception e) {
            new RuntimeException("Could not prepare query statement", e).printStackTrace();
        }

        try {
            stmt.setLong(1, section);
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
                Meeting obj = new Meeting();
                obj.setMid(results.getLong("mid"));
                obj.setSection(results.getLong("section"));
                obj.setDay(results.getInt("day"));
                obj.setStart(results.getTime("start"));
                obj.setStop(results.getTime("stop"));
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
