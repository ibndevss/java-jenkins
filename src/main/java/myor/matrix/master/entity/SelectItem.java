package myor.matrix.master.entity;

public class SelectItem<T> {
	private String label;
	private T value;
	
	public SelectItem() {
		// TODO Auto-generated constructor stub
	}


	public SelectItem(String label, T value) {
		super();
		this.label = label;
		this.value = value;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public T getValue() {
		return value;
	}


	public void setValue(T value) {
		this.value = value;
	}


	
	
}
