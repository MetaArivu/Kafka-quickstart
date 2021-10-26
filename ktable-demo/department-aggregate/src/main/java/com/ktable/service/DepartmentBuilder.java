package com.ktable.service;

import com.ktabel.model.Department;
import com.ktabel.model.Employee;

public class DepartmentBuilder {

	public static Department init() {
		return new Department(null,0, 0, 0);
	}

	public static Department add(Employee emp, Department dept) {
		return new Department(emp.getDeptId(), 
				dept.getTotalSalary() + emp.getSalary(), 
				dept.getNoOfEmp() + 1,
				(dept.getTotalSalary() + emp.getSalary()) / (dept.getNoOfEmp() + 1));

	}

	public static Department sub(Employee emp, Department dept) {
		return new Department(emp.getDeptId(), 
				dept.getTotalSalary() - emp.getSalary(), 
				dept.getNoOfEmp() - 1,
				(dept.getTotalSalary() - emp.getSalary()) / (dept.getNoOfEmp() - 1));
	}
}
