/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.service.VikingFindOnParametersService;
import ru.mephi.vikingdemo.model.Viking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author panda
 */

public class VikingByParametersFrame extends JFrame {

    private VikingFindOnParametersService fopService;
    private DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Age", "Height", "Hair", "Beard", "Equipment"}, 0);

    public VikingByParametersFrame(VikingFindOnParametersService fopService) {
        this.fopService = fopService;

        setTitle("Viking Analytics");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton tallBtn = new JButton("Random taller that 180");
        JButton legendaryBtn = new JButton("With legendary equipment");
        JButton redHairBtn = new JButton("Red heads by age");

        tallBtn.addActionListener(e -> {
            clearTable();
            fopService.getRandomTallViking().ifPresent(v -> addRow(v));
        });

        legendaryBtn.addActionListener(e -> {
            clearTable();
            for (Viking v : fopService.getLegendaryVikings()) {
                addRow(v);
            }
        });

        redHairBtn.addActionListener(e -> {
            clearTable();
            for (Viking v : fopService.getRedHairSortedByAge()) {
                addRow(v);
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(tallBtn);
        btnPanel.add(legendaryBtn);
        btnPanel.add(redHairBtn);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
        add(btnPanel, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);
    }

    private void addRow(Viking v) {
        tableModel.addRow(new Object[]{
            v.id(), v.name(), v.age(), v.heightCm(),
            v.hairColor(), v.beardStyle(),
            v.equipment().stream().map(e -> e.name() + " [" + e.quality() + "]").reduce((a, b) -> a + ", " + b).orElse("")
        });
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }
}
