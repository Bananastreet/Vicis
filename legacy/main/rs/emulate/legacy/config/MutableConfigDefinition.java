package rs.emulate.legacy.config;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;

/**
 * A base class for a definition with properties that can be mutated.
 *
 * @author Major
 */
public abstract class MutableConfigDefinition {

	/**
	 * The PropertyMap.
	 */
	protected final ConfigPropertyMap properties;

	/**
	 * The id of this MutableDefinition.
	 */
	private final int id;

	/**
	 * Creates the MutableDefinition.
	 *
	 * @param id The id of the MutableDefinition.
	 * @param properties The {@link ConfigPropertyMap}. Must not be {@code null}.
	 */
	public MutableConfigDefinition(int id, ConfigPropertyMap properties) {
		Preconditions.checkNotNull(properties, "Map cannot be null.");
		this.id = id;
		this.properties = new ConfigPropertyMap(properties);
	}

	/**
	 * Adds a {@link ConfigProperty} to this MutableDefinition, using the smallest available (positive) opcode.
	 *
	 * @param property The DefinitionProperty.
	 */
	public final void addProperty(ConfigProperty<?> property) {
		addProperty(properties.size(), property);
	}

	/**
	 * Adds a {@link ConfigProperty} with the specified opcode to this MutableDefinition.
	 *
	 * @param opcode The opcode.
	 * @param property The DefinitionProperty.
	 */
	public final void addProperty(int opcode, ConfigProperty<?> property) {
		properties.put(opcode, property);
	}

	/**
	 * Gets the id of this MutableDefinition.
	 *
	 * @return The id.
	 */
	public final int getId() {
		return id;
	}

	/**
	 * Gets the {@link Set} of {@link Map} {@link Entry} objects containing the opcodes and {@link ConfigProperty}
	 * objects.
	 *
	 * @return The Set.
	 */
	public final Set<Entry<Integer, ConfigProperty<?>>> getProperties() {
		return properties.getProperties();
	}

	/**
	 * Gets a {@link ConfigProperty} with the specified opcode.
	 *
	 * @param opcode The opcode of the DefinitionProperty.
	 * @return The DefinitionProperty.
	 */
	public final <T> ConfigProperty<T> getProperty(int opcode) {
		@SuppressWarnings("unchecked")
		ConfigProperty<T> property = (ConfigProperty<T>) properties.get(opcode);
		Preconditions.checkNotNull(property, "No property with opcode " + opcode + " exists.");
		return property;
	}

	/**
	 * Gets a {@link ConfigProperty} with the specified {@link ConfigPropertyType}.
	 *
	 * @param name The name of the DefinitionProperty.
	 * @return The DefinitionProperty.
	 * @throws IllegalArgumentException If no DefinitionProperty with the specified name exists.
	 */
	public final <T> ConfigProperty<T> getProperty(ConfigPropertyType name) {
		@SuppressWarnings("unchecked")
		ConfigProperty<T> property = (ConfigProperty<T>) properties.get(name);
		Preconditions.checkNotNull(property, "No property called " + name + " exists.");
		return property;
	}

	/**
	 * Sets the {@link ConfigProperty} with the specified name.
	 *
	 * @param opcode The opcode of the DefinitionProperty.
	 * @param property The DefinitionProperty.
	 */
	public final void setProperty(int opcode, ConfigProperty<?> property) {
		properties.put(opcode, property);
	}

	/**
	 * Sets the value of the {@link ConfigProperty} with the specified opcode.
	 *
	 * @param name The {@link ConfigPropertyType name} of the DefinitionProperty.
	 * @param value The value.
	 */
	public final <V> void setProperty(ConfigPropertyType name, V value) {
		getProperty(name).setValue(value);
	}

	@Override
	public final String toString() {
		ToStringHelper helper = MoreObjects.toStringHelper(this);

		for (Map.Entry<Integer, ConfigProperty<?>> entry : properties.getProperties()) {
			ConfigProperty<?> property = entry.getValue();
			if (property.valuePresent()) {
				String name = property.getName();

				helper.add(name, property);
			}
		}

		return helper.toString();
	}

}