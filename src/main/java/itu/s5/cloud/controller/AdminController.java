package itu.s5.cloud.controller;

import itu.s5.cloud.TpWsApplication;
import itu.s5.cloud.connection.ConnectDB;
import itu.s5.cloud.exception.LoginException;
import itu.s5.cloud.model.Admin;
import itu.s5.cloud.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/api/tpws/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login/{login}/{mdp}")
    public boolean login(@PathVariable String login, @PathVariable String mdp) throws SQLException {
        ConnectDB postgreSQL = TpWsApplication.getPostgreSQL();
        Connection conn = null;
        PreparedStatement stmt=null;
        try {
            conn = postgreSQL.getConnection();
            String sql = "select * from admin";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                if(login.compareTo(resultSet.getString(2))==0 && mdp.compareTo(resultSet.getString(3))==0) return true;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (stmt!=null) stmt.close();
        }
        return false;
    }
}
