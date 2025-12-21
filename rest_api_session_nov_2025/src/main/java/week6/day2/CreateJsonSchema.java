package week6.day2;

import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

public class CreateJsonSchema {

	public static <T> String create(Class<T> classType) {
		SchemaGeneratorConfig schemaGeneratorConfig = new SchemaGeneratorConfigBuilder(
				SchemaVersion.DRAFT_7,
				OptionPreset.PLAIN_JSON).build();
		SchemaGenerator schmeaGenerator = new SchemaGenerator(schemaGeneratorConfig);
		return schmeaGenerator.generateSchema(classType).toPrettyString();
	}

}
