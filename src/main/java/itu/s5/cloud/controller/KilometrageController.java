package itu.s5.cloud.controller;

import itu.s5.cloud.exception.ResourceNotFoundException;
import itu.s5.cloud.model.Kilometrage;
import itu.s5.cloud.repository.KilometrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tpws/kilometrage")
public class KilometrageController {
    @Autowired
    private KilometrageRepository kilometrageRepository;

    @GetMapping
    public List<Kilometrage> getAllKilometrages() {
        return kilometrageRepository.findAll();
    }

    @PostMapping
    public Kilometrage createKilometrage(@RequestBody Kilometrage kilometrage) {
        return kilometrageRepository.save(kilometrage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kilometrage> getKilometrageById(@PathVariable long id) {
        Kilometrage kilometrage = kilometrageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kilometrage not exist with id: "+id));
        return ResponseEntity.ok(kilometrage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kilometrage> updateKilometrage(@PathVariable long id, @RequestBody Kilometrage kilometrageDetails) {
        Kilometrage updateKilometrage = kilometrageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kilometrage not exit with id: "+id));
        updateKilometrage.setIdavion(kilometrageDetails.getIdavion());
        updateKilometrage.setDaty(kilometrageDetails.getDaty());
        updateKilometrage.setKilometre_debut(kilometrageDetails.getKilometre_debut());
        updateKilometrage.setKilometre_fin(kilometrageDetails.getKilometre_fin());
        kilometrageRepository.save(updateKilometrage);
        return ResponseEntity.ok(updateKilometrage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteKilometrage(@PathVariable long id) {
        Kilometrage kilometrage = kilometrageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kilometrage not exist with id: "+id));
        kilometrageRepository.delete(kilometrage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getExpire/{id}")
    public String[] getAllKilometrages(@PathVariable long id) {
        System.out.println(id+" ID");
        String[] strs = new String[]{"1", "2"};
        return strs;
    }
}
