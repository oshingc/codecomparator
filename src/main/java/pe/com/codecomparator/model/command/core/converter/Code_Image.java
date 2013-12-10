package pe.com.codecomparator.model.command.core.converter;

import java.util.ArrayList;

public class Code_Image {

	private ArrayList<Double> code_img;
	private String code_line;

	public Code_Image() {
		super();
	}

	public Code_Image(ArrayList<Double> code_img, String code_line) {
		super();
		this.code_img = code_img;
		this.code_line = code_line;
	}

	public ArrayList<Double> getCode_img() {
		return code_img;
	}

	public void setCode_img(ArrayList<Double> code_img) {
		this.code_img = code_img;
	}

	public String getCode_line() {
		return code_line;
	}

	public void setCode_line(String code_line) {
		this.code_line = code_line;
	}

}
