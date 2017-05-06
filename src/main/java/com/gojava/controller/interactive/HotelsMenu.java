package com.gojava.controller.interactive;

import com.gojava.dao.Utils;
import com.gojava.model.Hotel;
import com.gojava.model.Interactive;
import com.gojava.service.HotelService;
import com.gojava.service.impl.HotelServiceImpl;

import java.util.Optional;
import java.util.TreeSet;

import static com.gojava.dao.Utils.*;

public class HotelsMenu implements Interactive {

    private Interactive previousMenu;
    private HotelRoomsMenu hotelRoomsMenu;
    private HotelService<Hotel> hotelService = new HotelServiceImpl();

    public HotelsMenu(Interactive interactive) {
        this.previousMenu = interactive;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Users menu");
        System.out.println("1) Show all hotels");
        System.out.println("2) Add hotel");
        System.out.println("3) Update hotel");
        System.out.println("4) Delete hotel");
        System.out.println("5) Find hotel");
        System.out.println("6) Manage hotel rooms");
        System.out.println("7) Back to main menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    showAllHotels();
                    break;
                case 2:
                    addHotel();
                    break;
                case 3:
                    updateHotel();
                    break;
                case 4:
                    deleteHotel();
                    break;
                case 5:
                    findHotel();
                    break;
                case 6:
                    manageHotelRooms();
                    break;
                case 7:
                    previousMenu.showMenu();
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }

    public void findHotel() {
        printBorder();
        System.out.println("1) Find hotel by name");
        System.out.println("2) Find hotel by city");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            findHotel();
            while (true) {
                switch (selectedItem) {
                    case 1:
                        findHotelByName();
                        break;
                    case 2:
                        findHotelByCity();
                        break;
                    case 3:
                        previousMenu.showMenu();
                        break;
                    default:
                        showMenu();
                        findHotel();
                        break;
                }
            }
        }
    }


    public void findHotelByName() {
        // TODO
    }

    public void findHotelByCity() {
        // TODO
    }

    private void addHotel() {

        String name = provideStringInputStream("enter hotel name: ");
        if (!Utils.isValidString(name)) {
            addHotel();
        }

        String city = provideStringInputStream("enter hotel city: ");
        if (!Utils.isValidString(city)) {
            addHotel();
        }

        Optional<Hotel> first = hotelService.getAll().values().stream()
                .filter(s -> s.getCity().equals(city))
                .filter(s -> s.getName().equals(name))
                .findFirst();
        if (first.isPresent()) {
            System.out.println("This hotel in this city already exist");
            addHotel();
        }

        Hotel hotel = new Hotel(name, city);

        hotelRoomsMenu = new HotelRoomsMenu(hotel, this);

        Hotel addedHotel = hotelService.create(hotel);
        System.out.println("added new hotel: " + addedHotel.toString());
        showMenu();
    }

    private void updateHotel() {
        hotelService.getAll().values().forEach(System.out::println);

        Integer idChoose = provideIntInputStreamWithMessage("select hotel by id: ");
        if (idChoose == null) {
            System.out.println("incorrect data");
            updateHotel();
        }

        Long idChoose1 = idChoose.longValue();

        Hotel hotel = hotelService.getAll().get(idChoose1);
        if (hotel == null) {
            System.out.println("Incorrect data. Please try again");
            updateHotel();
        }
        String nameHotel = provideStringInputStream("enter hotel name: ");
        if (!isValidString(nameHotel)) {
            addHotel();
        }

        String cityName = provideStringInputStream("enter hotel city: ");
        if (!isValidString(cityName)) {
            addHotel();
        }

        hotel.setName(nameHotel);
        hotel.setCity(cityName);
        System.out.println("changed hotel's parameters id: " + idChoose);
        showMenu();
    }

    private void deleteHotel() {
        hotelService.getAll().values().forEach(System.out::println);

        Integer removeHotelId = provideIntInputStreamWithMessage("choose id hotel: ");
        if (removeHotelId == null) {
            System.out.println("incorrect data");
            deleteHotel();
        }

        long idHotel = removeHotelId.longValue();
        Hotel removedHotel = hotelService.getAll().remove(idHotel);
        if (removedHotel == null) {
            System.out.println("Hotel with this id doesn't exist");
        } else {
            System.out.println("This Hotel with id " + idHotel + " has been deleted");
        }
        showMenu();
    }

    private void showAllHotels() {
        System.out.println("Count of hotels: " + hotelService.getAll().size());
        if (hotelService.getAll().isEmpty()) {
            showMenu();
        } else
            new TreeSet<>(hotelService.getAll().values()).forEach(System.out::println);
        showMenu();
    }

    private void manageHotelRooms() {
        System.out.print("Enter hotel's id or press 'Enter' to return to menu: ");
        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null)
            showMenu();

        Hotel hotel = hotelService.findById(selectedItem);

        if (hotel == null) {
            System.out.println("Hotel with id " + selectedItem + " hasn't been found. Choose another id.");
            manageHotelRooms();
        }
        hotelRoomsMenu = new HotelRoomsMenu(hotel, this);
        hotelRoomsMenu.showMenu();
    }


}
