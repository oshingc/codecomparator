package pe.com.codecomparator.model.command.core.converter;

public class Item {

	private double value; // valor
	private String id; // operador

	public Item(double value, String id) {
		this.value = value;
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
