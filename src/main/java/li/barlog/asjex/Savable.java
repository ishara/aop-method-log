package li.barlog.asjex;

import java.util.StringJoiner;

public class Savable {

	int age ;
	String name;

	public Savable() {
	}

	@SystemChangeLogable(name = "H2H System",fieldsToLog = {"name"})
	public void save()
	{
		System.out.println("Savable.save");

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Savable.class.getSimpleName() + "[", "]")
			.add("age=" + age)
			.add("name='" + name + "'")
			.toString();
	}
}
