package main;

import java.util.Scanner;

import dao.JdbcFile;

public class TestEMS {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		JdbcFile db = new JdbcFile();
		db.databaseOps();
		int eid, esal;
		String ename;
		
		System.out.println("------Welcome to Employee Management System--------");
		System.out.println("1. Enter record of an employee");
		System.out.println("2. Update the record of an employee");
		System.out.println("3. Search record of an employee");
		System.out.println("4. Delete record of an employee");
		System.out.println("5. Exit!!");
		System.out.println();
		
		
		
		
		boolean flag = true;
		
		while(flag) {
			System.out.println("Enter Your Choice : ");
			int ch = sc.nextInt();
			switch(ch) {
			
			// Insert a record
			case 1:
				System.out.println("Enter empid : ");
				eid = sc.nextInt();
				System.out.println("Enter empname : ");
				ename = sc.next();
				System.out.println("Enter empsal : ");
				esal = sc.nextInt();
				
				db.rcrInsert(eid, ename, esal);
				
				break;
				
			// Update a record
			case 2:
				System.out.println("Enter empid : ");
				eid = sc.nextInt();
				System.out.println("Enter empname : ");
				ename = sc.next();
				System.out.println("Enter empsal : ");
				esal = sc.nextInt();
				
				db.rcrUpdate(eid, ename, esal);
				break;
				
			// Search an employee
			case 3:
				System.out.println("Enter empid : ");
				eid = sc.nextInt();
				
				db.rcrSearchEmployee(eid);
				break;
				
			// Delete an employee
			case 4:
				System.out.println("Enter empid : ");
				eid = sc.nextInt();
				
				db.rcrDelete(eid);
				
				break;
			
			// Exit the program
			case 5:
				flag = false;
				break;
				
			}
		}

	}
}
