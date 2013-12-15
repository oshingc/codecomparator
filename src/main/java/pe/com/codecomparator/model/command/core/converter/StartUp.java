package pe.com.codecomparator.model.command.core.converter;

import java.util.ArrayList;

public class StartUp {

	public static ArrayList<TokenCategory> categories = loadCategories();
	public static ArrayList<Item> reserved = loadReservedWords();
	public static double constant_value;
	public static double variable_value;
	public static String[] operators = new String[] { "[+]", "[-]", "[*]", "/",
			"%", "&", "[|]", "[\\^]", "!", "~", "&&", "[||]", "true", "false",
			"[+]", "[++]", "--", "<<", ">>", "==", "!=", "<", ">", "<=", ">=",
			"=", "[+=]", "-=", "[*=]", "/=", "%=", "&=", "^=", "<<=",
			">>=", "[??]", "[.]", "[\\[]", "]", "[(]", "[)]", "[?:]", "new" };

	private static ArrayList<TokenCategory> loadCategories() {
		ArrayList<TokenCategory> out = new ArrayList<TokenCategory>();
		int er = 34;
		er=er^er;
		TokenCategory category = new TokenCategory();
		category.setName("arithmetic");
		category.setObjects(arithmetic());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("logical");
		category.setObjects(logical());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("concatenation");
		category.setObjects(concatenation());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("incDec");
		category.setObjects(incDec());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("shift");
		category.setObjects(shift());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("relational");
		category.setObjects(relational());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("assignment");
		category.setObjects(assignment());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("memberAccess");
		category.setObjects(memberAccess());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("indexing");
		category.setObjects(indexing());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("cast");
		category.setObjects(cast());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("conditional");
		category.setObjects(conditional());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("objectCreation");
		category.setObjects(objectCreation());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		return out;
	}

	private static ArrayList<Item> loadReservedWords() {
		ArrayList<Item> out = new ArrayList<Item>();
		out.add(new Item(0.00625, "void"));
		out.add(new Item(0.01250, "byte"));
		out.add(new Item(0.01875, "int"));
		out.add(new Item(0.02500, "short"));
		out.add(new Item(0.03125, "long"));
		out.add(new Item(0.03750, "float"));
		out.add(new Item(0.04375, "boolean"));
		out.add(new Item(0.05000, "char"));
		out.add(new Item(0.05625, "double"));
		out.add(new Item(0.06250, "String"));
		out.add(new Item(0.06875, "Integer"));
		out.add(new Item(0.07500, "Double"));
		out.add(new Item(0.08125, "Float"));
		out.add(new Item(0.08750, "Byte"));
		out.add(new Item(0.09375, "Short"));
		out.add(new Item(0.10000, "Long"));
		out.add(new Item(0.10625, "Boolean"));
		out.add(new Item(0.11250, "Character"));
		out.add(new Item(0.11875, "for"));
		out.add(new Item(0.12500, "do"));
		out.add(new Item(0.13125, "while"));
		out.add(new Item(0.13750, "break"));
		out.add(new Item(0.14375, "continue"));
		out.add(new Item(0.15000, "default"));
		out.add(new Item(0.15625, "public"));
		out.add(new Item(0.16250, "private"));
		out.add(new Item(0.16875, "protected"));
		out.add(new Item(0.17500, "static"));
		out.add(new Item(0.18125, "final"));
		out.add(new Item(0.18750, "abstract"));
		out.add(new Item(0.19375, "strictfp"));
		out.add(new Item(0.20000, "const"));
		out.add(new Item(0.20625, "native"));
		out.add(new Item(0.21250, "volatile"));
		out.add(new Item(0.21875, "transient"));
		out.add(new Item(0.22500, "assert"));
		out.add(new Item(0.23125, "synchronized"));
		out.add(new Item(0.23750, "class"));
		out.add(new Item(0.24375, "new"));
		out.add(new Item(0.25000, "enum"));
		out.add(new Item(0.25625, "interface"));
		out.add(new Item(0.26250, "package"));
		out.add(new Item(0.26875, "import"));
		out.add(new Item(0.27500, "this"));
		out.add(new Item(0.28125, "instanceof"));
		out.add(new Item(0.28750, "if"));
		out.add(new Item(0.29375, "else"));
		out.add(new Item(0.30000, "switch"));
		out.add(new Item(0.30625, "case"));
		out.add(new Item(0.31250, "default"));
		out.add(new Item(0.31875, "return"));
		out.add(new Item(0.32500, "goto"));
		out.add(new Item(0.33125, "try"));
		out.add(new Item(0.33750, "catch"));
		out.add(new Item(0.34375, "finally"));
		out.add(new Item(0.35000, "throw"));
		out.add(new Item(0.35625, "throws"));
		out.add(new Item(0.36250, "super"));
		out.add(new Item(0.36875, "implements"));
		out.add(new Item(0.37500, "extends"));	
		return out;
	}

	private static Item[] arithmetic() {
		Item[] out = new Item[] { new Item(1.00, "+"), new Item(1.05, "-"),
				new Item(1.10, "*"), new Item(1.15, "/"), new Item(1.20, "%") };
		return out;
	}

	private static Item[] logical() {
		Item[] out = new Item[] { new Item(2.20, "&"), new Item(2.25, "|"),
				new Item(2.30, "^"), new Item(2.35, "!"), new Item(2.40, "~"),
				new Item(2.45, "&&"), new Item(2.50, "||"),
				new Item(2.55, "true"), new Item(2.60, "false") };
		return out;
	}

	private static Item[] concatenation() {
		Item[] out = new Item[] { new Item(3.60, "+") };
		return out;
	}

	private static Item[] incDec() {
		Item[] out = new Item[] { new Item(4.60, "++"), new Item(4.65, "--") };
		return out;
	}

	private static Item[] shift() {
		Item[] out = new Item[] { new Item(5.65, "<<"), new Item(5.70, ">>") };
		return out;
	}

	private static Item[] relational() {
		Item[] out = new Item[] { new Item(6.70, "=="), new Item(6.75, "!="),
				new Item(6.80, "<"), new Item(6.85, ">"), new Item(6.90, "<="),
				new Item(6.95, ">=") };
		return out;
	}

	private static Item[] assignment() {
		Item[] out = new Item[] { new Item(7.95, "="), new Item(8.00, "+="),
				new Item(8.05, "-="), new Item(8.10, "*="),
				new Item(8.15, "/="), new Item(8.20, "%="),
				new Item(8.25, "&="), new Item(8.30, "^="),
				new Item(8.35, "<<="), new Item(8.40, ">>=")};
		return out;
	}

	private static Item[] memberAccess() {
		Item[] out = new Item[] { new Item(8.45, ".") };
		return out;
	}

	private static Item[] indexing() {
		Item[] out = new Item[] { new Item(8.50, "["), new Item(9.50, "]") };
		return out;
	}

	private static Item[] cast() {
		Item[] out = new Item[] { new Item(10.50, "("), new Item(10.55, ")") };
		return out;
	}

	private static Item[] conditional() {
		Item[] out = new Item[] { new Item(11.55, "?:") };
		return out;
	}

	private static Item[] objectCreation() {
		Item[] out = new Item[] { new Item(11.60, "new") };
		return out;
	}

}
