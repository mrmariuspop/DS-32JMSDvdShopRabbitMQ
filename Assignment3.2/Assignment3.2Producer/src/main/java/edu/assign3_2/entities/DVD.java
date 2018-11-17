package edu.assign3_2.entities;
import java.io.Serializable;


public class DVD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private int year;
	private double price;
	
	public DVD(String title, int year, double price) {
		this.title = title;
		this.year = year;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DVD other = (DVD) obj;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String dvdString = new String("");
		dvdString = dvdString + "Title: " + getTitle() + "%n" + "Year: " + getYear() + "%n" + "Price: " + getPrice();
		dvdString = String.format(dvdString);
		return dvdString;
	}
}
