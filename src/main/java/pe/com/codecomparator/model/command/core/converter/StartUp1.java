package pe.com.codecomparator.model.command.core.converter;

import java.util.ArrayList;

public class StartUp1 {

	public static ArrayList<TokenCategory> categories = loadCategories();
	public static ArrayList<Item> reserved = loadReservedWords();
	public static double constant_value;
	public static double variable_value;
	public static String[] operators = new String[] { "[+]", "[-]", "[*]", "/",
			"%", "&", "[|]", "[\\^]", "!", "~", "&&", "[||]", "true", "false",
			"[+]", "[++]", "--", "<<", ">>", "==", "!=", "<", ">", "<=", ">=",
			"=", "[+=]", "-=", "[*=]", "/=", "%=", "&=", "[|=]", "^=", "<<=",
			">>=", "[??]", "[.]", "[\\[]", "]", "[(]", "[)]", "[?:]", "new",
			"as", "is", "sizeof", "typeof", "checked", "uncheked" };

	private static ArrayList<TokenCategory> loadCategories() {
		ArrayList<TokenCategory> out = new ArrayList<TokenCategory>();

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

		category = new TokenCategory();
		category.setName("typeInformation");
		category.setObjects(typeInformation());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		category = new TokenCategory();
		category.setName("exceptionControl");
		category.setObjects(exceptionControl());
		category.setValue(category.getObjects()[0].getValue());
		out.add(category);

		return out;
	}

	private static ArrayList<Item> loadReservedWords() {
		ArrayList<Item> out = new ArrayList<Item>();
		out.add(new Item(0.00625, "void"));
		out.add(new Item(0.01250, "byte"));
		out.add(new Item(0.01875, "sbyte"));
		out.add(new Item(0.02500, "int"));
		out.add(new Item(0.03125, "uint"));
		out.add(new Item(0.03750, "short"));
		out.add(new Item(0.04375, "ushort"));
		out.add(new Item(0.05000, "long"));
		out.add(new Item(0.05625, "ulong"));
		out.add(new Item(0.06250, "float"));
		out.add(new Item(0.06875, "double"));
		out.add(new Item(0.07500, "decimal"));
		out.add(new Item(0.08125, "bool"));
		out.add(new Item(0.08750, "char"));
		out.add(new Item(0.09375, "string"));
		out.add(new Item(0.10000, "for"));
		out.add(new Item(0.10625, "foreach"));
		out.add(new Item(0.11250, "in"));
		out.add(new Item(0.11875, "do"));
		out.add(new Item(0.12500, "while"));
		out.add(new Item(0.13125, "break"));
		out.add(new Item(0.13750, "continue"));
		out.add(new Item(0.14375, "public"));
		out.add(new Item(0.15000, "private"));
		out.add(new Item(0.15625, "protected"));
		out.add(new Item(0.16250, "static"));
		out.add(new Item(0.16875, "virtual"));
		out.add(new Item(0.17500, "abstract"));
		out.add(new Item(0.18125, "base"));
		out.add(new Item(0.18750, "const"));
		out.add(new Item(0.19375, "delegate"));
		out.add(new Item(0.20000, "volatile"));
		out.add(new Item(0.20625, "readonly"));
		out.add(new Item(0.21250, "value"));
		out.add(new Item(0.21875, "override"));
		out.add(new Item(0.22500, "implicit"));
		out.add(new Item(0.23125, "extern"));
		out.add(new Item(0.23750, "event"));
		out.add(new Item(0.24375, "explicit"));
		out.add(new Item(0.25000, "internal"));
		out.add(new Item(0.25625, "namespace"));
		out.add(new Item(0.26250, "class"));
		out.add(new Item(0.26875, "struct"));
		out.add(new Item(0.27500, "new"));
		out.add(new Item(0.28125, "enum"));
		out.add(new Item(0.28750, "interface"));
		out.add(new Item(0.29375, "lock"));
		out.add(new Item(0.30000, "sealed"));
		out.add(new Item(0.30625, "operator"));
		out.add(new Item(0.31250, "using"));
		out.add(new Item(0.31875, "object"));
		out.add(new Item(0.32500, "this"));
		out.add(new Item(0.33125, "as"));
		out.add(new Item(0.33750, "is"));
		out.add(new Item(0.34375, "if"));
		out.add(new Item(0.35000, "else"));
		out.add(new Item(0.35625, "switch"));
		out.add(new Item(0.36250, "case"));
		out.add(new Item(0.36875, "default"));
		out.add(new Item(0.37500, "return"));
		out.add(new Item(0.38125, "goto"));
		out.add(new Item(0.38750, "try"));
		out.add(new Item(0.39375, "catch"));
		out.add(new Item(0.40000, "throw"));
		out.add(new Item(0.40625, "cheked"));
		out.add(new Item(0.41875, "uncheked"));
		out.add(new Item(0.42500, "fixed"));
		out.add(new Item(0.43125, "unsafe"));
		out.add(new Item(0.43750, "get"));
		out.add(new Item(0.44375, "value"));
		out.add(new Item(0.45000, "partial"));
		out.add(new Item(0.45625, "where"));
		out.add(new Item(0.46250, "set"));
		out.add(new Item(0.46875, "yield"));
		out.add(new Item(0.47500, "out"));
		out.add(new Item(0.48125, "ref"));
		out.add(new Item(0.48750, "params"));
		out.add(new Item(0.49375, "sizeof"));
		out.add(new Item(0.50000, "stackalloc"));
		out.add(new Item(0.50625, "typeof"));
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
				new Item(8.25, "&="), new Item(8.30, "|="),
				new Item(8.35, "^="), new Item(8.40, "<<="),
				new Item(8.45, ">>="), new Item(8.50, "??") };
		return out;
	}

	private static Item[] memberAccess() {
		Item[] out = new Item[] { new Item(9.50, ".") };
		return out;
	}

	private static Item[] indexing() {
		Item[] out = new Item[] { new Item(10.50, "["), new Item(10.55, "]") };
		return out;
	}

	private static Item[] cast() {
		Item[] out = new Item[] { new Item(11.55, "("), new Item(11.60, ")") };
		return out;
	}

	private static Item[] conditional() {
		Item[] out = new Item[] { new Item(12.60, "?:") };
		return out;
	}

	private static Item[] objectCreation() {
		Item[] out = new Item[] { new Item(13.60, "new") };
		return out;
	}

	private static Item[] typeInformation() {
		Item[] out = new Item[] { new Item(14.60, "as"), new Item(14.65, "is"),
				new Item(14.70, "sizeof"), new Item(14.75, "typeof") };
		return out;
	}

	private static Item[] exceptionControl() {
		Item[] out = new Item[] { new Item(15.75, "checked"),
				new Item(15.80, "unchecked") };
		return out;
	}

}
