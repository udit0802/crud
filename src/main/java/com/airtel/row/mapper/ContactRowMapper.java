package com.airtel.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.airtel.model.Contact;

public class ContactRowMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getInt("contact_id"));
		contact.setName(rs.getString("name"));
		contact.setEmail(rs.getString("email"));
		contact.setAddress(rs.getString("address"));
		contact.setTelephone(rs.getString("telephone"));
		contact.setSport(rs.getString("fav_sport"));
		return contact;
	}

}
