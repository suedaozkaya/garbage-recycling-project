package garbageRecyclingProject;

import java.util.Random;

public class PaperRecycleBin<T> implements IBag<T>{

    private boolean initialized = false;
    private final T[] paperBag;
    private int numOfItems;
    private int[] arr = {5,10,15};
    Random rnd = new Random();
    private final int DEFAULT_CAPACITY = arr[rnd.nextInt(2)];

    public PaperRecycleBin(){
        T[] tempBag = (T[]) new Object[DEFAULT_CAPACITY];
        paperBag = tempBag;
        numOfItems = 0;
        initialized = true;
    }

    @Override
    public boolean add(T newItem) {
        checkInitialization();
        boolean result = true;
        if (isFull()){
            result = false;

        }else{
            paperBag[numOfItems] = newItem;
            numOfItems++;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return numOfItems == 0;
    }

    @Override
    public boolean isFull() {
        return  numOfItems >= paperBag.length;
    }

    @Override
    public T removeByIndex(int index) {
        T result = null;
        if(!isEmpty() && (index >= 0)){
            result = (T) paperBag[index];

            for (int i = index; i < numOfItems-1; i++) {
                paperBag[i] = paperBag[i+1];
            }

            paperBag[numOfItems-1] = null;
            numOfItems--;
        }
        return result;
    }

    @Override
    public T remove() {
        checkInitialization();
        T result = removeByIndex(numOfItems-1);
        return result;
    }

    @Override
    public T remove(T item) {
        checkInitialization();
        int index = getIndexOf(item);
        T result = removeByIndex(index);
        return result;
    }

    @Override
    public int getItemCount() {
        return numOfItems;
    }

    @Override
    public int getIndexOf(T item) {
        int place = -1;
        boolean notFound = true;
        int index = 0;

        while (notFound && (index<numOfItems)){
            if (item.equals(paperBag[index])){
                notFound = false;
                place = index;
            }
            index++;
        }
        return place;
    }

    @Override
    public boolean contains(T item) {
        checkInitialization();
        return getIndexOf(item) > -1;
    }

    @Override
    public void displayItems() {
        int counter = 0;
        for (int i = 1; i < numOfItems; i++) {
            if (paperBag[i-1]==(paperBag[i])){
                counter++;
            }else{
                System.out.println(paperBag[i-1].toString() + ", " + "Garbage Amount In Paper Recycling Bin: " + (counter+1));
                counter = 0;
            }
            if (i==numOfItems-1){
                System.out.println(paperBag[i].toString() + ", " + "Garbage Amount In Paper Recycling Bin: " + (counter+1));
            }
        }
    }

    @Override
    public void dump() {
        checkInitialization();
        for (T item: paperBag) {
            remove(item);
        }
    }

    @Override
    public boolean transferTo(IBag<T> targetBag, T item) {
        checkInitialization();
        if (targetBag.isFull()){
            return false;
        }else {
            targetBag.add(item);
            remove(item);
            return true;
        }
    }

    private void checkInitialization(){
        if (!initialized)
            throw new SecurityException("ArrayBag object is not initialized " +
                    "properly.");
    }

}
