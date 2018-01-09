package com.nestedobjects.ci;

import static com.nestedobjects.ci.Constants.CHANGE_OFFICE_DIALOG_URL;
import static com.nestedobjects.shared.Constants.OFFICE_CITY_KEY;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.nestedobjects.dialog.Dialog;
import com.nestedobjects.pojos.Employee;
import com.nestedobjects.pojos.IOfficeDao;
import com.nestedobjects.pojos.ITenureDao;
import com.nestedobjects.pojos.Office;
import com.nestedobjects.pojos.OfficeExpectedResults;
import com.nestedobjects.pojos.OfficeIdParams;
import com.nestedobjects.pojos.Tenure;
import com.nestedobjects.pojos.TestOfficeDaoImpl;
import com.nestedobjects.pojos.TestTenureDaoImpl;
import com.nestedobjects.services.ServiceDialog;
import com.nestedobjects.shared.ObjectMapperConfig;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * This is a data driven JUnit test using Spring configuration to autowire an
 * ObjectMapper. You customize the ObjectMapper in the ObjectMapperConfig class
 * (it is shared with the SutProxy code). Then the JsonProvider will use it to
 * convert the incoming JSON to objects and execute a single test method however
 * many times there is data in the test suite(s).
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = { ObjectMapperConfig.class, JsonProvider.class })
public class ChangeOfficeDialogTests extends JUnitBase {

	@Autowired
	JsonProvider jsonProvider;

	@Rule
	public Timeout globalTimeout = Timeout.millis(100);
	private static long totalTimeInMillisecond = 0;
    private static final long MAX_EXECUTION_TIME = 1000;
    
	@AfterClass
	public static void checkTotalTime() throws Exception {
		if (totalTimeInMillisecond > MAX_EXECUTION_TIME) {
			assertTrue(String.format("Tests exceeded total time %d. Actual time: %d", MAX_EXECUTION_TIME, totalTimeInMillisecond), MAX_EXECUTION_TIME < totalTimeInMillisecond);
		}
	}

	@Test
	@Parameters
	public void changeOfficeDialogTest(Employee employee, 
			Office office, 
			OfficeIdParams officeParams, 
			Tenure jobTenure,
			OfficeExpectedResults expectedResults) throws Exception {

		ServiceDialog service = new ServiceDialog();
		Dialog.init();
		IOfficeDao officeDao = new TestOfficeDaoImpl();
		officeDao.setOffice(office);
		service.setOfficeDao(officeDao);
		ITenureDao tenureDao = new TestTenureDaoImpl();
		tenureDao.setTenure(jobTenure.getJobTenure());
		service.setTenureDao(tenureDao);
		
		long startTime = System.currentTimeMillis();
		Employee updatedEmployee = service.changeOffice(employee, officeParams.getOfficeIdParam());
		long endTime = System.currentTimeMillis();
		
		long duration = (endTime - startTime);
		totalTimeInMillisecond = ++duration;
		Map<String, String> dialogMap = Dialog.getMap();
		String dialogValue = dialogMap.get(OFFICE_CITY_KEY);
		String expectedValue = expectedResults.getCity();
		assertTrue(String.format("Incorrect city: expected %s but found %s", expectedValue, dialogValue), dialogValue.equals(expectedValue));
	}

	/**
	 * This data method must start with 'parametersFor' and then the method
	 * which will get the parameters. Case for the method receiving the
	 * parameters is not important.
	 * 
	 * @return
	 */
	private Object[] parametersForChangeOfficeDialogTest() {
		try {
			// We have to tell the provider which method we want to provide data
			return jsonProvider.getObjects(this.getClass(), "changeOfficeDialogTest", CHANGE_OFFICE_DIALOG_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
