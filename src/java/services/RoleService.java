package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Esau, Connor
 * October 17, 2019
 */
public class RoleService {
        /**
     * This method calls the getAll() method from RoleDB
     * @return allRoles - a list of roles from the database.
     * @throws Exception - all exceptions that could be had.
     */
    public List<Role> getAll() throws Exception {
        RoleDB db = new RoleDB();
        ArrayList<Role> allRoles = (ArrayList<Role>) db.getAll();
        return allRoles;
    }
    
    /**
     * @Author Connor Esau
     * @param roleID the roleID of the role to be inserted
     * @param roleName role name
     * @return the number of rows inserted
     * @throws Exception - all exceptions that could be had.
     */
    public int insert(int roleID, String roleName) throws Exception {
        RoleDB db = new RoleDB();
        Role role = new Role(roleID, roleName);
        int i = db.insert(role);
        return i;
    }
    
    /**
     * @Author Connor Esau
     * @param roleID roleID to delete
     * @return the int from UserDb
     * @throws Exception - all exceptions that could be had.
     */
    public int delete(int roleID) throws Exception {
        RoleDB db = new RoleDB();
        Role role = getRole(roleID);
        int i = db.delete(role);
        return i;
    }
    
    /**
     * @Author Connor Esau
     * @param roleID roleID to delete
     * @param roleName role name to delete
     * @return the int from UserDb
     * @throws Exception - all exceptions that could be had.
     */
    public int update(int roleID, String roleName) throws Exception {
        RoleDB db = new RoleDB();
        Role role = new Role(roleID, roleName);
        int i = db.update(role);
        return i;
    }
    
    public Role getRole(int roleID) throws SQLException {
        RoleDB db = new RoleDB();
        Role role = db.getRole(roleID);
        return role;
    }
     
}
