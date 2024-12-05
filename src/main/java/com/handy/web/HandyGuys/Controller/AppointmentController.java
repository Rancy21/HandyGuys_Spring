package com.handy.web.HandyGuys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.Appointment;
import com.handy.web.HandyGuys.Models.Skill;
import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.AppointmentService;
import com.handy.web.HandyGuys.service.SkillService;
import com.handy.web.HandyGuys.service.UserService;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody Appointment appointment, 
            @RequestParam String userEmail, @RequestParam int skillId) {
                
        User user = userService.getUser(userEmail);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        // Skill skill = skillService.getS

        String saveAppointment = appointmentService.saveAppointment(appointment);
        if (saveAppointment.equalsIgnoreCase("student exists")) {
            return new ResponseEntity<>("student exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("student saved", HttpStatus.OK);
        }
    }
}
