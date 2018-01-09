package com.nestedobjects.pojos;

public interface IOfficeDao {
	Office getOffice(int officeId);

	void setOffice(Office office);

	void save(Office office);
}
