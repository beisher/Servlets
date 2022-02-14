package com.tms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet (value = "/car")
public class CarServlet extends HttpServlet {
    public static Map<Integer, Car> carMap = new HashMap();
    private static Integer carId = 0;

    @Override
    public void init() throws ServletException {
        Car car1 = new Car("TOYOTA");
        Car car2 = new Car("BMW");
        Car car3 = new Car("MERCEDEZ-BENZ");

        carMap.put(++carId, car1);
        carMap.put(++carId, car2);
        carMap.put(++carId, car3);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("lastDate" ,  System.currentTimeMillis() + "");
        resp.addCookie(cookie);

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            int i = Integer.parseInt(id);

            Car car = carMap.get(i);
            if (car != null) {
                resp.getOutputStream().print(car.toString());
            } else {
                resp.getOutputStream().print("Its auto don't searched");
            }
        } else {
            resp.getOutputStream().print(carMap.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("id");

        if (id == null && name != null) {
            Car car = new Car(name);
            carMap.put(++carId, car);
            resp.getOutputStream().print("Car id = " + carId + " " + "Car name = " + name);
        }
        if (id != null && name != null) {
            int i = Integer.parseInt(id);

            if (carMap.containsKey(i)) {
                carMap.put(i, new Car(name));
                resp.getOutputStream().print("Car id = " + i + " " + "Car name update = " + name);
            } else {
                resp.getOutputStream().print("Car haven't");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            int i = Integer.parseInt(id);
            if (carMap.containsKey(i)) {
                Car removedCar = carMap.remove(i);
                resp.getOutputStream().print(removedCar.toString());
            } else {
                resp.getOutputStream().print("Car is absent");
            }
        }
    }
}