package pe.com.codecomparator.model.command.core.converter;

public class TokenCategory {

	private String name; // categoría
	private Item[] objects; // operador
	private double value; // fi

	public TokenCategory() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setObjects(Item[] objects) {
		this.objects = objects;
	}

	public Item[] getObjects() {
		return objects;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

}
