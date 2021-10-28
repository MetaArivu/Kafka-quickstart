package com.ktable.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Department {

	private String deptId;
	private double totalSalary;
	private int noOfEmp;
	private double avgSalary;
	
	public Department() {
		
	}

	
	public Department(String deptId, double totalSalary, int noOfEmp, double avgSalary) {
		super();
		this.deptId = deptId;
		this.totalSalary = totalSalary;
		this.noOfEmp = noOfEmp;
		this.avgSalary = avgSalary;
	}


	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public int getNoOfEmp() {
		return noOfEmp;
	}

	public void setNoOfEmp(int noOfEmp) {
		this.noOfEmp = noOfEmp;
	}

	public double getAvgSalary() {
		return avgSalary;
	}

	public void setAvgSalary(double avgSalary) {
		this.avgSalary = avgSalary;
	}


	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", totalSalary=" + totalSalary + ", noOfEmp=" + noOfEmp + ", avgSalary="
				+ avgSalary + "]";
	}
	
	
	
}
