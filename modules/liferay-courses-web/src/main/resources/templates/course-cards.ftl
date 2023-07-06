<div class="container lfr-search-container-wrapper">
    <div class="row">
        <#list entries as entry>
            <#assign
                assetRenderer = entry.getAssetRenderer()
                entryTitle = assetRenderer.getTitle(locale)
                entryDescription = assetRenderer.getSummary(renderRequest, renderResponse)
                viewInContext = !stringUtil.equals(assetLinkBehavior, "showFullContent")
                viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, assetRenderer, entry, viewInContext)
            />
            <div class="col-md-4">
                <div class="card">
                    <div class="card-item">
                        <div class="card-body">
                            <div class="card-row">
                                <div class="autofit-col autofit-col-expand">
                                    <section class="autofit-section">
                                        <h3 class="card-title">${entryTitle}</h3>
                                        <p class="card-subtitle">${entryDescription}</p>
                                        <p class="text-right mt-2">
                                            <a href="${viewURL}">
                                                <@liferay.language key="read-more" /> &raquo;
                                            </a>
                                        </p>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>