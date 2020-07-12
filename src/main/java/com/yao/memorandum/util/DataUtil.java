package com.yao.memorandum.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yao.memorandum.entity.DataEntity;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DataUtil {

    public static List<String> readFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                List<String> lines = FileUtils.readLines(file, "UTF-8");
                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<String>();
    }

    public static synchronized void writLines(String filePath, List<Object> list) {
        File file = new File(filePath);
        try {
            FileUtils.writeLines(file, "UTF-8",list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void appendFile(String filePath, List<Object> list) {
        File file = new File(filePath);
        try {
            FileUtils.writeLines(file,"UTF-8", list, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Vector<Object>> getTableData(String filePath) {
        List<String> list = readFile(filePath);
        ArrayList<Vector<Object>> res = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Vector<Object> vector = new Vector<>();
                String s = list.get(i);
                JSONObject json = JSON.parseObject(s);
                int mark = json.getIntValue("mark");
                ImageIcon imageIcon = null;
                switch (mark) {
                    case 0:
                        imageIcon = IconUtil.getIcon(IconUtil.Icon.DEFAULT);
                        break;
                    case 1:
                        imageIcon = IconUtil.getIcon(IconUtil.Icon.TOP);
                        break;
                    case 2:
                        imageIcon = IconUtil.getIcon(IconUtil.Icon.FINISH);
                        break;
                    default:
                        break;
                }
                vector.add(imageIcon);
                vector.add(json.getString("date"));
                vector.add(json.getString("msg"));
                vector.add(json.getString("bz"));
                vector.add(json.getString("cDt"));
                vector.add(json.getString("uDt"));
                vector.add(mark);
                res.add(vector);
            }
        }
        return res;
    }

    public static List<Object> transTableData(List<Vector<Object>> list) {
        ArrayList<Object> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Vector vector = (Vector) obj;
            DataEntity dataEntity = new DataEntity();
            for (int j = 0; j < vector.size(); j++) {
                switch (j) {
                    case 1:
                        dataEntity.setDate(String.valueOf(vector.get(j)));
                        break;
                    case 2:
                        dataEntity.setMsg(String.valueOf(vector.get(j)));
                        break;
                    case 3:
                        dataEntity.setBz(String.valueOf(vector.get(j)));
                        break;
                    case 4:
                        dataEntity.setcDt(Long.parseLong(String.valueOf(vector.get(j))));
                        break;
                    case 5:
                        dataEntity.setuDt(Long.parseLong(String.valueOf(vector.get(j))));
                        break;
                    case 6:
                        dataEntity.setMark(Integer.parseInt(String.valueOf(vector.get(j))));
                        break;
                    default:
                        break;

                }
            }
            result.add(JSON.toJSONString(dataEntity));
        }
        return result;
    }
}
