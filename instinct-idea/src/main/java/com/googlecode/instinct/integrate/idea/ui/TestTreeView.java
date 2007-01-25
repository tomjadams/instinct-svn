package com.googlecode.instinct.integrate.idea.ui;

import javax.swing.*;
import javax.swing.plaf.TreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.intellij.execution.junit2.ui.PoolOfTestIcons;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.ui.PopupHandler;
import com.intellij.ui.TreeSpeedSearch;
import com.intellij.ui.TreeToolTipHandler;
import com.intellij.util.EditSourceOnDoubleClickHandler;
import com.intellij.util.containers.Convertor;
import com.intellij.util.ui.Tree;
import com.intellij.util.ui.tree.TreeUtil;
import com.googlecode.instinct.integrate.idea.model.TestNGConsoleProperties;
import com.googlecode.instinct.integrate.idea.model.TestNodeDescriptor;
import com.googlecode.instinct.integrate.idea.model.TestProxy;

/**
 * @author Hani Suleiman Date: Aug 1, 2005 Time: 11:33:12 AM
 */
public class TestTreeView extends Tree implements DataProvider
{
    private Project project;

    public void attachToModel(Project project, Object root, TestNGConsoleProperties consoleProperties) {
        this.project = project;
        setModel(new DefaultTreeModel(new DefaultMutableTreeNode(root)));
        getSelectionModel().setSelectionMode(1);
        registerListeners();
        setCellRenderer(new ResultTreeRenderer(consoleProperties));
    }

    public void dispose() {
        setModel(null);
        project = null;
    }

    @Override
    public void setUI(TreeUI treeui) {
        super.setUI(treeui);
        int fontHeight = getFontMetrics(getFont()).getHeight();
        int iconHeight = PoolOfTestIcons.PASSED_ICON.getIconHeight();
        setRowHeight(Math.max(fontHeight, iconHeight) + 2);
        setLargeModel(true);
    }

    public Object getData(String dataId) {
        if (dataId.equals(DataConstants.PROJECT) || dataId.equals(DataConstants.PROJECT_CONTEXT)) return project;
        TreePath treepath = getSelectionPath();
        if (treepath == null)
            return null;
        TestProxy proxy = getObject(treepath);
        if (proxy == null)
            return null;
        if (dataId.equals(DataConstants.PSI_ELEMENT)) return proxy.getPsiElement();
        if (dataId.equals(DataConstants.PSI_FILE) && proxy.getPsiElement() != null)
            return proxy.getPsiElement().getContainingFile();
        if (dataId.equals(DataConstants.VIRTUAL_FILE) && proxy.getPsiElement() != null && proxy.getPsiElement().getContainingFile() != null)
            return proxy.getPsiElement().getContainingFile().getVirtualFile();
        if (dataId.equals(DataConstants.NAVIGATABLE) || dataId.equals(DataConstants.NAVIGATABLE_ARRAY)) {
            PsiElement element = proxy.getPsiElement();
            Navigatable item = null;
            if (element instanceof PsiClass) {
                item = new OpenFileDescriptor(project, element.getContainingFile().getVirtualFile());
            } else if (element instanceof PsiMethod) {
                item = new OpenFileDescriptor(project, element.getContainingFile().getVirtualFile(),
                                              ((PsiMethod) element).getBody().getLBrace().getTextRange().getEndOffset());
            }
            if (item == null) return null;
            if (dataId.equals(DataConstants.NAVIGATABLE)) {
                return item;
            } else {
                return new Navigatable[] {item};
            }
        }
        //System.out.println("getData " + dataId + " on " + proxy.getName());
        return null;
    }

    private void registerListeners() {
        EditSourceOnDoubleClickHandler.install(this);
        new TreeSpeedSearch(this, new Convertor()
        {

            public String convert(TreePath path) {
                TestProxy proxy = getObject(path);
                if (proxy == null)
                    return null;
                else
                    return proxy.getName();
            }

            public Object convert(Object obj) {
                return convert((TreePath) obj);
            }
        });
        TreeToolTipHandler.install(this);
        TreeUtil.installActions(this);
        installTestTreePopupHandler(this);
    }

    public TestProxy getSelectedTest() {
        TreePath path = getSelectionPath();
        if (path != null) {
            return getObject(path);
        }
        return null;
    }

    public static TestProxy getObject(TreePath treepath) {
        Object lastComponent = treepath.getLastPathComponent();
        return getObject((DefaultMutableTreeNode) lastComponent);
    }

    public static TestProxy getObject(DefaultMutableTreeNode node) {
        if (!(node.getUserObject() instanceof TestNodeDescriptor)) return null;
        return ((TestNodeDescriptor) node.getUserObject()).getElement();
    }

    @Override
    public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        TestProxy proxy = getObject((DefaultMutableTreeNode) value);
        if (proxy != null) {
            return proxy.getName();
        }
        return "";
    }

    private static void installTestTreePopupHandler(JComponent component) {
        PopupHandler.installPopupHandler(component, "TestTreePopupMenu", "TestTreeViewPopup");
    }
}