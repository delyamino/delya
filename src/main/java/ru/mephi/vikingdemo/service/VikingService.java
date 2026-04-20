package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.VikingNoId;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;
    @Autowired
    public VikingService(VikingFactory vikingFactory) {
        this.vikingFactory = vikingFactory;
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public Viking createRandomViking() {
        

        Viking viking = vikingFactory.createRandomViking();

        vikings.add(viking);
        return viking;
    }
    public Viking createCustomViking(Viking viking) {
    Viking withId = new Viking(
            Math.abs(new Random().nextLong()),
            viking.name(),
            viking.age(),
            viking.heightCm(),
            viking.hairColor(),
            viking.beardStyle(),
            viking.equipment()
    );
    vikings.add(withId);
    return withId;
}
    
    public void deleteViking(long id) {
        for (Viking v:vikings) {
            if (v.id().equals(id)) {
                vikings.remove(v);
                return;
            }
        }
    }
    
    public Viking updateViking(long id, VikingNoId updated) {
         for (int i = 0; i < vikings.size(); i++) {
            Viking v = vikings.get(i);
            if (v.id().equals(id)) {
                vikings.remove(v);
                String name;
                Integer age;
                Integer heightCm;
                HairColor hairColor;
                BeardStyle beardStyle;
                List<EquipmentItem> equipment;
                 
                if (updated.name() != null) {
                    name=updated.name();
                }
                else {
                    name = v.name();
                }
                
                if (updated.age() != null) {
                    age=updated.age();
                }
                else {
                    age = v.age();
                }
                
                if (updated.heightCm() != null) {
                    heightCm=updated.heightCm();
                }
                else {
                    heightCm = v.heightCm();
                }
                
                if (updated.hairColor() != null) {
                    hairColor=updated.hairColor();
                }
                else {
                    hairColor = v.hairColor();
                }
                
                if (updated.beardStyle() != null) {
                    beardStyle=updated.beardStyle();
                }
                else {
                    beardStyle = v.beardStyle();
                }
                
                if (updated.equipment() != null) {
                    equipment=updated.equipment();
                }
                else {
                    equipment = v.equipment();
                }
                Viking updatedViking = new Viking(v.id(), name, age, heightCm, hairColor, beardStyle, equipment);
                vikings.set(i, updatedViking);
                vikings.add(updatedViking);
                return updatedViking;
            }
        }
        return null;
    }
}
