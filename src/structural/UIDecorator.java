/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structural;

import javax.swing.JButton;

/**
 *
 * @author Remas
 */
/* Abstract base decorator for UI components it defines the common structure.*/
public abstract class UIDecorator {

    protected final JButton button;

    public UIDecorator(JButton button) {
        this.button = button;
    }

    public abstract void apply();
}
