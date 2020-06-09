package net.rom.exoplanets.api.data;

/**
*
* @author ROMVoid95
* @Description used to store almost any kind of variable using a special Data. When working, remember, that you can store any Object to the corresponding name.
*/
public class FakeData {
	
	public final String dataName;
	public final String dataValue;
	
	public FakeData(String name, String value) {
		dataName = name;
		dataValue = value;
	}
	
	public FakeData(String name, Object value) {
		dataName = name;
		dataValue = value.toString();
	}
	
	@Override
	public String toString() {
		String string = "";
		return string.concat("||").concat(dataName).concat(dataValue.toString().toLowerCase());
	}
	
	public static FakeData toNull() {
		return new FakeData("null", "null");
	}

}
