package io.explore;

import java.util.HashSet;
import java.util.Set;

public class Organization {

	Set<Warehouse> warehouses = new HashSet<>();

	public Set<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void addWarehouse(Warehouse warehouse) {
		warehouses.add(warehouse);
	}

	public static void main(String[] args) {

		Organization org = initializeOrg();

		org
				.getWarehouses()
				.stream()
				.flatMap(w -> w.getItems().stream())
				.map(item -> item.getName() + " : " + item.getQuantity())
				.forEach(System.out::println);

		long totalItem = org
				.getWarehouses()
				.stream()
				.flatMap(w -> w.getItems().stream())
				.mapToLong(Item::getQuantity).sum();

		System.out.println("=============\nTotal : " + totalItem);
		
		assert (totalItem == 3600) : "should be 3600"; // Need to run with VM argument -ea
	}

	private static Organization initializeOrg() {
		Item item1 = new Item("Item1", 100);
		Item item2 = new Item("Item2", 200);
		Item item3 = new Item("Item3", 300);
		Item item4 = new Item("Item4", 400);
		Item item5 = new Item("Item5", 500);
		Item item6 = new Item("Item6", 600);
		Item item7 = new Item("Item7", 700);
		Item item8 = new Item("Item8", 800);

		Warehouse warehouse1 = new Warehouse("Sydney");
		warehouse1.addItem(item1);
		warehouse1.addItem(item2);
		warehouse1.addItem(item3);
		Warehouse warehouse2 = new Warehouse("Brisbane");
		warehouse2.addItem(item4);
		warehouse2.addItem(item5);
		warehouse2.addItem(item6);
		Warehouse warehouse3 = new Warehouse("Perth");
		warehouse3.addItem(item7);
		warehouse3.addItem(item8);

		Organization org = new Organization();
		org.addWarehouse(warehouse1);
		org.addWarehouse(warehouse2);
		org.addWarehouse(warehouse3);
		return org;
	}
}

class Warehouse {
	Set<Item> items = new HashSet<>();
	private String location;

	public Warehouse(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}
}

class Item {
	private long quantity;
	private String name;

	Item(String name, long quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public long getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}
}
