<clay:navigation-bar navigationItems='<%=
    new JSPNavigationItemList(pageContext) {
        {
            add(
                navigationItem -> {
                    navigationItem.setActive("my".equals(tab));
                    navigationItem.setHref(renderResponse.createRenderURL(), "tab", "my");
                    navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "tab-my-courses"));
                });
            add(
                navigationItem -> {
                    navigationItem.setActive("all".equals(tab));
                    navigationItem.setHref(renderResponse.createRenderURL(), "tab", "all");
                    navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "tab-all-courses"));
                });
        }
    }
 %>'
/>