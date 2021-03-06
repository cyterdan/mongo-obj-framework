package org.smof.dataModel;

import java.nio.file.Path;

import org.smof.annnotations.SmofBuilder;
import org.smof.annnotations.SmofIndex;
import org.smof.annnotations.SmofIndexField;
import org.smof.annnotations.SmofIndexes;
import org.smof.annnotations.SmofParam;
import org.smof.element.Element;
import org.smof.gridfs.SmofGridRef;
import org.smof.index.IndexType;

@SuppressWarnings("javadoc")
@SmofIndexes({
	@SmofIndex(fields = {
			@SmofIndexField(name = "owner", type = IndexType.TEXT)
	}),
	@SmofIndex(fields = {
			@SmofIndexField(name = "price", type = IndexType.ASCENDING) 
	}),
	@SmofIndex(fields = {
			@SmofIndexField(name = "model", type = IndexType.ASCENDING),
			@SmofIndexField(name = "type", type = IndexType.ASCENDING),
			@SmofIndexField(name = "age", type = IndexType.ASCENDING)
	}, unique = true)
})
public interface Guitar extends Element{

	String BRAND = "brand";
	String MODEL = "model";
	String TYPE = "type";
	String NECKS = "necks";
	String AGE = "age";
	String PRICE = "price";
	String COLOR = "color";
	String OWNER = "owner";
	String PICTURE = "pic";

	@SmofBuilder
	static Guitar create(
			@SmofParam(name=MODEL) Model model, 
			@SmofParam(name=TYPE) TypeGuitar type, 
			@SmofParam(name=NECKS) Integer neckNumber, 
			@SmofParam(name=AGE) Integer age) {
		switch(type) {
		case ACOUSTIC:
			return new AcousticGuitar(model);
		case CLASSIC:
			return new ClassicalGuitar(model, age);
		case ELECTRIC:
			return new ElectricGuitar(model, neckNumber);
		default:
			return null;
		}
	}

	Brand getBrand();

	Model getModel();

	TypeGuitar getGuitarType();

	String getOwner();

	String getColor();

	void setColor(String color);

	void setPrice(int price);

	int getPrice();
	
	SmofGridRef getPicture();
	
	void setPicture(Path picture);

}
