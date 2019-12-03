import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {

    Map<String, Integer> mapOfItems = new LinkedHashMap<String, Integer>();
    Map<String, Integer> mapOfItemsPrice = new LinkedHashMap<String, Integer>();



    public void parseInput(String input){
       // separate item by ##
        String[] inputArr = input.split("##");

        for (int i = 0; i < inputArr.length; i++){
            String[] item =  inputArr[i].split("[^a-zA-Z0-9:./ ]");
           addItemToMap (item[0],item[1]);
        }
      // System.out.println("Hi");
    }

    public void addItemToMap(String item, String price) {
        String itemprice = validateInput(item,price);
        Integer counter = 1;
        if (!itemprice.equals(""))
        {
            if (mapOfItemsPrice.containsKey(itemprice)) {
                counter += mapOfItemsPrice.get(itemprice);
            }
            mapOfItemsPrice.put(itemprice, counter);
        }


    }
    public String validateInput(String item, String price){
        String itemName = item.substring(item.indexOf(":") + 1).toLowerCase().replaceAll("0","o");
        String itemPrice = price.substring(price.indexOf(":") + 1);

        if(itemName.equals("") || itemPrice.equals("")){
            addToCounterMap("Error");
            return "";
        } else{
            addToCounterMap(itemName);
            return itemName+":"+itemPrice;
        }


    }

    public void addToCounterMap(String itemName){
        Integer counter = 1;
        if(mapOfItems.containsKey(itemName)){
            counter +=  mapOfItems.get(itemName);
        }
            mapOfItems.put(itemName, counter);
    }

    public void printMap(){
        for (HashMap.Entry<String, Integer> entry1 : mapOfItems.entrySet()) {
            String str = entry1.getKey();
            Integer num = entry1.getValue();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("name:\t"+str)
                    .append("\t\tseen: "+num+" times\n")
                    .append("=============\t\t=============\n");

            for(HashMap.Entry<String,Integer> entry2 : mapOfItems.entrySet()){
                String[] str1 = entry2.getKey().split("[:]");
                Integer num1 = entry2.getValue();

                if (str1[0].equals(str)) {
                    stringBuilder.append("Price:\t" + str1[1])
                            .append("\t\tseen: " + num1 + " times\n")
                            .append("-------------\t\t-------------\n");
                }
            }
            System.out.println(stringBuilder);
        }
        System.out.println("Errors:\t \t\t\tseen: " + mapOfItems.get("Errors")+" times\n");
    }

}

