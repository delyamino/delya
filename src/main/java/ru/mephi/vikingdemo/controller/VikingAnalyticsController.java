/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingFindOnParametersService;

/**
 *
 * @author panda
 */
@RestController
@RequestMapping("/api/vikings/analytics")

public class VikingAnalyticsController {

    private VikingFindOnParametersService fopService;

    public VikingAnalyticsController(VikingFindOnParametersService fopService) {
        this.fopService = fopService;
    }

    @GetMapping("/count/older/{age}")
    public long countOlderThan(@PathVariable int age) {
        return fopService.countOlderThan(age);
    }

    @GetMapping("/count/younger/{age}")
    public long countYoungerThan(@PathVariable int age) {
        return fopService.countYoungerThan(age);
    }

    @GetMapping("/count/age-range")
    public long countInRange(@RequestParam int from, @RequestParam int to) {
        return fopService.countInAgeRange(from, to);
    }

    @GetMapping("/count/age-out")
    public long countOutOfRange(@RequestParam int from, @RequestParam int to) {
        return fopService.countOutOfAgeRange(from, to);
    }

    @GetMapping("/count/beard-and-hair")
    public long countByBeardAndHair(@RequestParam BeardStyle beard, @RequestParam HairColor hair) {
        return fopService.countByBeardAndHair(beard, hair);
    }

    @GetMapping("/count/one-axe")
    public long countWithOneAxe() {
        return fopService.countWithOneAxe();
    }

    @GetMapping("/count/two-axes")
    public long countWithTwoAxes() {
        return fopService.countWithTwoAxes();
    }

    @GetMapping("/tall-random")
    public Optional<Viking> getRandomTallViking() {
        return fopService.getRandomTallViking();
    }

    @GetMapping("/legendary")
    public List<Viking> getLegendaryVikings() {
        return fopService.getLegendaryVikings();
    }

    @GetMapping("/red-hair-sorted")
    public List<Viking> getRedHairSortedByAge() {
        return fopService.getRedHairSortedByAge();
    }

    @GetMapping("/max-id")
    public Optional<Viking> getMaxId() {
        return fopService.getMaxId();
    }

    @GetMapping("/even-ids")
    public List<Viking> getEvenIds() {
        return fopService.getEvenIds();
    }
    
    @GetMapping("/count/one-or-two-axes")
    public long countWithOneOrTwoAxes() {
        return fopService .countWithOneOrTwoAxes();
    }
}
