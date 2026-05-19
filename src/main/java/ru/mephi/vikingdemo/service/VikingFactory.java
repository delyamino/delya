
package ru.mephi.vikingdemo.service;

import java.util.List;
import java.util.Random;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import java.util.Locale;
import java.util.UUID;

@Component
public class VikingFactory {

    private final Faker faker = new Faker(Locale.of("nor"));
    private final Random random = new Random();

    public Viking createRandomViking(int id) {
        return new Viking(
                id,
                faker.name().firstName(),
                18 + random.nextInt(43),
                160 + random.nextInt(41),
                HairColor.values()[random.nextInt(HairColor.values().length)],
                BeardStyle.values()[random.nextInt(BeardStyle.values().length)],
                createRandomEquipment()
        );
    }
    
    public Viking createCustomViking(String name, int age, int height, HairColor hairColor, BeardStyle beardStyle, List<EquipmentItem> equipment) {
        return new Viking (new Random().nextInt(), name, age, height, hairColor, beardStyle, equipment);
    }

    private List<EquipmentItem> createRandomEquipment() {
        return List.of(
                EquipmentFactory.createItem(),
                EquipmentFactory.createItem()
        );
    }
}
