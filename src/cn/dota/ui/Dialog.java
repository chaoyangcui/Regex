package cn.dota.ui;

import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.update.UiNotifyConnector;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.openapi.wm.impl.IdeBackgroundUtil.createTemporaryBackgroundTransform;

public class Dialog extends DialogWrapper {

    private String selectedText;

    private JPanel rootPanel = new JPanel(new GridLayout(4, 1));


    protected Dialog(@Nullable Project project, boolean canBeParent, final String selectedText) {
        super(project, canBeParent);
        setTitle("Dialog Test");
        this.selectedText = selectedText;

        setupComponents();

        init();
        pack();
    }

    protected void setupComponents() {
        ClockLabel dateLable = new ClockLabel("date");
        ClockLabel timeLable = new ClockLabel("time");
        ClockLabel dayLable = new ClockLabel("day");

        JFrame.setDefaultLookAndFeelDecorated(true);
        // JFrame f = new JFrame("Digital Clock");
        // JInternalFrame f = new JInternalFrame("Digital Clock");
        JInternalFrame f = new JInternalFrame();
        f.setSize(300,150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(3, 1));

        f.add(dateLable);
        f.add(timeLable);
        f.add(dayLable);

        f.getContentPane().setBackground(Color.black);

        f.setVisible(true);

        // rootPanel.add(f);
        rootPanel.add(dateLable);
        rootPanel.add(timeLable);
        rootPanel.add(dayLable);


        rootPanel.add(new JTextArea(this.selectedText, 10, 15));
        rootPanel.setPreferredSize(new Dimension(300, 150));
        rootPanel.setVisible(true);
        rootPanel.setBackground(Color.DARK_GRAY);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return rootPanel;
    }

    class ClockLabel extends JLabel implements ActionListener {
        String type;
        SimpleDateFormat sdf;

        public ClockLabel(String type) {
            this.type = type;
            setForeground(Color.green);

            switch (type) {
                case "date" : sdf = new SimpleDateFormat("  MMMM dd yyyy");
                    setFont(new Font("sans-serif", Font.PLAIN, 12));
                    setHorizontalAlignment(SwingConstants.LEFT);
                    break;
                case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
                    setFont(new Font("sans-serif", Font.PLAIN, 40));
                    setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case "day"  : sdf = new SimpleDateFormat("EEEE  ");
                    setFont(new Font("sans-serif", Font.PLAIN, 16));
                    setHorizontalAlignment(SwingConstants.RIGHT);
                    break;
                default     : sdf = new SimpleDateFormat();
                    break;
            }

            Timer t = new Timer(1000, this);
            t.start();
        }

        public void actionPerformed(ActionEvent ae) {
            Date d = new Date();
            setText(sdf.format(d));
        }
    }
}
