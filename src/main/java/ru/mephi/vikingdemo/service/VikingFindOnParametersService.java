/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

/**
 *
 * @author panda
 */
@Service
public class VikingFindOnParametersService {

    private final VikingService vikingService;

    
    public VikingFindOnParametersService(VikingService vikingService) {
        this.vikingService = vikingService;
    }

    public long countOlderThan(int age) {
        return vikingService.findAll().stream().filter(v -> v.age() > age).count();
    }

    public long countYoungerThan(int age) {
        return vikingService.findAll().stream().filter(v -> v.age() < age).count();
    }

    public long countInAgeRange(int from, int to) {
        return vikingService.findAll().stream()
                .filter(v -> v.age() >= from && v.age() <= to).count();
    }

    public long countOutOfAgeRange(int from, int to) {
        return vikingService.findAll().stream().filter(v -> v.age() < from || v.age() > to).count();
    }

    public long countByBeardAndHair(BeardStyle beard, HairColor hair) {
        return vikingService.findAll().stream().filter(v -> v.beardStyle() == beard && v.hairColor() == hair).count();
    }

    public long countWithOneAxe() {
        return vikingService.findAll().stream()
                .filter(v -> v.equipment().stream().filter(e -> e.name().equals("Axe")).count()==1).count();
    }

    public long countWithTwoAxes() {
        return vikingService.findAll().stream().filter(v -> v.equipment().stream().filter(e -> e.name().equals("Axe")).count()==2).count();
    }

    public Optional<Viking> getRandomTallViking() {
        List<Viking> tallVikings = vikingService.findAll().stream().filter(v -> v.heightCm() > 180).collect(Collectors.toList());

        if (tallVikings.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tallVikings.get(new Random().nextInt(tallVikings.size())));
    }

    public List<Viking> getLegendaryVikings() {
        return vikingService.findAll().stream().filter(v -> v.equipment().stream().anyMatch(e -> e.quality().equalsIgnoreCase("Legendary"))).collect(Collectors.toList());
    }

    public List<Viking> getRedHairSortedByAge() {
        return vikingService.findAll().stream().filter(v -> v.hairColor() == HairColor.Red&&v.beardStyle()!=BeardStyle.CLEAN_SHAVEN).sorted(Comparator.comparingInt(Viking::age)).collect(Collectors.toList());
    }

    public Optional<Viking> getMaxId() {
        return vikingService.findAll().stream().max(Comparator.comparingInt(Viking::id));
    }

    public List<Viking> getEvenIds() {
        return vikingService.findAll().stream().filter(v -> v.id() % 2 == 0).collect(Collectors.toList());
    }
    
    public long countWithOneOrTwoAxes() {
        return vikingService.findAll().stream().filter(v -> {long axeCount = v.equipment().stream().filter(e -> e.name().equalsIgnoreCase("Axe")).count();
                    return axeCount >= 1 && axeCount <= 2;}).count();
    }
}
