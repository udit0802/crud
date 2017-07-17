package com.airtel.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.airtel.model.Contact;
import com.airtel.row.mapper.ContactRowMapper;

public class ContactDaoImpl implements ContactDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.airtel.dao.ContactDao#saveOrUpdate(com.airtel.model.Contact)
	 */
	@Override
    public void saveorUpdate(Contact contact) {
		if (contact.getId() > 0) {
	        // update
	        String sql = "UPDATE contact SET name=?, email=?, address=?, "
	                    + "telephone=?, fav_sport=? WHERE contact_id=?";
	        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
	                contact.getAddress(), contact.getTelephone(), contact.getSport(), contact.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO contact (name, email, address, telephone,fav_sport)"
	                    + " VALUES (?, ?, ?, ?, ?)";
	        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
	                contact.getAddress(), contact.getTelephone(),contact.getSport());
	    }
    }
 
    /* (non-Javadoc)
	 * @see com.airtel.dao.ContactDao#delete(int)
	 */
    @Override
    public void delete(int contactId) {
    	String sql = "DELETE FROM contact WHERE contact_id=?";
        jdbcTemplate.update(sql, contactId);
    }
 
    /* (non-Javadoc)
	 * @see com.airtel.dao.ContactDao#list()
	 */
    @Override
    public List<Contact> list() {
    	String query = "SELECT contact_id,name,email,address,telephone,fav_sport FROM contact";
        List<Contact> contacts = jdbcTemplate.query(query, new ContactRowMapper());
        return contacts;
    }
 
    /* (non-Javadoc)
	 * @see com.airtel.dao.ContactDao#get(int)
	 */
    @Override
    public Contact get(int contactId) {
    	String query = "SELECT contact_id,name,email,address,telephone,fav_sport FROM contact WHERE contact_id=?";
    	Contact contact = jdbcTemplate.queryForObject(query, new Object[]{contactId}, new ContactRowMapper());
    	return contact;
    }
}
