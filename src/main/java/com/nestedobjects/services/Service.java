package com.nestedobjects.services;

import java.util.Calendar;
import java.util.Date;

import com.nestedobjects.dialog.Dialog;
import com.nestedobjects.pojos.Employee;
import com.nestedobjects.pojos.IOfficeDao;
import com.nestedobjects.pojos.Office;

/**
 * This class represents the SUT.
 * 
 * @author jplogsdon
 *
 */
public class Service {
	private IOfficeDao officeDao;

 
	public Employee changeEmployeeOffice(Employee employee, Integer officeCode, String newPhoneNumber){
    	Office office = this.officeDao.getOffice(officeCode);
    	office.setOfficeCode(officeCode);
    	office.setPhone(newPhoneNumber);
    	this.officeDao.save(office);
    	employee.setOffice(office);
    	return employee;
    }

	public IOfficeDao getOfficeDao() {
		return officeDao;
	}

	public void setOfficeDao(IOfficeDao officeDao) {
		this.officeDao = officeDao;
	}
}
