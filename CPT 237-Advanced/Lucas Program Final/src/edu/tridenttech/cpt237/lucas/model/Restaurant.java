package edu.tridenttech.cpt237.lucas.model;
/**
 * @file: Restaurant.java
 * @author: Ben Lucas
 * @purpose: Class creates a singleton restaurant object that is then passed to all GUI
 * windows to handle list management for all employee, menu item, check, and id tracking.
 * First the constructor loads the employees into the managers or servers list from a file. 
 * Then it loads all menu items from a file. There are two methods included to make a single 
 * opened check and single closed check for each employee in servers ArrayList.  If items
 * are deleted from the menu the console displays an item deleted message. The ability to load 
 * saved checks could be added later in a similar fashion to employees and menu items. 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
	
	private ArrayList<Server> servers = new ArrayList<>();//all servers
	private ArrayList<Manager> managers = new ArrayList<>();//all managers
	private ArrayList<MenuItem> menuItems = new ArrayList<>();
	private ArrayList<Check> openedChecks = new ArrayList<>();
	private ArrayList<Check> closedChecks = new ArrayList<>();
	private ArrayList<Integer> usedEmpIds = new ArrayList<>();
	private ArrayList<Integer> usedItemIds = new ArrayList<>();
	private static Restaurant restaurant;
	private static int instance = 0;
	
	private Restaurant(){
		
	}
	/**
	 * @purpose Loads employees and menu items into array lists. 
	 * @return Singleton Restaurant object with ArrayLists
	 */
	public static Restaurant loadRestaurant() {
		
		if(instance == 0) {
			restaurant = new Restaurant();
			String filePath = "employees.csv";
			try {
				restaurant.loadEmployees(filePath);				
			}

			catch (FileNotFoundException fx) {
				System.out.println("*************************************************");
				System.out.println("*************Incorrect File Name/Path************");
				System.out.println("*****************Program Exiting*****************");
				System.out.println("*************************************************");
				System.exit(1);
			}
			
			filePath = "menuItems.csv";
			try {
				restaurant.loadMenu(filePath);
				instance++;
			}

			catch (FileNotFoundException fx) {
				System.out.println("*************************************************");
				System.out.println("*************Incorrect File Name/Path************");
				System.out.println("*****************Program Exiting*****************");
				System.out.println("*************************************************");
				System.exit(1);
			}			
		}
		else {
			System.out.println("********************************");
			System.out.println("Restaurant Already Open!");
			System.out.println("********************************");
		}	
		return restaurant;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	/**
	 * @purpose adds new Server object, checks for duplicate ID's,
	 * outputs to console if Id is taken but this should never happen
	 * because user has no control over an auto incrementing ID. ID
	 * is also added to a used id list for employees.

	 * @param fName
	 * @param lName
	 * @param id
	 */
	public void addNewServer(String fName, String lName, int id){
		for(Integer ID : usedEmpIds) {
			if(ID == id) {
				System.out.println("ID Already Exists!");
				return;
			}	
		}		
		usedEmpIds.add(id);
		servers.add(new Server(fName,lName,id));		
	}
	/**
	 * @purpose adds new Manager object, checks for duplicate ID's,
	 * outputs to console if Id is taken but this should never happen
	 * because user has no control over an auto incrementing ID. ID
	 * is also added to a used id list for employees.
	 * @param fName
	 * @param lName
	 * @param id
	 */
	public void addNewManager(String fName, String lName,int id){
		for(Integer ID : usedEmpIds) {
			if(ID == id) {
				System.out.println("ID Already Exists!");
				return;
			}	
		}		
		usedEmpIds.add(id);
		managers.add(new Manager(fName,lName,id));		
	}	
	/**
	 *@purpose adds a new MenuItem object, checks for duplicate ID's,
	 * outputs to console if ID is taken but this should never happen
	 * because user has no control over an auto incrementing ID. ID
	 * is also added to a used id list for menu items. 
	 * @param name
	 * @param cost
	 * @param id
	 */
	public void addNewMenuItem(String name, double cost, int id){
		for(Integer ID : usedItemIds) {
			if(ID == id) {
				System.out.println("ID Already Exists!");
				return;
			}	
		}		
		usedItemIds.add(id);
		menuItems.add(new MenuItem(name,cost,id));
	}
	/**
	 * @purpose removes Server object from server list and server's 
	 * ID from used employee  ID list
	 * @param id
	 * @return true if server removal from list is successful
	 */
	public boolean removeServer(int id){
		if(servers.remove(getEmployeeById(id))) {
			usedEmpIds.remove(id);
			return true;
		}		
		else
		return false;
	}	
	/**
	 * @purpose removes Manager object from manager list and manager's 
	 * ID from used employee ID list
	 * @param id
	 * @return true if manager removal from list is successful
	 */
	public boolean removeManager(int id){
		if(managers.remove(getEmployeeById(id))) {
			usedEmpIds.remove(id);
			return true;
		}		
		else
		return false;	
	}
	
	/**
	 * @purpose removes MenuItem object from menu item list and items's 
	 * ID from used item ID list
	 * @param id
	 * @return true if menu item removal from list is successful
	 */
	public boolean removeMenuItem(int id){
		if(menuItems.remove(getItemById(id))) {
			usedItemIds.remove(id);
			return true;
		}		
		else
		return false;
		
	}
	/**
	 * @purpose finds employee by iterating through each employee
	 * list.  If there is a match then the match is returned.  There should
	 * never be more than one instance of each employee as either a server
	 * or manager.
	 * @param id
	 * @return first ID matched employee object, if nothing is found method
	 * returns a null object. There should always be a match.
	 */
	public Employee getEmployeeById(int id) {
		Employee x = null;
		for(Employee employee: servers){
			if(employee.getEmployeeId() == id){			
				x = (Server)employee;				
				return x;
			}			
		}
		for(Employee employee: managers){
			if(employee.getEmployeeId() == id){			
				x = (Manager)employee;				
				return x;
			}			
		}
		return x;
	}
	/**
	 * @purpose finds a menu item from the array list of 
	 * menu items available in the restaurant. Uses ID to match.
	 * @param id
	 * @return menu item object if there is an ID match, other wise
	 * an null object is returned. There should always be a match.
	 */
	public MenuItem getItemById(int id) {
		MenuItem x = null;
		for(MenuItem food: menuItems){
			if(food.getItemID() == id){			
				x = food;				
				return x;
			}			
		}
		return x;
	}
	/**
	 * @purpose uses check ID to find a match in an array list of checks
	 * @param id
	 * @return check object if there is a match, null otherwise. 
	 * There should always be a match.
	 */
	public Check getCheckById(int id) {
		Check x = null;
		for(Check check: openedChecks){
			if(check.getCheckId() == id){			
				x = check;				
				return x;
			}			
		}
		for(Check check: closedChecks){
			if(check.getCheckId() == id){			
				x = check;				
				return x;
			}			
		}
		return x;
	}
	/**
	 * @purpose opens a new check and adds it to opened checks 
	 * array list.
	 * @param serverId
	 */
	public void openNewCheck(int serverId) {
		Check check = new Check(serverId);
		openedChecks.add(check);		
	}
	/**
	 * @purpose closes a guest check. removes the check from opened checks.
	 * adds the check to closed checks.
	 * @param check
	 * @return balance of check to be used to add to server cash owed to restaurant
	 */
	public double closeGuest(Check check) {
		openedChecks.remove(check);
		closedChecks.add(check);
		return check.getCheckBalance();
	}
	/**
	 * @purpose clears closed check array list
	 */
	public void clearClosedChecks() {
		closedChecks.clear();
	}
	public ArrayList<Server> getServers() {
		return servers;
	}

	public ArrayList<Manager> getManagers() {
		return managers;
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	
	public ArrayList<Check> getAllClosedChecks() {
		return closedChecks;
	}
	
	public ArrayList<Check> getAllOpenedChecks() {
		return openedChecks;
	}
	
	public ArrayList<Integer> getUsedEmpIds() {
		return usedEmpIds;
	}
	
	public ArrayList<Integer> getUsedItemIds() {
		return usedItemIds;
	}

	/**
	 * @purpose used to load employees from a file. Uses first field of file to determine
	 * employee type. Called from loadRestaurant(). prints to console if there is an error 
	 * but that should never happen. report includes line of file where there is an error.
	 * @param file file to load from
	 * @return true if load is successful
	 * @throws FileNotFoundException
	 */
	public boolean loadEmployees(String file) throws FileNotFoundException{			
		
		Scanner input = new Scanner(new File(file));

		while(input.hasNext()){					
			
			String line = input.nextLine();
			String[] fields = line.split(",");
			
			char empType = fields[0].toUpperCase().charAt(0);
			int lineCount = 1;		
			
			switch(empType) {
			
			case 'M':
				try {
					addNewManager(fields[1],fields[2],Integer.parseInt(fields[3]));
					break;
				} catch (Exception e) {
					System.out.println("***********************************");
					System.out.printf("Employee Line Reading Error Line:%d%n", lineCount+1);
					System.out.println("***********************************");
					lineCount++;
					break;
				}	
			case 'S':
				try {
					addNewServer(fields[1],fields[2],Integer.parseInt(fields[3]));
					break;
				} catch (Exception e) {
					System.out.println("************************************");
					System.out.printf("Employee Line Reading Error Line:%d%n", lineCount+1);
					System.out.println("************************************");
					lineCount++;
					break;
				}
			default:
				System.out.println("********************************");
				System.out.printf("Employee Type Error Line:%d%n", lineCount+1);
				System.out.println("********************************");
				lineCount++;
				break;
			}			
		}
		input.close();
		return true;
	}
	/**
	 * @purpose loads menu from a file. called in loadRestaurant(). if there is an input
	 * error then console outputs error message with line # of the error in the file. an error 
	 * should never occur unless the file is corrupted
	 * @param file file to load from
	 * @return true if load is successful
	 * @throws FileNotFoundException
	 */
	public boolean loadMenu(String file) throws FileNotFoundException{			
		
		Scanner input = new Scanner(new File(file));

		while(input.hasNext()){					
			
			String line = input.nextLine();
			String[] fields = line.split(",");
			int lineCount = 1;		
			
			try {
				addNewMenuItem(fields[0],Double.parseDouble(fields[1]),Integer.parseInt(fields[2]));
			} catch (NumberFormatException e) {
				System.out.println("********************************");
				System.out.printf("Menu Line Reading Error Line:%d%n", lineCount+1);
				System.out.println("********************************");
			}			
			lineCount++;
		}
		input.close();
		return true;
	}
	
	/**
	 * @purpose create fake opened checks for each server for testing
	 * prints item deleted. through exception that is caught in 
	 * loadRestaurant and notifies user through console that item was deleted from menu.
	 * @throws CloneNotSupportedException
	 * @throws NullPointerException
	 */
	public void loadOpenedTestChecks() throws CloneNotSupportedException,NullPointerException {
		
		for(Server server: servers) {
			Check check = new Check(server.getEmployeeId());
			ArrayList<MenuItem> checkList = check.getCheckItemList();
			checkList.add((MenuItem)getItemById(1001).clone());
			checkList.add((MenuItem)getItemById(1002).clone());
			checkList.add((MenuItem)getItemById(1003).clone());
			checkList.add((MenuItem)getItemById(1007).clone());
			checkList.add((MenuItem)getItemById(1008).clone());
			for(MenuItem item : checkList) {
				check.addToCheckBalance(item.getItemCost());
			}
			openedChecks.add(check);
		}		
	}
	
	/**
	 * @purpose create fake closed checks for each server for testing
	 * prints item deleted. through exception that is caught in 
	 * loadRestaurant and notifies user through console that item was deleted 
	 * from menu. Adds check balance to server cash owed to restaurant
	 * @throws CloneNotSupportedException
	 * @throws NullPointerException
	 */
	public void loadClosedTestChecks() throws CloneNotSupportedException,NullPointerException {
		
		for(Server server: servers) {
			Check check = new Check(server.getEmployeeId());
			ArrayList<MenuItem> checkList = check.getCheckItemList();
			checkList.add((MenuItem)getItemById(1004).clone());
			checkList.add((MenuItem)getItemById(1003).clone());
			checkList.add((MenuItem)getItemById(1007).clone());
			checkList.add((MenuItem)getItemById(1005).clone());
			for(MenuItem item : checkList) {
				check.addToCheckBalance(item.getItemCost());
			}
			closedChecks.add(check);
			server.addCashOwedToRestaurant(check.getCheckBalance());
		}		
	}

}
