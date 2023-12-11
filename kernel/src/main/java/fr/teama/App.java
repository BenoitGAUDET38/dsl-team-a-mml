package fr.teama;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

public class App implements Visitable {

	private String name;

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "App{" +
				"\nname='" + name + '\'' +
				"\n}";
	}
}
