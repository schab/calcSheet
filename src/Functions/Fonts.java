package Functions;

import java.awt.*;

/**
 * Created by Kacper on 2015-04-07.
 */
public enum Fonts {


    Calibri(new Font("Calibri",Font.TYPE1_FONT,14)),
    Segoe(new Font("Segoe UI", Font.BOLD, 14));

    private Font _font;
    private int _size;

    Fonts(Font _font){
        this._font = _font;
        this._size = _font.getSize();

    }

    public int get_size(){ return _size;}
    public Font font(){ return _font;}


}
