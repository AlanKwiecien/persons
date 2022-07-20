package com.gui;

import javax.swing.*;
import java.awt.*;

public class Persons {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyFrame());

    }

}

enum Size {
    XS, S, M, L, XL;
}

class Person {

    private String name;
    private Size size;
    private int height;
    private int weight;

    public Person(String name, Size size, int height, int weight) {
        this.name = name;
        this.size = size;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (h=" + height + ", w=" + weight + ", size=" + size+")";
    }

}

class MyFrame extends JFrame {

    public MyFrame() {
        this.setLayout(new GridLayout(2, 1));

        //panel wyswietlajacy
        DefaultListModel<Person> listModel = new DefaultListModel();

        //wartości defaultowe
        listModel.addElement(new Person("Mary", Size.S, 170, 56));
        listModel.addElement(new Person("Jane", Size.XL, 160, 78));
        listModel.addElement(new Person("Susan", Size.XS, 168, 50));
        listModel.addElement(new Person("Rachel", Size.L, 160, 54));
        JList<Person> persons = new JList<>(listModel);

        persons.setCellRenderer(new PersonView());
        this.add(new JScrollPane(persons, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));

        JPanel bottomHalf = new JPanel();
        bottomHalf.setLayout(new GridLayout(3, 1));

        //slider wzrostu
        JSlider height = new JSlider(100, 200);
        height.setMajorTickSpacing(10);
        height.setMinorTickSpacing(1);
        height.setPaintTicks(true);
        height.setPaintLabels(true);
        height.setBorder(BorderFactory.createTitledBorder("Height [cm]"));
        bottomHalf.add(height);

        //slider wagi
        JSlider weight = new JSlider(40, 120);
        weight.setMajorTickSpacing(10);
        weight.setMinorTickSpacing(2);
        weight.setPaintTicks(true);
        weight.setPaintLabels(true);
        weight.setBorder(BorderFactory.createTitledBorder("Weight [kg]"));
        bottomHalf.add(weight);

        //najnizszy komponent z polami i przyciskami
        JPanel bottomComponents = new JPanel();
        bottomComponents.setLayout(new BorderLayout());

        JPanel leftBottomComponents = new JPanel();

        JPanel nameFiled = new JPanel();
        JLabel nameLabel = new JLabel("name: ");
        JTextField name = new JTextField(15);
        nameFiled.add(nameLabel);
        nameFiled.add(name);
        leftBottomComponents.add(nameFiled);


        JPanel sizeField = new JPanel();
        JLabel sizeLabel = new JLabel("size: ");
        JComboBox<Size> size = new JComboBox<>(new Size[]{Size.XS, Size.S, Size.M, Size.L, Size.XL});
        sizeField.add(sizeLabel);
        sizeField.add(size);
        leftBottomComponents.add(sizeField);

        JButton addPerson = new JButton("Add Person");
        addPerson.addActionListener(e -> {
            listModel.addElement(new Person(name.getText(), (Size) size.getSelectedItem(), height.getValue(), weight.getValue()));
        });

        leftBottomComponents.add(addPerson);

        bottomComponents.add(leftBottomComponents, BorderLayout.LINE_START);

        JPanel exitButton = new JPanel();

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            System.exit(0);
        });

        exitButton.add(exit);


        bottomComponents.add(exitButton, BorderLayout.LINE_END);

        bottomHalf.add(bottomComponents);

        this.add(bottomHalf);

        this.setTitle("PERSONS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.setVisible(true);
    }

}

class PersonView extends JPanel implements ListCellRenderer {

    private JLabel lPerson;

    public PersonView(){
        this.setLayout(new BorderLayout());
        this.lPerson = new JLabel("", SwingConstants.LEFT);
        this.add(lPerson, BorderLayout.LINE_START);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Person toShow = (Person)value;
        lPerson.setText(toShow.toString());
        this.setBackground(toShow.getSize() == Size.XS ? Color.GREEN : toShow.getSize() == Size.XL ? Color.RED : Color.WHITE);
        return this;
    }
}


