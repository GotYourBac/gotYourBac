package com.GotYourBac.gotYourBac.controllers;


import com.GotYourBac.gotYourBac.models.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class HappyHourController {

    @MessageMapping("/happyhour")
    @SendTo("/topic/happyhour")
    public HappyHour happyHour(Message message) throws Exception{
        Thread.sleep(1000);
        return new HappyHour(message.getName() + " has RSVP'd to OHANA!");
    }
}

