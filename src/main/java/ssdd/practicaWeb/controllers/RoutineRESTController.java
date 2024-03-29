package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.practicaWeb.entities.Routine;
import ssdd.practicaWeb.service.RoutineService;

import java.util.Collection;

@RestController
@RequestMapping("/api/routines")
public class RoutineRESTController {
    @Autowired
    private RoutineService routineService;
    @GetMapping
    public ResponseEntity<Collection<Routine>> getAllRoutines(){
        return ResponseEntity.ok(routineService.getAllRoutines());
    }
    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine){
        return ResponseEntity.status(201).body(routineService.createRoutine(routine));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutine(@PathVariable Long id){
        Routine routine = routineService.getRoutine(id);
        if(routine == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(routine);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Routine> updateRoutine(@PathVariable Long id, @RequestBody Routine routine){
        Routine updated = routineService.updateRoutine(id,routine);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id){
        routineService.deleteRoutine(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Routine> patchRoutine(@PathVariable Long id, @RequestBody Routine parcialRoutine){
        Routine routine = routineService.getRoutine(id);
        if (routine == null){
            return ResponseEntity.notFound().build();
        }
        if (parcialRoutine.getRoutineName() != null) {
            routine.setRoutineName(parcialRoutine.getRoutineName());
        }
        if (parcialRoutine.getIntensity() != null) {
            routine.setIntensity(parcialRoutine.getIntensity());
        }
        if (parcialRoutine.getDuration() != 0) {
            routine.setDuration(parcialRoutine.getDuration());
        }
        if(parcialRoutine.getExercises() != null) {
            routine.setExercises(parcialRoutine.getExercises());
        }
        if(parcialRoutine.getGoal() != null) {
            routine.setGoal(parcialRoutine.getGoal());
        }
        routineService.updateRoutine(id,routine);
        return ResponseEntity.ok(routine);
    }
}
