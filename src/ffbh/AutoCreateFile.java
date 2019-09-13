package ffbh;

import com.intellij.ide.ui.EditorOptionsTopHitProvider;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.junit.Test;

import javax.annotation.Untainted;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AutoCreateFile extends AnAction  {
    private static final String Coding = "Coding";
    private static final String CodeAccepted = "CodeAccepted";
    private static final String Later = "Later";
    private static final String Template = "Template";
    private static final String InOutput = "InOutput";
    private static final String CmakeLists = "CMakeLists.txt";
    private static String sepa = java.io.File.separator;

    public void Debug(Project project, String message){
        Messages.showMessageDialog(
                project,
                message,
                "debug",
                Messages.getInformationIcon());
    }

    void Init(String base){
        File file = null;

        file = new File(base + sepa + Coding);
        if(!file.exists())
            file.mkdir();

        file = new File(base + sepa + CodeAccepted);
        if(!file.exists())
            file.mkdir();

        file = new File(base + sepa + Later);
        if(!file.exists())
            file.mkdir();

        file = new File(base + sepa + Template);
        if(!file.exists())
            file.mkdir();

    }


    @Override
    public void actionPerformed(AnActionEvent event) {
        // TODO: insert action logic here
        Project project = event.getProject();
        String file_name =
                Messages.showInputDialog(project,
                        "C++ File",
                        "Auto Create C++ File", Messages.getInformationIcon());
        if(file_name == null){
            return;
        }
        file_name = file_name.trim();
        if(file_name.length() == 0){
            return;
        }
        String project_base_path = project.getBasePath();

        //mkdirs
        //Init(project_base_path);

        if(!file_name.endsWith(".cpp")){
            file_name += ".cpp";
        }

        String coding_path = project_base_path + sepa + Coding;
        String later_path = project_base_path + sepa + Later;
        String template_path = project_base_path + sepa + Template;

        try {
            Utils.moveAll(coding_path, later_path);
            Utils.copy(template_path, coding_path, "source.cpp", file_name);
            Utils.cmakeLists(project_base_path + sepa + CmakeLists ,project.getName(),coding_path + sepa +file_name);
        }
        catch (Exception e){
            Debug(project,e.toString());
            e.printStackTrace();
        }


    }
}
