package pl.krzysztofwywial.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.krzysztofwywial.config.ImagePathConfig;
import pl.krzysztofwywial.exception.RecordNotFoundException;
import pl.krzysztofwywial.model.CarEntity;
import pl.krzysztofwywial.service.CarService;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/")
public class CarMvcController {

    @Autowired
    CarService service;

    @Autowired
    ImagePathConfig config;



    @RequestMapping
    public String getAllCars(Model model) {
        List<CarEntity> list = service.getAllCars();
        model.addAttribute("cars", list);
        model.addAttribute("imagePath", config.getImageGet());
        return "cars-list";
    }

    @RequestMapping("/edit-car")
    public String editAllCars(Model model) {
        getAllCars(model);
        return "cars-list-edit";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editCarById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            CarEntity entity = service.getCarById(id.get());
            model.addAttribute("car", entity);
        } else {
            model.addAttribute("car", new CarEntity());
        }
        return "add-edit-car";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteCarById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteCarById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createCar", method = RequestMethod.POST)
    public String createOrUpdateCar(CarEntity car,
                                    @RequestParam("img") MultipartFile multipartFile) {
        service.createOrUpdateCar(car);
        try {
            service.saveImage(car, multipartFile);
        } catch (Exception e) {
            log.error("Error save photo", e);
        }
        return "redirect:/";
    }
}
