package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.VikingNoId;

@Service
public class VikingService {
    private final java.util.concurrent.atomic.AtomicInteger idCounter = new java.util.concurrent.atomic.AtomicInteger(0);
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
        Viking viking = vikingFactory.createRandomViking(idCounter.incrementAndGet());

        vikings.add(viking);
        return viking;
    }
    public Viking createCustomViking(Viking viking) {
    Viking withId = new Viking(
            idCounter.incrementAndGet(),
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
    
    public void deleteViking(int id) {
        for (Viking v:vikings) {
            if (v.id()==id) {
                vikings.remove(v);
                return;
            }
        }
    }
    
    public Viking updateViking(long id, VikingNoId updated) {
         for (int i = 0; i < vikings.size(); i++) {
            Viking v = vikings.get(i);
            if (v.id()==id) {
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
                return updatedViking;
            }
        }
        return null;
    }
    
    public List<Viking> generateVikings(int count) {
        return Stream.generate(() -> vikingFactory.createRandomViking(idCounter.incrementAndGet())).limit(count).peek(vikings::add).collect(Collectors.toList());
    }
}
