/*
 * The MIT License
 *
 * Copyright 2018 marco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package memory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;

public class Images {
<<<<<<< HEAD
<<<<<<< HEAD
    private class Item{
        Integer intCod;
        String strNomeRecurso;
        Item(Integer intCod, String strNomeRecurso){
            this.intCod = intCod;
            this.strNomeRecurso = strNomeRecurso;
        }
    }
    private final Map<Integer,Item> mapa;
    public Images(){
        mapa = new HashMap<>();
        preenche();
    }

    public String getResourceName(Integer intCod){
        return mapa.get(intCod).strNomeRecurso;
    }
    public ImageIcon IconFactory(Integer intCod){
      if(!mapa.containsKey(intCod)) {
=======
=======

    String localDir = System.getProperty("user.dir");
    private GameConstants gameConstants;
>>>>>>> b0f7769 (refactoring images class and including constants)
    private final Map<Integer,Item> imagesMap;
    public Images(){
        imagesMap = new HashMap<>();
        getImages();
    }
    public String getItemName(Integer id){
        return "images/" + imagesMap.get(id).name;
    }
<<<<<<< HEAD
    public ImageIcon IconFactory(Integer id){
      if(!imagesMap.containsKey(id)) {
>>>>>>> e5af5bd (using dependency injection and UIElement Factory to decouple game)
          System.out.println("IconFactory problem");
=======

    public ImageIcon createIcon(Integer id){
      if(imagesMap.containsKey(id)) {
          return new ImageIcon(
                  getClass()
                          .getClassLoader()
                          .getResource(getItemName(id)));

      } else {
          System.out.println("Icon could not be created");
>>>>>>> b0f7769 (refactoring images class and including constants)
          return null;
      }
    }
<<<<<<< HEAD
    private void preenche(){
=======
    private void getImages(){
<<<<<<< HEAD
>>>>>>> e5af5bd (using dependency injection and UIElement Factory to decouple game)
        Item item;
        int i = -1;

        // undiscovered image
        item = new Item(i++,"images/ic_help_outline_black_18dp.png");
<<<<<<< HEAD
        mapa.put(item.intCod, item);        

        // discovered image
        item = new Item(i++,"images/ic_done_black_18dp.png");
        mapa.put(item.intCod, item);        


        item = new Item(i++,"images/ic_airport_shuttle_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_all_inclusive_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_beach_access_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_business_center_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_casino_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_child_care_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_child_friendly_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_fitness_center_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_free_breakfast_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_hot_tub_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_kitchen_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_pool_black_18dp.png");
        mapa.put(item.intCod, item);        
=======
        imagesMap.put(item.id, item);

        // discovered image
        item = new Item(i++,"images/ic_done_black_18dp.png");
        imagesMap.put(item.id, item);


        item = new Item(i++,"images/ic_airport_shuttle_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_all_inclusive_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_beach_access_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_business_center_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_casino_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_child_care_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_child_friendly_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_fitness_center_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_free_breakfast_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_hot_tub_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_kitchen_black_18dp.png");
        imagesMap.put(item.id, item);

        item = new Item(i++,"images/ic_pool_black_18dp.png");
        imagesMap.put(item.id, item);
>>>>>>> e5af5bd (using dependency injection and UIElement Factory to decouple game)
=======
        File f = new File(localDir + "/src/images");
        String[] imageList = f.list();
        for(Integer i=0; i<imageList.length-1; i++){
            Item item = new Item(i, imageList[i]);
            imagesMap.put(i, item);
        }
    }
    public ImageIcon getIcon(IconType iconType){
        switch (iconType){
            case SUCCESS:
                File s = new File(localDir + gameConstants.URL_SUCCESS_ICON);
                String[] successIcon = s.list();
                return new ImageIcon(getClass()
                        .getClassLoader()
                        .getResource("images/done/" + successIcon[0]));
            case UNKNOWN:
                File u = new File(localDir + gameConstants.URL_UNKNOWN_ICON);
                String[] unknownIcon = u.list();
                return new ImageIcon(getClass()
                        .getClassLoader()
                        .getResource("images/unknown/" + unknownIcon[0]));
            default: return null;
        }
    }
    public Integer getImageIdByValue(String value){
        List<Integer> key = imagesMap.entrySet().stream()
                .filter(item -> item.getValue().name.contains(value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return key.get(0);
>>>>>>> b0f7769 (refactoring images class and including constants)
    }
}
