public class AllPossibleCombinations {
    
    public GrowableArray all_anagrams;
    public int[] tracking;
    public String the_string;

    public AllPossibleCombinations(String s){

        all_anagrams = new GrowableArray();
        tracking = new int[s.length()];
        the_string = s;
    }
    
    public void generate_all_possible_anagrams(int idx, int strlen) {

        if(idx == strlen){
            String str = "";

            Boolean add_space = false;
            for(int i = 0; i<the_string.length(); i++) {
                if(tracking[i] == 1){
                    str+= Character.toString(the_string.charAt(i)) ;
                    add_space = true;
                }
            }
            if(add_space) str += " ";

            add_space = false;
            for(int i = 0; i<the_string.length(); i++) {
                if(tracking[i] == 2){
                    str+= Character.toString(the_string.charAt(i)) ;
                    add_space = true;
                }
            }
            if(add_space) str += " ";

            add_space = false;
            for(int i = 0; i<the_string.length(); i++) {
                if(tracking[i] == 3){
                    str+= Character.toString(the_string.charAt(i)) ;
                    add_space = true;
                }
            }
            if(add_space) str += " ";
            
            str = str.trim();
            all_anagrams.insertKey(str);

            return;
        }

        tracking[idx] = 1;
        generate_all_possible_anagrams((idx + 1), strlen);
        tracking[idx] = 2;
        generate_all_possible_anagrams((idx + 1), strlen);
        tracking[idx] = 3;
        generate_all_possible_anagrams((idx + 1), strlen);

    }

    public String[] generate_all_possible_anagrams(){
        this.generate_all_possible_anagrams(0, this.the_string.length());
        int counter = 0;
        for (Object string : this.all_anagrams.arr_elements) {
            if (string!=null) {
                counter+=1;
            }
        }
        
        String AllPossibleCombinations[] = new String[counter];
        for (int i = 0; i < counter; i++) {
            AllPossibleCombinations[i] = (String)this.all_anagrams.arr_elements[i];
        }

        return AllPossibleCombinations;
    }

}
