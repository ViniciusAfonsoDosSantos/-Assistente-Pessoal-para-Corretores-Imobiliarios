/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
/**
 *
 * @author Vinicius
 */
public class GenericComboModel<E> extends AbstractListModel<E> implements ComboBoxModel<E>{

    private List<E> itemList;
    private E selection;

    public GenericComboModel(List<E> list) {
        this.itemList = list;
    }

    @Override
    public int getSize() {
        return this.itemList.size();
    }

    @Override
    public E getElementAt(int index) {
        return this.itemList.get(index);
    }

    @Override
    public Object getSelectedItem() {
        return this.selection;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selection = (E) anItem;
    }
}
