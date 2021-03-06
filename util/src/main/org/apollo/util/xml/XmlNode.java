package org.apollo.util.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A class which represents a single node in the DOM tree, maintaining information about its children, attributes,
 * value
 * and name.
 *
 * @author Graham
 */
public final class XmlNode implements Iterable<XmlNode> {

	/**
	 * The attribute Map.
	 */
	private final Map<String, String> attributes = new HashMap<>();

	/**
	 * The List of child XmlNodes.
	 */
	private final List<XmlNode> children = new ArrayList<>();

	/**
	 * The name of this XmlNode.
	 */
	private String name;

	/**
	 * The value of this XmlNode, or {@code null} if it has no value.
	 */
	private String value;

	/**
	 * Creates the XmlNode with the specified name.
	 *
	 * @param name The name of the XmlNode.
	 */
	public XmlNode(String name) {
		this.name = name;
	}

	/**
	 * Adds a child XmlNode.
	 *
	 * @param child The XmlNode to add.
	 */
	public void addChild(XmlNode child) {
		children.add(child);
	}

	/**
	 * Checks if an attribute with the specified name exists.
	 *
	 * @param name The attribute's name.
	 * @return {@code true} if an attribute with that name exists, {@code false} otherwise.
	 */
	public boolean containsAttribute(String name) {
		return attributes.containsKey(name);
	}

	/**
	 * Gets an attribute by it's name.
	 *
	 * @param name The name of the attribute.
	 * @return The attribute's value, or {@code null} if it doesn't exist.
	 */
	public Optional<String> getAttribute(String name) {
		return Optional.ofNullable(attributes.get(name));
	}

	/**
	 * Gets the attribute count.
	 *
	 * @return The number of attributes.
	 */
	public int getAttributeCount() {
		return attributes.size();
	}

	/**
	 * Gets a {@link Set} of attribute names.
	 *
	 * @return The set of names.
	 */
	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	/**
	 * Gets a {@link Set} of attribute map entries.
	 *
	 * @return The set of entries.
	 */
	public Set<Map.Entry<String, String>> getAttributes() {
		return attributes.entrySet();
	}

	/**
	 * Gets the first child with the specified name.
	 *
	 * @param name The name of the child.
	 * @return The {@link Optional} containing the found child {@link XmlNode}, if there is one with a matching name.
	 */
	public Optional<XmlNode> getChild(String name) {
		return children.stream().filter(child -> child.getName().equals(name)).findAny();
	}

	/**
	 * Gets the child count.
	 *
	 * @return The number of child {@link XmlNode}s.
	 */
	public int getChildCount() {
		return children.size();
	}

	/**
	 * Gets a {@link Collection} of child {@link XmlNode}s.
	 *
	 * @return The collection.
	 */
	public Collection<XmlNode> getChildren() {
		return Collections.unmodifiableCollection(children);
	}

	/**
	 * Gets the name of this node.
	 *
	 * @return The name of this node.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the value of this node.
	 *
	 * @return The value of this node, or {@code null} if it has no value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Checks if this node has a value.
	 *
	 * @return {@code true} if so, {@code false} if not.
	 */
	public boolean hasValue() {
		return value != null;
	}

	@Override
	public Iterator<XmlNode> iterator() {
		return children.iterator();
	}

	/**
	 * Removes all attributes.
	 */
	public void removeAllAttributes() {
		attributes.clear();
	}

	/**
	 * Removes all children.
	 */
	public void removeAllChildren() {
		children.clear();
	}

	/**
	 * Removes an attribute.
	 *
	 * @param name The name of the attribute.
	 */
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	/**
	 * Removes a child {@link XmlNode}.
	 *
	 * @param child The child to remove.
	 */
	public void removeChild(XmlNode child) {
		children.remove(child);
	}

	/**
	 * Removes the value of this node.
	 */
	public void removeValue() {
		value = null;
	}

	/**
	 * Adds an attribute. It will overwrite an existing attribute if it exists.
	 *
	 * @param name The name of the attribute.
	 * @param value The value of the attribute.
	 */
	public void setAttribute(String name, String value) {
		attributes.put(name, value);
	}

	/**
	 * Sets the name of this node.
	 *
	 * @param name The name of this node.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the value of this node.
	 *
	 * @param value The value of this node.
	 */
	public void setValue(String value) {
		this.value = value;
	}

}