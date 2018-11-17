package com.dvds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dvds.entities.DVD;
import com.dvds.jms.Emitor;

@Controller
public class DVDController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String sendMessages(@RequestParam("title") String title,
                               @RequestParam("year") Integer year,
                               @RequestParam("length") Integer length) {

        final DVD dvd = new DVD(title, year, length);

        Emitor e = new Emitor();
        try {
			e.sendDvd(dvd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return "index";
    }
}
