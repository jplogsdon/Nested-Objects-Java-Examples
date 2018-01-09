package com.nestedobjects.sutproxy;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nestedobjects.pojos.Customer;
import com.nestedobjects.pojos.Employee;
import com.nestedobjects.pojos.Office;
import com.nestedobjects.pojos.Payments;
import com.nestedobjects.shared.ObjectMapperConfig;

@Configuration
@Import(ObjectMapperConfig.class)
public class Converters {


	@Component
	public class EmployeeConverter extends StringToObjectConverter<Employee> {
		EmployeeConverter() {
			super(Employee.class);
		}
	};

	@Component
	public class OfficeConverter extends StringToObjectConverter<Office> {
		OfficeConverter() {
			super(Office.class);
		}
	};

	@Component
	public class PaymentsConverter extends StringToObjectConverter<Payments> {
		PaymentsConverter() {
			super(Payments.class);
		}
	};

	@Component
	public class PaymentsArrayConverter extends StringToObjectConverter<Payments[]> {
		PaymentsArrayConverter() {
			super(Payments[].class);
		}
	};

	@Component
	public class CustomerConverter extends StringToObjectConverter<Customer> {
		CustomerConverter() {
			super(Customer.class);
		}
	};

}
