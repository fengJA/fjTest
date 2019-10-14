package com.jf.shop.login.goBang;

public class GoBang {
    private static int BORDER_SIZE = 15;
    private String[][] border;
    private String white = "○";
    private String black ="●";
    //判断是哪一方（白、黑）
    private String color;

    public void initBorder(){
        border = new String[BORDER_SIZE][BORDER_SIZE];
        for (int i = 0; i < BORDER_SIZE; i++) {
            for (int j = 0; j < BORDER_SIZE; j++) {
                border[i][j] = "╋";
            }
        }
    }

    public void printBorder(){
        for (int i = 0; i < BORDER_SIZE; i++) {
            for (int j = 0; j < BORDER_SIZE; j++) {
                System.out.print(border[i][j]);
            }
            System.out.println("\n");
        }
    }

    public Point splitPoint(String point){
        String[] posStrArr = point.split(",");

        int xPos = Integer.parseInt(posStrArr[0]);
        int yPos = Integer.parseInt(posStrArr[1]);

        return new Point(xPos,yPos);
    }

    //判断是否落子
    public boolean isGoBang(Point point, boolean color){
        while (true){
            int xPos = point.getxPos() - 1;
            int yPos = point.getyPos() - 1;
            if (xPos > BORDER_SIZE || xPos < 0 || yPos > BORDER_SIZE || yPos < 0){
                System.out.println("超出了范围，不能落子");
                continue;
            }else if (! border[xPos][yPos].equals("╋")){
                System.out.println("该位置已经有了棋子，请重新输入");
                continue;
            }else {
                border[xPos][yPos] = color ? white : black;
                return true;
            }
        }
    }

    public boolean isWin(Point point, boolean color){
        int num = 1;
        int xPos = point.getxPos() - 1;
        int yPos = point.getyPos() - 1;

        //向上查找
        for (int x = xPos - 1; x >= 0; x--) {
            if (border[x][yPos].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //向下查找
        for (int x = xPos + 1; x < BORDER_SIZE; x++) {
            if (border[x][yPos].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //判断是否赢了
        if (num >= 5){
            System.out.println(color + "赢了");
            return true;
        }else {
            num = 1;
        }

        //向左查找
        for (int y = yPos - 1; y >= 0; y--) {
            if (border[xPos][y].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //向右查找
        for (int y = yPos + 1; y < BORDER_SIZE; y++) {
            if (border[xPos][y].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //判断是否赢了
        if (num >= 5){
            System.out.println(color + "赢了");
            return true;
        }else {
            num = 1;
        }

        //向右上查找
        for (int x = xPos - 1,y = yPos + 1; x >= 0 && y < BORDER_SIZE; x--,y++) {
            if (border[x][y].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //向左下查找
        for (int x = xPos + 1,y = yPos - 1; y >= 0 && x < BORDER_SIZE; y--,x++) {
            if (border[x][y].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //判断是否赢了
        if (num >= 5){
            System.out.println(color + "赢了");
            return true;
        }else {
            num = 1;
        }

        //向左上查找
        for (int x = xPos - 1,y = yPos - 1; x >= 0 && y >= 0; x--,y--) {
            if (border[x][y].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //向右下查找
        for (int x = xPos + 1,y = yPos + 1; y <BORDER_SIZE && x < BORDER_SIZE; y++,x++) {
            if (border[x][y].equals(color ? white : black)){
                ++num;
            }else {
                break;
            }
        }

        //判断是否赢了
        if (num >= 5){
            System.out.println(color + "赢了");
            return true;
        }else {
            return true;
        }
    }
}



























