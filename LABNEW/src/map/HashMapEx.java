package map;

import java.util.HashMap;
import java.util.Map;

/*

Map sử dụng để lưu trữ dữ liệu dạng khóa(key) và giá trị(value).
Mỗi cặp key và value được gọi là một entry

HashMap: Không đảm bảo thứ tự Entry được thêm vào
LinkedHashMap: Đảm bảo các thứ tự Entry được thêm vào
TreeMap: Duy trì thứ tự caác phần tử dựa vào bộ so sánh Comparator
Entry là một interface của Map. Vì vậy có thể truy cập bằng tên Map.Entry; Cung cấp các truy suất cáckeey và value
vd:
 public interface Map<K, V>{
    interface Entry<K,V> {
    }
 }


 */
public class HashMapEx {
    public static void  initHashMap(){
        HashMap<Integer, String> persons = new HashMap<>();
        Map<Integer, String> studens = new HashMap<>();

        studens.put(1, "Hai");
        studens.put(2, "Thi");
        studens.put(3, "Huy");

        System.out.println(studens);
        studens.put(2, "Ngoc");
        System.out.println(studens);
        studens.remove(1);
        System.out.println(studens);

        // show entrySet

        for(Map.Entry<Integer, String> entry:studens.entrySet()){
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "=" + value);
        }

    }

    public static void main(String[] args) {
        initHashMap();
    }
}
