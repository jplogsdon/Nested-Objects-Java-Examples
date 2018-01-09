package com.nestedobjects.services;

import static com.nestedobjects.shared.Constants.LASTNAME_KEY;
import static com.nestedobjects.shared.Constants.NO_OFFICE_KEY;
import static com.nestedobjects.shared.Constants.OFFICE_CITY_KEY;

import java.util.Calendar;
import java.util.Date;

import com.nestedobjects.dialog.Dialog;
import com.nestedobjects.pojos.Employee;
import com.nestedobjects.pojos.IOfficeDao;
import com.nestedobjects.pojos.ITenureDao;
import com.nestedobjects.pojos.Office;
/**
 * This example service illustrates using a Dialog.
 * 
 * @author jplogsdon
 *
 */
public class ServiceDialog {
	private final String EMPLOYEE_TIME_REQUIREMENT_LOG = "Employee has not met one year employment requirement.";
	private final String EMPLOYEE_CANNOT_CHANGE_LOG = "Employee cannot change offices.";
	private final String EMPLOYEE_MOVED_LOG = "Employee has moved to office number %d.";
	private final String CHANGE_OFFICE_LOG = "Executing method change office.";
	private final String NO_OFFICE_LOG = "There is no office.";
	
	private final String VP = "VP";
	private final String PRESIDENT = "President";
	private final String ASSOCIATE = "Associate";

	private IOfficeDao officeDao;
	private ITenureDao tenureDao;

	public Employee changeOffice(Employee employee, Integer officeId) {
		Office office = this.officeDao.getOffice(officeId);
		Integer jobTenure = this.tenureDao.getTenure();

		Dialog.log(CHANGE_OFFICE_LOG);
		if (employee.getOffice() == null) {
			Dialog.log(NO_OFFICE_LOG);
			Dialog.log(OFFICE_CITY_KEY, NO_OFFICE_KEY);
			return employee;
		}
		String jobTitle = employee.getJobTitle();
		if (VP.equals(jobTitle) && (jobTenure < 1) || ASSOCIATE.equals(jobTitle) && (jobTenure < 2)) {
			Dialog.log(EMPLOYEE_TIME_REQUIREMENT_LOG);
		} else if (PRESIDENT.equals(jobTitle) || jobTenure > 2) {
			employee.setOffice(office);
			if (Dialog.isEnabled()) {
				Dialog.log(String.format(EMPLOYEE_MOVED_LOG, office.getOfficeCode()));
			}
		} else {
			Dialog.log(EMPLOYEE_CANNOT_CHANGE_LOG);
		}
		Dialog.log(LASTNAME_KEY, employee.getLastName());
		Dialog.log(OFFICE_CITY_KEY, employee.getOffice().getCity());
		return employee;
	}

	private boolean checkHireDate(Date hireDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		if (cal.after(hireDate)) {
			return false;
		}
		return true;
	}

	public IOfficeDao getOfficeDao() {
		return officeDao;
	}

	public void setOfficeDao(IOfficeDao officeDao) {
		this.officeDao = officeDao;
	}

	public void setTenureDao(ITenureDao tenureDao) {
		this.tenureDao = tenureDao;

	}

}
