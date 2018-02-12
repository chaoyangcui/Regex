package cn.dota.ui;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.intellij.images.fileTypes.ImageFileTypeManager;

public class UI extends DumbAwareAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        boolean image = file != null && ImageFileTypeManager.getInstance().isImage(file);
        // SetBackgroundImageDialog dialog =
        //         new SetBackgroundImageDialog(project, image ? file.getPath() : null);
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        Dialog dialog = new Dialog(project, true, selectedText);
        dialog.showAndGet();

    }
}
