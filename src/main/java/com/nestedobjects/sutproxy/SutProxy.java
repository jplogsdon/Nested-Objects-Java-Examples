package com.nestedobjects.sutproxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Import;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nestedobjects.dialog.Dialog;
import com.nestedobjects.pojos.Customer;
import com.nestedobjects.pojos.Employee;
import com.nestedobjects.pojos.IOfficeDao;
import com.nestedobjects.pojos.ITenureDao;
import com.nestedobjects.pojos.Office;
import com.nestedobjects.pojos.Payments;
import com.nestedobjects.pojos.TestOfficeDaoImpl;
import com.nestedobjects.pojos.TestTenureDaoImpl;
import com.nestedobjects.services.Service;
import com.nestedobjects.services.ServiceDialog;

/**
 * When you run the Springboot server, it will load this class and process this
 * class.
 * 
 */

@RequestMapping(method = RequestMethod.POST, 
			    consumes = "application/x-www-form-urlencoded", 
			    produces = "application/json")
@RestController
@Import({ Converters.class })
public class SutProxy extends SutProxyBase {

	/***************** Tests Begin Here ***********************************/
	/**
	 * 1. When returning any simple type, we need to set content-type to
	 * 'text/plain'.
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(path = "/getOfficeCity", produces = "text/plain")
	public String getOfficeCity(@RequestParam("employee") Employee employee) {
		return employee.getOffice().getCity();
	}

	/**
	 * Used for demo purposes
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(path = "/getOfficeCityDemo", produces = "text/plain")
	public String getOfficeCityDemo(@RequestParam("employee-demo") Employee employee) {
		return employee.getOffice().getCity();
	}

	/*
	 * 2. Return an inner object
	 */
	@RequestMapping(path = "/getOffice")
	public Office getOffice(@RequestParam("employee") Employee employee) {
		return employee.getOffice();
	}

	/*
	 * 3. Test query parameters, and multiple objects
	 */
	@RequestMapping(path = "/changeEmployeeOffice/{id}")
	public Employee changeEmployeeOffice(@RequestParam("employee") Employee employee,
										 @RequestParam("office") Office daoOfficeData, 
										 Integer officeCode, 
										 String newPhoneNumber) {
		Service serviceSUT = new Service();
		IOfficeDao officeDao = new TestOfficeDaoImpl();
		officeDao.setOffice(daoOfficeData);
		serviceSUT.setOfficeDao(officeDao);
		return serviceSUT.changeEmployeeOffice(employee, officeCode, newPhoneNumber);
	}

	/**
	 * 4. Tests an array
	 * 
	 * @param payments
	 * @return
	 */

	@RequestMapping(path = "/getSecondPayment")
	public Payments getSecondPayment(@RequestParam("paymentsArray") Payments[] payments) {
		return payments[1];
	}

	/**
	 * 5. Tests query parameters BigDecmial and Date
	 * 
	 * @param customer
	 * @param paymentDate
	 * @param paymentAmount
	 * @return
	 */
	@RequestMapping(path = "/changePaymentDate")
	public Payments changePayment(@RequestParam("customer") Customer customer,
								  @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate, 
								  BigDecimal paymentAmount) {
		Payments[] payments = customer.getCustomerPayments();
		payments[0].setPaymentDate(paymentDate);
		payments[0].setAmount(paymentAmount);
		return payments[0];
	}

	@RequestMapping(path = "/testSimpleArrays")
	public String[] testArrays(@RequestParam("stringArray") String[] stringArray,
							   @RequestParam("doubleArray") Double[] doubleArray, 
							   @RequestParam("bdArray") BigDecimal[] bdArray) {
		List<String> list = new ArrayList<String>();
		list.add(stringArray[0]);
		list.add(doubleArray[0].toString());
		list.add(bdArray[0].toString());
		return list.toArray(new String[0]);

	}

	/**
	 * This method illustrates how to use Dialogs. The Dialog holds user
	 * friendly log statements that are sent back to Nested Objects in a dialog
	 * header where they can be compared with expected results in the same way
	 * that the normal response.
	 * 
	 * The Dialog objects stores the conversation in a thread local object so it
	 * doesn't have to pass the dialog instance around and you can use the
	 * Dialog class to log and get the log results across all objects.
	 * 
	 * @see The Service class and the JUnit tests.
	 * @param employee
	 * @param office
	 * @param officeId
	 * @return
	 */

	@RequestMapping(path = "/changeOfficeDialog/{id}")
	public ResponseEntity<Employee> changeOffice(@RequestParam("employee") Employee employee, 
			Office office,
			Integer officeIdParam, 
			int jobTenure) {
		ServiceDialog service = new ServiceDialog();
		Dialog.init();
		IOfficeDao officeDao = new TestOfficeDaoImpl();

		officeDao.setOffice(office);
		service.setOfficeDao(officeDao);
		ITenureDao tenureDao = new TestTenureDaoImpl();
		tenureDao.setTenure(jobTenure);
		service.setTenureDao(tenureDao);

		Employee updatedEmployee = service.changeOffice(employee, officeIdParam);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("dialog", Dialog.get());
		return new ResponseEntity<Employee>(updatedEmployee, responseHeaders, HttpStatus.OK);
	}

	/**
	 * Used for demo purposes
	 * 
	 * @param employee
	 * @param office
	 * @param officeIdParam
	 * @param jobTenure
	 * @return
	 */
	@RequestMapping(path = "/changeOfficeDialogDemo/{id}")
	public ResponseEntity<Employee> changeOfficeDemo(@RequestParam("employeeDemo") Employee employee,
													 @RequestParam("officeDemo") Office office, 
													 Integer officeIdParam, 
													 Integer jobTenure) {
		ServiceDialog service = new ServiceDialog();
		Dialog.init();
		IOfficeDao officeDao = new TestOfficeDaoImpl();

		officeDao.setOffice(office);
		service.setOfficeDao(officeDao);
		ITenureDao tenureDao = new TestTenureDaoImpl();
		tenureDao.setTenure(jobTenure);
		service.setTenureDao(tenureDao);

		Employee updatedEmployee = service.changeOffice(employee, officeIdParam);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("dialog", Dialog.get());
		return new ResponseEntity<Employee>(updatedEmployee, responseHeaders, HttpStatus.OK);
	}
}