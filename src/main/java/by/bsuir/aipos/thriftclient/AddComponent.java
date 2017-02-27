package by.bsuir.aipos.thriftclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddComponent {
    /**
     * Path to folder with images
     */
    private static final String IMG_PATCH = "img/";
    /**
     * Internal paddings of a component
     */
    private static final Insets insets = new Insets(0, 0, 0, 0);

    /**
     * Add component to container with params
     *
     * @param container container in witch component will be added
     * @param component component that is added to the container
     * @param gridx x coordinate
     * @param gridy y coordinate
     * @param gridwidth width of grid
     * @param gridheight height of grid
     */
    public static void add(Container container, Component component,
                           int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
        container.add(component, gbc);
    }

    /**
     * Set image and action listener to given button
     *
     * @param button - customized button
     * @param imgString - set image
     * @param action - set action listener
     *
     * @return customized button
     */
    public static JButton makeButton(JButton button, String imgString, ActionListener action){
        button.addActionListener(action);
        String patch = IMG_PATCH + imgString;
        ImageIcon img = new ImageIcon(patch);
        button.setIcon(img);
        return button;
    }
}