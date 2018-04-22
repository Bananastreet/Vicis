package rs.emulate.editor.workspace.resource.bundles.legacy.config

import javafx.collections.FXCollections
import javafx.collections.ObservableMap
import rs.emulate.editor.workspace.resource.Resource
import rs.emulate.editor.workspace.resource.ResourceBundle
import rs.emulate.editor.workspace.resource.ResourceId
import rs.emulate.legacy.archive.Archive
import rs.emulate.legacy.config.ConfigDecoder
import rs.emulate.legacy.config.DefinitionSupplier
import rs.emulate.legacy.config.MutableConfigDefinition
import rs.emulate.legacy.config.SerializableProperty

/**
 * A [Resource] from the config archive.
 */
class ConfigResource<T : MutableConfigDefinition>(override val id: ResourceId, definition: T) : Resource {

    val properties: ObservableMap<SerializableProperty<*>, *> = FXCollections.observableMap(
        definition.serializableProperties().associateBy({ it.value }, { it.value.value })
    )

    override fun equals(other: Any?): Boolean { /* TODO should we only care about matching id? */
        return other is ConfigResource<*> && id == other.id && properties == other.properties
    }

    override fun hashCode(): Int {
        return 31 * properties.hashCode() + id.hashCode()
    }

    override fun toString(): String {
        return "ConfigResource($id) { $properties }"
    }

}

abstract class ConfigResourceBundle<T : MutableConfigDefinition>(
    config: Archive,
    supplier: DefinitionSupplier<T>,
    toResourceId: T.() -> ResourceId
) : ResourceBundle {

    protected val definitions: Map<ResourceId, T> = ConfigDecoder(config, supplier).decode()
        .associateBy(toResourceId)

    override fun load(id: ResourceId): Resource = ConfigResource(id, definitions[id]!!)

}
