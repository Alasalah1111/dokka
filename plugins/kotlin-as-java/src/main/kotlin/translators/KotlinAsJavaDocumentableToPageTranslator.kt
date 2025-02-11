package org.jetbrains.dokka.kotlinAsJava.translators

import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.base.signatures.SignatureProvider
import org.jetbrains.dokka.base.transformers.pages.comments.CommentsToContentConverter
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.transformers.documentation.DocumentableToPageTranslator
import org.jetbrains.dokka.model.DModule
import org.jetbrains.dokka.pages.ModulePageNode
import org.jetbrains.dokka.plugability.*
import org.jetbrains.dokka.utilities.DokkaLogger

class KotlinAsJavaDocumentableToPageTranslator(context: DokkaContext) : DocumentableToPageTranslator {
    private val configuration = configuration<DokkaBase, DokkaBaseConfiguration>(context)
    private val commentsToContentConverter = context.plugin<DokkaBase>().querySingle { commentsToContentConverter }
    private val signatureProvider = context.plugin<DokkaBase>().querySingle { signatureProvider }
    private val customTagContentProviders = context.plugin<DokkaBase>().query { customTagContentProvider }
    private val logger: DokkaLogger = context.logger

    override fun invoke(module: DModule): ModulePageNode =
        KotlinAsJavaPageCreator(
            configuration,
            commentsToContentConverter,
            signatureProvider,
            logger,
            customTagContentProviders
        ).pageForModule(module)
}