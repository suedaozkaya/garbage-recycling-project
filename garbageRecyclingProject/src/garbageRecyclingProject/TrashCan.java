package garbageRecyclingProject;

public class TrashCan<T> implements IBag<T> {

    private boolean initialized = false;
    private final T[] trashCanBag;
    private int numOfItems;
    private static final int DEFAULT_CAPACITY = 450;

    static MetalRecycleBin metalBinObj = new MetalRecycleBin();
    static OrganicRecycleBin organicBinObj = new OrganicRecycleBin();
    static PaperRecycleBin paperBinObj = new PaperRecycleBin();
    static PlasticRecycleBin plasticBinObj = new PlasticRecycleBin();
    static FabricRecycleBin fabricBinObj = new FabricRecycleBin();
    static GlassRecycleBin glassBinObj = new GlassRecycleBin();

    public T[] getTrashCanArrBag() {
        return trashCanBag;
    }

    public TrashCan(){
        T[] tempBag = (T[]) new Object[DEFAULT_CAPACITY];
        trashCanBag = tempBag;
        numOfItems = 0;
        initialized = true;
    }

    public boolean separate(T item){
        Garbage garbageItem = (Garbage) item;
        String garbageType = garbageItem.getGarbageType();
        boolean isTransfered = false;

        switch (garbageType){
            case "metal":
                isTransfered = transferTo(metalBinObj, item);
                break;
            case "organic":
                isTransfered = transferTo(organicBinObj, item);
                break;
            case "paper":
                isTransfered = transferTo(paperBinObj, item);
                break;
            case "plastic":
                isTransfered = transferTo(plasticBinObj, item);
                break;
            case "fabric":
                isTransfered = transferTo(fabricBinObj, item);
                break;
            case "glass":
                isTransfered = transferTo(glassBinObj, item);
                break;
        }
        return isTransfered;
    }

    @Override
    public boolean add(T newItem) {
        checkInitialization();
        boolean result = true;
        if (isFull()){

            result = false;
        }else{
            trashCanBag[numOfItems] = newItem;
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
        return  numOfItems >= trashCanBag.length;
    }

    @Override
    public T removeByIndex(int index) {
        T result = null;
        if(!isEmpty() && (index >= 0)){
            result = (T) trashCanBag[index];

            for (int i = index; i < numOfItems-1; i++) {
                trashCanBag[i] = trashCanBag[i+1];
            }

            trashCanBag[numOfItems-1] = null;
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
            if (item.equals(trashCanBag[index])){
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
            if (trashCanBag[i-1]==(trashCanBag[i])){
                counter++;
            }else{
                System.out.println(trashCanBag[i-1].toString() + ", " + "Garbage Amount In Trash Can: " + (counter+1));
                counter = 0;
            }
            if (i==numOfItems-1){
                System.out.println(trashCanBag[i].toString() + ", " + "Garbage Amount In Trash Can: " + (counter+1));
            }
        }
    }

    @Override
    public void dump() {
        checkInitialization();
        for (T item: trashCanBag) {
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
