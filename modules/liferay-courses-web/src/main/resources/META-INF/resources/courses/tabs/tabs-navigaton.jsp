<clay:navigation-bar navigationItems='<%=
    new JSPNavigationItemList(pageContext) {
        {
            add(
                navigationItem -> {
                    navigationItem.setActive("my".equals(tab));
                    navigationItem.setHref(renderResponse.createRenderURL());
                    navigationItem.setLabel("My Courses");
                });
            add(
                navigationItem -> {
                    navigationItem.setActive("all".equals(tab));
                    navigationItem.setHref(renderResponse.createRenderURL(), "tab", "all");
                    navigationItem.setLabel("All Courses");
                });
        }
    }
 %>'
/>