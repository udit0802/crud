package com.airtel.controllers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.airtel.dao.ContactDao;
import com.airtel.model.Contact;

@Controller
@RequestMapping("/form")
public class FormController {
	
	private static Logger log = LoggerFactory.getLogger(FormController.class);
	
	@Autowired
    private ContactDao contactDAO;

	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException{
	    List<Contact> listContact = contactDAO.list();
	    listContact.forEach(new Consumer<Contact>() {

			@Override
			public void accept(Contact t) {
				log.info("contact = {}",t.toString());
				
			}
	
	    });
	    model.addObject("listContact", listContact);
	    model.setViewName("home");
	 
	    return model;
	}
	
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
	    Contact newContact = new Contact();
	    model.addObject("contact", newContact);
	    model.setViewName("ContactForm");
	    return model;
	}
	
	@RequestMapping(value = "/saveOrUpdateContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
	    contactDAO.saveorUpdate(contact);
	    log.info("contact saved");
	    return new ModelAndView("redirect:/form/");
	}
	
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam(value = "id") int id){
		contactDAO.delete(id);
		return new ModelAndView("redirect:/form/");
	}
	
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(@RequestParam(value = "id") int id) {
//	    int contactId = Integer.parseInt(id);
	    Contact contact = contactDAO.get(id);
	    ModelAndView model = new ModelAndView("ContactForm");
	    model.addObject("contact", contact);
	 
	    return model;
	}
}
