/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.gui.VikingDesktopFrame;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.model.VikingNoId;
import ru.mephi.vikingdemo.service.VikingService;

/**
 *
 * @author test2023
 */
@Component
public class VikingListener {
    private VikingService service;
    private VikingDesktopFrame gui;

    @Autowired
    public VikingListener(VikingService service) {
        this.service = service;
    }
    
    public void setGui(VikingDesktopFrame gui){
        this.gui = gui;
    }

    void testAdd() {
        gui.addNewViking(service.createRandomViking());
    }
    
    void customAdd(Viking viking) {
        gui.addNewViking(service.createCustomViking(viking));
    }
    
    void deleteViking(int id) {
        gui.removeViking(id);
        service.deleteViking(id);
    }
    
    void updateViking(long id, VikingNoId updated) {
        Viking result = service.updateViking(id, updated);
        gui.updateViking(result);
    }
    
    void testAdd(Viking viking) {
        gui.addNewViking(viking);
    }
}
