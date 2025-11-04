package persistence;

import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import models.*;
import java.io.*;

public class SupermarkeSaveLoad {
    private static final String DATA_FILE = "supermarket_data.txt";

    public void saveSupermarket(DoublyLinkedList<FloorArea> floorAreaList) throws IOException {
        try (PrintWriter writer = new PrintWriter(DATA_FILE)) {
            Node<FloorArea> floorNode = floorAreaList.getHead();
            while (floorNode != null) {
                FloorArea floor = floorNode.getValue();
                writer.println("FLOORAREA:" + escape(floor.getTitle()) + "," + escape(floor.getLevel()));

                Node<Aisle> aisleNode = floor.getAisles().getHead();
                while (aisleNode != null) {
                    Aisle aisle = aisleNode.getValue();
                    writer.println("AISLE:" + escape(aisle.getAisleName()) + "," + aisle.getLength() + "," +
                            aisle.getWidth() + "," + escape(aisle.getTemperature()));

                    Node<Shelf> shelfNode = aisle.getShelves().getHead();
                    while (shelfNode != null) {
                        Shelf shelf = shelfNode.getValue();
                        writer.println("SHELF:" + shelf.getShelfNumber());

                        Node<Good> goodNode = shelf.getGoodsList().getHead();
                        while (goodNode != null) {
                            Good good = goodNode.getValue();
                            writer.println("GOOD:" + escape(good.getDescription()) + "," + good.getWeight() + "," +
                                    good.getQuantityToAdd() + "," + escape(good.getTemperature()) + "," +
                                    escape(good.getPhotoURL()) + "," + good.getUnitPrice());
                            goodNode = goodNode.getNext();
                        }
                        shelfNode = shelfNode.getNext();
                    }
                    aisleNode = aisleNode.getNext();
                }
                floorNode = floorNode.getNext();
            }
        }
    }

    public LoadedData loadSupermarket() throws IOException {
        LoadedData result = new LoadedData();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            FloorArea currentFloor = null;
            Aisle currentAisle = null;
            Shelf currentShelf = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length < 2) continue;

                String type = parts[0];
                String data = parts[1];

                switch (type) {
                    case "FLOORAREA":
                        String[] floorData = data.split(",");
                        currentFloor = new FloorArea(unescape(floorData[0]), unescape(floorData[1]));
                        result.floorAreas.insertAtTail(currentFloor);
                        break;

                    case "AISLE":
                        String[] aisleData = data.split(",");
                        if (currentFloor != null) {
                            currentAisle = new Aisle(unescape(aisleData[0]), Integer.parseInt(aisleData[1]),
                                    Integer.parseInt(aisleData[2]), unescape(aisleData[3]));
                            currentFloor.addAisle(currentAisle);
                            result.aisles.insertAtTail(currentAisle);
                        }
                        break;

                    case "SHELF":
                        if (currentAisle != null) {
                            currentShelf = new Shelf(Integer.parseInt(data));
                            currentAisle.addShelf(currentShelf);
                            result.shelves.insertAtTail(currentShelf);
                        }
                        break;

                    case "GOOD":
                        String[] goodData = data.split(",");
                        if (currentShelf != null && goodData.length >= 6) {
                            Good good = new Good(unescape(goodData[0]), Double.parseDouble(goodData[1]),
                                    Integer.parseInt(goodData[2]), unescape(goodData[3]),
                                    unescape(goodData[4]), Double.parseDouble(goodData[5]));
                            currentShelf.addGood(good);
                        }
                        break;
                }
            }
        }

        return result;
    }

    public boolean dataExists() {
        return new File(DATA_FILE).exists();
    }

    private String escape(String text) {
        if (text == null) return "";
        return text.replace(",", "\\,").replace(":", "\\:");
    }

    private String unescape(String text) {
        if (text == null) return "";
        return text.replace("\\,", ",").replace("\\:", ":");
    }
}