package com.gojava.controller.interactive;

import com.gojava.service.HotelService;
import com.gojava.model.Hotel;
import com.gojava.model.Interactive;
import com.gojava.service.impl.HotelServiceImpl;

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
        System.out.println("1) Add hotel");
        System.out.println("2) Update hotel");
        System.out.println("3) Delete hotel");
        System.out.println("4) Find hotel");
        System.out.println("5) Show all hotels");
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
                    addHotel();
                    break;
                case 2:
                    updateHotel();
                    break;
                case 3:
                    deleteHotel();
                    break;
                case 4:
                    findHotel();
                    break;
                case 5:
                    showAllHotels();
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

    public void addHotel() {
        //TODO check enter data
        String name = provideStringInputStream("enter hotel name: ");
        String city = provideStringInputStream("enter hotel city: ");

        Hotel hotel = new Hotel(name, city);
        hotelRoomsMenu = new HotelRoomsMenu(hotel, this);
        for (int i = 0; i < 10; i++) {
            // todo addRooms
        }

        Hotel addedHotel = hotelService.create(hotel);
        System.out.println("added new hotel: " + addedHotel.toString());

        showMenu();
    }

    public void updateHotel() {
        //TODO make it

        System.err.println("not ready yet");
        showMenu();
    }

    public void deleteHotel() {
        //TODO make it

        System.err.println("not ready yet");
        showMenu();
    }

    public void showAllHotels() {
        System.out.println("Count of users: " + hotelService.getAll().size());
        if (hotelService.getAll().isEmpty()) {
            showMenu();
        } else
            hotelService.getAll().values().forEach(System.out::println);
        showMenu();
    }

    private void manageHotelRooms() {
        System.out.print("Enter hotels id: ");
        Integer selectedItem = provideIntInputStream();

        Hotel hotel = null; //TODO find this hotel


        hotelRoomsMenu = new HotelRoomsMenu(hotel, this);
        hotelRoomsMenu.showMenu();
    }


}
