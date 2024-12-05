package com.handy.web.HandyGuys.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.AppoinmentStatus;
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
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment, 
            @RequestParam String userEmail, @RequestParam String skillId) {
                
        //Fetch the user and check if it exists
        User user = userService.getUser(userEmail);
        if (user == null) {
            //If not, return not found status
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        //Fetch the skill and check if it exists
        Skill skill = skillService.getSkill(UUID.fromString(skillId));
        if (skill == null) {
            //If not, return not found status
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }
        //Create a boolean to validate wether there are empty fields or not
        boolean emptyFields = false;
        if(appointment.getDateOfAppointment() == null) 
            emptyFields = true;
        
        if(appointment.getPurpose().isEmpty())
            emptyFields = true;
        
        if(appointment.getClient_address().isEmpty())
            emptyFields = true;

        //If there are empty fields, return a bad request status
        if (emptyFields) 
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
        
        //Once everything has been validated add the user, the skill and the pending status to appointment
        appointment.setClient(user);
        appointment.setSkill(skill);
        AppoinmentStatus status = AppoinmentStatus.Pending;
        appointment.setStatus(status);
        //Save appointment
        String saveAppointment = appointmentService.saveAppointment(appointment);
        if (saveAppointment.equalsIgnoreCase("Appointment saved successfully")) {
            return new ResponseEntity<>("Appointment saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updateAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment, 
            @RequestParam String userEmail, @RequestParam String skillId) {
                
        //Fetch the user and check if it exists
        User user = userService.getUser(userEmail);
        if (user == null) {
            //If not, return not found status
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        //Fetch the skill and check if it exists
        Skill skill = skillService.getSkill(UUID.fromString(skillId));
        if (skill == null) {
            //If not, return not found status
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }
        //Create a boolean to validate wether there are empty fields or not
        boolean emptyFields = false;
        if(appointment.getDateOfAppointment() == null) 
            emptyFields = true;
        
        if(appointment.getPurpose().isEmpty())
            emptyFields = true;
        
        if(appointment.getClient_address().isEmpty())
            emptyFields = true;

        //If there are empty fields, return a bad request status
        if (emptyFields) 
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
        
        //Once everything has been validated add the user, the skill and the pending status to appointment
        appointment.setClient(user);
        appointment.setSkill(skill);
        //Update the appointment and return a OK status if the appointment is found.
        String saveAppointment = appointmentService.updateAppointment(appointment);
        if (saveAppointment.equalsIgnoreCase("Appointment updated successfully")) {
            return new ResponseEntity<>("Appointment Updated Succesfully", HttpStatus.OK);
        }
        else if(saveAppointment.equalsIgnoreCase("Appointment not found")) {
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }


}
