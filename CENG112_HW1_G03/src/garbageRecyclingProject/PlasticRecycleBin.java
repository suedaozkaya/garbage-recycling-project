package garbageRecyclingProject;

import java.util.Random;

public class PlasticRecycleBin<T> implements IBag<T> {

    private boolean initialized = false;
    private final T[] plasticBag;
    private int numOfItems;
    private int[] arr = {5,10,15};
    Random rnd = new Random();
    private final int DEFAULT_CAPACITY = arr[rnd.nextInt(2)];

    public PlasticRecycleBin(){
        T[] tempBag = (T[]) new Object[DEFAULT_CAPACITY];
        plasticBag = tempBag;
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
            plasticBag[numOfItems] = newItem;
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
        return  numOfItems >= plasticBag.length;
    }

    @Override
    public T removeByIndex(int index) {
        T result = null;
        if(!isEmpty() && (index >= 0)){
            result = (T) plasticBag[index];

            for (int i = index; i < numOfItems-1; i++) {
                plasticBag[i] = plasticBag[i+1];
            }

            plasticBag[numOfItems-1] = null;
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
            if (item.equals(plasticBag[index])){
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
            if (plasticBag[i-1]==(plasticBag[i])){
                counter++;
            }else{
                System.out.println(plasticBag[i-1].toString() + ", " + "Garbage Amount In Plastic Recycling Bin: " + (counter+1));
                counter = 0;
            }
            if (i==numOfItems-1){
                System.out.println(plasticBag[i].toString() + ", " + "Garbage Amount In Plastic Recycling Bin: " + (counter+1));
            }
        }
    }

    @Override
    public void dump() {
        checkInitialization();
        for (T item: plasticBag) {
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