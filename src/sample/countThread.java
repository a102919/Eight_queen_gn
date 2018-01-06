package sample;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.*;


public class countThread extends Thread{
    private int queenSize;
    private TextArea showid;
    private Random random = new Random();
    public countThread(int queenSize, TextArea showid) {
        this.queenSize = queenSize;
        this.showid = showid;
    }

    @Override
    public void run() {
        Map<List<Integer>,Integer> queenMap = initQueen(queenSize);
        List<Integer> ans = GNRun(queenMap);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=queenSize;i++){
                    for(int j=1;j<=queenSize;j++){
                        boolean ishere = false;
                        for(int k=0;k<queenSize;k++){
                            if((k+1) == j && ans.get(k) == i){
                                ishere = true;
                            }
                        }
                        if(ishere){
                            showid.appendText("|o ");
                        }else {
                            showid.appendText("|__");
                        }

                    }
                    showid.appendText("|");
                    showid.appendText("\n");
                }
            }
        });

        super.run();
    }

    private List<Integer> GNRun(Map<List<Integer>, Integer> queenMap){
        boolean run = true;
        List<Integer> ans = new ArrayList();
        int count = 0;
        while(run){
            count++;
            List<Map.Entry<List<Integer>, Integer>> sortList = SortMap(queenMap);
            if(sortList.get(0).getValue() == 0){
                ans = sortList.get(0).getKey();
                break;
            }

            queenMap.clear();
            int sortmatSize = sortList.size()/4;
            for(int i=0;i<sortmatSize;i++){
                queenMap.put(sortList.get(i).getKey(),sortList.get(i).getValue());
                queenMap.put(sortList.get(i+sortmatSize).getKey(),sortList.get(i+sortmatSize).getValue());
                mating(sortList.get(i).getKey(),sortList.get(i+sortmatSize).getKey(),queenMap);
            }
        }
        System.out.println(count);
        return ans;
    }

    private void mating(List<Integer> fa, List<Integer> ma, Map<List<Integer>, Integer> queenMap){
        int ListSize = fa.size();
        int ListSizeX = fa.size()/2;
        List sonOne = new ArrayList();
        int i = 0;
        for(;i<ListSizeX; i++){
            if(random.nextInt(1000)<2){
                sonOne.add(random.nextInt(queenSize) + 1);
                continue;
            }
            sonOne.add(fa.get(i));
        }
        for(;i<ListSize;i++){
            if(random.nextInt(1000)<2){
                sonOne.add(random.nextInt(queenSize) + 1);
                continue;
            }
            sonOne.add(ma.get(i));
        }

        List sonTwo = new ArrayList();
        int j=0;
        for(;j<ListSizeX;j++){
            if(random.nextInt(1000)<2){
                sonTwo.add(random.nextInt(queenSize) + 1);
                continue;
            }
            sonTwo.add(ma.get(j));
        }
        for(;j<ListSize;j++){
            if(random.nextInt(1000)<2){
                sonTwo.add(random.nextInt(queenSize) + 1);
                continue;
            }
            sonTwo.add(fa.get(j));
        }
        queenMap.put(sonOne,countQeenEqual(sonOne));
        queenMap.put(sonTwo,countQeenEqual(sonTwo));
    }

    private List<Map.Entry<List<Integer>, Integer>> SortMap(Map<List<Integer>, Integer> queenMap){
        Map<List<Integer>, Integer> sortMap = new HashMap<>();
        // 升序比较器
        Comparator<Map.Entry<List<Integer>, Integer>> valueComparator = new Comparator<Map.Entry<List<Integer>,Integer>>() {
            @Override
            public int compare(Map.Entry<List<Integer>, Integer> o1,
                               Map.Entry<List<Integer>, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        };

        // map转换成list进行排序
        List<Map.Entry<List<Integer>, Integer>> list = new ArrayList<Map.Entry<List<Integer>,Integer>>(queenMap.entrySet());

        // 排序
        Collections.sort(list,valueComparator);
        return list;
    }

    private Map<List<Integer>,Integer> initQueen(int queenSize) {
        Map<List<Integer>,Integer> queenList = new HashMap<>();
        int times = 20000;
        for(int j=0;j<times;j++ ){
            List data = new ArrayList();
            for(int i=0;i<queenSize;i++){
                int x = random.nextInt(queenSize) + 1;
                data.add(x);
            }
            queenList.put(data,countQeenEqual(data));
        }
        return queenList;
    }

    private int countQeenEqual(List<Integer> queenList){
        int check = 0;
        int queenSize = queenList.size();
        for (int i=0;i<queenSize;i++) {
            for (int j=0;j<queenSize;j++) {
                if(j==i)
                    continue;
                if ( queenList.get(j) == queenList.get(i)) {
                    check++;
                }
                if(Math.abs((i+1) -(j+1)) == Math.abs(queenList.get(i) - queenList.get(j))){
                    check++;
                }
            }
        }
        return check;
    }
}
