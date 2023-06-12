package dev.dex.fixerprodockerpostgres.test;

public class Test {

    public static void main(String[] args) {
        String title = "castrol-edge-5w-30-ll-5l";
//        System.out.println(title.replaceAll("\\D[^w]-", " "));
//        Pattern p = Pattern.compile("\\D[^w]-");

        char arr[] = title.toCharArray();
        for (int i = 2; i < title.length(); i++) {
            if (arr[i] == '-') {
                if (arr[i - 1] == 'w'){
                    if (!Character.isDigit(arr[i - 2])) {
                        arr[i] = ' ';
                    }
                }
                else {
                    arr[i] = ' ';
                }
            }
        }
        System.out.println(new String(arr));

        // I should start by checking for "-"
        arr = title.toCharArray();
        for (int i = 2; i < title.length() - 2; i++) {
            char curr = arr[i];
            if (curr == '-') {
                char previousChar = arr[i - 1];
                if (previousChar == 'w'){
                    char secondPreviousChar = arr[i - 2];
                    if (Character.isDigit(secondPreviousChar)) {

                    }
                    else {
                        arr[i] = ' ';
                    }
                }
                else {
                    arr[i] = ' ';
                }
            }
        }
        System.out.println(arr);


    }
}
