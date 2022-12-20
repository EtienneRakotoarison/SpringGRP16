package itu.s5.cloud.controller;

import itu.s5.cloud.TpWsApplication;
import itu.s5.cloud.connection.ConnectDB;
import itu.s5.cloud.exception.ResourceNotFoundException;
import itu.s5.cloud.model.Avion;
import itu.s5.cloud.model.DetailsAvion;
import itu.s5.cloud.model.ExpiredAssurance;
import itu.s5.cloud.repository.AvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tpws/avion")
public class AvionController {
    @Autowired
    private AvionRepository avionRepository;

    @GetMapping
    public List<Avion> getAllVehicules() {
        return avionRepository.findAll();
    }

    @PostMapping
    public Avion createAvion(@RequestBody Avion vehicule) {
        return avionRepository.save(vehicule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avion> getAvionById(@PathVariable long id) {
        Avion vehicule = avionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicule not exist with id: "+id));
        return ResponseEntity.ok(vehicule);
    }

    @GetMapping("/list")
    public ArrayList<Avion> getAvions() throws SQLException {
        ArrayList<Avion> avions = new ArrayList<>();
        ConnectDB postgreSQL = TpWsApplication.getPostgreSQL();
        Connection conn = null;
        PreparedStatement stmt=null;
        try {
            conn = postgreSQL.getConnection();
            String sql = "select idavion, nom, photo from avion";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                Avion avion = new Avion(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                avions.add(avion);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (stmt!=null) stmt.close();
        }
        return avions;
    }

    @GetMapping("/details/{id}")
    public ArrayList<Object> getDetails(@PathVariable long id) throws SQLException {
        ArrayList<Object> details = new ArrayList<>();
        ConnectDB postgreSQL = TpWsApplication.getPostgreSQL();
        Connection conn = null;
        PreparedStatement stmt=null;
        try {
            conn = postgreSQL.getConnection();
            String sql = "select * from v_details_avion where idavion="+id;
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                DetailsAvion avion = new DetailsAvion(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6));
                details.add(avion);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (stmt!=null) stmt.close();
        }
        return details;
    }

    @GetMapping("/expired/{month}")
    public ArrayList<Object> getExpiredInMonth(@PathVariable long month) throws SQLException {
        ArrayList<Object> expired = new ArrayList<>();
        ConnectDB postgreSQL = TpWsApplication.getPostgreSQL();
        Connection conn = null;
        PreparedStatement stmt=null;
        try {
            conn = postgreSQL.getConnection();
            String sql = "select * from date_expiration where days<=("+month+"*30)";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                ExpiredAssurance avion = new ExpiredAssurance(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6));
                expired.add(avion);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (stmt!=null) stmt.close();
        }
        return expired;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avion> updateAvion(@PathVariable long id, @RequestBody Avion vehiculeDetails) {
        Avion updateAvion = avionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Avion not exit with id: "+id));
        updateAvion.setNom(vehiculeDetails.getNom());
        updateAvion.setKilometrage_initial(vehiculeDetails.getKilometrage_initial());
        updateAvion.setCapacite(vehiculeDetails.getCapacite());
        updateAvion.setPhoto(vehiculeDetails.getPhoto());
        avionRepository.save(updateAvion);
        return ResponseEntity.ok(updateAvion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteVehicule(@PathVariable long id) {
        Avion kilometrage = avionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicule not exist with id: "+id));
        avionRepository.delete(kilometrage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
