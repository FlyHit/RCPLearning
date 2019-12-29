package propertiestest;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.setEditorAreaVisible(false);
        layout.addView("org.eclipse.ui.views.PropertySheet", IPageLayout.BOTTOM, 0.5f, layout.getEditorArea());
    }
}
