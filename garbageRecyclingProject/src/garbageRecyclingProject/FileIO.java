package garbageRecyclingProject;

import java.io.*;

public class FileIO {

    TrashCan trashCanObj = new TrashCan();

    public IBag<Garbage> readTrashCan(){

        try {

            File file = new File("src\\garbageRecyclingProject\\garbage.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {

                String[] garbageWordList = line.split(",");
                Garbage garbageObj = new Garbage(garbageWordList[0], garbageWordList[1], garbageWordList[2]);
                int garbageRepeatAmount = Integer.parseInt(garbageWordList[2]);
                for (int i = 0; i < garbageRepeatAmount; i++) {
                    trashCanObj.add(garbageObj);
                }
                line = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return trashCanObj;
    }


    public boolean updateTrashCan(){
        Object[] trashCanArr = trashCanObj.getTrashCanArrBag();
        for (int i = 0; i < trashCanObj.getItemCount(); i++) {
            if(trashCanObj.separate(trashCanArr[i])){
                i--;
                continue;
            }
        }

        try {
            File file = new File("src\\garbageRecyclingProject\\updated_garbage.txt");
            if (! file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            int counter = 0;
            String garbageInfo = "";
            Garbage remainingGarbage;
            for (int i = 1; i < trashCanObj.getItemCount(); i++) {
                if (trashCanArr[i-1]==(trashCanArr[i])){
                    counter++;
                }else{
                    remainingGarbage = (Garbage) trashCanArr[i-1];
                    garbageInfo = remainingGarbage.getGarbageName() + "," + remainingGarbage.getGarbageType() + "," + (counter+1) + "\n";
                    writer.write(garbageInfo);
                    counter = 0;
                }
                if (i==trashCanObj.getItemCount()-1){
                    remainingGarbage = (Garbage) trashCanArr[i];
                    garbageInfo = remainingGarbage.getGarbageName() + "," + remainingGarbage.getGarbageType() + "," + (counter+1) + "\n";
                    writer.write(garbageInfo);
                }
                writer.flush();
            }
            writer.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
