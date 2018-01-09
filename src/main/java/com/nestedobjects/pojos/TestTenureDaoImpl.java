package com.nestedobjects.pojos;

public class TestTenureDaoImpl implements ITenureDao {
	private Integer jobTenure;

	@Override
	public void setTenure(Integer tenure) {
		this.jobTenure = tenure;
	}

	@Override
	public Integer getTenure() {
		return this.jobTenure;
	}

}
