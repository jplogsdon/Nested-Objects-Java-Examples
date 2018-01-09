package com.nestedobjects.pojos;

public class TestOfficeDaoImpl implements IOfficeDao {

	public Office office;

	@Override
	public Office getOffice(int officeId) {
		return this.office;
	}

	@Override
	public void save(Office office) {
	}

	@Override
	public void setOffice(Office office) {
		this.office = office;

	}

}
