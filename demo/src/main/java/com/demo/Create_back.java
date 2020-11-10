package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Create_back {

    public static List<String> readFile(String dir) {
        List<String> result = new ArrayList<>();
        File file = new File(dir);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line.split(",")[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws IOException {

        List<String> userIdList = readFile("users.csv");
        System.out.println("users:" + userIdList.size());
        int nodeLength = userIdList.size();
        boolean allHasEnoughQuan = false;
        final int SHOULT_HAS_NUMBER = 50;
        int everyHasNum = 0;
        int offset = 0;
        Random r = new Random();
        int totalcount = 0;
        //如果有用户没有满群
        while (!allHasEnoughQuan) {
            int randonNum = r.nextInt(500);
            if (randonNum < 3) {
                randonNum += 3;
            }
            List<String> idList = new ArrayList<>();
            if (offset + randonNum >= nodeLength) {
                //把randonNum改为刚好到list最后一个数
                randonNum = nodeLength - offset;
                //重新开始循环
                offset = 0;
                everyHasNum++;
                if (everyHasNum == SHOULT_HAS_NUMBER) {
                    allHasEnoughQuan = true;
                    break;
                }
                if (randonNum < 2) {
                    continue;
                }
                for (int i = 0; i < randonNum; i++) {
                    idList.add(userIdList.get(nodeLength - 1 - i));
                }
                totalcount ++;
            } else {
                for (int i = 0; i < randonNum; i++) {
                    idList.add(userIdList.get(offset + i));
                }
                offset = offset + randonNum;
                totalcount ++;
            }
            System.out.println("running total"+totalcount);
            idList.clear();
        }
        System.out.println("everyHasNum:" + everyHasNum);
    }

    static class Node {
        private String userId;
        private int hasQuanNum;

        Node(String userId) {
            userId = userId;
            hasQuanNum = 0;
        }
    }
}