package com.petr.petr.controller;

import com.petr.petr.persistence.entity.Role;
import com.petr.petr.persistence.entity.User;
import com.petr.petr.persistence.repository.UserRepository;
import com.petr.petr.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Controller
@Log4j
public class WelcomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
//
//    @Autowired
//    StationService stationService;
//
//    @Autowired
//    StationRepository cityRepository;
//
//    Way currentWay;


    @GetMapping("/registration")
    public ModelAndView home1() throws IOException, ClassNotFoundException {
//        userService.registrationUser("11","11");
//        List<Station> stations = cityRepository.findAll();
//        LocalDateTime dateTime = LocalDateTime.now().withSecond(0);
//        for (Station station : stations) {
//            for (Station station1 : stations) {
//                System.out.println("st = " + station.getName() + " fin = " + station1.getName());
//                TicketService ticketService = new TicketService();
////                TicketService ticketService = new TicketService(cityService.searchByFullName("c0"), cityService.searchByFullName("c1"),  1);
//                Way result = ticketService.getWay(station, station1, 1, dateTime, 12);
//                ticketService.printWays(result);
//            }
//        }

        return new ModelAndView("registration");
    }


    @PostMapping("/registration2")
    public ModelAndView home(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("inn") String inn,
            @RequestParam("passport") String passport,
            @RequestParam("issuedBy") String issuedBy,
            @RequestParam("issuedWhen") String issuedWhen,
            @RequestParam("f1") MultipartFile f1,
            @RequestParam("f2") MultipartFile f2,
            @RequestParam("f3") MultipartFile f3,
            @RequestParam("f4") MultipartFile f4


    ) {
        ModelAndView modelAndView = new ModelAndView("registration");

        if (name.equals("") || surname.equals("") || patronymic.equals("") || birthDate.equals("") || email.equals("")
        ||phone.equals("")||inn.equals("")||passport.equals("")||issuedBy.equals("")||issuedWhen.equals("")) {
            modelAndView.addObject("error", "У вас есть не заполненые поля");
            modelAndView.addObject("name", name);
            modelAndView.addObject("surname", surname);
            modelAndView.addObject("patronymic", patronymic);
            modelAndView.addObject("email", email);
            modelAndView.addObject("phone", phone);
            modelAndView.addObject("inn", inn);
            modelAndView.addObject("passport", passport);
            modelAndView.addObject("issuedBy", issuedBy);
            modelAndView.addObject("issuedWhen", issuedWhen);
            modelAndView.addObject("passport", passport);

            return modelAndView;
        }
        if (f1.isEmpty()||f2.isEmpty()||f3.isEmpty()||f4.isEmpty()){
            modelAndView.addObject("error", "У вас есть не прикрепленный фото");
            return modelAndView;
        }

        User user = User.builder()
                .authority(Role.ROLE_USER)
                .parentId(null)
                .verify(false)
                .login(email)
                .password(passwordEncoder().encode(String.valueOf(100000000 + (int) (Math.random() * 999999999)) + Instant.now().getEpochSecond()))
                .name(name.toLowerCase())
                .surname(surname.toLowerCase())
                .patronymic(patronymic.toLowerCase())
                .birthDate(birthDate.toLowerCase())
                .email(email)
                .phone(phone.toLowerCase())
                .INN(inn.toLowerCase())
                .passport(passport.toLowerCase())
                .issuedBy(issuedBy.toLowerCase())
                .issuedWhen(issuedWhen.toLowerCase())
                .passwordFirstPage("1")
                .passwordLastPage("1")
                .passwordSecondPage("1")
                .photo("1")
                .build();
        userRepository.save(user);
        try {
            Files.createDirectories(Paths.get("/home/user/img2/" + user.getId()));
            // Get the file and save it somewhere
            byte[] bytes = f1.getBytes();
            Path path = Paths.get("/home/user/img2/" + user.getId().toString() + "/" + Instant.now().getEpochSecond() + f1.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


//        List<Station> stations = stationService.getAll();
//        modelAndView.addObject("stations", s
// tations);
//        if ("".equals(error)){
//            modelAndView.addObject("error", error);
//        }
//        if ("".equals(way)){
//            modelAndView.addObject("way", currentWay);
//        }

        return modelAndView;
    }
//
//    @GetMapping("/home/searchTrain")
//    public ModelAndView searchTrain(@RequestParam(value = "valueTransplant") String valueTransplant,
//                                    @RequestParam(value = "waiting") String waitingOnTransplant,
//                                    @RequestParam(value = "sourceStation") String sourceStation,
//                                    @RequestParam(value = "sourceDateTime") String sourceDateTime,
//                                    @RequestParam(value = "destinationStation") String destinationStation,
//                                    @RequestParam(value = "destinationDateTime") String destinationDateTime
//                              ) throws IOException, ClassNotFoundException {
//        ModelAndView modelAndView = new ModelAndView("home");
//        Seacher seacher = stationService.validData(valueTransplant, waitingOnTransplant, sourceStation,
//                destinationStation, sourceDateTime, destinationDateTime);
//        if (!seacher.getMessage().equals("OK")) {
//            modelAndView.addObject("error", seacher.getMessage());
//        } else {
//
//            TicketService ticketService = new TicketService();
//            Way way = ticketService.getWay(seacher.getCurrentSourceStation(), seacher.getCurrentDestinationStation(),
//                    seacher.getTransplantValue(), seacher.getSourceDate().withSecond(0),
//                    seacher.getDestinationDate().withSecond(0), seacher.getTransplantTime());
//            ticketService.printWays(way);
//            if (way != null&&!way.getWays().isEmpty()) {
//                modelAndView.addObject("way", way);
//            }else {
//                modelAndView.addObject("way", null);
//            }
//
//        }
//        List<Station> stations = stationService.getAll();
//        modelAndView.addObject("stations", stations);
//        modelAndView.addObject("destinationDateTime", destinationDateTime);
//        modelAndView.addObject("sourceDateTime", sourceDateTime);
//        return modelAndView;
//    }
//
//    @GetMapping("/login")
//    public ModelAndView admin(@RequestParam(value = "error", required = false) String error) {
//
//        ModelAndView model = new ModelAndView("login");
//        if (userService.isAuthenticate()) {
//            log.debug("Redirect from URL = /admin/home");
//            return new ModelAndView("redirect:/admin/home");
//        }
//        if (error != null) {
//            model.addObject("error", "Invalid login or password");
//        }
//        return model;
//    }

    @GetMapping("/logout")
    public String lo() {
        return "logout";
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            private final Md5PasswordEncoder md5 = new Md5PasswordEncoder();

            @Override
            public String encode(CharSequence rawPassword) {
                return md5.encodePassword(rawPassword.toString(), 1);
            }

            @Override
            @SuppressWarnings("PMD")
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                log.debug("Get encode password");
                return (md5.encodePassword(rawPassword.toString(), 1)).equals(encodedPassword);
            }
        };
    }
}

