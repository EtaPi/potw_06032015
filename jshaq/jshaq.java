import java.util.ArrayList;

public class RobinHood {

  /*1 24
  8 5
  8 52
  17 11
  18 86

  your program should output
  1 24
  8 52
  18 86*/

  public static void main(String[] args) {
    ArrayList<Item> Items = new ArrayList<Item>();

    Items.add(new Item(1,24));
    Items.add(new Item(8,5));
    Items.add(new Item(8,52));
    Items.add(new Item(17,11));
    Items.add(new Item(18,86));

    Knapsack sack = new Knapsack();
    int target_weight = 30;
    find_weight_sums(Items,target_weight, sack);

    printSackTrys(sack);

    System.out.println("\n\n\n\nANSWER");

    printItems(sack.getAnswerList());
  }

  static void printSackTrys(Knapsack sack){
          for(int i=0;i<sack.getSackList().size();i++){
            for(int j=0;j<sack.getSackList().get(i).getAnswerList().size();j++){
                    System.out.print(sack.getSackList().get(i).getAnswerList().get(j).getWeight() + " " + sack.getSackList().get(i).getAnswerList().get(j).getValue() + ", ");
            }

            System.out.println("");
          }

          System.out.println("\n\nTotalTrys");
          System.out.println(sack.getSackList().size());
  }

  static void printItems(ArrayList<Item> Items){

          for(int i=0;i<Items.size();i++){

              System.out.println(Items.get(i).getWeight() + " " + Items.get(i).getValue());

          }

  }

  static void find_weight_sums_recursive(ArrayList<Item> ItemsToTry, int target_weight, ArrayList<Item> PartialItemSet,Knapsack sack) {
    int calculate_weight = 0;

    //Check to see if the elements left are less than or equal to 30
    for(Item item:PartialItemSet){
      calculate_weight = calculate_weight + item.getWeight();
    }

    //Capture this try to analyze for duplicates
    Knapsack sackTry = new Knapsack();
    sackTry.setAnswerList(PartialItemSet);
    sack.getSackList().add(sackTry);

    //if the elemenst left have a higher value use them
    if(calculate_weight <= target_weight){
      validate_sack_items( PartialItemSet, sack);
    }

    //if we are too heavy end recursion
    if(calculate_weight > target_weight)
      return;

    //Start recursion with first number in the reduces list
    for(int i = 0; i < ItemsToTry.size(); i++){

      //GET Next Node Value for Item
      Item item = ItemsToTry.get(i);
      ArrayList<Item> partial_item_set = new ArrayList<Item>(PartialItemSet);
      partial_item_set.add(item);

      //Create a list of the remaining items
      ArrayList<Item> items_to_try_next = new ArrayList<Item>();

      for(int j = i+1; j<ItemsToTry.size();j++){
        items_to_try_next.add(ItemsToTry.get(j));
      }

      find_weight_sums_recursive(items_to_try_next,target_weight,partial_item_set, sack);
    }
  }

  static boolean validate_sack_items(ArrayList<Item> ReducedItemsList, Knapsack sack){
    int calculated_value = 0;

    for(Item item:ReducedItemsList){
      calculated_value = calculated_value + item.getValue();
    }

    if(calculated_value >sack.getHighestValue()){
      sack.setHighestValue(calculated_value);
      sack.setAnswerList(ReducedItemsList);

      return true;
    }

    return false;
  }

  static void find_weight_sums(ArrayList<Item> Items, int target_weight,Knapsack sack) {
    find_weight_sums_recursive(Items ,target_weight,new ArrayList<Item>(),sack);
  }
}

class Item {
  private int weight;
  private int value;

  public Item(int Weight, int Value){
    this.weight = Weight;
    this.value = Value;
  }

  // getter
  public int getWeight() { return weight; }
  public int getValue() { return value; }
}

class Knapsack {
  private ArrayList<Item> answer_list;
  private ArrayList<Knapsack> sack_list;
  private int highest_value;

  public Knapsack(){
    highest_value = 0;
    sack_list = new ArrayList<Knapsack>();
  }

  // getter
  public ArrayList<Item> getAnswerList() { return answer_list; }
  public ArrayList<Knapsack> getSackList() { return sack_list; }
  public int getHighestValue() { return highest_value; }

  public void setAnswerList(ArrayList<Item> answerList) {
    answer_list = answerList;
  }

  public void setHighestValue(int highestValue) {
    highest_value = highestValue;
  }
}
