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
import java.util.stream.Collectors;
import javax.swing.ImageIcon;

public class Image {
    String localDir = System.getProperty("user.dir");
    private GameConstants gameConstants;
    private final Map<Integer,Item> imagesMap;
    public Image(){
        imagesMap = new HashMap<>();
        getImages();
    }
    public String getItemName(Integer id){
        return "images/" + imagesMap.get(id).name;
    }
    public ImageIcon createIcon(Integer id){
      if(imagesMap.containsKey(id)) {
          return new ImageIcon(
                  getClass()
                          .getClassLoader()
                          .getResource(getItemName(id)));

      } else {
          System.out.println("Icon could not be created");
          return null;
      }
    }
    private void getImages(){
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
    }
}
