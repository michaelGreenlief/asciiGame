package gameTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Line {

    private List<Point> points;


    public List<Point> getPoints(){

        return points;
    }

    public Line(int x0, int y0, int x1, int y1){

        points = new ArrayList<Point>();

        int deltax = (x1 - x0);
        int deltay = (y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int error = Math.abs(deltax - deltay);


        while (true){

            points.add(new Point(x0, y0, 0));

            if(x0==x1 && y0==y1){
                break;
            }

            int e2 = error * 2;
            if(e2 > -deltax){
                error -= deltay;
                x0 += sx;
            }

            if(e2 < deltax){
                error += deltax;
                y0 += sy;
            }
        }
    }

    public Iterator<Point> iterator(){

        return points.iterator();
    }


}
