package org.example.Controller;


import org.example.model.SpinResult;
import org.example.service.SlotMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/slot")
@CrossOrigin(origins = "http://localhost:5173")
public class SlotMachineController {

    private final SlotMachineService slotMachineService;

    public SlotMachineController(SlotMachineService slotMachineService) {
        this.slotMachineService = slotMachineService;
    }

    @PostMapping("/{playerId}/spin")
    public ResponseEntity<SpinResult> spin(@PathVariable Long playerId, @RequestParam BigDecimal bet) {
        return ResponseEntity.ok(slotMachineService.spin(playerId, bet));
    }
}
