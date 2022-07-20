package com.gui;

import javax.swing.*;
import java.awt.*;

public class PersonView extends JPanel implements ListCellRenderer {

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
