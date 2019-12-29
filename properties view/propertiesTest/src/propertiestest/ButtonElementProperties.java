package propertiestest;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * 该类用于描述button的属性
 * IPropertySource接口：提供了item属性的描述（description）
 */
public class ButtonElementProperties implements IPropertySource {
    final protected ButtonElement element;

    //    属性
    protected static final String PROPERTY_FONT = "font";
    protected static final String PROPERTY_SIZE = "size";
    protected static final String PROPERTY_TEXT = "text";

    private final Object[][] PropertiesTable = {
            {PROPERTY_FONT, new FontPropertyDescriptor(PROPERTY_FONT, "Font")},
            {PROPERTY_SIZE, new PropertyDescriptor(PROPERTY_SIZE, "Size")},
            {PROPERTY_TEXT, new TextPropertyDescriptor(PROPERTY_TEXT, "Text")},
    };

    String strFont = "";
    Point ptSize = null;
    String strText = "";

    protected void firePropertyChanged(String propName, Object value) {
        Button ctl = element.getControl();

        if (ctl == null) {
            // the GUIView is probably hidden in this case
            return;
        }

        if (propName.equals(PROPERTY_FONT)) {
            /**
             Font oldfont = ctl.getFont();
             if (oldfont != null) {
             oldfont.dispose();
             }
             **/
            ctl.setFont(new Font(ctl.getDisplay(), new FontData((String) value)));
            return;
        }
        if (propName.equals(PROPERTY_TEXT)) {
            ctl.setText((String) value);
            return;
        }

    }

    protected void initProperties() {
        Button ctl = element.getControl();

        if (ctl == null) {
            // the GUIView is probably hidden in this case
            return;
        }

        strText = ctl.getText();
        /**
         Font font = ctl.getFont();
         if (font != null) {
         strFont = font.getFontData().toString();
         }
         **/
        ptSize = ctl.getSize();
    }


    /**
     * Creates a new ButtonElementProperties.
     *
     * @param element the element whose properties this instance represents
     */
    public ButtonElementProperties(ButtonElement element) {
        super();
        this.element = element;
        initProperties();
    }

    public Object getEditableValue() {
        return this;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        // Create the property vector.
        IPropertyDescriptor[] propertyDescriptors = new IPropertyDescriptor[PropertiesTable.length];

        for (int i = 0; i < PropertiesTable.length; i++) {
            // Add each property supported.

            PropertyDescriptor descriptor;

            descriptor = (PropertyDescriptor) PropertiesTable[i][1];
            propertyDescriptors[i] = descriptor;
            descriptor.setCategory("Basic");
        }

        // Return it.
        return propertyDescriptors;

    }

    public Object getPropertyValue(Object name) {
        if (name.equals(PROPERTY_FONT))
            return strFont;
        if (name.equals(PROPERTY_SIZE))
            return new SizePropertySource(element, ptSize);
        if (name.equals(PROPERTY_TEXT))
            return strText;

        return null;
    }

    public boolean isPropertySet(Object id) {
        return false;
    }

    public void resetPropertyValue(Object id) {
    }

    public void setPropertyValue(Object name, Object value) {
        firePropertyChanged((String) name, value);

        if (name.equals(PROPERTY_FONT)) {
            strFont = (String) value;
            return;
        }
        if (name.equals(PROPERTY_TEXT)) {
            strText = (String) value;
            return;
        }
        if (name.equals(PROPERTY_SIZE)) {
            SizePropertySource sizeProp = (SizePropertySource) value;
            ptSize = sizeProp.getValue();
        }
    }

    /**
     * Returns the mocha element.
     *
     * @return MochaElement
     */
    public ButtonElement getButtonElement() {
        return element;
    }
}
