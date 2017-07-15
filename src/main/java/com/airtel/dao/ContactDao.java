package com.airtel.dao;

import java.util.List;

import com.airtel.model.Contact;

public interface ContactDao {

	void saveorUpdate(Contact contact);

	void delete(int contactId);

	List<Contact> list();

	Contact get(int contactId);

}