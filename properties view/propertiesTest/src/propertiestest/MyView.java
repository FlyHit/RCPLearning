package propertiestest;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

public class MyView extends ViewPart {
    ListViewer listViewer;
    private Group group;
    private Button button1;
    private Button button2;

    public MyView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        listViewer = new ListViewer(parent);

        group = new Group(parent, SWT.NONE);
        group.setText("Preview");
        RowLayout rowLayout = new RowLayout();
        group.setLayout(rowLayout);
        button1 = new Button(group, SWT.PUSH);
        button1.setText("Hello");
        button1.setLayoutData(new RowData(60, 60));
        button2 = new Button(group, SWT.PUSH);
        button2.setText("bigger");
        button2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                button1.setText("changed");
                ISelection selection = listViewer.getSelection();
                listViewer.setSelection(null);
                listViewer.setSelection(selection);
            }
        });

//        AdaptableList:可修改的IAdaptable对象的列表
        AdaptableList ctlList = new AdaptableList();
        ButtonElement btnEl = new ButtonElement(button1, "Button");
        ctlList.add(btnEl);

        listViewer.setContentProvider(new BaseWorkbenchContentProvider());
        listViewer.setLabelProvider(new WorkbenchLabelProvider());
        listViewer.setInput(ctlList);
        getSite().setSelectionProvider(listViewer);
    }

    @Override
    public void setFocus() {
        listViewer.getControl().setFocus();
    }

    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);
        setPartName("my view");
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return super.getAdapter(adapter);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
