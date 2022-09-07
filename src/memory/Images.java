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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

public class Images {

    private Map<Integer,Item> imageList;
    public Images() throws IOException {
        imageList = new HashMap<>();
        getImages(14);
    }
    public String getResourceName(Integer intCod){
        return imageList.get(intCod).url;
    }
    public ImageIcon IconFactory(Integer intCod){
      if(!imageList.containsKey(intCod)) {
          System.out.println("IconFactory problem");
          return null;
      }
      return new ImageIcon(
              getClass()
                      .getClassLoader()
                      .getResource(getResourceName(intCod)));
    }

    private List<Item> getImages(int numOfImages) throws IOException {
        List<Item> imageList = new ArrayList<>();
        List<String> images = getImageRoutes(new File("./images"));
        for(int i=0; i<=numOfImages; i++){
            imageList.add(new Item(i, images.get(i)));
        }
        return imageList;
    }
    public List<String> getImageRoutes(File directory) throws IOException {
        List<String> resultList = new ArrayList<>();
        File[] f = directory.listFiles();
        for (File file : f) {
            if (file != null && file.getName().startsWith("ic_") && file.getName().toLowerCase().endsWith(".png")) {
                resultList.add(file.getCanonicalPath());
            }
        }
        if (resultList.size() > 0)
            return resultList;
        else
            return null;
    }
    
}
