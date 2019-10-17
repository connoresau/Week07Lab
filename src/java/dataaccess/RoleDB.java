/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author awarsyle
 */
public class RoleDB {
    public Role getRole(int roleID) throws SQLException {

        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            Role role = null;
            String preparedQuery = "SELECT RoleID, RoleName FROM role_table WHERE RoleID=?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String roleName = rs.getString(2);
                role = new Role(roleID, roleName);
            }

            return role;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
    /**
     * This method queries the database for all roles. Every role is put into an
     * ArrayList of roles
     *
     * @return ArrayList roles - the list of roles retrieved from the database.
     * @throws SQLException
     */
    public List<Role> getAll() throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            
            ArrayList<Role> roles = new ArrayList<>();
            String preparedQuery = "SELECT RoleID, RoleName FROM role_table";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ResultSet results = ps.executeQuery();
            
            while(results.next()) {
                Role role = new Role(results.getInt(1), results.getString(2));
                roles.add(role);
            }
            
            ps.close();
            return roles;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
   /**
     * This method inserts a role element and returns the number of rows affected.
     * @author Esau, Connor
     * @param role role
     * @return rows rows
     * @throws java.sql.SQLException
     */
    public int insert(Role role) throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        
        int rowCount;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            
            String preparedQuery = "INSERT INTO Role_Table VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, role.getRoleID());
            ps.setString(2, role.getRoleName());
            
            rowCount = ps.executeUpdate();
            ps.close();
            
            return rowCount;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
    /**
     * This method deletes a role element and returns the number of rows affected.
     * @author Esau, Connor
     * @param role role
     * @return rows rows
     * @throws java.sql.SQLException
     */
    public int delete(Role role) throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        
        int rowCount;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            
            String preparedQuery = "DELETE FROM role_table where roleId=?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, role.getRoleID());
            
            rowCount = ps.executeUpdate();
            ps.close();
            
            return rowCount;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
    /**
     * This method alters a role element and returns the number of rows affected.
     * @author Esau, Connor
     * @param role role
     * @return rows rows
     * @throws java.sql.SQLException
     */
    public int update(Role role) throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        
        int rowCount;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            
            String preparedQuery = "UPDATE Role_Table SET RoleName = ? WHERE RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, role.getRoleName());
            ps.setInt(2, role.getRoleID());
            
            rowCount = ps.executeUpdate();
            ps.close();
            
            return rowCount;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
}
