package com.demo;

import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Create {

//    static class Node {
//        private String userId;
//        private int hasQuanNum;
//        Node (String userId){
//            userId = userId;
//            hasQuanNum = 0;
//        }
//    }
//
//    public static List<String> readFile(String dir){
//        List<String> result = new ArrayList<>();
//        return result;
//    }
//
//    public static void main(String[] args) throws IOException {
//        List<String> userIdList = readFile("XXX");
//        int nodeLength = userIdList.size();
//        boolean allHasEnoughQuan = false;
//        final int SHOULT_HAS_NUMBER = 50;
//        int everyHasNum = 0;
//        int offset = 0;
//        Random r = new Random();
//        //如果有用户没有满群
//        while (!allHasEnoughQuan){
//            int randonNum = r.nextInt(500);
//            if (randonNum < 3) {
//                randonNum += 3;
//            }
//            List<String> idList = new ArrayList<>();
//            if (offset + randonNum >= nodeLength) {
//                //把randonNum改为刚好到list最后一个数
//                randonNum = nodeLength - offset;
//                //重新开始循环
//                offset = 0;
//                everyHasNum++;
//                if (everyHasNum == SHOULT_HAS_NUMBER) {
//                    allHasEnoughQuan = true;
//                    break;
//                }
//                if (randonNum < 2) {
//                    continue;
//                }
//                for(int i = 0;i < randonNum;i++){
//                    idList.add(userIdList.get(nodeLength-1-i));
//                }
//            }else{
//                for (int i = 0; i<randonNum; i++) {
//                    idList.add(userIdList.get(offset + i));
//                }
//                offset = offset + randonNum;
//            }
//            HttpPost httpPost = HttpPostFactory.getHttpClient("https://ei-webrtc.baijiahulian.com:10081/nimserver/team/create.action");
//            HttpClient httpClient = HttpClientBuilder.create().build();
//
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            List<String> idList = new ArrayList<>();
//            idList.add("user1");
//            idList.add("chenyin123");
//            String jsonArray = JSONObject.toJSONString(idList);
//            idList.clear();
//            nvps.add(new BasicNameValuePair("tname", "高级群"));
//            nvps.add(new BasicNameValuePair("owner", "user2"));
//            nvps.add(new BasicNameValuePair("members", jsonArray));
//            nvps.add(new BasicNameValuePair("msg", "msg"));
//            nvps.add(new BasicNameValuePair("magree", "0"));
//            nvps.add(new BasicNameValuePair("joinmode", "0"));
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//            // 执行请求
//            HttpResponse response = httpClient.execute(httpPost);
//            // 打印执行结果
//            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
//
//        }
//
//    }
}
