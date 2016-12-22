package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import javax.servlet.http.HttpSession;


/**
 * Created by noelaniekan on 12/21/16.
 */
@Controller
public class MicroblogController {
    @Autowired
    MessageRepository messages;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        List<Message> messageList = (List<Message>) messages.findAll();
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messageList);
        return "message";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String message(String text) {
        Message m = new Message(text);
        messages.save(m);
        return "redirect:/";

    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(int id) {
        messages.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String edit(int id, String message) {
        Message m = messages.findOne(id);
        m.text = message;
        messages.save(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
