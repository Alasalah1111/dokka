package org.jetbrains.dokka.transformers.documentation

import org.jetbrains.dokka.model.DModule
import org.jetbrains.dokka.plugability.DokkaContext

fun interface DocumentableMerger {
    operator fun invoke(modules: Collection<DModule>): DModule?
}