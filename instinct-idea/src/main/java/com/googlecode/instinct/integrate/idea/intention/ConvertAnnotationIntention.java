package com.googlecode.instinct.integrate.idea.intention;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.googlecode.instinct.integrate.idea.util.Intentions;
import com.googlecode.instinct.integrate.idea.util.TestNGUtil;

/**
 * @author Hani Suleiman Date: Aug 3, 2005 Time: 4:17:59 PM
 */
public class ConvertAnnotationIntention extends AbstractProjectIntention
{
    public ConvertAnnotationIntention(Project project) {
        super(project);
    }

    public static ConvertAnnotationIntention getInstance(Project project) {
        return project.getComponent(ConvertAnnotationIntention.class);
    }

    public String getComponentName() {
        return "ConvertAnnotation";
    }

    public String getText() {
        return "Convert TestNG annotations to javadocs";
    }

    public boolean isAvailable(Project project, Editor editor, PsiFile file) {
        if (!file.isWritable()) return false;
        if (!(file instanceof PsiJavaFile)) return false;
        AnnotationsVisitor visitor = new AnnotationsVisitor();
        file.accept(visitor);
        return visitor.hasAnnotations;
    }

    public void invoke(Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        for (PsiAnnotation annotation : TestNGUtil.getTestNGAnnotations(file)) {
            PsiElement parent = annotation.getParent();
            if (parent instanceof PsiModifierList) {
                Intentions.convertAnnotationToJavadoc((PsiModifierListOwner) parent.getParent());
            }
        }
        PsiJavaFile javaFile = (PsiJavaFile) file;
        PsiImportStatement annotationsImport = javaFile.getImportList().findOnDemandImportStatement("org.testng.annotations");
        if (annotationsImport != null) {
            annotationsImport.delete();
        }
    }

    public boolean startInWriteAction() {
        return true;
    }

}
