package propertiestest;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * 当properties view得知workbench选择发生改变时，将会查询被选择的项目寻找property source
 * 提供property source的方式有两种：
 * 1.被选择的项目实现IPropertySource接口，并提供属性
 * 2.被选择的项目实现IAdaptable接口，当getAdapter被调用时返回一个IPropertySource对象
 * 该自定义项目（item）用于存储button的元素
 */
public class ButtonElement implements IAdaptable, IWorkbenchAdapter {
    private String headingName;
    private Button ctl;


    public ButtonElement(Button initBtn, String heading) {
        this.headingName = heading;
        this.ctl = initBtn;
    }

    /* (non-Javadoc)
     * Method declared on IAdaptable
     */
    public Object getAdapter(Class adapter) {
//        if (adapter == IWorkbenchAdapter.class)
//            return this;
        if (adapter == IPropertySource.class)
            return new ButtonElementProperties(this);
        return null;
    }

    @Override
    public Object[] getChildren(Object o) {
        return new Object[0];
    }

    @Override
    public ImageDescriptor getImageDescriptor(Object object) {
        return null;
    }

    /* (non-Javadoc)
     * Method declared on IWorkbenchAdapter
     */
    public String getLabel(Object o) {
        return headingName;
    }

    @Override
    public Object getParent(Object o) {
        return null;
    }

    public Button getControl() {
        return ctl;
    }
}
