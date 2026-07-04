package com.bloodmoonrises;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

public class BloodMoonRisesPanel extends PluginPanel
{
    private static final Color CURRENT_BG = new Color(90, 30, 30);
    private static final Color DONE_FG = new Color(110, 110, 110);

    private final BloodMoonRisesPlugin plugin;
    private final JLabel progressLabel = new JLabel();
    private final List<PhaseSection> sections = new ArrayList<>();

    public BloodMoonRisesPanel(BloodMoonRisesPlugin plugin)
    {
        this.plugin = plugin;

        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(ColorScheme.DARK_GRAY_COLOR);

        JLabel title = new JLabel("The Blood Moon Rises");
        title.setForeground(new Color(200, 40, 40));
        title.setFont(title.getFont().deriveFont(16f));
        progressLabel.setForeground(Color.LIGHT_GRAY);

        // Step cycling controls.
        JButton prev = new JButton("< Back");
        JButton next = new JButton("Done, next >");
        prev.setToolTipText("Cycle back a step");
        next.setToolTipText("Mark this step done and advance");
        prev.addActionListener(e -> plugin.previousStep());
        next.addActionListener(e -> plugin.nextStep());
        JPanel buttons = new JPanel(new GridLayout(1, 2, 6, 0));
        buttons.setBackground(ColorScheme.DARK_GRAY_COLOR);
        buttons.add(prev);
        buttons.add(next);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(ColorScheme.DARK_GRAY_COLOR);
        header.add(title);
        header.add(progressLabel);
        header.add(Box.createVerticalStrut(6));
        header.add(buttons);

        // Collapsible phase sections; steps are clickable to jump anywhere.
        JPanel sectionsPanel = new JPanel();
        sectionsPanel.setLayout(new BoxLayout(sectionsPanel, BoxLayout.Y_AXIS));
        sectionsPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);

        sectionsPanel.add(buildOverviewSection());
        sectionsPanel.add(Box.createVerticalStrut(4));

        Map<String, List<Integer>> phases = new LinkedHashMap<>();
        for (int i = 0; i < QuestData.STEPS.size(); i++)
        {
            phases.computeIfAbsent(QuestData.STEPS.get(i).getPhase(), k -> new ArrayList<>()).add(i);
        }
        for (Map.Entry<String, List<Integer>> entry : phases.entrySet())
        {
            PhaseSection section = new PhaseSection(entry.getKey(), entry.getValue());
            sections.add(section);
            sectionsPanel.add(section);
            sectionsPanel.add(Box.createVerticalStrut(4));
        }

        add(header, BorderLayout.NORTH);
        add(sectionsPanel, BorderLayout.CENTER);

        refresh();
    }

    public void refresh()
    {
        SwingUtilities.invokeLater(() ->
        {
            int current = plugin.getCurrentStep();
            progressLabel.setText("Step " + (current + 1) + " of " + QuestData.STEPS.size()
                + "  -  " + QuestData.STEPS.get(current).getPhase());
            for (PhaseSection section : sections)
            {
                section.refresh(current);
            }
            revalidate();
            repaint();
        });
    }

    // Collapsible quest overview shown above phase 1: requirements, items,
    // item-color legend, rewards and quest enemies.
    private JPanel buildOverviewSection()
    {
        JPanel section = new JPanel(new BorderLayout());
        section.setBackground(ColorScheme.DARK_GRAY_COLOR);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel content = new JLabel("<html><body style='width:150px'>"
            + "<b>Requirements</b><br>" + QuestData.REQUIREMENTS
            + "<br><br><b>Items overview</b><br>" + QuestData.ITEMS
            + "<br><br><b>Item colors</b><br>"
            + "<font color='#59d97c'>green = carried/equipped</font><br>"
            + "<font color='#ffffff'>white = in your bank</font><br>"
            + "<font color='#e57373'>red = not found</font><br>"
            + "<i>Open your bank once to sync its contents.</i>"
            + "<br><br><b>Rewards</b><br>" + QuestData.REWARDS
            + "<br><br><b>Enemies</b><br>" + QuestData.ENEMIES
            + "</body></html>");
        content.setForeground(Color.LIGHT_GRAY);
        content.setFont(net.runelite.client.ui.FontManager.getRunescapeSmallFont());

        JPanel contentWrap = new JPanel(new BorderLayout());
        contentWrap.setBackground(ColorScheme.DARK_GRAY_COLOR);
        contentWrap.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 0));
        contentWrap.add(content, BorderLayout.CENTER);
        contentWrap.setVisible(false);

        JButton headerButton = new JButton("▶ Overview");
        headerButton.setHorizontalAlignment(JButton.LEFT);
        headerButton.setFocusPainted(false);
        headerButton.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        headerButton.setForeground(Color.ORANGE);
        headerButton.setFont(net.runelite.client.ui.FontManager.getRunescapeSmallFont());
        headerButton.setMargin(new java.awt.Insets(4, 4, 4, 4));
        headerButton.addActionListener(e ->
        {
            boolean show = !contentWrap.isVisible();
            contentWrap.setVisible(show);
            headerButton.setText((show ? "▼ " : "▶ ") + "Overview");
            revalidate();
        });

        section.add(headerButton, BorderLayout.NORTH);
        section.add(contentWrap, BorderLayout.CENTER);
        return section;
    }

    private final class PhaseSection extends JPanel
    {
        private final List<Integer> stepIndices;
        private final JButton headerButton;
        private final JPanel content;
        private final List<StepRow> rows = new ArrayList<>();
        private boolean expanded;
        private boolean userToggled;

        PhaseSection(String phase, List<Integer> stepIndices)
        {
            this.stepIndices = stepIndices;

            setLayout(new BorderLayout());
            setBackground(ColorScheme.DARK_GRAY_COLOR);
            setAlignmentX(Component.LEFT_ALIGNMENT);

            content = new JPanel();

            headerButton = new JButton();
            headerButton.setHorizontalAlignment(JButton.LEFT);
            headerButton.setFocusPainted(false);
            headerButton.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            headerButton.setForeground(Color.ORANGE);
            headerButton.setFont(net.runelite.client.ui.FontManager.getRunescapeSmallFont());
            headerButton.setMargin(new java.awt.Insets(4, 4, 4, 4));
            headerButton.addActionListener(e ->
            {
                expanded = !expanded;
                userToggled = true;
                content.setVisible(expanded);
                updateHeaderText(phase);
                revalidate();
            });

            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setBackground(ColorScheme.DARK_GRAY_COLOR);
            content.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
            for (int idx : stepIndices)
            {
                StepRow row = new StepRow(idx);
                rows.add(row);
                content.add(row);
                content.add(Box.createVerticalStrut(3));
            }
            content.setVisible(false);
            updateHeaderText(phase);

            add(headerButton, BorderLayout.NORTH);
            add(content, BorderLayout.CENTER);
        }

        private void updateHeaderText(String phase)
        {
            headerButton.setText((expanded ? "▼ " : "▶ ") + phase + " (" + stepIndices.size() + ")");
        }

        void refresh(int currentStep)
        {
            boolean containsCurrent = stepIndices.contains(currentStep);
            // Auto-expand the active phase (and collapse others) unless the user
            // has manually toggled this section.
            if (!userToggled)
            {
                expanded = containsCurrent;
                content.setVisible(expanded);
                updateHeaderText(QuestData.STEPS.get(stepIndices.get(0)).getPhase());
            }
            if (containsCurrent)
            {
                userToggled = false; // re-enable auto behavior once progress moves on
            }
            for (StepRow row : rows)
            {
                row.refresh(currentStep);
            }
        }
    }

    private final class StepRow extends JPanel
    {
        private final int index;
        private final JLabel label = new JLabel();

        StepRow(int index)
        {
            this.index = index;
            setLayout(new BorderLayout());
            setBackground(ColorScheme.DARKER_GRAY_COLOR);
            setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
            setAlignmentX(Component.LEFT_ALIGNMENT);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setToolTipText("Click to jump to this step");

            label.setText(buildHtml());
            label.setFont(net.runelite.client.ui.FontManager.getRunescapeSmallFont());
            add(label, BorderLayout.CENTER);

            MouseAdapter click = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    plugin.setStep(index);
                }
            };
            addMouseListener(click);
            label.addMouseListener(click);
        }

        private String buildHtml()
        {
            QuestStep step = QuestData.STEPS.get(index);
            StringBuilder html = new StringBuilder("<html><body style='width:150px'>").append(step.getText());
            if (step.hasItems())
            {
                html.append("<br><font color='#8cc8ff'><i>").append(step.getItems()).append("</i></font>");
            }
            if (step.hasTrackedItems() && plugin.getConfig().trackItems())
            {
                html.append("<br>");
                boolean first = true;
                for (String item : step.getTrackedItems())
                {
                    if (!first)
                    {
                        html.append("<font color='#888888'> · </font>");
                    }
                    first = false;
                    html.append("<font color='").append(itemColor(item)).append("'>")
                        .append(item).append("</font>");
                }
            }
            html.append("</body></html>");
            return html.toString();
        }

        private String itemColor(String item)
        {
            switch (plugin.getItemState(item))
            {
                case CARRIED:
                    return "#59d97c";
                case BANKED:
                    return "#ffffff";
                default:
                    return "#e57373";
            }
        }

        void refresh(int currentStep)
        {
            label.setText(buildHtml());
            if (index == currentStep)
            {
                setBackground(CURRENT_BG);
                label.setForeground(Color.WHITE);
            }
            else if (index < currentStep)
            {
                setBackground(ColorScheme.DARKER_GRAY_COLOR);
                label.setForeground(DONE_FG);
            }
            else
            {
                setBackground(ColorScheme.DARKER_GRAY_COLOR);
                label.setForeground(Color.LIGHT_GRAY);
            }
        }
    }
}
