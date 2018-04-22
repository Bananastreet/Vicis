package rs.emulate.editor.workspace.resource.bundles.legacy

import rs.emulate.editor.workspace.resource.ResourceBundle
import rs.emulate.editor.workspace.resource.ResourceBundleLoader
import rs.emulate.editor.workspace.resource.bundles.legacy.config.ConfigResourceBundleFactory
import rs.emulate.legacy.AccessMode
import rs.emulate.legacy.IndexedFileSystem
import java.nio.file.Path

class LegacyResourceBundleLoader(private val path: Path) : ResourceBundleLoader {

    override fun load(out: MutableList<ResourceBundle<*>>) {
        val fs = IndexedFileSystem(path, AccessMode.READ)

        out += ConfigResourceBundleFactory(fs).bundles()
        out += ModelResourceBundle(fs)
    }

}