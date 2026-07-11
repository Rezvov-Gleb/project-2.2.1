package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);


        User user1 = new User("Kobe", "Bryant", "champion@nba.com");
        User user2 = new User("Tim", "Duncan", "fundamental@nba.com");
        User user3 = new User("Kevin", "Garnet", "ticket@nba.com");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);


        Car car1 = new Car("Toyota Land Cruiser", 300);
        Car car2 = new Car("Mercedes GLA", 200);
        Car car3 = new Car("Porsche", 911);

        carService.add(car1);
        carService.add(car2);
        carService.add(car3);


        List<User> usersFromDb = userService.listUsers();
        List<Car> carsFromDb = carService.listCars();


        usersFromDb.get(0).setCar(carsFromDb.get(0));
        usersFromDb.get(1).setCar(carsFromDb.get(1));
        usersFromDb.get(2).setCar(carsFromDb.get(2));


        for (User user : usersFromDb) {
            userService.update(user);
        }


        System.out.println("Все пользователи с машинами:");
        List<User> finalUsers = userService.listUsers();
        for (User user : finalUsers) {
            System.out.println(user);
        }


        User result = userService.getUserByCarModelAndSeries("Mercedes GLA", 200);
        System.out.println("\nНайден пользователь по машине: " + result);

        context.close();
    }
}