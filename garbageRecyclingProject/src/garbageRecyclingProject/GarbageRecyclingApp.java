package garbageRecyclingProject;

import java.util.Arrays;

public class GarbageRecyclingApp {
    public static void main(String[] args){

        FileIO fileIOObj = new FileIO();

        System.out.println("************************************************* Trash Can *************************************************");

        TrashCan trashCanBag = (TrashCan) fileIOObj.readTrashCan();

        System.out.println("Size: " + trashCanBag.getItemCount());
        System.out.println("Content: ");

        trashCanBag.displayItems();

        fileIOObj.updateTrashCan();

        System.out.println("\n************************************************* Plastic Recycling Bin *************************************************");
        System.out.println("Size: " + TrashCan.plasticBinObj.getItemCount());
        System.out.println("Content: ");
        TrashCan.plasticBinObj.displayItems();

        System.out.println("\n************************************************* Metal Recycling Bin *************************************************");
        System.out.println("Size: " + TrashCan.metalBinObj.getItemCount());
        System.out.println("Content: ");
        TrashCan.metalBinObj.displayItems();

        System.out.println("\n************************************************* Fabric Recycling Bin *************************************************");
        System.out.println("Size: " + TrashCan.fabricBinObj.getItemCount());
        System.out.println("Content: ");
        TrashCan.fabricBinObj.displayItems();

        System.out.println("\n************************************************* Paper Recycling Bin *************************************************");
        System.out.println("Size: " + TrashCan.paperBinObj.getItemCount());
        System.out.println("Content: ");
        TrashCan.paperBinObj.displayItems();

        System.out.println("\n************************************************* Glass Recycling Bin *************************************************");
        System.out.println("Size: " + TrashCan.glassBinObj.getItemCount());
        System.out.println("Content: ");
        TrashCan.glassBinObj.displayItems();

        System.out.println("\n************************************************* Organic Recycling Bin *************************************************");
        System.out.println("Size: " + TrashCan.organicBinObj.getItemCount());
        System.out.println("Content: ");
        TrashCan.organicBinObj.displayItems();

        System.out.println("\n************************************************* Updated Trash Can *************************************************");
        System.out.println("Size: " + trashCanBag.getItemCount());
        System.out.println("Updated Content: ");
        trashCanBag.displayItems();


    }
}
