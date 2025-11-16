/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.registration;

import cuongnh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable{
//    public boolean checkLogin(String username, String password)
//        throws SQLException, ClassNotFoundException {
    public RegistrationDTO checkLogin(String username, String password)
        throws SQLException, ClassNotFoundException {
        //boolean result = false;
        RegistrationDTO result = null;
        //khai bao bien va gan null
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Model connects DB
            con = DBHelper.getConnection();
            if (con != null) {

                //2. Model queries data from DB
                //2.1 Create sql String
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //2.2 Load sql into Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 Execute Query (R --> ResultSet,
                // ResultSet is a pointer that point List Data --> First Row: BOF, Last Row: EOF)
                // use next() method (forward only)
                rs = stm.executeQuery();
                if (rs.next()) {
                    //3. Model loads data from ResultSet to Model
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //4. Model process and return result
                    result = new RegistrationDTO(username, null, fullName, role);
                } // end username and password are existed
            } //end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) { //close object any way
                con.close();
            }
        }
        
        return result;
    }
    
    List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue) 
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Model connects DB
            con = DBHelper.getConnection();
            if (con != null) {

                //2. Model queries data from DB
                //2.1 Create sql String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //2.2 Load sql into Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    //2.3 Execute Query (R --> ResultSet,
                    // ResultSet is a pointer that point List Data --> First Row: BOF, Last Row: EOF)
                    // use next() method (forward only)
                    //2.3.1 Model load data from DB
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    //2.3.2 Model store data from db itself
                    if (this.accounts == null) {
                        accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                    //3. Model loads data from DB to Model
                     
                    //4. Model process and return result
                    
                } // end username and password are existed
            } //end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) { //close object any way
                con.close();
            }
        }
        
    }
    
    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException {
        boolean result = false;
        //khai bao bien va gan null
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Model connects DB
            con = DBHelper.getConnection();
            if (con != null) {

                //2. Model queries data from DB
                //2.1 Create sql String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //2.2 Load sql into Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //2.3 Execute Query (R --> ResultSet,
                // ResultSet is a pointer that point List Data --> First Row: BOF, Last Row: EOF)
                // use next() method (forward only)
                int effectedRows = stm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                } //end username are existed                              
            } //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) { //close object any way
                con.close();
            }
        }
        
        return result;       
    }
    
    public boolean updateAccount(String username, String password, boolean role) 
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        //khai bao bien va gan null
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Model connects DB
            con = DBHelper.getConnection();
            if (con != null) {

                //2. Model queries data from DB
                //2.1 Create sql String
                String sql = "Update Registration "
                        + "Set password = ?, "
                        + "isAdmin = ? "
                        + "Where username = ?";
                //2.2 Load sql into Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //2.3 Execute Query (R --> ResultSet,
                // ResultSet is a pointer that point List Data --> First Row: BOF, Last Row: EOF)
                // use next() method (forward only)
                int effectedRows = stm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                } //end username are existed                              
            } //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) { //close object any way
                con.close();
            }
        }
        
        return result;       
    }
    
    public boolean createAccount(RegistrationDTO account) 
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        //khai bao bien va gan null
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Model connects DB
            con = DBHelper.getConnection();
            if (con != null) {

                //2. Model queries data from DB
                //2.1 Create sql String
                String sql = "Insert into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //2.2 Load sql into Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullname());
                stm.setBoolean(4, account.isRole());
                
                //2.3 Execute Query (R --> ResultSet,
                // ResultSet is a pointer that point List Data --> First Row: BOF, Last Row: EOF)
                // use next() method (forward only)
                int effectedRows = stm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                } //end username are existed                              
            } //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) { //close object any way
                con.close();
            }
        }
        
        return result; 
    }
}
