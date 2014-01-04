package org.esupportail.helpdesk.data.web.controllers;

import org.esupportail.helpdesk.data.web.beans.Welcome;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Welcome listCommands() {
        Welcome welcome = Welcome.welcome();
        welcome.add(linkTo(WelcomeController.class).withSelfRel());
        welcome.add(linkTo(UserController.class).withRel("users"));
        return welcome;
    }
}
